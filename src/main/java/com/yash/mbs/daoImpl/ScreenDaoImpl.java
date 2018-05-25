package com.yash.mbs.daoImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.model.Screen;
import com.yash.mbs.util.FileUtil;

public class ScreenDaoImpl implements ScreenDao {

	
	public int insertScreen(Screen screen) throws FileNotFoundException {

		boolean doScreenExist = false;
		Gson gson = new Gson();
		List<Screen> listOfAllScreen = loadAllScreen();
		for (Screen existingScreen : listOfAllScreen) {
			if (screen.getScreenName().equalsIgnoreCase(existingScreen.getScreenName())) {
				doScreenExist = true;
			}
		}
		return insertScreenIfSizeLessThanThreeAndNotExistAlready(screen, doScreenExist, gson, listOfAllScreen);

	}

	private int insertScreenIfSizeLessThanThreeAndNotExistAlready(Screen screen, boolean doScreenExist, Gson gson,
			List<Screen> listOfAllScreen) {
		int inserted = 1;
		String filepath = "src/main/resources/json/listOfScreen.json";
		int notInserted = 0;
		if (!doScreenExist && listOfAllScreen.size() < 3) {
			listOfAllScreen.add(screen);
			String json = gson.toJson(listOfAllScreen);
			FileUtil.convertObjectToJson(json, filepath);
			return inserted;
		} else
			return notInserted;

	}

	public List<Screen> loadAllScreen() throws FileNotFoundException {
		List<Screen> participantJsonList = null;
		ObjectMapper mapperObj = new ObjectMapper();
		String filePath = "src/main/resources/json/listOfScreen.json";
		String jsonString = FileUtil.readJsonFile(filePath);
		try {
			participantJsonList = mapperObj.readValue(jsonString, new TypeReference<List<Screen>>() {
			});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return participantJsonList;
	}

}
