package com.yash.mbs.serviceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.dao.SeatArrangementDao;
import com.yash.mbs.exception.CategoryNameCannotBeNullException;
import com.yash.mbs.exception.RowNumberCannotBeGreaterThanTenException;
import com.yash.mbs.exception.RowNumberCannotBeNegativeException;
import com.yash.mbs.exception.SeatNumberCannotBeNegativeException;
import com.yash.mbs.model.SeatArrangement;
import com.yash.mbs.model.SeatCategory;
import com.yash.mbs.service.SeatArrangementSerivice;

public class SeatArrangementImplTest {

	@Test
	public void addSeatArrangementToScreen_WhenScreenNameAndCategoryGiven_ShouldCreateSeatArrangement()
			throws FileNotFoundException, RowNumberCannotBeNegativeException, SeatNumberCannotBeNegativeException,
			RowNumberCannotBeGreaterThanTenException {
		SeatArrangementDao seatArrangementDao = mock(SeatArrangementDao.class);
		SeatArrangementSerivice seatArrangementService = new SeatArrangementServiceImpl(seatArrangementDao);
		String screenName = "screen1";
		List<SeatCategory> listOfSeatCategories = new ArrayList<SeatCategory>();
		listOfSeatCategories.add(new SeatCategory(1, "primium", 5, 20));
		listOfSeatCategories.add(new SeatCategory(2, "silver", 6, 12));
		when(seatArrangementDao.insertSeatArrangementCategoryWise(listOfSeatCategories)).thenReturn(1);
		int seatArrangement = seatArrangementService.addSeatArrangementToScreen(screenName, listOfSeatCategories);
		assertEquals(1, seatArrangement);

	}

	@Test(expected = RowNumberCannotBeNegativeException.class)
	public void addSeatArrangementToScreen_WhenRowNumberIsNegtiv_ThrowExceptionRowNumberCannotBeNegative()
			throws Exception {
		SeatArrangementDao seatArrangementDao = mock(SeatArrangementDao.class);
		SeatArrangementSerivice seatArrangementService = new SeatArrangementServiceImpl(seatArrangementDao);
		String screenName = "screen1";
		List<SeatCategory> listOfSeatCategories = new ArrayList<SeatCategory>();
		listOfSeatCategories.add(new SeatCategory(1, "primium", -5, 10));
		listOfSeatCategories.add(new SeatCategory(2, "silver", -6, 12));
		seatArrangementService.addSeatArrangementToScreen(screenName, listOfSeatCategories);

	}

	@Test(expected = SeatNumberCannotBeNegativeException.class)
	public void addSeatArrangementToScreen_WhenNumberOfSeatInFirstRowIsNegtive_ThrowSeatNumberCannotBeNegativeException()
			throws Exception {
		SeatArrangementDao seatArrangementDao = mock(SeatArrangementDao.class);
		SeatArrangementSerivice seatArrangementService = new SeatArrangementServiceImpl(seatArrangementDao);
		String screenName = "screen1";
		List<SeatCategory> listOfSeatCategories = new ArrayList<SeatCategory>();
		listOfSeatCategories.add(new SeatCategory(1, "primium", 5, -10));
		listOfSeatCategories.add(new SeatCategory(2, "silver", 6, -12));
		seatArrangementService.addSeatArrangementToScreen(screenName, listOfSeatCategories);

	}

	@Test(expected = CategoryNameCannotBeNullException.class)
	public void addSeatArrangementToScreen_WhenScreenNameIsNull_NullPointerException()
			throws FileNotFoundException, RowNumberCannotBeNegativeException, SeatNumberCannotBeNegativeException,
			RowNumberCannotBeGreaterThanTenException {
		SeatArrangementDao seatArrangementDao = mock(SeatArrangementDao.class);
		SeatArrangementSerivice seatArrangementService = new SeatArrangementServiceImpl(seatArrangementDao);
		String screenName = "screen1";
		String categoryname = null;
		List<SeatCategory> listOfSeatCategories = new ArrayList<SeatCategory>();
		listOfSeatCategories.add(new SeatCategory(1, categoryname, 5, 10));
		listOfSeatCategories.add(new SeatCategory(2, categoryname, 6, 10));
		seatArrangementService.addSeatArrangementToScreen(screenName, listOfSeatCategories);

	}

	@Test(expected = RowNumberCannotBeGreaterThanTenException.class)
	public void addSeatArrangementToScreen_WhenScreenNameIsNull_RowNumberCannotBeGreaterThanTenException()
			throws Exception {
		SeatArrangementDao seatArrangementDao = mock(SeatArrangementDao.class);
		SeatArrangementSerivice seatArrangementService = new SeatArrangementServiceImpl(seatArrangementDao);
		String screenName = "screen1";
		List<SeatCategory> listOfSeatCategories = new ArrayList<SeatCategory>();
		listOfSeatCategories.add(new SeatCategory(1, "primium", 11, 10));
		listOfSeatCategories.add(new SeatCategory(2, "silver", 12, 12));
		seatArrangementService.addSeatArrangementToScreen(screenName, listOfSeatCategories);

	}

}
