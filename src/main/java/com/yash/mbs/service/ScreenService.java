package com.yash.mbs.service;

import com.yash.mbs.exception.ScreenAlreadyExistException;
import com.yash.mbs.exception.ScreenNameCannotBeEmptyException;
import com.yash.mbs.model.Screen;

public interface ScreenService {

	int  addScreen(Screen screen) throws ScreenNameCannotBeEmptyException,ScreenAlreadyExistException ;

}
