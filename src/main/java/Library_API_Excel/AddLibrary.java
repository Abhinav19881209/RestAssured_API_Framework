package Library_API_Excel;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import java.util.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AddLibrary {
	
//	@Test
	public void addLibrary() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
	String resp =	given().body("{\r\n"
			+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
			+ "\"isbn\":\"bcd\",\r\n"
			+ "\"aisle\":\"227\",\r\n"
			+ "\"author\":\"John foe\"\r\n"
			+ "}").
		when().post("Library/Addbook.php").
		then().log().all().extract().response().getBody().asString();
	
		JsonPath js = new JsonPath(resp);
		js.get("ID");
		
	}
	
	@Test
	public void addLibraryByHashMap() {
		
		HashMap<String,Object> hsm = new HashMap<String,Object>();
		hsm.put("name", "Stranger Things");
		hsm.put("isbn", "STR");
		hsm.put("aisle", 1988);
		hsm.put("author", "Tulsidas Khan");
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
	String resp =	given().body(hsm).
		when().post("Library/Addbook.php").
		then().log().all().extract().response().getBody().asString();
	
		JsonPath js = new JsonPath(resp);
		 System.out.println(js.get("ID").toString());
		System.out.println(js.get("Msg").toString());
		
	}
}
