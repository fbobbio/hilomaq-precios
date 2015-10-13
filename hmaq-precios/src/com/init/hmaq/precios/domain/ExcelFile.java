package com.init.hmaq.precios.domain;

import java.io.File;
import java.util.List;

public class ExcelFile {

	/** El archivo excel completo */
	private File file;
	/** La primer fila con información */
	private int firstDataRowIndex;
	/** Los índices de las columnas que matchean con los atributos del ItemProvider
	 * 
	 *  0 = code
	 *  1 = basePrice
	 *  2 = description
	 *  3 = presentation
	 *  4 = minSell
	 *  5 = unitPrice
	 *  6 = unitPriceFormula
	 *  
	 * */
	private int [] columnsIndexes;
	/** Lista de intervalos de interés */
	private List<DataInterval> intervals;
	
	public ExcelFile() {
		super();
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public int getFirstDataRowIndex() {
		return firstDataRowIndex;
	}
	public void setFirstDataRowIndex(int firstDataRowIndex) {
		this.firstDataRowIndex = firstDataRowIndex;
	}
	
	/** Los índices de las columnas que matchean con los atributos del ItemProvider
	 * 
	 *  0 = code
	 *  1 = basePrice
	 *  2 = description
	 *  3 = presentation
	 *  4 = minSell
	 *  5 = unitPrice
	 *  6 = unitPriceFormula
	 *  
	 *  @return
	 * */
	public int[] getColumnsIndexes() {
		return columnsIndexes;
	}
	public void setColumnsIndexes(int[] columnsIndexes) {
		this.columnsIndexes = columnsIndexes;
	}
	public List<DataInterval> getIntervals() {
		return intervals;
	}
	public void setIntervals(List<DataInterval> intervals) {
		this.intervals = intervals;
	}

}
