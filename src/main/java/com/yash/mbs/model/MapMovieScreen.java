package com.yash.mbs.model;


public class MapMovieScreen {

	private int id;
	private Movie movie;
	private Screen screen;

	public MapMovieScreen(int id, Movie movie, Screen screen) {
		super();
		this.id = id;
		this.movie = movie;
		this.screen = screen;

	}

	public MapMovieScreen() {
	}

	public int getId() {
		return id;
	}

	public Movie getMovie() {
		return movie;
	}

	public Screen getScreen() {
		return screen;
	}

}
