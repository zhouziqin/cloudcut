package com.mainiway.cloudcut.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;


public class HttpClientUtil {


		 /**
		 * 执行一个HTTP POST请求，返回请求响应的HTML
		 *
		 * @param url       请求的URL地址
		 * @param params    请求的查询参数,可以为null
		 * @param charset 字符集
		 * @param pretty    是否美化
		 * @return 返回请求响应的HTML
		 */
		public static String doPost(String url, Map<String, Object> params, String charset, boolean pretty) throws Exception {
	        StringBuffer response = new StringBuffer();
	        HttpClient client = new HttpClient();
	        HttpMethod method = new PostMethod(url);
	        //设置Http Post数据
	        if (params != null) {
                HttpMethodParams p = new HttpMethodParams();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                     p.setParameter(entry.getKey(), entry.getValue());
                }
                method.setParams(p);
	        }
	        try {
		        client.executeMethod(method);
		        if (method.getStatusCode() == HttpStatus.SC_OK) {
	                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
	                String line;
	                while ((line = reader.readLine()) != null) {
                        if (pretty){
                        	response.append(line).append(System.getProperty("line.separator"));
                        }else{
                        	 response.append(line);
                        }
	                }
	                reader.close();
		        }
	        } catch (IOException e) {
	        	throw new Exception("执行HTTP Post请求" + url + "时，发生异常！", e);
	        } finally {
	            method.releaseConnection();
	        }
	        return response.toString();
	}

		public static String doGet(String url, Map query) throws Exception { 
            String response = null; 
            HttpClient client = new HttpClient(); 
            HttpMethod method = new GetMethod(url); 
            String queryString = getUrlParamsByMap(query);
            try { 
                    if (StringUtils.isNotBlank(queryString)) 
                            method.setQueryString(URIUtil.encodeQuery(queryString)); 
                    client.executeMethod(method); 
                    if (method.getStatusCode() == HttpStatus.SC_OK) { 
                            response = method.getResponseBodyAsString(); 
                    } 
            } catch (URIException e) { 
            	throw new Exception("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e); 
            } catch (IOException e) { 
            	throw new Exception("执行HTTP Get请求" + url + "时，发生异常！", e); 
            } finally { 
                    method.releaseConnection(); 
            } 
            return response; 
    } 
		  public static String getUrlParamsByMap(Map<String, Object> map) {  
		        if (map == null) {  
		            return "";  
		        }  
		        StringBuffer sb = new StringBuffer();  
		        for (Map.Entry<String, Object> entry : map.entrySet()) {  
		            sb.append(entry.getKey() + "=" + entry.getValue());  
		            sb.append("&");  
		        }  
		        String s = sb.toString();  
		        if (s.endsWith("&")) {  
		            s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");  
		        }  
		        return s;  
		    }
//		public static void main(String[] args) {
//			Map<String,Object> paramMap = new HashMap<String, Object>();
//			Map<String,String> urlVariables = new HashMap<String, String>();
//			urlVariables.put("outTradeNo", "2016070900233");
//			urlVariables.put("totalFee", "1");
//			urlVariables.put("subject", "test商品");
//			urlVariables.put("channel", "MW_TEST");
//			urlVariables.put("notifyUrl", "http://127.0.0.1:8804/idinbaopersonal/orderManage/paymentSuccess");
//			paramMap.put("param", urlVariables);
//			try {
//				String returnString = HttpClientUtil.doPost("http://127.0.0.1:8806/mainiwaytrade/startPay", paramMap, "utf-8", false);
//				System.err.println("returnString======"+returnString);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

}
