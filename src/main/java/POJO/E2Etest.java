package POJO;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

public class E2Etest {

		public static void main(String[] args) {
			
			RestAssured.baseURI = "https://rahulshettyacademy.com/";
			POJOGetMap	getres = given()
				.queryParam("key", "qaclick123")
				.queryParam("place_id","5811fd090c8d7a24d8679b028e63885b")
			.when()
				.get("/maps/api/place/get/json").as(POJOGetMap.class);
			
			System.out.println(getres.getName());
			System.out.println(getres.getWebsite());
			System.out.println(getres.getLocation().getLongitude());

			}

	}

