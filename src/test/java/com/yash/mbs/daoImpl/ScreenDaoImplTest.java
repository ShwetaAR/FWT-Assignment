package com.yash.mbs.daoImpl;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.model.Screen;

public class ScreenDaoImplTest {

	@Test
	public void insertScreen_WhenScreenSizeLessThanFourAndNotExistInJsonFile_ShouldInsertAndReturnOne()
			throws FileNotFoundException {
		ScreenDao screenDao = new ScreenDaoImpl();
		assertEquals(1, screenDao.insertScreen(new Screen(100, "hello")));

	}

	@Test
	public void insertScreen_WhenScreenExistAndSizeGreaterThan3_ShouldNotInsertAndReturnZero()
			throws FileNotFoundException {
		ScreenDao screenDao = new ScreenDaoImpl();
		assertEquals(0, screenDao.insertScreen(new Screen(100, "hello")));

	}

	@Test
	public void loadAllScreen_WhenGetScreenIsCalled_ReturnListOfSCreens() throws Exception {
		ScreenDao screenDao = new ScreenDaoImpl();
		List<Screen> listOfScreens = screenDao.loadAllScreen();
		assertEquals(3, listOfScreens.size());
	}

}
