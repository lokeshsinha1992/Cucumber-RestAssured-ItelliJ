package Resources;

import pojo.Location;
import pojo.PlaceMainPoJo;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

	PlaceMainPoJo mainPoJo;

	public PlaceMainPoJo addPlacePayload(String name,Integer accuracy ){
		 mainPoJo = new PlaceMainPoJo();
		Location  location = new Location();

		mainPoJo.setAccuracy(accuracy);
		mainPoJo.setAddress("asd asd asd ");
		mainPoJo.setLanguage("lang");
		mainPoJo.setName(name);
		mainPoJo.setPhoneNumber("(+91) 132321 32131");
		mainPoJo.setWebsite("htts://asd.com");

		List<String> typesList = new ArrayList<String>();
		typesList.add("type1");
		typesList.add("type2");
		typesList.add("type3");
		mainPoJo.setTypes(typesList);

		location.setLat(-32.200);
		location.setLng(-34.200);
		mainPoJo.setLocation(location);

		return mainPoJo;

	}



	public String deletePayload(String placeId)
	{
		return "{\r\n\"place_id\":\""+placeId+"\"\r\n}";
	}




}
