package com.mainiway.cloudcut.common.exception;

@SuppressWarnings("serial")
public class DBOperationException extends RuntimeException {

	public DBOperationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DBOperationException(
			String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DBOperationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DBOperationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DBOperationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}


}
