package com.mainiway.cloudcut.dao;

import org.logicalcobwebs.proxool.ProxoolDataSource;

import com.mainiway.cloudcut.util.AES;

public class EncryptDataSource extends ProxoolDataSource {

	public EncryptDataSource(){
		super();
	}
	
	@Override
	public void setPassword(String password) {
					
		try {
			
			byte[] key = {55, 65, 48, 122, 53, 67, 64, 51, 36, 57, 38, 66, 56, 99, 70, 35};
			
			//密码格式： crypt{xxxxxxxx}
			if( password.startsWith("crypt{") && password.endsWith("}")){
				
				password = password.substring(6,password.length() - 1);
				password = AES.Decrypt(password, new String(key));
			}
			super.setPassword(password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
