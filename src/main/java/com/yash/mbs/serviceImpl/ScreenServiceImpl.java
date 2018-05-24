package com.yash.mbs.serviceImpl;

import java.util.List;

import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.exception.ScreenAlreadyExistException;
import com.yash.mbs.exception.ScreenNameCannotBeEmptyException;
import com.yash.mbs.model.Screen;
import com.yash.mbs.service.ScreenService;

public class ScreenServiceImpl implements ScreenService {

	private ScreenDao screenDao;

	public ScreenServiceImpl(ScreenDao screenDao) {
		this.screenDao = screenDao;
	}

	public int addScreen(Screen screen) throws ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {
		checkIfScreenIsNull(screen);
		checkIfScreenNameIsEmpty(screen);
		int screenExist = 1;
		List<Screen> listOfScreens = screenDao.getScreens();

		return checkIfScreenAlreadyExist(screen, listOfScreens, screenExist);
	}

	private int checkIfScreenAlreadyExist(Screen screen, List<Screen> listOfScreens, int screenExist)
			throws ScreenAlreadyExistException {
		for (Screen existingScreens : listOfScreens) {
			if (existingScreens.getScreenName().equalsIgnoreCase(screen.getScreenName())) {
				screenExist = 0;
				throw new ScreenAlreadyExistException("Screen already exist ");
			}
		}
		return screenExist;
	}

	private void checkIfScreenNameIsEmpty(Screen screen) throws ScreenNameCannotBeEmptyException {
		if (screen.getScreenName().equals("")) {
			throw new ScreenNameCannotBeEmptyException("Screen Name Cannot Be Empty Exception");
		}
	}

	private void checkIfScreenIsNull(Screen screen) {
		if (screen == null) {
			throw new NullPointerException("Screen should not be null");
		}
	}

}
