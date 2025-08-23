package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

//Data Provider 1

public class DataProviders {

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        String path=".\\testData\\OpencartLoginData.xlsx";
        ExelUtility xlUtil= new ExelUtility(path);
         int totalRows= xlUtil.getRowCount("Sheet1");
         int totalCols= xlUtil.getCellCount("Sheet1",1);
         String loginData[][]= new String[totalRows][totalCols];
         for(int i=1;i<=totalRows;i++){
             for(int j=0;j<totalCols;j++){
                 loginData[i-1][j]=xlUtil.getCellData("Sheet1",i,j);
             }
         }
         return loginData;

    }
}
