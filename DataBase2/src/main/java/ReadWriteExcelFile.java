

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcelFile
{

  public static void readXLSFile() throws IOException
  {
    InputStream ExcelFileToRead = new FileInputStream("C:/Test.xls");
    HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);

    HSSFSheet sheet = wb.getSheetAt(0);
    HSSFRow row;
    HSSFCell cell;

    Iterator rows = sheet.rowIterator();

    while (rows.hasNext())
    {
      row = (HSSFRow) rows.next();
      Iterator cells = row.cellIterator();

      while (cells.hasNext())
      {
        cell = (HSSFCell) cells.next();

        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        {
          System.out.print(cell.getStringCellValue() + " ");
        }
        else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        {
          System.out.print(cell.getNumericCellValue() + " ");
        }
        else
        {
          // U Can Handel Boolean, Formula, Errors
        }
      }
      System.out.println();
    }
  }

  public static void writeXLSFile() throws IOException
  {

    String excelFileName = "Test.xls";// name of excel file

    String sheetName = "Sheet1";// name of sheet

    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = wb.createSheet(sheetName);

    // iterating r number of rows
    for (int r = 0; r < 5; r++)
    {
      HSSFRow row = sheet.createRow(r);

      // iterating c number of columns
      for (int c = 0; c < 5; c++)
      {
        HSSFCell cell = row.createCell(c);

        cell.setCellValue("Cell " + r + " " + c);
      }
    }

    FileOutputStream fileOut = new FileOutputStream(excelFileName);

    // write this workbook to an Outputstream.
    wb.write(fileOut);
    fileOut.flush();
    fileOut.close();
  }

  public static void readXLSXFile() throws IOException
  {
    InputStream ExcelFileToRead = new FileInputStream("Test.xlsx");
    XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

    XSSFSheet sheet = wb.getSheetAt(0);
    XSSFRow row;
    XSSFCell cell;

    Iterator rows = sheet.rowIterator();

    while (rows.hasNext())
    {
      row = (XSSFRow) rows.next();
      Iterator cells = row.cellIterator();
      while (cells.hasNext())
      {
        cell = (XSSFCell) cells.next();

        if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
        {
          System.out.print(cell.getStringCellValue() + " ");
        }
        else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
        {
          System.out.print(cell.getNumericCellValue() + " ");
        }
        else
        {
          // U Can Handel Boolean, Formula, Errors
        }
      }
      System.out.println();
    }

  }

  public static void writeXLSXFile(ArrayList<Student> s) throws IOException
  {
	
    String excelFileName = "Test.xlsx";// name of excel file

    String sheetName = "Sheet1";// name of sheet

    XSSFWorkbook wb = new XSSFWorkbook();
    XSSFSheet sheet = wb.createSheet(sheetName);

    // iterating r number of rows
    XSSFRow row0 = sheet.createRow(0);
    for(int c=0;c<3;c++) {
    	XSSFCell cell=row0.createCell(c);
    	switch(c) {
    	case 0:cell.setCellValue("stuNo");break;
    	case 1:cell.setCellValue("stuName");break;
    	case 2:cell.setCellValue("questionNos");break;
    	default:break;
    	}
    }
    
    for (int r = 0; r < s.size(); r++)
    {
      XSSFRow row = sheet.createRow(r+1);
      // iterating c number of columns
      for (int c = 0; c < 3; c++)
      {
    	XSSFCell cell=row.createCell(c);
    	switch(c) {
	      	case 0:cell.setCellValue(s.get(r).getStuNo());break;
	      	case 1:cell.setCellValue(s.get(r).getStuName());break;
	      	case 2:cell.setCellValue(s.get(r).getQuesNos());break;
	      	default:break;
      	}
      }
     
    }

    FileOutputStream fileOut = new FileOutputStream(excelFileName);

    // write this workbook to an Outputstream.
    wb.write(fileOut);
    fileOut.flush();
    fileOut.close();
  }

  public static void main(String[] args) throws IOException
  {
	ArrayList<Student> s=GetResult.readDB();
    writeXLSXFile(s);
  }
}