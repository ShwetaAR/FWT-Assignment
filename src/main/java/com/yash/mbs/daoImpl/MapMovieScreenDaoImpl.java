package com.yash.mbs.daoImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yash.mbs.dao.MapMovieScreenDao;
import com.yash.mbs.enumeration.SeatCategory;
import com.yash.mbs.model.MapMovieScreen;
import com.yash.mbs.model.Movie;
import com.yash.mbs.model.Screen;
import com.yash.mbs.util.FileUtil;

public class MapMovieScreenDaoImpl implements MapMovieScreenDao {

	public List<MapMovieScreen> loadMovieToScreen() {

		String filePath = "src/main/resources/json/listOfMovieScreenMapping.json";
		JSONParser parser = new JSONParser();
		List<MapMovieScreen> mapMovieScreen = null;
		ObjectMapper mapperObj = new ObjectMapper();
		mapMovieScreen = convertJsonFileToMapMovieScreenObject(filePath, mapMovieScreen);
		return mapMovieScreen;

	}

	private List<MapMovieScreen> convertJsonFileToMapMovieScreenObject(String filePath,
			List<MapMovieScreen> mapMovieScreen) {
		try {
			String jsonString = FileUtil.readJsonFile(filePath);
			Gson gson = new Gson();
			MapMovieScreen[] jsonFile = gson.fromJson(jsonString, MapMovieScreen[].class);
			mapMovieScreen = Arrays.asList(jsonFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapMovieScreen;
	}

	public int insertMovieToScreen(Movie movie, Screen screen) {
		Gson gsonObj = new Gson();
		int inserted = 1;
		MapMovieScreen MovieScreenMapping = new MapMovieScreen(1, movie, screen);
		String json = gsonObj.toJson(MovieScreenMapping);
		String filePath = "src/main/resources/json/listOfMovieScreenMapping.json";
		FileUtil.convertObjectToJson(json, filePath);
		return inserted;

	}

	private int CheckifMovieScreenInsert(Movie movie, Screen screen, Gson gsonObj, int inserted) {
		if (inserted == 1) {

			MapMovieScreen MovieScreenMapping = new MapMovieScreen(1, movie, screen);
			String json = gsonObj.toJson(MovieScreenMapping);
			String filePath = "src/main/resources/json/listOfMovieScreenMapping.json";
			FileUtil.convertObjectToJson(json, filePath);
			return inserted;
		} else

			return inserted;
	}

	public List<Movie> loadAllMovie() {
		List<Movie> jsonList = null;
		String filePath = "src/main/resources/json/listOfMovies.json";
		ObjectMapper mapperObj = new ObjectMapper();
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
