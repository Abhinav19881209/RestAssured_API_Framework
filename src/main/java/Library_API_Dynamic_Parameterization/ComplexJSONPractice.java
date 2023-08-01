package Library_API_Dynamic_Parameterization;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class ComplexJSONPractice {

	public static void main(String[] args) {
			
		RestAssured.baseURI = "https://reqres.in/";
		
	String response = 	given()
			.queryParam("page", "2")
		.when()
			.get("api/users")
		.then()
			.extract().response().asString();
	
	JsonPath js = new JsonPath(response);
	
	System.out.println(js.getInt("data.size()"));//js.get("data.count()");
	
	System.out.println(js.getString("per_page"));
	
	//System.out.println(js.getString("data.email"));
	
	String first_name = "Byron";
	
	for(int i = 0; i<js.getInt("data.size()");i++) {
		
		if(js.getString("data.first_name["+i+"]").equalsIgnoreCase(first_name)) {
			
			System.out.println(js.getInt("data.id["+i+"]"));
			System.out.println(js.getString("data.avatar["+i+"]"));
			
			
		}
		
	}

	}

}
