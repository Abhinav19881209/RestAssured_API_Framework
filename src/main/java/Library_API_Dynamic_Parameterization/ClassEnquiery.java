package Library_API_Dynamic_Parameterization;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class ClassEnquiery {
		
	public static void main(String[] args) {
	
	RestAssured.baseURI = "https://rahulshettyacademy.com/";
		given()
		.log().all()
		.queryParam("key", "qaclick123")
		.queryParam("place_id","5811fd090c8d7a24d8679b028e63885b")
	.when()
		.get("/maps/api/place/get/json")
	.then()
		.log().all()
		.assertThat()
		.statusCode(200);
	
	}
}
