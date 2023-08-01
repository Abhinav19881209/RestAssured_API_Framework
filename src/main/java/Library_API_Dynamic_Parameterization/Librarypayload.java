package Library_API_Dynamic_Parameterization;

public class Librarypayload {
	
	
	public static String addBook(String isbn, String aisle ) {
		
		String book = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
		
		return book;
	}

	public static String deleteBook(String isbn, String aisle ) {
			
		String delbook = "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+isbn+""+aisle+"\"\r\n"
				+ " \r\n"
				+ "} ";
		
		return delbook;
	}
}
