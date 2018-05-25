package com.yash.mbs.exception;

public class InvalidMovieException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidMovieException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
