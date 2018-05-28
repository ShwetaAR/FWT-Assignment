package com.yash.mbs.readmenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.yash.mbs.dao.MapMovieScreenDao;
import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.dao.SeatArrangementDao;
import com.yash.mbs.daoImpl.MapMovieScreenDaoImpl;
import com.yash.mbs.daoImpl.ScreenDaoImpl;
import com.yash.mbs.exception.FileDoNotExist;
import com.yash.mbs.exception.FileIsEmpty;
import com.yash.mbs.exception.RowNumberCannotBeGreaterThanTenException;
import com.yash.mbs.exception.RowNumberCannotBeNegativeException;
import com.yash.mbs.exception.ScreenAlreadyExistException;
import com.yash.mbs.exception.ScreenNameCannotBeEmptyException;
import com.yash.mbs.exception.SeatNumberCannotBeNegativeException;
import com.yash.mbs.model.Movie;
import com.yash.mbs.model.Screen;
import com.yash.mbs.model.SeatArrangement;
import com.yash.mbs.model.SeatCategory;
import com.yash.mbs.service.MapMovieScreenService;
import com.yash.mbs.service.ScreenService;
import com.yash.mbs.service.SeatArrangementSerivice;
import com.yash.mbs.serviceImpl.MapMovieScreenServiceImpl;
import com.yash.mbs.serviceImpl.ScreenServiceImpl;
import com.yash.mbs.serviceImpl.SeatArrangementServiceImpl;

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
	private Movie movie;
	private MapMovieScreenService mapMovieScreenService;
	private MapMovieScreenDao mapMovieScreenDao;
	private  SeatArrangementSerivice seatArrangementSerivice;
	private SeatArrangementDao seatArrangementDao;

	public MovieBookingSystem() {
		scanner = new Scanner(System.in);
		screen = new Screen();
		movie = new Movie();
		screenDao = new ScreenDaoImpl();
		screenService = new ScreenServiceImpl(screenDao);
		mapMovieScreenDao = new MapMovieScreenDaoImpl();
		mapMovieScreenService = new MapMovieScreenServiceImpl(mapMovieScreenDao);
		seatArrangementSerivice = new SeatArrangementServiceImpl(seatArrangementDao);

	}

	private static BufferedReader br;

	public void getMenu(File filePath)
			throws IOException, ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {
		checkIfFileIsNull(filePath);
		checkIfFileExist(filePath);
		checkIfFileIsEmpty(filePath);
		br = new BufferedReader(new FileReader(filePath));
		String st;
		while ((st = br.readLine()) != null)
			System.out.println(st);
		// getMovieBookingSystemMenu();

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

	public void getMovieBookingSystemMenu()
			throws ScreenNameCannotBeEmptyException, ScreenAlreadyExistException, IOException, RowNumberCannotBeNegativeException, SeatNumberCannotBeNegativeException, RowNumberCannotBeGreaterThanTenException {
		String choice = null;

		do {
			File file = new File("src/main/resources/menu/OperatorsMenu.txt");
			getMenu(file);

			int key = scanner.nextInt();
			switch (key) {
			case 1:
				addScreenUserInput();
				break;
			case 2:
				addingMovieToScreen();
				break;
			case 3:
				createAndShowSeatingArrangement();
				break;
			default:
				System.out.println("Invalid Choice");
			}
			System.out.println("Do you want continue?Y/N");
			choice = scanner.next();
		} while (choice.equalsIgnoreCase("y"));
	}

	private void addScreenUserInput()
			throws FileNotFoundException, ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {
		System.out.println("Enter screen  name :");
		scanner.nextLine();
		screen.setScreenName(scanner.nextLine());
		System.out.println("Enter screen  id :");
		screen.setId(scanner.nextInt());
		int rowAffected = screenService.addScreen(screen);
		if (rowAffected == 1) {
			logger.info("Screen Added");
		} else
			System.out.println("Screen Not Added");

	}

	private void addingMovieToScreen() throws FileNotFoundException {
		List<Screen> listScreen = screenService.getAllScreen();
		List<Movie> listMovie = screenService.getAllMovie();

		System.out.println("Enter screen to which movie has to be mapped ");
		scanner.nextLine();
		String screenName = scanner.nextLine();

		System.out.println("Enter movie which has to be  mapped to above screen ");
		String movieName = scanner.nextLine();

		int rowAffected = mapMovieScreenService.addMovieToScreen(movieName, screenName);
		if (rowAffected == 1) {
			System.out.println("Movie Screeen Mapped");
		} else {
			System.out.println("Movie not Mapped to Screen");
		}

	}

	private void createAndShowSeatingArrangement() throws FileNotFoundException, RowNumberCannotBeNegativeException, SeatNumberCannotBeNegativeException, RowNumberCannotBeGreaterThanTenException {
		List<SeatCategory> listOfCategory = new ArrayList<SeatCategory>();
		
		System.out.println("Enter screen name for which seating arrangement has to be done ");
		scanner.nextLine();
		String screenName = scanner.nextLine();
		System.out.println("============Premium=============");
		System.out.println("Enter number of rows for Premium category");
		int numberOfRowsforPremium = scanner.nextInt();
		System.out.println("Enter number of seats for first row ");
		int numberOfSeatforPfirstRow= scanner.nextInt();
		 
		listOfCategory.add(new SeatCategory(1, "primium",numberOfRowsforPremium , numberOfSeatforPfirstRow));
  
		System.out.println("============Silver=============");
		System.out.println("Enter number of rows for Silver category");
		int numberOfRowsforSilver = scanner.nextInt();
		System.out.println("Enter number of seats for first row ");
		int numberOfSeatforSfirstRow= scanner.nextInt();
		listOfCategory.add(new SeatCategory(2, "silver",numberOfRowsforSilver , numberOfSeatforSfirstRow));
		

		System.out.println("============Gold=============");
		System.out.println("Enter number of rows for Silver category");
		int numberOfRowsforSGold = scanner.nextInt();
		System.out.println("Enter number of seats for first row ");
		int numberOfSeatforGfirstRow= scanner.nextInt();
		listOfCategory.add(new SeatCategory(2, "gold",numberOfRowsforSGold , numberOfSeatforGfirstRow));

		
		seatArrangementSerivice.addSeatArrangementToScreen(screenName, listOfCategory);
		
		
		
		
		
		
		
	}

}
