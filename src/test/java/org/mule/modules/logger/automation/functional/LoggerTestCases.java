/**
 *
 */
package org.mule.modules.logger.automation.functional;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.logger.LoggerConnector;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;

public class LoggerTestCases extends
		AbstractTestCase<LoggerConnector> {

	public LoggerTestCases() {
		super(LoggerConnector.class);
	}

	@Before
	public void setup() {
		// TODO
	}

	@After
	public void tearDown() {
		// TODO
	}

	@Test
	public void verify() {
		java.lang.String expectedJSON = "{\"key1\": \"value1\", \"key2\": \"value2\"}";
		java.lang.String expectedStandard = "key1 = \"value1\", key2 = \"value2\"";
		java.lang.String friend = null;
		HashMap<String, String> logMap = new HashMap<String, String>();
		logMap.put("key1", "value1");
		logMap.put("key2", "value2");
		assertEquals(getConnector().formatJSONLog(logMap), expectedJSON);
		assertEquals(getConnector().formatStandardLog(logMap), expectedStandard);
	}

}