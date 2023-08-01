 package ecommersAPI;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class eCommerceAPITest {

	public static void main(String[] args) {
		
	RequestSpecification reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
															.setContentType(ContentType.JSON).build();
	
	LoginRequestpojo lreqp = new LoginRequestpojo();
	lreqp.setUserEmail("abhinav.k@gmail.com");
	lreqp.setUserPassword("Abhinav@09121988");
	
	LoginResponsepojo lresp = new LoginResponsepojo();
	
	RequestSpecification resspec = given().relaxedHTTPSValidation().log().all().spec(reqspec).body(lreqp);
	lresp =	resspec.when().post("/api/ecom/auth/login")
			.then().log().all().extract().response().as(LoginResponsepojo.class);

	System.out.println(lresp.getToken());
	String token = lresp.getToken();
	System.out.println(lresp.getUserId());
	String userId = lresp.getUserId();
	System.out.println(lresp.getMessage());
	
	// add product for TC
	
	RequestSpecification resSpecbaseAddprod = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("Authorization", token).build();
	
	RequestSpecification resSpecAddprod =given().log().all().spec(resSpecbaseAddprod).param("productName", "Shoes").
	param("productAddedBy", userId).
	param("productCategory", "fashion").
	param("productSubCategory", "shirt").
	param("productPrice", 15000).
	param("productDescription", "Addias Originals").param("productFor", "women")
	.multiPart("productImage",new File("C:\\Users\\ABHINAV\\OneDrive\\Desktop\\API Learning\\Shoe200.png"));
	
	String addproductresponse = resSpecAddprod.when().post("/api/ecom/product/add-product")
	.then().log().all().extract().response().asString();
	JsonPath js= new JsonPath(addproductresponse);
	String productId = js.get("productId");
	
	System.out.println(productId);
	
	
	// to places an order
	RequestSpecification reqSpecToAddOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
			.addHeader("Authorization", token).build();

	PlaceOrderpojo placeorder = new PlaceOrderpojo();
	
	OrderDetailPojo orderdetails = new OrderDetailPojo();
	orderdetails.setCountry("India");
	orderdetails.setProductOrderedId(productId);
	
	ArrayList<OrderDetailPojo> al = new ArrayList<OrderDetailPojo>();
	al.add(orderdetails);
	
	placeorder.setOrders(al);
	
	RequestSpecification reqSpecToplaceOrder = given().log().all().spec(reqSpecToAddOrder).body(placeorder);
	
	String res =reqSpecToplaceOrder.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
	
	JsonPath js1= new JsonPath(res);
		System.out.println(js1.get("message"));

		////delete the product 
		System.out.println("Deleting the product=====================");
		RequestSpecification reqSpecToDeleteOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
				.addHeader("Authorization", token).build();
		
		String deleteresponse = given().log().all().spec(reqSpecToDeleteOrder)
		.when().delete("/api/ecom/product/delete-product/"+productId+"")
		.then().log().all().extract().response().asString();
		
		JsonPath js2= new JsonPath(deleteresponse);
		System.out.println(js2.get("message"));
	
	}
	
	
	
}
