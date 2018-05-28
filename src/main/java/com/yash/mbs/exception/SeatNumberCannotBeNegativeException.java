package com.yash.mbs.exception;

public class SeatNumberCannotBeNegativeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SeatNumberCannotBeNegativeException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
