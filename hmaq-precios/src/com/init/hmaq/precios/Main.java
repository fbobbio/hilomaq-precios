package com.init.hmaq.precios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.init.hmaq.precios.core.Integrator;
import com.init.hmaq.precios.domain.ExcelFile;
import com.init.hmaq.precios.domain.DataInterval;

public class Main {
	
	//TODO: Implementar tests
	//TODO: Arreglar el tema del mapeo de columnas que ta feito
	//TODO: Hacer interfáz simple de uso para que configuren los intervalos visualmente
	
	
	public static void main (String[] args) {
		File f = new File("../lists/cordon.xls");
		// Set up
		int[] columnsIndexes = {0,5,1,2,6};
		ExcelFile excelFile = new ExcelFile();
		excelFile.setColumnsIndexes(columnsIndexes);
		excelFile.setFile(f);
		excelFile.setFirstDataRowIndex(12);
		
		List<DataInterval> intervals = new ArrayList<>();
		
		DataInterval i1 = new DataInterval(12,29,11);
		DataInterval i2 = new DataInterval(32,39,31);
		DataInterval i3 = new DataInterval(42,52,41);
		DataInterval i4 = new DataInterval(55,73,54);
		DataInterval i5 = new DataInterval(76,83,75);
		DataInterval i6 = new DataInterval(86,87,85);
		DataInterval i7 = new DataInterval(90,91,89);
		DataInterval i8 = new DataInterval(94,107,93);
		DataInterval i9 = new DataInterval(109,133,108);
		DataInterval i10 = new DataInterval(136,162,135);
		DataInterval i11 = new DataInterval(164,217,163);
		DataInterval i12 = new DataInterval(219,270,218);
		DataInterval i13 = new DataInterval(274,327,273);
		DataInterval i14 = new DataInterval(329,382,238);
		DataInterval i15 = new DataInterval(384,392,383);
		intervals.add(i1);
		intervals.add(i2);
		intervals.add(i3);
		intervals.add(i4);
		intervals.add(i5);
		intervals.add(i6);
		intervals.add(i7);
		intervals.add(i8);
		intervals.add(i9);
		intervals.add(i10);
		intervals.add(i11);
		intervals.add(i12);
		intervals.add(i13);
		intervals.add(i14);
		intervals.add(i15);
		excelFile.setIntervals(intervals);
		
		
		Integrator.openExcelFile(excelFile);
	}

}
