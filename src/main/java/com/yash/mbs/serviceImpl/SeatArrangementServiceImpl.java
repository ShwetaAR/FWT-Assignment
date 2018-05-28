package com.yash.mbs.serviceImpl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.dao.SeatArrangementDao;
import com.yash.mbs.daoImpl.ScreenDaoImpl;
import com.yash.mbs.daoImpl.SeatArrangementDaoImpl;
import com.yash.mbs.enumeration.SeatAvailability;
import com.yash.mbs.exception.RowNumberCannotBeGreaterThanTenException;
import com.yash.mbs.exception.RowNumberCannotBeNegativeException;
import com.yash.mbs.exception.ScreenAlreadyExistException;
import com.yash.mbs.exception.SeatNumberCannotBeNegativeException;
import com.yash.mbs.model.Screen;
import com.yash.mbs.model.Seat;
import com.yash.mbs.model.SeatArrangement;
import com.yash.mbs.model.SeatCategory;
import com.yash.mbs.service.SeatArrangementSerivice;

public class SeatArrangementServiceImpl implements SeatArrangementSerivice {

	private SeatArrangementDao seatArrangementDao;
	private ScreenDao screenDao;
	private Screen screen;
	private Seat seat;
	private List<SeatArrangement> seatArrangementCategoryWise;

	public SeatArrangementServiceImpl(SeatArrangementDao seatArrangementDao) {
		this.seatArrangementDao = new SeatArrangementDaoImpl();
		seatArrangementCategoryWise = new ArrayList<SeatArrangement>();
		seat = new Seat();
		screen = new Screen();
	}

	public int addSeatArrangementToScreen(String screenName, List<SeatCategory> listOfSeatCategories)
			throws FileNotFoundException, RowNumberCannotBeNegativeException, SeatNumberCannotBeNegativeException, RowNumberCannotBeGreaterThanTenException {
		screenDao = new ScreenDaoImpl();
		List<Screen> screens = screenDao.loadAllScreen();
		for (Screen screenObj : screens) {
			if (screenName.equalsIgnoreCase(screenObj.getScreenName())) {
				screen = screenObj;

			}
		}
		for (SeatCategory category : listOfSeatCategories) {
            
			int numberOfRows = category.getNumberOfRows();
			if(numberOfRows<0){
				throw new RowNumberCannotBeNegativeException("Row Number cannot be Negative");
			}
			if(numberOfRows>10){
				throw new RowNumberCannotBeGreaterThanTenException("Row Number cannot Greater than 10");
			}
			int numberOfSeatInFirstRow = category.getNumberOfSeatInFirstRow();
			
			if(numberOfSeatInFirstRow<0){
				throw new SeatNumberCannotBeNegativeException("Seat Number cannot be Negative");
			}
			int[][] seatsNumber = new int[numberOfRows][numberOfSeatInFirstRow];
			String categoryName = category.getName();
			for (int i = 0; i < numberOfRows; i++) {

				categoryName = categoryName + (i + 1);
				int count = 1;
				for (int l = 0; l < numberOfSeatInFirstRow; l++) {
					int value = ((i + 1) * 10) + count;
					seatsNumber[i][l] = value;
					count++;
				}
				numberOfSeatInFirstRow = numberOfSeatInFirstRow - 2;

			}
			seatArrangementCategoryWise.add(new SeatArrangement(screen,
					new Seat(1, seatsNumber, SeatAvailability.AVAILBLE.getValue()), category));

		}
		displaySeatArrangement(seatArrangementCategoryWise);
		return seatArrangementDao.insertSeatArrangementCategoryWise(seatArrangementCategoryWise);
	}

	public void displaySeatArrangement(List<SeatArrangement> seatArrangementCategoryWise3) {
		List<SeatArrangement> seatArrangementCategoryWise2 =seatArrangementDao.loadAllScreenArrangementCategoryWise();
		for (SeatArrangement seatArrangement : seatArrangementCategoryWise2) {

			SeatCategory seatCategory = seatArrangement.getSeatCategory();
			int numberOfRows = seatCategory.getNumberOfRows();
			int numberOfSeatInFirstRow = seatCategory.getNumberOfSeatInFirstRow();
			String categoryName = seatCategory.getName();

			screen = seatArrangement.getScreen();
			seat = seatArrangement.getSeats();
			int[][] seatNumber = seat.getSeatNumber();
			char rowIdentification = 'A';
			for (int i = 0; i < numberOfRows; i++) {
				int count = 1;
				System.out.print(categoryName);
				System.out.print(rowIdentification);
				rowIdentification++;
				for (int k = 0; k < i + 1; k++) {
					System.out.print("\t");
				}
				for (int l = 0; l < numberOfSeatInFirstRow; l++) {
					System.out.print("*" + seatNumber[i][l] + "[" + seat.getSeatAvailability() + "] *" + "\t");
					count++;
				}
				System.out.println("\n");
				numberOfSeatInFirstRow = numberOfSeatInFirstRow - 2;

			}

		}

	}
}
