package stepdefinitions;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void deforeScenario()
	{
		MapsStepDefinition stepDefinition = new MapsStepDefinition();
		// Write code to give placeId
		// Execute this code only when PlaceId is null
		if (MapsStepDefinition.placeId==null){
			stepDefinition.addplacePayloadWith("Nameable", 10);
			stepDefinition.user_calls_with_http_request("AddPlaceAPI","POST");
			stepDefinition.verifyPlace_IdCreatedMapsToUsing("Nameable", "GetPlaceAPI");
		}


	}
}
