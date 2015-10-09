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

	/**
	 * Método que a partir de un archivo Excel levanta sus datos a objetos
	 * @param file
	 */
	public static void openExcelFile(File file) {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			HSSFSheet sheet = new HSSFWorkbook(fs).getSheetAt(0);

			// Índice de fila donde comenzarán los datos
			int firstDataRowIndex = getFirstDataRowIndex(sheet);

			// Lista de artículos exportados desde excel
			List<ItemProvider> list = importItems(sheet, firstDataRowIndex);

			System.out.println("\n\nSE ENCONTRARON UN TOTAL DE " + list.size()
					+ " ARTÍCULOS EN LA LISTA " + file.getName());
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Método que levanta las filas de un sheet a objetos
	 * 
	 * @param sheet
	 * @param firstDataRowIndex
	 * @return la lista de artículos populada
	 */
	private static List<ItemProvider> importItems(HSSFSheet sheet, int firstDataRowIndex) {
		List<ItemProvider> list = new ArrayList<ItemProvider>();
		int rows = sheet.getPhysicalNumberOfRows();
		HSSFRow row;
		// Ciclo que recorre los datos levantándolos a objeto
		for (; firstDataRowIndex < rows; firstDataRowIndex++) {
			row = sheet.getRow(firstDataRowIndex);
			ItemProvider item = importItemFromRow(row);
			if (item != null)
				list.add(item);
			System.out.println("\n" + list.get(list.size() - 1));
		}
		return list;
	}

	/**
	 * Método que calcula el índice de la primer fila que contiene data para cargar
	 * 
	 * @param sheet
	 * @return
	 */
	private static int getFirstDataRowIndex(HSSFSheet sheet) {
		int dataInitIndex;
		HSSFRow row;
		int rows = sheet.getPhysicalNumberOfRows();
		for (dataInitIndex = 0; dataInitIndex < rows; dataInitIndex++) {
			row = sheet.getRow(dataInitIndex);
			// Evalúo si la fila es la de los Encabezados
			if (row != null && evaluateHeaderRow(row)) {
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
	private static boolean evaluateHeaderRow(HSSFRow row) {
		HSSFCell firstCell = row.getCell(0);
		return (firstCell != null
				&& firstCell.getCellType() == HSSFCell.CELL_TYPE_STRING && firstCell
				.getStringCellValue().equalsIgnoreCase("codigo"));
	}

	/**
	 * Método que levanta a partir de una fila de datos el objeto ItemProvider correspondiente
	 * @param row
	 * @return
	 */
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
