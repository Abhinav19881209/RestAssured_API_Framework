package Library_API_Dynamic_Parameterization;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DynamicJson {
	
	//passing dynamic payload value
//	@Test
	public void addBook() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all()
			.header("Content-Type","application/json")
			.body(Librarypayload.addBook("Rambo","4"))
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
	
	//passing dynamic payload value
		@Test(dataProvider = "booktestdata")
		public void addmultipleBook(String isbn, String aisle) {
			
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			
			String response = given().log().all()
				.header("Content-Type","application/json")
				.body(Librarypayload.addBook(isbn,aisle))
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
		
		@Test(dataProvider = "booktestdata")
		public void deleteBook(String isbn, String aisle) {
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			
			 given().log().all()
				.header("Content-Type","application/json")
				.body(Librarypayload.deleteBook(isbn,aisle))
			.when()
				.post("/Library/DeleteBook.php")
			.then().assertThat().statusCode(200)
			.log().all();
			
		}
		
		
	@DataProvider (name = "booktestdata")
	public Object[][] bookTestData() {
		
		return new Object[][] {{"Test","101"},{"Test","202"},{"Test","303"}};
	}
	
}
//,{"333","zsrgaw"},{"345245","asef"}