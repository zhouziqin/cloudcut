package com.mainiway.cloudcut.common.error.code;
/**
 * 错误返回码
 * @author zzq
 * 共5位前两位代表系统编码，后三位代表业务错误编码，
 * 例如 10001， 
 * 10：个人模块代码，001：个人模块的参数错误代码
 *
 */
public enum CommErr {	
	COMM_SUCCESS("0", "请求成功"),
	COMM_ERROR("1", "请求失败"),
	COMM_ERROR_PARAM("2", "请求参数错误");
 
	private String code;
	private String value;
	CommErr(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
