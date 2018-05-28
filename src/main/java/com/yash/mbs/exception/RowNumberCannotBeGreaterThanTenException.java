package com.yash.mbs.exception;

public class RowNumberCannotBeGreaterThanTenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RowNumberCannotBeGreaterThanTenException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
