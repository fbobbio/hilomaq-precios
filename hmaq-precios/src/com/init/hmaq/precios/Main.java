package com.init.hmaq.precios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.init.hmaq.precios.core.Integrator;
import com.init.hmaq.precios.domain.ExcelFile;
import com.init.hmaq.precios.domain.DataInterval;
import com.init.hmaq.precios.domain.ItemProvider;

public class Main {
	
	//TODO: Implementar tests
	//TODO: Arreglar el tema del mapeo de columnas que ta feito
	//TODO: Hacer interfáz simple de uso para que configuren los intervalos visualmente
	
	
	public static void main (String[] args) {
		// Lista completa de artículos importados desde excel
		List<ItemProvider> fullList = new ArrayList<>();
		
		// Set up La Cordobesa y procesamiento
		File fCordobesa = new File("../lists/cordobesa.xls");
		int[] ciCordobesa = {0,3,1,2};
		
		List<DataInterval> intervalsCordobesa = new ArrayList<>();
		intervalsCordobesa.add(new DataInterval(8,547));
		
		fullList.addAll(processExcelFile(fCordobesa, intervalsCordobesa, ciCordobesa));
		
		// Set up Cordón de Oro
		File fCordon = new File("../lists/cordon.xls");
		int[] ciCordon = {0,5,1,2,6};
		
		List<DataInterval> intervalsCordonOro = new ArrayList<>();
		intervalsCordonOro.add(new DataInterval(12,29,11));
		intervalsCordonOro.add(new DataInterval(32,39,31));
		intervalsCordonOro.add(new DataInterval(42,52,41));
		intervalsCordonOro.add(new DataInterval(55,73,54));
		intervalsCordonOro.add(new DataInterval(76,83,75));
		intervalsCordonOro.add(new DataInterval(86,87,85));
		intervalsCordonOro.add(new DataInterval(90,91,89));
		intervalsCordonOro.add(new DataInterval(94,107,93));
		intervalsCordonOro.add(new DataInterval(109,133,108));
		intervalsCordonOro.add(new DataInterval(136,162,135));
		intervalsCordonOro.add(new DataInterval(164,217,163));
		intervalsCordonOro.add(new DataInterval(219,270,218));
		intervalsCordonOro.add(new DataInterval(274,327,273));
		intervalsCordonOro.add(new DataInterval(329,382,238));
		intervalsCordonOro.add(new DataInterval(384,392,383));
		
		fullList.addAll(processExcelFile(fCordon, intervalsCordonOro, ciCordon));
		
		System.out.println("\n\nSE PROCESARON EN TOTAL " + fullList.size() + " ARTÍCULOS DESDE PLANILLAS EXCEL");
	}
	
	/** 
	 * Método que se encarga de procesar un archivo excel con sus correspondientes intervalos
	 * 
	 * @param excelFile
	 * @param intervals
	 * @param columnsIndexes
	 */
	public static List<ItemProvider> processExcelFile(File f, List<DataInterval> intervals, int[] columnsIndexes) {
		
		ExcelFile excelFile = new ExcelFile();
		excelFile.setColumnsIndexes(columnsIndexes);
		excelFile.setFile(f);
		excelFile.setFirstDataRowIndex(12);
		
		excelFile.setIntervals(intervals);		
		
		return Integrator.openExcelFile(excelFile);
		
	}

}
