package com.yash.mbs.service;

import java.io.FileNotFoundException;

import com.yash.mbs.model.Movie;
import com.yash.mbs.model.Screen;

public interface MapMovieScreenService {

	int  addMovieToScreen(String movieName, String screenName) throws FileNotFoundException;

}
