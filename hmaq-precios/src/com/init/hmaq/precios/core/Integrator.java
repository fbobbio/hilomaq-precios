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

import com.init.hmaq.precios.domain.DataInterval;
import com.init.hmaq.precios.domain.ExcelFile;
import com.init.hmaq.precios.domain.ItemProvider;
import com.init.hmaq.utils.SheetUtils;

/**
 * Clase que levanta archivos excel y los lleva a objetos
 * 
 * @author fbobbio
 * 
 */
public class Integrator {

	/**
	 * Método que a partir de un archivo Excel levanta sus datos a objetos
	 * 
	 * @param excelFile
	 */
	public static List<ItemProvider> openExcelFile(ExcelFile excelFile) {
		// Lista de artículos exportados desde excel
		List<ItemProvider> list = importItems(excelFile);

		System.out.println("\n\nSE ENCONTRARON UN TOTAL DE " + list.size()
				+ " ARTÍCULOS EN LA LISTA " + excelFile.getFile().getName());
		return list;
	}

	/**
	 * Método que levanta las filas de un sheet a objetos
	 * 
	 * @param excelFile
	 * @return la lista de artículos populada
	 */
	private static List<ItemProvider> importItems(ExcelFile excelFile) {

		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					excelFile.getFile()));
			HSSFSheet sheet = new HSSFWorkbook(fs).getSheetAt(0);

			List<ItemProvider> list = new ArrayList<ItemProvider>();
			HSSFRow row;

			// Ciclo que recorre los datos levantándolos a objeto
			for (DataInterval interval : excelFile.getIntervals()) {
				for (int i = interval.getInitIndex() - 1; i < interval
						.getEndIndex(); i++) {
					row = sheet.getRow(i);
					ItemProvider item = importItemFromRow(row,
							excelFile.getColumnsIndexes());
					if (item != null) {
						list.add(item);
						//System.out.println("\n" + list.get(list.size() - 1));
					}
				}
			}
			return list;
		} catch (Exception ioe) {
			ioe.printStackTrace();
			return null;
		}
	}

	/**
	 * Método que calcula el índice de la primer fila que contiene data para
	 * cargar
	 * 
	 * @param sheet
	 * @return
	 */
	private static int getFirstDataRowIndex(HSSFSheet sheet, String mainHeader) {
		int dataInitIndex;
		HSSFRow row;
		int rows = sheet.getPhysicalNumberOfRows();
		for (dataInitIndex = 0; dataInitIndex < rows; dataInitIndex++) {
			row = sheet.getRow(dataInitIndex);
			// Evalúo si la fila es la de los Encabezados
			if (row != null && evaluateHeaderRow(row, mainHeader)) {
				dataInitIndex++;
				break;
			}
		}
		return dataInitIndex;
	}

	/**
	 * Método que calcula la cantidad de columnas que tiene un sheet de excel
	 * 
	 * @param sheet
	 * @param rows
	 * @return
	 */
	private static int getCols(HSSFSheet sheet, int rows) {

		int tmp = 0;
		int cols = 0;
		HSSFRow row;

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
		return cols;
	}

	/**
	 * Método que evalua si una fila comienza con una celda en posición "0" y
	 * texto "Codigo" ignorando mayúsculas/minúsculas
	 * 
	 * @param row
	 * @return
	 */
	private static boolean evaluateHeaderRow(HSSFRow row, String mainHeader) {
		HSSFCell firstCell = row.getCell(0);
		return (firstCell != null
				&& firstCell.getCellType() == HSSFCell.CELL_TYPE_STRING && firstCell
				.getStringCellValue().equalsIgnoreCase(mainHeader));
	}

	/**
	 * Método que levanta a partir de una fila de datos el objeto ItemProvider
	 * correspondiente
	 * 
	 * @param row
	 * @return
	 */
	private static ItemProvider importItemFromRow(HSSFRow row, int[] cols) {
		ItemProvider ret = null;
		if (row != null
				&& row.getCell(cols[0]) != null
				&& row.getCell(cols[0]).getCellType() != HSSFCell.CELL_TYPE_BLANK) {
			ret = new ItemProvider();
			ret.setCode(SheetUtils.getStringValueFromCell(row.getCell(cols[0])).replaceAll("'", ""));
			ret.setDescription(SheetUtils.getStringValueFromCell(row.getCell(cols[2])));
			ret.setPresentation(SheetUtils.getStringValueFromCell(row.getCell(cols[3])));
			ret.setBasePrice(row.getCell(cols[1]).getNumericCellValue());
			return ret;
		}
		return null;
	}
}
