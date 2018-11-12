package com.avalara.apitest.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APITestBase {

	public Properties props;
	public String URL;

	public String APITestURL() {
		props = new Properties();
		try {
			FileInputStream fileInput = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/avalara/apitest/config/config.properties");
			props.load(fileInput);
			URL = props.getProperty("URL") + props.getProperty("serviceURL");
		} catch (IOException e) {
			System.out.println(e);
		}
		return URL;
	}

	public Set<Entry<String, Object>> APITestPayload() {
		Map<String, Object> payloadMap = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			payloadMap = mapper.readValue(
					new FileInputStream(System.getProperty("user.dir")
							+ "/src/main/java/com/avalara/apitest/config/leadsPayload.properties"),
					new TypeReference<Map<String, Object>>() {
					});
		} catch (IOException e) {
			System.out.println(e);
		}

		Set<Entry<String, Object>> payloadEntries = payloadMap.entrySet();
		return payloadEntries;

	}

}
