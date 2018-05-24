package com.yash.mbs.exception;

public class FileDoNotExist extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileDoNotExist(String exceptionMessage) {
		super(exceptionMessage);
	}

}
