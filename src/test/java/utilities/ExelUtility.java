package utilities;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.text.DateFormatter;
import java.io.*;

public class ExelUtility {
    FileInputStream fi;
    FileOutputStream fo;
    XSSFWorkbook hw;
    XSSFSheet hs;
    XSSFRow row;
    XSSFCell cell;
    String path;
    File xfile;


    public ExelUtility(String path){
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fi= new FileInputStream(path);
        hw= new XSSFWorkbook(fi);
        hs=hw.getSheet(sheetName);
        hw.close();
        fi.close();
        return hs.getLastRowNum();
    }

    public int getCellCount(String sheetName, int rowNo) throws IOException {
        fi= new FileInputStream(path);
        hw=new XSSFWorkbook(fi);
        hw.close();
        fi.close();
        return hw.getSheet(sheetName).getRow(rowNo).getLastCellNum();
    }
    public String getCellData(String sheetName,int rowNo, int cellNo) throws IOException {
        fi=new FileInputStream(path);
        hw=new XSSFWorkbook(fi);
        hs=hw.getSheet(sheetName);
        row=hs.getRow(rowNo);
        cell=row.getCell(cellNo);
        String data;
        HSSFDataFormatter  df= new HSSFDataFormatter();
        try {
            data=df.formatCellValue(cell);
        }catch (Exception e){
            data="";
        }
        hw.close();
        fi.close();
        return data;
    }
 public void setCellData(String sheetName,int rowNo,int column,String data) throws IOException {
        xfile= new File(path);
        if(!xfile.exists()){
        hw=new XSSFWorkbook();//create black workbook
        fo=new FileOutputStream(path);
        hw.write(fo);
     }
        fi= new FileInputStream(path);
        hw=new XSSFWorkbook(fi);

        if(hw.getSheetIndex(sheetName)==-1){  //if sheet not exists then create new sheet
            hw.createSheet(sheetName);
            hs=hw.getSheet(sheetName);
            if(hs.getRow(rowNo)==null){ //if row not exists then create new row
                hs.createRow(rowNo);
                row=hs.getRow(rowNo);
                cell=row.createCell(column);
                cell.setCellValue(data);
                fo= new FileOutputStream(path);
                hw.write(fo);
                hw.close();
                fi.close();
                fo.close();
            }

     }

 }
}
