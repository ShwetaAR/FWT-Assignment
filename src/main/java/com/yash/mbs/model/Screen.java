package com.yash.mbs.model;

public class Screen {

	private int id;

	private String screenName;

	public Screen(int id, String screenName) {
		super();
		this.id = id;
		this.screenName = screenName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

}
