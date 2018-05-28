package com.yash.mbs.exception;

public class CategoryNameCannotBeNullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryNameCannotBeNullException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
