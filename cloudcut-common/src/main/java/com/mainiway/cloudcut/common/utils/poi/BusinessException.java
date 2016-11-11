package com.mainiway.cloudcut.common.utils.poi;


public class BusinessException extends BaseException {

	private static final long serialVersionUID = -7886374686595984204L;

	public BusinessException(String errorCode, String errorMsg, Throwable caused) {
		super(errorCode, errorMsg, caused);
	}

	public BusinessException(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}

	public BusinessException(String errorCode, Throwable caused) {
		super(errorCode, caused);
	}

	public BusinessException(ErrorCodeEnumConstants errorCodeEnumConstants) {
		super(errorCodeEnumConstants.getCode(), errorCodeEnumConstants.getDesc());
	}

	public BusinessException(ErrorCodeEnumConstants errorCodeEnumConstants, Throwable caused) {
		super(errorCodeEnumConstants.getCode(), errorCodeEnumConstants.getDesc(), caused);
	}
}
