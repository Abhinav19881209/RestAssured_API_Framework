package Oauth2;

import io.restassured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class E2ETEST {
	
	//OAuth2 HTTP GET method
	
	public static void main(String[] args) {
		
		
		
		
		
		// to get the access token we need to hit the token url with below param, provided by contract(or by developers)
		String access_token_res = given()
		.queryParams("code", "")
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().log().all()
			.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(access_token_res);
		String accesstoken = js.get("access_token");
		
		// here we need access token to get the response from URL, 
		String response = given().queryParam("access_token", accesstoken)
		
		.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		
	
}
}