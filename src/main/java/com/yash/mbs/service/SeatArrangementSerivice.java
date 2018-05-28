package com.yash.mbs.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.mbs.exception.RowNumberCannotBeGreaterThanTenException;
import com.yash.mbs.exception.RowNumberCannotBeNegativeException;
import com.yash.mbs.exception.SeatNumberCannotBeNegativeException;
import com.yash.mbs.model.SeatArrangement;
import com.yash.mbs.model.SeatCategory;

public interface SeatArrangementSerivice {


	int addSeatArrangementToScreen(String screenName, List<SeatCategory> listOfSeatCategories) throws FileNotFoundException, RowNumberCannotBeNegativeException, SeatNumberCannotBeNegativeException, RowNumberCannotBeGreaterThanTenException;

	void displaySeatArrangement(List<SeatArrangement> seatArrangementCategoryWise3);

}
