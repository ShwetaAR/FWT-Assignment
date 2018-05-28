package com.yash.mbs.readmenu;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.yash.mbs.exception.FileDoNotExist;
import com.yash.mbs.exception.FileIsEmpty;
import com.yash.mbs.exception.ScreenAlreadyExistException;
import com.yash.mbs.exception.ScreenNameCannotBeEmptyException;
import com.yash.mbs.readmenu.MovieBookingSystem;

public class MovieBookingSystemTest {
	private File filePath;
	private String filename;
	private MovieBookingSystem movieBookingSystem;

	@Before
	public void setUp() {
		movieBookingSystem = new MovieBookingSystem();
	}

	@Test(expected = FileDoNotExist.class)
	public void getMenu_ThrowFileGivenNotFoundException_WhenFileGivenDonotExist() throws IOException, ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {

		filename = "OperatorsMenu1";
		filePath = new File("src/main/resources/menu/" + filename + ".txt");
		movieBookingSystem.getMenu(filePath);

	}

	@Test(expected = FileIsEmpty.class)
	public void getMenu_ThrowFileIsEmptyException_WhenFileIsEmpty() throws IOException, ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {
		String filename = "OperatorsMenuToTestEmpty";
		filePath = new File("src/test/resources/menu/" + filename + ".txt");
		movieBookingSystem.getMenu(filePath);
	}

	@Test(expected = NullPointerException.class)
	public void getMenu_ThrowNullPointerException_WhenFileGivenIsNull() throws IOException, ScreenNameCannotBeEmptyException, ScreenAlreadyExistException {
		movieBookingSystem.getMenu(filePath);

	}

}
