package pojoserialization;

import org.testng.annotations.Test;

import Library_API_Dynamic_Parameterization.googlemapPayload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.util.*;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AddPlaceGooglemap {
	
	@Test
	public void addPlace() {
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		POJOGetMap pm = new POJOGetMap();
		pm.setAccuracy(90);
		pm.setAddress("Delhi,near Kunj Vihar");
		pm.setLanguage("English-EN");
		pm.setName("Test");
		pm.setPhone_number("1010101010");
		
		List<String> list = new ArrayList<String>();
		list.add("V Park");
		list.add("Pheonix");
		pm.setTypes(list);
		
		pm.setWebsite("https://rahulshettyacademy.com");
		
		POJOLocation pl = new POJOLocation();
		pl.setLatitude(-35.98967);
		pl.setLongitude(35.98967);
		pm.setLocation(pl);
		
		//request Specification builder
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		//response Specification builder
		ResponseSpecification res =	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		RequestSpecification request = given().log().all().spec(req).body(pm);

		Response response = request.when()
			.post("/maps/api/place/add/json")	
			.then().spec(res).log().all().extract().response();
		
		String resp = response.getBody().asString();
		
		System.out.println(resp);
			
	}

}
