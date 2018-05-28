package com.yash.mbs.readmenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.daoImpl.ScreenDaoImpl;
import com.yash.mbs.exception.FileDoNotExist;
import com.yash.mbs.exception.FileIsEmpty;
import com.yash.mbs.exception.ScreenAlreadyExistException;
import com.yash.mbs.exception.ScreenNameCannotBeEmptyException;
import com.yash.mbs.model.Screen;
import com.yash.mbs.service.ScreenService;
import com.yash.mbs.serviceImpl.ScreenServiceImpl;

public class MovieBookingSystem {
	/**
	 * logger is used for logging and to write messages to the configured log
	 * files
	 */
	private static Logger logger = Logger.getLogger(MovieBookingSystem.class);
	private Scanner scanner;
	private ScreenService screenService;
	private ScreenDao screenDao;
	private Screen screen;

	public MovieBookingSystem() {
		scanner = new Scanner(System.in);
		screen = new Screen();
		screenDao = new ScreenDaoImpl();
		screenService = new ScreenServiceImpl(screenDao);
	}

	private static BufferedReader br;

	public void getMenu(File filePath) throws IOException, ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {
		checkIfFileIsNull(filePath);
		checkIfFileExist(filePath);
		checkIfFileIsEmpty(filePath);
		br = new BufferedReader(new FileReader(filePath));
		String st;
		while ((st = br.readLine()) != null)
			System.out.println(st);
		getMovieBookingSystemMenu();

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

	public void getMovieBookingSystemMenu() throws FileNotFoundException, ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {
		String choice;
		do {
			int key = scanner.nextInt();
			switch (key) {
			case 1:
				addScreenUserInput();
				break;
			case 2:

				break;
			case 3:

				break;
			default:
				logger.error("Invalid Choice");
			}
			logger.info("Do you want continue?Y/N");
			choice = scanner.next();
		} while (choice.equalsIgnoreCase("y"));
	}

	private void addScreenUserInput() throws FileNotFoundException, ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {

		String choice;
		int count=1;
		do {
			System.out.println("Enter screen  name :");
			scanner.nextLine();
			screen.setScreenName(scanner.nextLine());
			System.out.println("Enter screen  id :");
			screen.setId(scanner.nextInt());
			screenService.addScreen(screen);
			logger.info("Do you want continue?Y/N");
			choice = scanner.next();
		
		} while (choice.equalsIgnoreCase("y"));

	}

}
