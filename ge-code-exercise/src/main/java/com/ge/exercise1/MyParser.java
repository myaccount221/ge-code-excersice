package com.ge.exercise1;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyParser implements Parser {

	public Application parseApplicationData(String data) throws JsonParseException, JsonMappingException, IOException {

		String withOutClassNameIntxt = data.replaceAll("Application|User|Group"+"\\(", "{");
		String txt = withOutClassNameIntxt.replaceAll("\\(", "").replaceAll("\\)", "}");
		String finalTxt = txt.replaceAll("([\\w ]+)", "\"$1\"");
		ObjectMapper mapper = new ObjectMapper();
		Application obj = mapper.readValue(finalTxt, SubApplication.class);
		return obj;
	}

}