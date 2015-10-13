package com.init.hmaq.precios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.init.hmaq.precios.core.Integrator;
import com.init.hmaq.precios.domain.ExcelFile;
import com.init.hmaq.precios.domain.DataInterval;

public class Main {
	
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
		DataInterval i2 = new DataInterval(32,38,31);
		DataInterval i3 = new DataInterval(42,52,41);
		intervals.add(i1);
		intervals.add(i2);
		intervals.add(i3);
		excelFile.setIntervals(intervals);
		
		
		Integrator.openExcelFile(excelFile);
	}

}
