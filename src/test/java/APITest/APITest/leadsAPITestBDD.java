package APITest.APITest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.avalara.apitest.base.APITestBase;

public class leadsAPITestBDD extends APITestBase {
	public String URL;
	private String MilesAPIKey = "b380ebe4-84a4-47f5-a354-bb02c57112f4";

	@Test
	public void PostCallBDD() {
		JSONObject requestParams = new JSONObject();
		for (Map.Entry<String, Object> key : APITestPayload()) {
			requestParams.put(key.getKey(), key.getValue());
		}
		given().
			header("Content-Type", "application/json").
			header("X-Miles-Api-Key", MilesAPIKey).body(requestParams).
		when().
			post(APITestURL()).
		 then().
		 	log().all().statusCode(200).
			body("status", equalTo("success")).
			body("statusid", equalTo(1)).
			body("transactionid", not(equalTo(empty()))).
			body("profile_pid", not(equalTo(empty()))).
			body("date", not(equalTo(empty())));

	}

}
