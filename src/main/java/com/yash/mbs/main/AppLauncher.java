package com.yash.mbs.main;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.yash.mbs.exception.RowNumberCannotBeGreaterThanTenException;
import com.yash.mbs.exception.RowNumberCannotBeNegativeException;
import com.yash.mbs.exception.ScreenAlreadyExistException;
import com.yash.mbs.exception.ScreenNameCannotBeEmptyException;
import com.yash.mbs.exception.SeatNumberCannotBeNegativeException;
import com.yash.mbs.readmenu.MovieBookingSystem;


/**
 * This class contain main method for start up
 * 
 * @author Shweta.baberia
 *
 */
public class AppLauncher {
	
	
	/**
	 * Application will start from here
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws ScreenAlreadyExistException 
	 * @throws ScreenNameCannotBeEmptyException 
	 * @throws RowNumberCannotBeGreaterThanTenException 
	 * @throws SeatNumberCannotBeNegativeException 
	 * @throws RowNumberCannotBeNegativeException 
	 */
	public static void main(String[] args) throws IOException, ScreenNameCannotBeEmptyException, ScreenAlreadyExistException, RowNumberCannotBeNegativeException, SeatNumberCannotBeNegativeException, RowNumberCannotBeGreaterThanTenException {
		MovieBookingSystem movieBookingSystem = new MovieBookingSystem();
		movieBookingSystem.getMovieBookingSystemMenu();
		
	}

}
