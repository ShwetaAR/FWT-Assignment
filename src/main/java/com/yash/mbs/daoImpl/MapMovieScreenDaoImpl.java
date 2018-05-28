package com.yash.mbs.daoImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yash.mbs.dao.MapMovieScreenDao;
import com.yash.mbs.model.MapMovieScreen;
import com.yash.mbs.model.Movie;
import com.yash.mbs.model.Screen;
import com.yash.mbs.util.FileUtil;

public class MapMovieScreenDaoImpl implements MapMovieScreenDao {

	private Gson gsonObj;
	private ObjectMapper mapperObj;

	public MapMovieScreenDaoImpl() {
		mapperObj = new ObjectMapper();
		gsonObj = new Gson();
	}

	public List<MapMovieScreen> loadMovieToScreen() {

		String filePath = "src/main/resources/json/listOfMovieScreenMapping.json";
		List<MapMovieScreen> mapMovieScreen = null;
		mapMovieScreen = convertJsonFileToMapMovieScreenObject(filePath, mapMovieScreen);
		return mapMovieScreen;

	}

	private List<MapMovieScreen> convertJsonFileToMapMovieScreenObject(String filePath,
			List<MapMovieScreen> mapMovieScreen) {
		try {
			String jsonString = FileUtil.readJsonFile(filePath);
			MapMovieScreen[] jsonFile = gsonObj.fromJson(jsonString, MapMovieScreen[].class);
			mapMovieScreen = Arrays.asList(jsonFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapMovieScreen;
	}

	public int insertMovieToScreen(Movie movie, Screen screen) {
		int id = 0;
		List<MapMovieScreen> listOfAllMovieScreen = loadMovieToScreen();
		for (MapMovieScreen mapMovieToScreen : listOfAllMovieScreen) {
			id += mapMovieToScreen.getId();
		}
		listOfAllMovieScreen.add(new MapMovieScreen(id, movie, screen));

		int inserted = 1;
		String json = gsonObj.toJson(listOfAllMovieScreen);
		String filePath = "src/main/resources/json/listOfMovieScreenMapping.json";
		FileUtil.convertObjectToJson(json, filePath);
		return inserted;

	}

	public List<Movie> loadAllMovie() {
		List<Movie> jsonList = null;
		String filePath = "src/main/resources/json/listOfMovies.json";
		String jsonString;
		try {
			jsonString = FileUtil.readJsonFile(filePath);
			jsonList = mapperObj.readValue(jsonString, new TypeReference<List<Movie>>() {
			});

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonList;

	}

}
