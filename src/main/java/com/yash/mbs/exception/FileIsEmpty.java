package com.yash.mbs.exception;

public class FileIsEmpty extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileIsEmpty(String exceptionMessage) {
		super(exceptionMessage);
	}

}
