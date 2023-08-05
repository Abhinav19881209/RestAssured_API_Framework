package ApachePOI;

import java.io.*;
import java.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIPratice {
		
	public ArrayList<String> getData(String sheetname,String Testcasename , String testcase) throws IOException {
		
		ArrayList<String> al = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream("C://Users//ABHINAV//OneDrive//Desktop//POI_API.xlsx");
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		int sheets = wb.getNumberOfSheets();
		
		//System.out.println(sheets);
		//name of sheets
		
		for(int i = 0;i<sheets;i++) {
			
			if (wb.getSheetName(i).equalsIgnoreCase(sheetname)) {
				
				XSSFSheet sheet = wb.getSheetAt(i);
				
			Iterator<Row> rows = sheet.rowIterator();
			Row firstrow=rows.next();
			Iterator<Cell> cell =firstrow.cellIterator();
			
			int k = 0;
			int column =0;
			
			while(cell.hasNext()) {
				
				Cell value = cell.next();
				
				if(value.getStringCellValue().equalsIgnoreCase(Testcasename)) {
					
					 column = k;
				}
				
				k++;
			}
			System.out.println(column);
			
			while(rows.hasNext()) {
				
				Row r =rows.next();
				
				if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcase)) {
					
					Iterator<Cell>	c = r.cellIterator();
					
					while(c.hasNext()) {
						
						//System.out.println(c.next().getStringCellValue()); 
						al.add(c.next().getStringCellValue());
						
					}
				}
				
			}
			
			}
		
		}
		
		return al;
	}
	
}
