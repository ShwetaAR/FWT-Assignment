package com.yash.mbs.dao;

import java.io.FileNotFoundException;
import java.util.List;

import com.yash.mbs.model.Screen;

public interface ScreenDao {

	public int insertScreen(Screen screen) throws FileNotFoundException;

	public List<Screen> loadAllScreen() throws FileNotFoundException;



}
