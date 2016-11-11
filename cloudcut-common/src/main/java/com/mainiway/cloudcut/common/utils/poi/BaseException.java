package com.mainiway.cloudcut.common.utils.poi;

import org.apache.commons.lang.StringUtils;

public class BaseException extends Exception {

	private static final long serialVersionUID = 2250103184949102549L;

	/**
	 * 异常错误代码
	 */
	protected String errorCode;

	/** 根异常，保持异常链 */
	protected Throwable caused;

	public BaseException(String errorCode, String errorMsg) {
		super(errorMsg);

		this.errorCode = errorCode;
	}

	public BaseException(String errorCode, Throwable caused) {
		super(caused);

		this.errorCode = errorCode;
		this.caused = caused;
	}

	public BaseException(String errorCode, String errorMsg, Throwable caused) {
		super(errorMsg, caused);

		this.errorCode = errorCode;
		this.caused = caused;
	}

	/**
	 * 获得异常的错误代码
	 * 
	 * @return the errorCode
	 */
	public String getErrorCode() {
		/** 如果异常定义了错误代码 */
		if (errorCode != null && !"".equals(errorCode.trim())) {
			return errorCode;
		}

		/**
		 * 如果没有定义错误代码,并且该异常是一个间接异常 则返回根异常的错误代码
		 */
		if (caused != null) {
			if (caused instanceof BaseException) {
				BaseException causedException = (BaseException) caused;
				return causedException.getErrorCode();
			} else {
				return errorCode;
			}
		}
		return errorCode;
	}

	@Override
	public String getMessage() {
		/** 如果异常定义了错误信息 */
		if (StringUtils.isNotEmpty(super.getMessage())) {
			return super.getMessage();
		}

		/**
		 * 如果没有定义错误信息,并且该异常是一个间接异常 则返回根异常的错误信息
		 */
		if (caused != null) {
			if (caused instanceof BaseException) {
				BaseException causedException = (BaseException) caused;
				return causedException.getMessage();
			}
		}

		return null;
	}
}
