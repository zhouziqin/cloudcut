package com.mainiway.cloudcut.common.utils;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.web.client.RestTemplate;

/**
 * ***************************************************************************
 *模块名 : RestTemplateUtil
 *文件名 : RestTemplateUtil.java
 *创建时间 : 2016年7月11日
 *实现功能 :
 *作者 : liliangjun
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年7月11日 v0.0.1 liliangjun 创建
 ****************************************************************************
 */
public class RestTemplateUtil {


	public static void post(String url,Map<String,Object> urlVariables){
		RestTemplate restTemplate = new RestTemplate();
		JSONObject jsonObj = JSONObject.fromObject(urlVariables);
		String result = restTemplate.getForObject(url+"?param="+jsonObj.toString(), String.class);
		System.out.println("result:"+result);
	}



//	public static void main(String[] args) {
//		//localhost:8806/mainiwaytrade/startPay?
//		//param={"outTradeNo":"2016070900233","totalFee":"10","subject":"test商品","channel":"MW_TEST","notifyUrl":"http://localhost:8806/mainiwaytrade/testReceiveNotify"}
//		Map<String,Object> urlVariables = new HashMap<String, Object>();
//		urlVariables.put("outTradeNo", "2016070900233");
//		urlVariables.put("totalFee", "1");
//		urlVariables.put("subject", "test商品");
//		urlVariables.put("channel", "MW_TEST");
//		urlVariables.put("notifyUrl", "http://127.0.0.1:8804/idinbaopersonal/orderManage/paymentSuccess");
//		RestTemplateUtil.post("http://127.0.0.1:8806/mainiwaytrade/startPay", urlVariables);
//
//		String url = "http://localhost:8804/idinbaopersonal/orderManage/startPay";
//		System.out.println(url.substring(0,url.lastIndexOf("/")));
//
//	}
}
