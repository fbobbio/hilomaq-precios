package com.init.hmaq.precios;

import java.io.File;

import com.init.hmaq.precios.core.Integrator;

public class Main {
	
	public static void main (String[] args) {
		File f = new File("../lists/cordon.xls");
		Integrator.openExcelFile(f);
	}

}
