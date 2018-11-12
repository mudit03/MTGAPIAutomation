package APITest.APITest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.avalara.apitest.base.APITestBase;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class leadsAPITest extends APITestBase {
	public String URL;

	@BeforeClass
	public void before() {
		this.URL = APITestURL();
	}

	/*
	 * @Test public void getcall() { Response response = RestAssured.get(this.URL);
	 * int code = response.getStatusCode(); System.out.println("Code is" + code);
	 * System.out.println("response is " +response.asString());
	 * System.out.println("time taken is " +response.getTime()); }
	 */

	@Test
	public void PostCall() throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {

		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		
		// Creating payload from properties file
		for (Map.Entry<String, Object> key : APITestPayload()) {
			requestParams.put(key.getKey(), key.getValue());
		}
		System.out.println("Request Payload sent is" + requestParams.toJSONString());

		// Adding Header
		request.header("Content-Type", "application/json");
		
		// adding Header and payload to json body
		request.body(requestParams.toJSONString());
		
		// Calling the URI with specified request
		System.out.println("Requesting URI" +this.URL);
		Response response = request.post(this.URL);
		
		// Printing response
		String ResponseBody = response.asString();
		System.out.println("Response Data is " + ResponseBody);
		System.out.println("Status Code is " + response.getStatusCode());
		
		// Validating the response
		Assert.assertEquals(JsonPath.read(ResponseBody, "$.name"), "morpheus");
		Assert.assertEquals(JsonPath.read(ResponseBody, "$.job"), "leader");
		System.out.println("testing done");

	}

	
}
