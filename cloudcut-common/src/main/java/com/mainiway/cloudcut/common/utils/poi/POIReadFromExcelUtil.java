package com.mainiway.cloudcut.common.utils.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POIReadFromExcelUtil {

	private Map<String, Integer> colIdxMap; // 根据title的名称获得对应的列号
	private Sheet sheet;
	private int realRows;// 实际行号
	private int startRow;// 开始的行号
	private List<String> titleNameList = new ArrayList<String>(); // 保存title

	/**
	 * @param sheet 
	 * @param headRow 标题行
	 * @param startRow 数据起始行
	 */
	public POIReadFromExcelUtil(Sheet sheet, int headRow, int startRow) {
		this.sheet = sheet;
		this.startRow = startRow;
		this.realRows = sheet.getLastRowNum();
		this.colIdxMap = getColIdxMap(sheet, headRow);
	}

	/**
	 * 获得实际的行号
	 * 
	 * @return
	 */
	public int getRealRows() {
		return realRows;
	}

	/**
	 * 获得导入数据的行数
	 * 
	 * @return
	 */
	public int getStartRows() {
		return startRow;
	}

	/**
	 * 判断excle中的title是否正确
	 * @param tls 需要判断的title
	 * @return ture(ok) false(不匹配需要核查的)
	 */
	public boolean checkTitleName(String[] tls) {

		if (tls == null || tls.length != titleNameList.size()) {
			return false;
		}

		for (int i = 0; i < tls.length; i++) {
			if (titleNameList.indexOf(tls[i].trim()) == -1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 获得title对应的列号
	 * @param sheet
	 * @param headRow
	 * @return
	 */
	private Map<String, Integer> getColIdxMap(Sheet sheet, int headRow) {
		Map<String, Integer> colIdxMap = new HashMap<String, Integer>();

		Row row = sheet.getRow(headRow);

		int lastNum = row.getLastCellNum();

		for (int i = 0; i < lastNum; i++) {
			Cell cell = row.getCell(i);
			String retStr = getValue(cell);
			if (!retStr.equals("")) {
				colIdxMap.put(retStr, i);
				titleNameList.add(retStr);
			}
		}

		return colIdxMap;
	}

	/**
	 * 根据rowNum行数和title名称获得对应的数据
	 * @param rowNum 行数
	 * @param titleName title名称
	 * @return
	 */
	public String getColContents(int rowNum, String titleName) {
		String retStr = "";
		Row row = sheet.getRow(rowNum);
		int idx = colIdxMap.get(titleName);
		Cell cell = row.getCell(idx);
		if (cell != null) {
			retStr = getValue(cell);
		}

		return retStr;
	}

	/**
	 * 根据行列获得对应数据
	 * @param row 行号
	 * @param col 列号
	 * @return
	 */
	public String getColContents(int rowNum, int colNum) {
		String retStr = "";
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(colNum);
		retStr = getValue(cell);
		return retStr;
	}

	private String getValue(Cell cell) {
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(cell.getStringCellValue().trim());
		}
		return null;
	}

}
