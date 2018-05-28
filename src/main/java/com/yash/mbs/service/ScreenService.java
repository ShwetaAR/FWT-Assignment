package com.yash.mbs.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.mbs.exception.ScreenAlreadyExistException;
import com.yash.mbs.exception.ScreenNameCannotBeEmptyException;
import com.yash.mbs.exception.ScreenNameDoNotExistException;
import com.yash.mbs.model.Movie;
import com.yash.mbs.model.Screen;

public interface ScreenService {

	int addScreen(Screen screen)
			throws ScreenNameCannotBeEmptyException, ScreenAlreadyExistException, FileNotFoundException;

	Screen getScreenByScreenName(String screenName) throws FileNotFoundException, ScreenNameDoNotExistException;

	List<Screen> getAllScreen() throws FileNotFoundException;

	List<Movie> getAllMovie() throws FileNotFoundException;

}
