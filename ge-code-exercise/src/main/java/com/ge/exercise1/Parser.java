package com.ge.exercise1;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface Parser {
	Application parseApplicationData(String data) throws JsonParseException, JsonMappingException, IOException;
}
