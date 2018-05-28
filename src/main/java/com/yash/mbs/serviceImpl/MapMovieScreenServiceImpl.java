package com.yash.mbs.serviceImpl;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.mbs.dao.MapMovieScreenDao;
import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.daoImpl.MapMovieScreenDaoImpl;
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
		mapMovieScreenDao = new MapMovieScreenDaoImpl();
		List<Movie> movies = mapMovieScreenDao.loadAllMovie();

		int inserted = 1;
		int notInserted = 0;
		boolean doScreenExist = doScreenExist(screenName);
		boolean doMovieExist = doesMovieExist(movieName, movies);
		checkIfScreenAndMovieExist(doMovieExist, doScreenExist);
		return insertForValidScreenAndMovie(movie, doMovieExist, inserted, notInserted, screen, doScreenExist);

	}

	private boolean doesMovieExist(String movieName, List<Movie> movies) {
		boolean doMovieExist = false;
		for (Movie movieObj : movies) {
			if (movieObj.getTitle().equalsIgnoreCase(movieName)) {
				doMovieExist = true;
				movie = movieObj;
				break;
			}
		}
		return doMovieExist;
	}

	private boolean doScreenExist(String screenName) throws FileNotFoundException {
		List<Screen> screens = screenDao.loadAllScreen();
		boolean doScreenExist = false;
		for (Screen screenObj : screens) {
			if (screenObj.getScreenName().equalsIgnoreCase(screenName)) {
				doScreenExist = true;
				screen = screenObj;
				break;
			}
		}
		return doScreenExist;
	}

	private int insertForValidScreenAndMovie(Movie movie, boolean doMovieExist, int inserted, int notInserted,
			Screen screen, boolean doScreenExist) {
		if (!doScreenExist) {
			throw new InvalidScreenException("Screen Do not  Exist ");
		} else if (!doMovieExist) {
			throw new InvalidMovieException("Movie Do not  Exist ");
		} else {
			mapMovieScreenDao = new MapMovieScreenDaoImpl();
			List<MapMovieScreen> existingMovieScreen = mapMovieScreenDao.loadMovieToScreen();
			for (MapMovieScreen mapMovieScreen : existingMovieScreen) {

				if (mapMovieScreen.getMovie().getTitle().equalsIgnoreCase(movie.getTitle())
						&& mapMovieScreen.getScreen().getScreenName().equalsIgnoreCase(screen.getScreenName())) {
					inserted = 0;
					break;
				}
			}
			if (inserted == 1) {
				mapMovieScreenDao = new MapMovieScreenDaoImpl();
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
