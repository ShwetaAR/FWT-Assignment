package com.yash.mbs.main;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.yash.mbs.exception.FileDoNotExist;
import com.yash.mbs.exception.FileIsEmpty;

public class MovieBookingSystemTest {
	private File filePath;
	private String filename;

	@Test(expected = FileDoNotExist.class)
	public void getMenu_ThrowFileGivenNotFoundException_WhenFileGivenDonotExist() throws IOException {
		MovieBookingSystem movieBookingSystem = new MovieBookingSystem();
		filename = "OperatorsMenu1";
		filePath = new File("src/main/resources/menu/" + filename + ".txt");
		movieBookingSystem.getMenu(filePath);

	}

	@Test(expected = FileIsEmpty.class)
	public void getMenu_ThrowFileIsEmptyException_WhenFileIsEmpty() throws IOException {
		MovieBookingSystem movieBookingSystem = new MovieBookingSystem();
		String filename = "OperatorsMenuToTestEmpty";
		filePath = new File("src/test/resources/menu/" + filename + ".txt");
		movieBookingSystem.getMenu(filePath);
	}

	@Test(expected = NullPointerException.class)
	public void getMenu_ThrowNullPointerException_WhenFileGivenIsNull() throws IOException {
		MovieBookingSystem movieBookingSystem = new MovieBookingSystem();
		movieBookingSystem.getMenu(filePath);

	}

}
