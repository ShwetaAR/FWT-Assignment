package com.yash.mbs.serviceImpl;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.mbs.dao.MapMovieScreenDao;
import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.daoImpl.ScreenDaoImpl;
import com.yash.mbs.exception.InvalidMovieException;
import com.yash.mbs.exception.InvalidScreenException;
import com.yash.mbs.model.MapMovieScreen;
import com.yash.mbs.model.Movie;
import com.yash.mbs.model.Screen;
import com.yash.mbs.service.MapMovieScreenService;

public class MapMovieScreenServiceImpl implements MapMovieScreenService {

	private MapMovieScreenDao mapMovieScreenDao;
	private ScreenDao screenDao;
	private Screen screen;
	private Movie movie;

	public MapMovieScreenServiceImpl(MapMovieScreenDao movieScreenAssoiciationDao) {
		this.mapMovieScreenDao = movieScreenAssoiciationDao;
		screenDao = new ScreenDaoImpl();
		screen = new Screen();
		movie = new Movie();
	}

	public int addMovieToScreen(String movieName, String screenName) throws FileNotFoundException {
		
		List<Movie> movies = mapMovieScreenDao.loadAllMovie();
		boolean doMovieExist = false;
		int inserted = 1;
		int notInserted = 0;
		List<Screen> screens = screenDao.loadAllScreen();
		boolean doScreenExist = false;
		for (Screen screenObj : screens) {
			if (screenObj.getScreenName().equalsIgnoreCase(screenName)) {
				doScreenExist = true;
				screen = screenObj;
				break;
			}
		}
		for (Movie movieObj : movies) {
			if (movieObj.getTitle().equalsIgnoreCase(movieName)) {
				doMovieExist = true;
				movie = movieObj;
				break;
			}
		}
		checkIfScreenAndMovieExist(doMovieExist, doScreenExist);
		return insertForValidScreenAndMovie(movie, doMovieExist, inserted, notInserted, screen, doScreenExist);

	}

	private int insertForValidScreenAndMovie(Movie movie, boolean doMovieExist, int inserted, int notInserted,
			Screen screen, boolean doScreenExist) {
		if (!doScreenExist) {
			throw new InvalidScreenException("Screen Do not  Exist ");
		} else if (!doMovieExist) {
			throw new InvalidMovieException("Movie Do not  Exist ");
		} else {
			List<MapMovieScreen> existingMovieScreen = mapMovieScreenDao.loadMovieToScreen();
			for (MapMovieScreen mapMovieScreen : existingMovieScreen) {
				if (mapMovieScreen.getMovie().getTitle().equalsIgnoreCase(movie.getTitle())
						&& mapMovieScreen.getScreen().getScreenName().equalsIgnoreCase(screen.getScreenName())) {
					inserted = 0;
				}
			}
			if (inserted == 1) {

				return mapMovieScreenDao.insertMovieToScreen(movie, screen);
			} else

				return notInserted;
		}
	}

	private void checkIfScreenAndMovieExist(boolean doMovieExist, boolean doScreenExist) {
		if (!doScreenExist && !doMovieExist) {
			throw new InvalidScreenException("Screen And Movie Donot Exist ");
		}
	}

}
