package com.mainiway.cloudcut.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/*******************************************************************************  
 * AES加解密算法  
 *   
 * 使用AES-128-CBC加密模式，key需要为16  
 *  
 */

public class AES {

	final private static String IV = "0102030405060709";

	// 加密
	public static String Encrypt(String src, String key) throws Exception {
		if (key == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (key.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/不补码"
		int blockSize = cipher.getBlockSize();

		byte[] keyBuffer = key.getBytes();
		byte[] dataBuffer = src.getBytes();
		
		//因为是块式分组加密，数据长度必须是块长的整倍数，不足部分补0
		int newLength = dataBuffer.length;
		if (newLength % blockSize != 0) {
			newLength = newLength + (blockSize - (newLength % blockSize));
		}

		byte[] dataWithPadding = new byte[newLength];
		System.arraycopy(dataBuffer, 0, dataWithPadding, 0, dataBuffer.length);

		SecretKeySpec skeySpec = new SecretKeySpec(keyBuffer, "AES");

		IvParameterSpec iv = new IvParameterSpec(IV.getBytes());// 使用CBC模式，需要一个输入向量，增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(dataWithPadding);

		return Base64.encodeBase64String(encrypted);// 此处使用Base64编码
	}

	// 解密
	public static String Decrypt(String sSrc, String sKey) throws Exception {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] rawKey = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encryptedData = Base64.decodeBase64(sSrc);// 先用Base64解码

			try {
				byte[] trimData = null;
				byte[] original = cipher.doFinal(encryptedData);

				// 扫描original数组，发现等于0的字节即终止，截断0字节以后的内容（这部分是填充的）
				for (int n = 0; n < original.length; n++) {
					if (original[n] == 0) {
						trimData = new byte[n];
						System.arraycopy(original, 0, trimData, 0, trimData.length);
						break;
					}
				}

				if (trimData == null)
					trimData = original;

				return new String(trimData);

			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
}
