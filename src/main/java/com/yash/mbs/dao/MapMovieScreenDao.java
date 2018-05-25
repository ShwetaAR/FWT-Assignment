package com.yash.mbs.dao;

import java.util.List;
import java.util.Map;

import com.yash.mbs.model.MapMovieScreen;
import com.yash.mbs.model.Movie;
import com.yash.mbs.model.Screen;

public interface MapMovieScreenDao {

	List<MapMovieScreen> loadMovieToScreen();

	int  insertMovieToScreen(Movie movie, Screen screen);

	List<Movie> loadAllMovie();

	

}
