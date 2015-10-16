package com.init.hmaq.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class SheetUtils {

	public static String getStringValueFromCell(HSSFCell cell) {
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
			case HSSFCell.CELL_TYPE_BLANK:
				return cell.getStringCellValue();
			case HSSFCell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue() + "";
			case HSSFCell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue() + "";
			}
		}
		return "";
	}

}
