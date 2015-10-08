package com.init.hmaq.precios.core;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Clase que levanta archivos excel y los lleva a objetos
 * 
 * @author fbobbio
 *
 */
public class Integrator {
	
	public static void openExcelFile(File file) {
		try {
		    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
		    HSSFWorkbook wb = new HSSFWorkbook(fs);
		    HSSFSheet sheet = wb.getSheetAt(0);
		    HSSFRow row;
		    HSSFCell cell;

		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();

		    int cols = 0; // No of columns
		    int tmp = 0;

		    // This trick ensures that we get the data properly even if it doesn't start from first few rows
		    for(int i = 0; i < 10 || i < rows; i++) {
		        row = sheet.getRow(i);
		        if(row != null) {
		            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
		            if(tmp > cols) cols = tmp;
		        }
		    }

		    for(int r = 0; r < rows; r++) {
		        row = sheet.getRow(r);
		        if(row != null) {
		        	// TODO: Revisar cuándo comienzan las columnas de datos y empezar a cargar los objetos ahí
		            for(int c = 0; c < cols; c++) {
		                cell = row.getCell(c);
		                if(cell != null) {
		                	switch (cell.getCellType()) {
			                	case HSSFCell.CELL_TYPE_NUMERIC:
				                	System.out.println(cell.getNumericCellValue());
				                	break;
			                	case HSSFCell.CELL_TYPE_STRING:
				                	System.out.println(cell.getStringCellValue());
				                	break;
			                	case HSSFCell.CELL_TYPE_FORMULA:
				                	System.out.println("--Fórmula--: " + cell.getCellFormula());
				                	break;
				                default:
				                	System.out.println(cell.getStringCellValue());		                	
		                	}
		                }
		            }
		        }
		    }
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
	}
	

}
