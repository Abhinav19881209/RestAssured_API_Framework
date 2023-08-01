package Library_API_Dynamic_Parameterization;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticPayloadInexternalJsonfile {
	
	
	@Test
	public void addBook() throws IOException {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all()
			.header("Content-Type","application/json")
			.body(new String (Files.readAllBytes(Paths.get("D:\\Selenium project\\API_Practice\\externalbookdata.json"))))
		.when()
			.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.log().all()
		.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
	//	Assert.assertEquals(js.getString("Msg"), "successfully added");
		String id = js.getString("ID");
		System.out.println(id);
	
	}

}
