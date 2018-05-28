package com.yash.mbs.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @author Shweta.baberia
 *
 *FileUtil class is used to readjson file and convertObjectToJson for all the modules.
 *Its a common class for for all the modules.
 */
public class FileUtil {
	


	public static final String readJsonFile(String filepath) throws FileNotFoundException {

		JSONParser parser = new JSONParser();
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr = null;
		try {
			Object obj = parser.parse(new FileReader(filepath));
			jsonStr = mapperObj.writeValueAsString(obj);
			
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
