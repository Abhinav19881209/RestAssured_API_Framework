package POJO;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

public class ResapiUsersDeserialization {
	
	
	@Test
	public void validateResUserresponse () {
			
		RestAssured.baseURI = "https://reqres.in";
		
		userslist rd = given()
		.when().get("/api/users?page=2").as(userslist.class);
		
		System.out.println(rd.getPage());
		
		List<datalist> userlist = rd.getData();
		
		for(int i =0;i<userlist.size();i++) {
			
			System.out.println(userlist.get(i).getEmail());
			
		}
		

	}
	
}
