package com.ge.exercise1;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ParserTest {

	Parser parser;

	@Before
	public void setUp() {
		try {
			Class parserClass = Class.forName("com.ge.exercise1.MyParser");
			parser = (Parser) parserClass.newInstance();
		} catch (Exception e) {
			Assume.assumeNoException(e);
		}
	}

	@Test
	public void parseApplicationDataSimpleTest() throws JsonParseException, JsonMappingException, IOException {
		String simpleTestData = "Application(id: 0,name: MyApp,users:[User(id: 2,name: Beth Jones)],groups:[Group(id: 1,name: SimpleGroup,users:[User(id: 2,name: Beth Jones)])])";
		Application app = parser.parseApplicationData(simpleTestData);

		assertEquals("SimpleGroup", app.getGroup("1").getName());
		assertEquals(1, app.getGroup("1").size);

		assertEquals("Beth Jones", app.getUser("2").getName());
	}
}