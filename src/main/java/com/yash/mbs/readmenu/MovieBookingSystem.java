package com.yash.mbs.readmenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.yash.mbs.exception.FileDoNotExist;
import com.yash.mbs.exception.FileIsEmpty;

public class MovieBookingSystem {
	/**
	 * logger is used for logging and to write messages to the configured log
	 * files
	 */
	private static Logger logger = Logger.getLogger(MovieBookingSystem.class);
	private static BufferedReader br;

	public void getMenu(File filePath) throws IOException {
		checkIfFileIsNull(filePath);
		checkIfFileExist(filePath);
		checkIfFileIsEmpty(filePath);
		br = new BufferedReader(new FileReader(filePath));
		String st;
		while ((st = br.readLine()) != null)
			System.out.println(st);

	}

	private void checkIfFileIsEmpty(File file) {
		if (file.exists() && file.length() == 0) {
			throw new FileIsEmpty("Given file is empty ");
		}
	}

	private void checkIfFileExist(File file) {
		if (!file.exists()) {
			throw new FileDoNotExist("Given file donot exist in the given directory ");
		}
	}

	private void checkIfFileIsNull(File filePath) {
		if (filePath == null) {
			throw new NullPointerException("File cannot be null, assign some value to it");
		}
	}

}
