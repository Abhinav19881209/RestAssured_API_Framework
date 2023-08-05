package ApachePOI;

import java.io.IOException;
import java.util.ArrayList;

public class callTestData {

	public static void main(String[] args) throws IOException {
		
		POIPratice pp = new POIPratice();
		
		ArrayList<String> getdataal = pp.getData("Sheet 1", "Test case", "Add profile");
		
		System.out.println(getdataal.get(0));
		System.out.println(getdataal.get(1));
		System.out.println(getdataal.get(2));
		System.out.println(getdataal.get(3));
	}

}
