package com.init.hmaq.precios.domain;

/**
 * Clase que representa un intervalo de datos de valor, donde se encontrarán los ItemProviders dentro de una sheet excel
 * A su vez contiene las categorías de productos a las cuales se están levantand, si así hubiese.
 * 
 * @author fbobbio
 *
 */
public class DataInterval {

	/** Inicio del intervalo de datos de interés */
	private int initIndex;
	/** Fin del intervalo de datos de interés */
	private int endIndex;
	/** Fila donde se encuentra la categoría */
	private int categoryIndex;
	
	public DataInterval(int initIndex, int endIndex, int categoryIndex) {
		super();
		this.initIndex = initIndex;
		this.endIndex = endIndex;
		this.categoryIndex = categoryIndex;
	}
	
	public int getInitIndex() {
		return initIndex;
	}
	public void setInitIndex(int initIndex) {
		this.initIndex = initIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public int getCategoryIndex() {
		return categoryIndex;
	}
	public void setCategoryIndex(int categoryIndex) {
		this.categoryIndex = categoryIndex;
	}
}
