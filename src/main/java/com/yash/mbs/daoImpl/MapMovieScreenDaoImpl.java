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
	private String filePath;

	public MapMovieScreenDaoImpl() {
		mapperObj = new ObjectMapper();
		gsonObj = new Gson();
	}

	public List<MapMovieScreen> loadMovieToScreen() {
		List<MapMovieScreen> jsonList = null;
		String filePath = "src/main/resources/json/listOfMovieScreenMapping.json";
		String jsonString;
		try {
			jsonString = FileUtil.readJsonFile(filePath);
			jsonList = mapperObj.readValue(jsonString, new TypeReference<List<MapMovieScreen>>() {
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


	public int insertMovieToScreen(Movie movie, Screen screen) {
		filePath = "src/main/resources/json/listOfMovieScreenMapping.json";
		int id=12;
		int inserted=1;
		List<MapMovieScreen> listOfAllScreenMovie = loadMovieToScreen();
		if (listOfAllScreenMovie.isEmpty() || listOfAllScreenMovie == null) {
			String json = gsonObj.toJson(new MapMovieScreen(id, movie, screen));
		} else
			listOfAllScreenMovie.add(new MapMovieScreen(id, movie, screen));
		id++;
		String json = gsonObj.toJson(listOfAllScreenMovie);
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
