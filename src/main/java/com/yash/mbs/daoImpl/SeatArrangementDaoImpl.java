package com.yash.mbs.daoImpl;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yash.mbs.dao.SeatArrangementDao;
import com.yash.mbs.model.Screen;
import com.yash.mbs.model.SeatArrangement;
import com.yash.mbs.util.FileUtil;

public class SeatArrangementDaoImpl implements SeatArrangementDao {

	private Gson gson;
	private String filepath;
	
	public SeatArrangementDaoImpl() {
		gson= new Gson();
	}

	public int insertSeatArrangementCategoryWise(List<SeatArrangement> seatArrangementCategoryWise) {
		filepath = "src/main/resources/json/seatArrangement.json";
		int inserted = 1;
	/*	List<Screen> listOfAllScreen = loadAllScreen();
		if (listOfAllScreen.isEmpty()) {
			String json = gson.toJson(screen);
		} else
			listOfAllScreen.add(screen);*/
		String json = gson.toJson(seatArrangementCategoryWise);
		FileUtil.convertObjectToJson(json, filepath);
		return inserted;
		
	}
	
	public List<SeatArrangement> loadAllScreenArrangementCategoryWise(){
		List<SeatArrangement> screenArrangementList  = null;
		ObjectMapper mapperObj = new ObjectMapper();
		filepath = "src/main/resources/json/seatArrangement.json";
		try {
		String jsonString = FileUtil.readJsonFile(filepath);
		screenArrangementList = mapperObj.readValue(jsonString, new TypeReference<List<SeatArrangement>>() {
			});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenArrangementList;
	}
		

}
