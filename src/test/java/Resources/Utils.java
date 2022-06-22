package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

	static RequestSpecification requestSpecBuilder;

	ResponseSpecification responseSpecBuilder;

	public static String globalPropValues(String key)  {

		Properties properties = new Properties();

		try {
			FileInputStream fileInputStream = new FileInputStream("src/test/java/Resources/global.properties");
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return	properties.getProperty(key);

	}


	public  static RequestSpecification requestSpecification()  {

		PrintStream printStream = null;

		if(requestSpecBuilder==null){
			try {
				printStream = new PrintStream(new FileOutputStream("logging.txt"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			requestSpecBuilder = new RequestSpecBuilder()
					.setBaseUri(globalPropValues("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(printStream))  // this will log request data   into the file
					.addFilter(ResponseLoggingFilter.logResponseTo(printStream)) // this will log response data  into the file
					.setContentType(ContentType.JSON).build();


			return  requestSpecBuilder;
		}
		return requestSpecBuilder;

	}

	public ResponseSpecification responseSpecifications(){
		responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		return  responseSpecBuilder;
	}

	public String getJsonPath(Response response, String key){

		String respStr= response.asString();
		JsonPath jsonPath = new JsonPath(respStr);
		return jsonPath.get(key).toString();
	}





}
