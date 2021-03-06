package com.yash.mbs.daoImpl;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.yash.mbs.dao.MapMovieScreenDao;
import com.yash.mbs.model.Movie;
import com.yash.mbs.model.Screen;

public class MapMovieScreenDaoImplTest {

	private MapMovieScreenDao mapMovieScreenDao;
	private Movie movie;
	private Screen screen;

	@Before
	public void setUp() {
		mapMovieScreenDao = new MapMovieScreenDaoImpl();
	}

	@Test
	public void insertMovieToScreen_WhenScreenAndMovieExist_InsertMovieToScreen() {
		MapMovieScreenDao mapMovieScreenDao = new MapMovieScreenDaoImpl();
		movie = new Movie(2, "Dear Zindagi", "02:00:00", Arrays.asList("aliabhat,coactor"), "Bhat");
		screen = new Screen(11, "screen2");
		mapMovieScreenDao.loadMovieToScreen();

	}

	@Test
	public void loadMovieToScreen_WhenLoadMovieToScreenCalled_ReturnMovieScreenMapping() {
		MapMovieScreenDao mapMovieScreenDao = new MapMovieScreenDaoImpl();
		assertEquals(2, mapMovieScreenDao.loadMovieToScreen().size());

	}

	@Test
	public void loadAllMovie_WhenLoadAllMovieCalled_ReturnAllMovies() {
		assertEquals(2, mapMovieScreenDao.loadAllMovie().size());

	}

}