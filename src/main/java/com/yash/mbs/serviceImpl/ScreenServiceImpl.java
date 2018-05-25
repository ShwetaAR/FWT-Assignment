package com.yash.mbs.serviceImpl;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.exception.ScreenAlreadyExistException;
import com.yash.mbs.exception.ScreenNameCannotBeEmptyException;
import com.yash.mbs.exception.ScreenNameDoNotExistException;
import com.yash.mbs.exception.ScreenSizeMoreThanThreeException;
import com.yash.mbs.model.Screen;
import com.yash.mbs.service.ScreenService;

public class ScreenServiceImpl implements ScreenService {

	private ScreenDao screenDao;

	public ScreenServiceImpl(ScreenDao screenDao) {
		this.screenDao = screenDao;
	}

	public int addScreen(Screen screen)
			throws ScreenNameCannotBeEmptyException, ScreenAlreadyExistException, FileNotFoundException {
		int screenExist = 0;
		checkIfScreenIsNull(screen);
		checkIfScreenNameIsEmpty(screen);
		List<Screen> listOfScreens = screenDao.loadAllScreen();
		checkIfScreenAlreadyExist(screen, listOfScreens, screenExist);
		return checkForScreenSizeLessThanThree(screen, screenExist, listOfScreens);
	}

	private int checkForScreenSizeLessThanThree(Screen screen, int screenExist, List<Screen> listOfScreens)
			throws ScreenAlreadyExistException, FileNotFoundException {
		if (listOfScreens.size() < 3 || checkIfScreenAlreadyExist(screen, listOfScreens, screenExist) == 1) {
			throw new ScreenSizeMoreThanThreeException("Screen Size cannot be greater than 3 ");
		} else
			return screenDao.insertScreen(screen);
	}

	private int checkIfScreenAlreadyExist(Screen screen, List<Screen> listOfScreens, int screenExist)
			throws ScreenAlreadyExistException {
		for (Screen existingScreens : listOfScreens) {
			if (existingScreens.getScreenName().equalsIgnoreCase(screen.getScreenName())) {
				screenExist = 1;
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

	public List<Screen> getAllScreen() throws FileNotFoundException {
		List<Screen> lisAllScreen = screenDao.loadAllScreen();
		return lisAllScreen;
	}

	public Screen getScreenByScreenName(String screenName) throws FileNotFoundException, ScreenNameDoNotExistException {
		List<Screen> allScreens = screenDao.loadAllScreen();
		Screen fethchedScreen = new Screen();
		boolean ifScreenExist = false;
		for (Screen screen : allScreens) {
			if (screen.getScreenName().equalsIgnoreCase(screenName)) {
				fethchedScreen = screen;
				ifScreenExist = true;
				break;
			}
		}
		if (!ifScreenExist)
			return fethchedScreen;
		else
			throw new ScreenNameDoNotExistException("No Screen present with this name");
	}

}
