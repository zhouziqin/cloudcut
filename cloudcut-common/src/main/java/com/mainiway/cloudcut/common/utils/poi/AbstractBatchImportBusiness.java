package com.mainiway.cloudcut.common.utils.poi;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBatchImportBusiness implements BatchImportBusiness {
	protected static int maxRowsNum = 1000;
	
	protected static int maxProcessNum = 100;

	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	/**返回所有的title名称
	 * @return
	 */
	protected abstract String[] getTitleNames();

	@Override
	public List<String> batchImport(InputStream inputStream, String adminId,String bigRoleId, String batchNo, Date fileDate) throws BusinessException, Exception {
		LOGGER.info("batchImport start");
		InputStream bufferedInputStream = null;
		Workbook workbook = null;
		try {
			//存放结果信息
			List<String> resultList = new ArrayList<String>();
			bufferedInputStream = new BufferedInputStream(inputStream);
			workbook = WorkbookFactory.create(bufferedInputStream);
			Sheet sheet = workbook.getSheetAt(0);
			POIReadFromExcelUtil excelUtil = new POIReadFromExcelUtil(sheet, 0, 1);
			// 验证公共项
			checkCommons(excelUtil);
			
			String[] titleNames = getTitleNames();
			
			//存放所有解析后的数据
			List<Map<String, String>> recordList = new ArrayList<Map<String, String>>();
			//存放每行解析后的数据
			Map<String,String> recordMap;
			
			//解析数据，并checkBase
			for(int i = excelUtil.getStartRows();i<=excelUtil.getRealRows();i++){
				if(resultList.size() >= 20){
					return resultList;
				}
				
				recordMap = new HashMap<String,String>();
				for(String titleName : titleNames){
					recordMap.put(titleName, excelUtil.getColContents(i, titleName));
				}
				
				String errorMsg = checkBase(recordMap);
				if(StringUtils.isNotEmpty(errorMsg)){
					resultList.add(String.format("第%d行校验出错:%s",i,errorMsg));
				}
				recordList.add(recordMap);
			}
			
			if(!resultList.isEmpty()){
				return resultList;
			}
			
			Map<String,Object> paramMap = new HashMap<String,Object>();
			preProcess(recordList,paramMap,bigRoleId,batchNo,fileDate);
			
			//需处理的次数
			int processCount = recordList.size() % maxProcessNum == 0 ? recordList.size() / maxProcessNum : recordList.size() / maxProcessNum + 1;
			
			//处理成功数量
			int processSuccessNumber = 0;
			for(int i = 0; i < processCount; i++){
				int fromIndex = i * maxProcessNum;
				int toIndex = fromIndex + maxProcessNum;
				try{
					if(toIndex > recordList.size()){
						toIndex = recordList.size();
					}			
					postProcess(Collections.unmodifiableList(recordList.subList(fromIndex, toIndex)),paramMap);
					processSuccessNumber = toIndex;
				}catch(BusinessException e){
					LOGGER.error("",e);
					throw new BusinessException(e.getErrorCode(),String.format("已成功导入%d行:第%d到%d行导入失败，错误信息:%s",processSuccessNumber,fromIndex+1,toIndex,e.getMessage()));
				}catch (Exception e) {
					LOGGER.error("",e);
					throw new BusinessException("","");
				}
			}


			resultList.add("导入成功");
			//添加总数，成功，失败
			resultList.add(recordList.size()+"");
			resultList.add(processSuccessNumber+"");
			resultList.add(recordList.size()-processSuccessNumber+"0");

			return resultList;
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					LOGGER.error("关闭workbook异常",e);
				}
			}

			if (bufferedInputStream != null) {
				try {
					bufferedInputStream.close();
				} catch (IOException e) {
					LOGGER.error("关闭bufferedInputStream异常",e);
				}
			}
			LOGGER.info("batchImport end");
		}
	}

	/**
	 * 验证公共项，对最大行数进行验证
	 * 
	 * @param excelUtil
	 * @return
	 */
	protected void checkCommons(POIReadFromExcelUtil excelUtil) throws BusinessException {
		if (excelUtil.getRealRows() > maxRowsNum) {
			throw new BusinessException(ErrorCodeEnumConstants.BatchImportCode.OVER_MAX_ROWS_LIMIT.getCode(),
					ErrorCodeEnumConstants.BatchImportCode.OVER_MAX_ROWS_LIMIT.getDesc() + ",最大行数" + maxRowsNum);
		}
		
		if(!excelUtil.checkTitleName(getTitleNames())){
			throw new BusinessException(ErrorCodeEnumConstants.BatchImportCode.MISS_TITLE_NAME);
		}
		
		if(excelUtil.getRealRows() < excelUtil.getStartRows()){
			throw new BusinessException(ErrorCodeEnumConstants.BatchImportCode.UPLOAD_DATA_IS_EMPTY);
		}
	}

	/**基础校验，正确返回空，错误则返回错误信息
	 * @param recordMap
	 * @return
	 */
	protected abstract String checkBase(Map<String, String> recordMap);
	
	/**
	 * 前置处理
	 * @param recordList
	 * @param paramMap
	 * @throws BusinessException
	 */
	protected abstract void preProcess(List<Map<String, String>> recordList,Map<String,Object> paramMap,String bigRoleId,String batchNo,Date fileDate) throws Exception;

	
	/**后置处理
	 * @param subRecordList
	 * @param adminId
	 * @param paramMap
	 */
	protected abstract void postProcess(List<Map<String,String>> subRecordList,Map<String,Object> paramMap) throws Exception;
}
