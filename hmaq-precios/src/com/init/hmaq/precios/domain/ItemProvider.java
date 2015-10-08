package com.init.hmaq.precios.domain;

public class ItemProvider {
	
	public String code;
	public double basePrice;
	public String description;
	public String presentation;
	public String minSell;
	public double unitPrice;
	public String unitPriceFormula;
	
	public String toString() {
		return "Código: " + code + "\nPrecio base: " + basePrice + "\nDescripción: " + description + "\nPresentación: " + presentation;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPresentation() {
		return presentation;
	}
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	public String getMinSell() {
		return minSell;
	}
	public void setMinSell(String minSell) {
		this.minSell = minSell;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getUnitPriceFormula() {
		return unitPriceFormula;
	}
	public void setUnitPriceFormula(String unitPriceFormula) {
		this.unitPriceFormula = unitPriceFormula;
	}

}
