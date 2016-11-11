package com.mainiway.cloudcut.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * ***************************************************************************
 *模块名 : MessageSend
 *文件名 : MessageSend.java
 *创建时间 : 2016年7月14日
 *实现功能 : 验证码发送
 *作者 : tang
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年7月14日 v0.0.1 tang 创建
 ****************************************************************************
 */
public class MessageSend {
	
	public static HashMap<String, String> msgTemplateMap = new HashMap<String, String>();//短信模板key-value
	public static HashMap<String, String> mailTemplateMap = new HashMap<String, String>();//邮件模板key-value
//	public static final String MSG_TEMPLATE1;//短信模板1，手机绑定
//	public static final String MSG_TEMPLATE2;//短信模板2，手机注册
	public static final String MAIL;
	public static final String MAIL_ACC;
	public static final String MAIL_PWD;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSend.class);
	
	private static final String URL;//短信平台
	private static final String CUST_CODE;//账号
	private static final String CUST_PW;//密码
	private static final String SP_CODE;//长号码
	
	private static final int CONN_TIMEOUT;//连接超时时间 ms
	private static final int READ_TIMEOUT;//读超时时间 ms
	private static final int READ_BUFFER;//读字节数（缓存）byte
	
	static {
		Properties properties = new Properties();
		String tmp = null;
		try {
			properties.load(MessageSend.class.getResourceAsStream("/properties/message.properties"));
			for (String string : properties.stringPropertyNames()) {
				if (string.startsWith("message.content.template") && string.length() > 24) {//取出短信模板key
					tmp = new String(properties.getProperty(string).getBytes("ISO8859-1"), "utf-8");
					msgTemplateMap.put(string.substring(24), tmp);
				}else if(string.startsWith("mail.content.template") && string.length() > 21){
					tmp = new String(properties.getProperty(string).getBytes("ISO8859-1"), "utf-8");
					mailTemplateMap.put(string.substring(21), tmp);
				}
			}
		} catch (IOException e) {
		} finally {
			for (String key : msgTemplateMap.keySet()) {
				System.out.println("key=" + key + ", value=" + msgTemplateMap.get(key));
				LOGGER.info("key={}, value={}", key, msgTemplateMap.get(key));
			}
			
		}
		URL = properties.getProperty("message.url");
		CUST_CODE = properties.getProperty("message.cust.code");
		CUST_PW = properties.getProperty("message.cust.pw");
		SP_CODE = properties.getProperty("message.sp.code");
		
		MAIL = properties.getProperty("server.mail");
		MAIL_ACC = properties.getProperty("server.mail.account");
		MAIL_PWD = properties.getProperty("server.mail.pwd");
		
		CONN_TIMEOUT = Integer.parseInt(properties.getProperty("message.connection.timeout"));
		READ_TIMEOUT = Integer.parseInt(properties.getProperty("message.read.timeout"));
		READ_BUFFER = Integer.parseInt(properties.getProperty("message.read.buffer"));
		
		System.out.println(URL);
		System.out.println(CUST_CODE);
		System.out.println(CUST_PW);
		System.out.println(SP_CODE);
		System.out.println(CONN_TIMEOUT);
		System.out.println(READ_TIMEOUT);
		System.out.println(READ_BUFFER);
	}
	
	private HttpURLConnection httpURLConnection;
	private URL url;
	
	public MessageSend() throws MalformedURLException {
		this.url = new URL(URL);
	}
	
	private void open() throws IOException {
		httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setConnectTimeout(CONN_TIMEOUT);
		httpURLConnection.setReadTimeout(READ_TIMEOUT);
		
	}
	
	private void setParams() throws IOException {
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setUseCaches(false);
		httpURLConnection.setRequestMethod("POST");
	}
	
	private void close() {
		if (httpURLConnection != null) {
			httpURLConnection.disconnect();
			httpURLConnection = null;
		}
	}

	/**
	 * ====================================================================
	 *函 数 名： @param phoneNo 手机号码
	 *函 数 名： @param msgCode 短信验证码
	 *函 数 名： @param msgTemplate 短信模板
	 *函 数 名： @param timeout 有效时间（分钟）
	 *函 数 名： @return 短信发送成功与否
	 *功 能： 
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年7月14日 v0.0.1 tang 创建
	====================================================================
	 */
	public boolean post(String phoneNo, String msgCode, String msgTemplate, int timeout) {
		OutputStream outputStream = null;
		InputStream inputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		String content = null;
		String sign = null;
		String ret = null;
		try {
			open();
			setParams();
			content = URLEncoder.encode(msgTemplate, "utf-8");
			sign = MD5Util.MD5(content + CUST_PW);
			content = "content=" + content + "&destMobiles=" + phoneNo + URLEncoder.encode("|", "utf-8") + msgCode + URLEncoder.encode("|", "utf-8") + timeout + "&sign=" + sign + "&cust_code=" + CUST_CODE + "&sp_code=" + SP_CODE;
			
			System.out.println(content);
			
			outputStream = httpURLConnection.getOutputStream();
			outputStream.write(content.getBytes("utf-8"));
			outputStream.flush();
			
			inputStream =httpURLConnection.getInputStream();
			
			byteArrayOutputStream = new ByteArrayOutputStream();
			
			int len;
			byte[] b = new byte[READ_BUFFER];
			while ((len = inputStream.read(b)) != -1) {
				byteArrayOutputStream.write(b, 0, len);
			}
			byteArrayOutputStream.flush();
			
			ret = URLDecoder.decode(byteArrayOutputStream.toString("utf-8"), "utf-8");
			LOGGER.info("MessageSend==>post ret={}", ret);
			
			System.out.println("MessageSend==>post ret=" + ret);
			
			if (ret.startsWith("SUCCESS")) {
				String[] parts = ret.replaceAll("\r\n", "").replaceAll("\n", "").split(":");
				return parts.length == 4 && "0".equals(parts[3]);
			}
			return false;
		} catch (Exception e) {
			LOGGER.error("Post error!", e);
			return false;
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e) {
					LOGGER.error("Post outputStream error!", e);
					outputStream = null;
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					LOGGER.error("Post inputStream error", e);
					inputStream = null;
				}
			}
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (Exception e) {
					LOGGER.error("Post byteArrayOutputStream error", e);
					byteArrayOutputStream = null;
				}
			}
			try {
				close();
			} catch (Exception e) {
				LOGGER.error("Post close httpconnection error!", e);
			}
		}
		
	}
	
//	public static void main(String[] args) throws IOException {
//		System.out.println("SUCCESS:提交成功！\n18817591601:59106107151039179729:0\n");
//		
//		String ret = "SUCCESS:提交成功！\r\n18817591601:59106107151039179729:0\n";
//		if (ret.startsWith("SUCCESS")) {
//			String[] parts = ret.replaceAll("\r\n", "").replaceAll("\n", "").split(":");
//			for (String string : parts) {
//				System.out.println(string);
//			}
//			System.out.println(parts.length == 4 && "0".equals(parts[3]));
//		}
//
//		
////		MessageSend messageSend = new MessageSend();
////		messageSend.post("18817591601", "abc456", MSG_TEMPLATE1);
		
//	}
		
}
