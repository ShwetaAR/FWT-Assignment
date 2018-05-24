package com.yash.mbs.serviceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;

import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.exception.ScreenAlreadyExistException;
import com.yash.mbs.exception.ScreenNameCannotBeEmptyException;
import com.yash.mbs.model.Screen;
import com.yash.mbs.service.ScreenService;

public class ScreenServiceImplTest {
	private ScreenDao screenDao = mock(ScreenDao.class);

	@Test(expected = NullPointerException.class)
	public void addScreen_WhenSetOfScreenIsNULL_ThrowNullPointerException()
			throws ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {
		ScreenService screenService = new ScreenServiceImpl(screenDao);
		Screen screen = null;
		screenService.addScreen(screen);
	}

	@Test(expected = ScreenNameCannotBeEmptyException.class)
	public void addScreen_WhenEmptyScreenName_ThrowScreenNameCannotBeEmptyException()
			throws ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {
		ScreenService screenService = new ScreenServiceImpl(screenDao);
		screenService.addScreen(new Screen(11, ""));

	}

	@Test
	public void addScreen_WhenValidScreenObjectGivenAsInput_ShouldBeAdded()
			throws ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {
		ScreenService screenService = new ScreenServiceImpl(screenDao);
		when(screenDao.getScreens()).thenReturn(Arrays.asList(new Screen(12, "audi1"), new Screen(13, "audi2")));
		when(screenDao.insertScreen(new Screen(1, "Screen1"))).thenReturn(1);
		assertEquals(1, screenService.addScreen(new Screen(1, "Screen1")));

	}

	@Test(expected = ScreenAlreadyExistException.class)
	public void addScreen_WhenExistingScreenGivenAsInput_ShouldNotBeAdded()
			throws ScreenAlreadyExistException, ScreenNameCannotBeEmptyException {
		ScreenService screenService = new ScreenServiceImpl(screenDao);
		when(screenDao.getScreens()).thenReturn(Arrays.asList(new Screen(12, "screen1"), new Screen(13, "audi2")));
		when(screenDao.insertScreen(new Screen(1, "Screen1"))).thenReturn(0);
		assertEquals(0, screenService.addScreen(new Screen(1, "Screen1")));

	}

}
