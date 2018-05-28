package com.yash.mbs.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.yash.mbs.dao.MapMovieScreenDao;
import com.yash.mbs.exception.InvalidMovieException;
import com.yash.mbs.exception.InvalidScreenException;
import com.yash.mbs.model.Movie;
import com.yash.mbs.service.MapMovieScreenService;

public class MapMovieScreenServiceImplTest {

	private MapMovieScreenDao movieScreenAssoiciationDao;
	private MapMovieScreenService mapMovieScreenService;
	private String movieName;
	private String screenName;

	@Before
	public void setUp() {
		movieScreenAssoiciationDao = mock(MapMovieScreenDao.class);
		mapMovieScreenService = new MapMovieScreenServiceImpl(movieScreenAssoiciationDao);
	}

	@Test(expected = InvalidScreenException.class)
	public void addMovieToScreen_WhenScreenNameDoesNotExist_ThrowInvalidScreenException() throws FileNotFoundException {
		movieName = "raazi";
		screenName = "Screen2";
		mapMovieScreenService.addMovieToScreen(movieName, screenName);

	}

	@Test
	public void addMovieToScreen_WhenScreenNameExist_AssignMovieToScreen() throws FileNotFoundException {
		movieName = "movie2";
		screenName = "screen23";
		//loadAllMovie_WhenLoadAllMovieCalled_ReturnListOfMovie(movieScreenAssoiciationDao);
		assertEquals(1, mapMovieScreenService.addMovieToScreen(movieName, screenName));

	}

	/**
	 * loadAllMovie_WhenLoadAllMovieCalled_ReturnListOfMovie is Refactored
	 * Method of addMovieToScreen_WhenScreenNameExist_AssignMovieToScreen used
	 * to get mocked allmovies.
	 * 
	 * @param movieScreenAssoiciationDao
	 */
	private void loadAllMovie_WhenLoadAllMovieCalled_ReturnListOfMovie(MapMovieScreenDao movieScreenAssoiciationDao) {
		when(movieScreenAssoiciationDao.loadAllMovie()).thenReturn(Arrays.asList(
				new Movie(1, "Raazi", "02:00:00", Arrays.asList("aliabhat,coactor"), "Bhat"),
				new Movie(2, "Dear ZIndagi", "02:30:00", Arrays.asList("aliabhat,Sharukh"),
						"productionnamne")));
	}

	@Test(expected = InvalidMovieException.class)
	public void addMovieToScreen_WhenMovieNameDoNotExist_ThrowInvalidMovieException() throws FileNotFoundException {
		movieName = "Dear Zindagi";
		screenName = "screen1";
		loadAllMovie_WhenLoadAllMovieCalled_ReturnListOfMovie(movieScreenAssoiciationDao);

		mapMovieScreenService.addMovieToScreen(movieName, screenName);

	}

	@Test
	public void addMovieToScreen_WhenBothMovieScreenExist_InsertMovieToScreen() throws FileNotFoundException {
		movieName = "Movie2";
		screenName = "screen23";

		mapMovieScreenService.addMovieToScreen(movieName, screenName);

	}

}
