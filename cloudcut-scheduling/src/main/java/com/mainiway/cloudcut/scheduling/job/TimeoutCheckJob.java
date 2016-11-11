package com.mainiway.cloudcut.scheduling.job;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.springframework.context.ApplicationContext;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;
import com.mainiway.cloudcut.scheduling.Application;
import com.mainiway.cloudcut.scheduling.bean.config.CronConfig;
import com.mainiway.cloudcut.scheduling.service.ObjectTimeoutService;

/**
 * ***************************************************************************
 *模块名 : TimeoutCheckJob
 *文件名 : TimeoutCheckJob.java
 *创建时间 : Aug 2, 2016
 *实现功能 : 对象超时检测任务对象，属于OneOff类型的任务，按调度时间重复执行
 *作者 : justin
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *Aug 2, 2016 v0.0.1 justin 创建
 ****************************************************************************
 */
public class TimeoutCheckJob extends AbstractSimpleElasticJob {

	private Log logger = LogFactory.getLog(TimeoutCheckJob.class);

	/**
	 * ====================================================================
	 *函 数 名： process
	 *功 能： 每次启动时先获取所有需要进行超时检查的对象，这些对象必须按时间顺序排列，这样确保需要先处理的对象
	 *             被添加到队列前面
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *Jun 19, 2016 v0.0.1 justin 创建
	 *Aug 02,2016 v0.0.1 justin 重构代码
	====================================================================
	 */
	@Override
	public void process(JobExecutionMultipleShardingContext jobContext) {

		ApplicationContext appCtx = Application.getContext();
		if( appCtx == null){
			logger.warn("application is not ready.");
			return;
		}

		//获得Job的cron设置及业务服务Bean
		String jobParam = jobContext.getJobParameter();

		String cronPropName = jobContext.getJobName() + "_cron";

		String cron = CronConfig.cronMap.get(cronPropName);

		ObjectTimeoutService<Object> timeoutService = (ObjectTimeoutService<Object>) appCtx.getBean(jobParam);

		if( timeoutService == null){
			logger.error(jobParam + ", Bean is not definition. ");
			return;
		}

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("shardingItems",jobContext.getShardingItems());
		param.put("shardingTotal", jobContext.getShardingTotalCount());
		timeoutService.setJobShardingParameter(param);

		Long nextTime;
		Long cronTime;

		try {
			CronExpression cronExpression = new CronExpression(cron);
			Date currentDate = new Date();
			//获得下一次执行日期时间点
			nextTime = cronExpression.getNextValidTimeAfter(currentDate).getTime();

			//计算时间差
			cronTime= nextTime-currentDate.getTime();
			logger.info("---------------------new job start---------------------");
			logger.info("---------------------cronTime: "+cronTime);

		} catch (ParseException e) {
			logger.error(e.getMessage());
			return;
		}

		logger.info(jobContext.getJobName() + "---------------------Job process---------------------");

		Queue<Object> queue = new LinkedBlockingQueue<Object>();
		List<Object> list = timeoutService.loadTimeoutObjects(cronTime);

		for (Object o : list) {
			// 添加到队列
			queue.offer(o);
		}

		logger.info("---------------------queue size: "+queue.size());

		// 返回队列头部的元素
		Object object = queue.poll();

		while (object != null) {

			Long time = timeoutService.getObjectRestTime(object);

			if(time != null){
				//当剩余时间小于0时，进行状态改变
				if (time <= 0) {

					try {
						timeoutService.updateTimeoutStatus(object);
					} catch (Exception e) {
						e.printStackTrace();
					}

					//移除并返问队列头部的元素
					object = queue.poll();

				//剩余时间大于0
				} else {
					logger.info("---------------------now into sleep, the sleep time: "+time);
					Date date  = new Date(System.currentTimeMillis()+time);
					logger.info("---------------------wake time: "+date);
					try {
						// 等待小于调度时间的对象，直到它超时
						Thread.sleep(time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}else{
				//移除并返问队列头部的元素
				object = queue.poll();
			}

		}
		logger.info("---------------------this job end , next start time: "+new Date(nextTime));
	}

}
