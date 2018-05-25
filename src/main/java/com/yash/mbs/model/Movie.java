package com.yash.mbs.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Movie {

	private int id;
	private String title;
	private Time duration;
	private List<String> actors;
	private String production;

	public Movie(int id, String title, Time duration, List<String> actors, String production) {
		super();
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.actors = actors;
		this.production = production;
	}

	public Movie() {
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Time getDuration() {
		return duration;
	}

	public List<String> getActors() {
		return actors;
	}

	public String getProduction() {
		return production;
	}

}
