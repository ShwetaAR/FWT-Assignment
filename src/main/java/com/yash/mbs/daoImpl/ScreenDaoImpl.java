package com.yash.mbs.daoImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.w3c.dom.stylesheets.LinkStyle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yash.mbs.dao.ScreenDao;
import com.yash.mbs.model.Screen;
import com.yash.mbs.util.FileUtil;

public class ScreenDaoImpl implements ScreenDao {

	private Gson gson;
	private String filepath;

	public ScreenDaoImpl() {
		gson = new Gson();
	}

	public int insertScreen(Screen screen) throws FileNotFoundException {
		filepath = "src/main/resources/json/listOfScreen.json";
		int inserted = 1;
		List<Screen> listOfAllScreen = loadAllScreen();
		if(listOfAllScreen.isEmpty()||listOfAllScreen==null){
			String json = gson.toJson(screen);
		}
		else
		listOfAllScreen.add(screen);
		String json = gson.toJson(listOfAllScreen);
		FileUtil.convertObjectToJson(json, filepath);
		return inserted;

	}

	public List<Screen> loadAllScreen()  {
		List<Screen> participantJsonList = null;
		ObjectMapper mapperObj = new ObjectMapper();
		filepath = "src/main/resources/json/listOfScreen.json";
		try {
		String jsonString = FileUtil.readJsonFile(filepath);
		if(jsonString==null){
			return null;
		}
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
