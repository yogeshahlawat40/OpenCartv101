package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path="C:\\Users\\yoges\\eclipse-workspace\\OpenCartv101\\testData\\OpenCart_logindata.xlsx";       // taking xl file from testdata
		//".\testData\OpenCart_logindata.xlxs"
		ExcelUtility xlutil= new ExcelUtility(path);		//creating object for XLutilities
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1",1);
		
		String logindata[][] = new String[totalrows][totalcols];	//created for 2D array which can store
		
		for(int i=1;i<=totalrows;i++)				//1	//read data from xl storing in 2d array
		{
			for(int j=0;j<totalcols;j++)				// 0   i is rows j is col
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1",i ,j); 	//1,0
			}
		}
		return logindata; 	//returning 2 d array
	}
}
