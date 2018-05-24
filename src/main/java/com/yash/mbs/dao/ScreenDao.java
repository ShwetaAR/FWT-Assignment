package com.yash.mbs.dao;

import java.util.List;

import com.yash.mbs.model.Screen;

public interface ScreenDao {

	public int insertScreen(Screen screen);

	public List<Screen> getScreens();
}
