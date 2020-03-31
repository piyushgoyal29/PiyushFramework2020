package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelSheet 
{
	XSSFWorkbook workbook;
	
	public Object[][] getCellData(String filePath, String sheetName) throws IOException
	{
		//To read any external file we need to create an object of FileInputStream and pass the excel file path. 
		FileInputStream fis = new FileInputStream(filePath);
		
		//To access .xlsx file we need to create an Object of XSSFWorkbook and pass the object of FileInputStream
		workbook = new XSSFWorkbook(fis);

		//To access the desired sheet inside the workbook, we need to call getSheet() method present inside the XSSFWorkbook object and pass the sheet name.
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		//To get row count we need to call getLastRowNum() present inside the XSSFSheet object.
		int rowCount = sheet.getLastRowNum();
		rowCount = rowCount +1; 
		/*We have to increment the row count by +1, because the row count provided by getLastRowNum() method starts from Zero.
		 i.e If the count of total rows is 6, then getLastRowNum() method provides count as 5.	*/
		
		//To get cell / column count we need to call getLastCellNum() method present inside the XSSFSheet object.
		short cellCount = sheet.getRow(0).getLastCellNum();
		
		//Declaring an Array of Object to store the data inside the array	
		Object[][] data = new Object[rowCount][cellCount];
		
		int cellType=0;
		
		//Create 2 for loops- One for row and another for column and then by iterating through the loop fetch the data from the excel
		for(int i=0; i<rowCount; i++) 
		{
			for(int j=0; j<cellCount;j++)
			{
				try
				{
					/* We have used try-catch block because if the cell is Blank,
					then getCellType() method will throw NULL POINTER EXCEPTION.*/
					cellType = sheet.getRow(i).getCell(j).getCellType();				
				}
				catch(Exception e)
				{
					cellType = 3;
					/* Catch block will only be executed when the cell is BLANK. 
					 * Now our below "Cell.CELL_TYPE_BLANK" if condition only gets satisfied
					 *  when the integer value is 3.
					 * So we have hardcoded cellType = 3 */
				}
				if(cellType == Cell.CELL_TYPE_STRING)
				{
					data[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
				}
				else if(cellType == Cell.CELL_TYPE_NUMERIC)
				{
					data[i][j] = sheet.getRow(i).getCell(j).getNumericCellValue();
				}
				else if(cellType == Cell.CELL_TYPE_BLANK)
				{
					data[i][j] = "";
				}
				else if(cellType == Cell.CELL_TYPE_BOOLEAN)
				{
					data[i][j] = sheet.getRow(i).getCell(j).getBooleanCellValue();
				}
			}
			
		}
		return data;
	}
}
