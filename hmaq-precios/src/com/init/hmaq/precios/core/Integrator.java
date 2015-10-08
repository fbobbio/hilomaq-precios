package com.init.hmaq.precios.core;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.init.hmaq.precios.domain.ItemProvider;

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

			// This trick ensures that we get the data properly even if it
			// doesn't start from first few rows
			for (int i = 0; i < 10 || i < rows; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if (tmp > cols)
						cols = tmp;
				}
			}

			// Índice de fila donde comenzarán los datos
			int dataInitIndex;
			for (dataInitIndex = 0; dataInitIndex < rows; dataInitIndex++) {
				row = sheet.getRow(dataInitIndex);
				// Evalúo si la fila es la de los Encabezados
				if (row != null && evaluateHeaderRow(row)) {
					dataInitIndex++;
					break;
				}
			}

			// Lista de artículos exportados desde excel
			List<ItemProvider> list = new ArrayList<ItemProvider>();

			// Ciclo que recorre los datos levantándolos a objeto
			for (; dataInitIndex < rows; dataInitIndex++) {
				row = sheet.getRow(dataInitIndex);
				ItemProvider item = importItemFromRow(row);
				if (item != null)
					list.add(item);
				System.out.println("\n" + list.get(list.size() - 1));
			}

			System.out.println("\n\nSE ENCONTRARON UN TOTAL DE " + list.size()
					+ " ARTÍCULOS EN LA LISTA " + file.getName());
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Método que evalua si una fila comienza con una celda en posición "0" y
	 * texto "Codigo" ignorando mayúsculas/minúsculas
	 * 
	 * @param row
	 * @return
	 */
	private static boolean evaluateHeaderRow(HSSFRow row) {
		HSSFCell firstCell = row.getCell(0);
		return (firstCell != null
				&& firstCell.getCellType() == HSSFCell.CELL_TYPE_STRING && firstCell
				.getStringCellValue().equalsIgnoreCase("codigo"));
	}

	private static ItemProvider importItemFromRow(HSSFRow row) {
		ItemProvider ret = null;
		if (row != null && row.getCell(0) != null && row.getCell(0).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
			ret = new ItemProvider();
			ret.setCode(row.getCell(0).getStringCellValue().replaceAll("'", ""));
			ret.setDescription(row.getCell(1).getStringCellValue());
			ret.setPresentation(row.getCell(2).getStringCellValue());
			ret.setBasePrice(row.getCell(3).getNumericCellValue());
			return ret;
		}
		return null;
	}
}
