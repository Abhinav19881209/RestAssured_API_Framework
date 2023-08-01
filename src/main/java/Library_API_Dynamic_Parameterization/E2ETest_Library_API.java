package Library_API_Dynamic_Parameterization;

import io.restassured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

//scenario is to add the place - > update the place -> Get the place & then delete the place

public class E2ETest_Library_API {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		
		//to add the Place - POST method	
		String response = 
		given()
			.log().all()
			.queryParam("key", "qaclick123")
			.body(googlemapPayload.addPlace())
		.when()
			.post("/maps/api/place/add/json")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.52 (Ubuntu)")
			.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		
		//System.out.println(js.get("place_id"));
		String placeId = js.get("place_id");
		
		// To update the place - PUT method
		
	given()
		.log().all()
		.queryParam("key", "qaclick123")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\"70 Summer walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
	.when()
		.put("/maps/api/place/update/json")
	.then()
		.log().all()
		.assertThat()
		.statusCode(200)         
		.header("Server", "Apache/2.4.52 (Ubuntu)")
		.header("Content-Type", "application/json;charset=UTF-8")
		.body("msg",equalTo("Address successfully updated"));
		
		System.out.println("Put method completed");
		
		
		// To Get the place - GET method
		
		given()
			.log().all()
			.queryParam("key", "qaclick123")
			.queryParam("place_id",placeId)
		.when()
			.get("/maps/api/place/get/json")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200);
		
		// to delete 
		given()
		.log().all()
		.queryParam("key", "qaclick123")
		.body("{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}\r\n"
				+ "")
		.when()
		.get("/maps/api/place/delete/json")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200)
		.body("status", equalTo("OK"));
		
	
	}

}
