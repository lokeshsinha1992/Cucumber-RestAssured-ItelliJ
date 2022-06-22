package stepdefinitions;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;

public class MapsStepDefinition extends Utils {

	RequestSpecification reqSpec;
	ResponseSpecification respSpec;
	Response resp;
	TestDataBuild data =  new TestDataBuild();
	static String placeId;

	@Given(value = "AddPlace Payload with {string} {int}")
	public void addplacePayloadWith(String name, Integer accuracy){
		// Write code here that turns the phrase above into concrete actions
		System.out.println("add place payload with name : "+name+"and accuracy : "+accuracy);



		reqSpec = given().spec(requestSpecification()).body(data.addPlacePayload(name, accuracy));// data.addPlacePayload() will return mainPojo object having all values
		// and requestSpecification() will return request specfication

	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		// Write code here that turns the phrase above into concrete actions
		System.out.println( resource +" request ");

		APIResources apiResources = APIResources.valueOf(resource); // constructor will be called with value of resource which you pass.
		String resourceURI = apiResources.getResource();
		System.out.println( apiResources.getResource());

		if(httpMethod.equalsIgnoreCase("POST")){
			resp = reqSpec.when().post(resourceURI).then().spec(responseSpecifications()).extract().response();
		}

		else if (httpMethod.equalsIgnoreCase("GET")){
			resp = reqSpec.when().get(resourceURI).then().log().all().spec(responseSpecifications()).extract().response();
		}



	}
	@Then("The API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer statusCode) {
		// Write code here that turns the phrase above into concrete actions


		assertEquals(resp.getStatusCode(),200);


	}
	@Then("{string} in response Body is {string}")
	public void in_response_body_is(String key, String value) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(resp, key), value); // get value for key and asser with value

	}


	@And("Verify place_Id created maps to {string} using {string}")
	public void verifyPlace_IdCreatedMapsToUsing(String expectedName, String resourceName) {

		 placeId = getJsonPath(resp, "place_id");
		System.out.println(placeId);

		reqSpec = given().spec(requestSpecification()).queryParam("place_Id", placeId);

		user_calls_with_http_request(resourceName,"GET"); // reusing http method above so that it will hit GET request

		//resp = reqSpec.queryParam("place_Id", placeId).when().get(resourceName);
		String responseStr = resp.prettyPrint();
		System.out.println("Under this : "+responseStr);
//
//		String actualName = getJsonPath(resp, "name");
//
//		assertEquals(actualName, expectedName);

	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() {

		reqSpec = given().spec(requestSpecification()).body(data.deletePayload(placeId));
	}
}
