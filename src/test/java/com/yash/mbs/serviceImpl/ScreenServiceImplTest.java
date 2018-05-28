package com.yash.mbs.serviceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.exception.ScreenAlreadyExistException;
import com.yash.mbs.exception.ScreenNameCannotBeEmptyException;
import com.yash.mbs.exception.ScreenSizeMoreThanThreeException;
import com.yash.mbs.model.Screen;
import com.yash.mbs.service.ScreenService;

public class ScreenServiceImplTest {

	private ScreenDao screenDao;
	private ScreenService screenService;
	private Screen screen;

	@Before
	public void setUp() {
		screenDao = mock(ScreenDao.class);
		screenService = new ScreenServiceImpl(screenDao);
		screen = new Screen();

	}

	@Test(expected = NullPointerException.class)
	public void addScreen_WhenSetOfScreenIsNULL_ThrowNullPointerException()
			throws ScreenNameCannotBeEmptyException, ScreenAlreadyExistException, FileNotFoundException {
		screen = null;
		screenService.addScreen(screen);
	}

	@Test(expected = ScreenNameCannotBeEmptyException.class)
	public void addScreen_WhenEmptyScreenName_ThrowScreenNameCannotBeEmptyException()
			throws ScreenNameCannotBeEmptyException, ScreenAlreadyExistException, FileNotFoundException {
		screenService.addScreen(new Screen(11, ""));

	}

	@Test(expected = ScreenSizeMoreThanThreeException.class)
	public void addScreen_WhenScreenSizeMoreThanThree_ThrowScreenSizeMoreThanThreeException()
			throws ScreenNameCannotBeEmptyException, ScreenAlreadyExistException, FileNotFoundException {
		screenService.addScreen(new Screen(1, "Screen0"));

	}

	@Test
	public void addScreen_WhenValidScreenObjectGivenAsInput_ShouldBeAdded()
			throws ScreenNameCannotBeEmptyException, ScreenAlreadyExistException, FileNotFoundException {
		assertEquals(1, screenService.addScreen(new Screen(5, "auxdi4")));

	}

	@Test(expected = ScreenAlreadyExistException.class)
	public void addScreen_WhenExistingScreenGivenAsInput_ShouldNotBeAdded()
			throws ScreenAlreadyExistException, ScreenNameCannotBeEmptyException, FileNotFoundException {
		assertEquals(0, screenService.addScreen(new Screen(1, "Screen1")));

	}

}
