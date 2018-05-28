package com.yash.mbs.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtil {

	public static final String readJsonFile(String filepath) throws FileNotFoundException {

		JSONParser parser = new JSONParser();
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = null;
		try {
			Object obj = parser.parse(new FileReader(filepath));
			if(obj ==null){
				return null;
			}
			jsonStr = mapperObj.writeValueAsString(obj);
			if(jsonStr ==null){
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return jsonStr;
	}

	public static void convertObjectToJson(String json, String filepath) {

		try {
			FileWriter writer = new FileWriter(filepath);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
