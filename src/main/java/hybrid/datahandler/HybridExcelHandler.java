package hybrid.datahandler;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HybridExcelHandler {

	public Workbook workbook = null;
	public Sheet sheet = null;
	public int numberofRows = 0;

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public void setWorkbookAndSheet(Workbook workbook, Sheet sheet) {
		this.workbook = workbook;
		this.sheet = sheet;
	}

	public Workbook getWorkbook(String fileName) {

		FileInputStream inputStream;

		try {
			if (fileName != null) {
				inputStream = new FileInputStream(fileName);

				if ((fileName.trim().toLowerCase().endsWith("xlsx"))
						|| ((fileName.trim().toLowerCase().endsWith("xlsm")))) {
					workbook = new XSSFWorkbook(inputStream);
				} else if (fileName.toLowerCase().trim().endsWith("xls")) {
					workbook = new HSSFWorkbook(inputStream);
				} else {
					throw new Exception("Invalid file format!");
				}

			} else {
				throw new Exception("Input filename is null!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occured : " + e + e.getMessage());
		}
		return workbook;
	}

	public Sheet getSheetFromWorkbook(String fileName, String sheetname) {

		workbook = getWorkbook(fileName);

		try {

			if (sheetname.trim() != null) {
				sheet = workbook.getSheet(sheetname);

				if (sheet == null) {
					throw new Exception("There is no sheet name '" + sheetname + "' in " + fileName + "  workbook !");
				}
			} else {
				throw new Exception("Input sheetname is null!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occured : " + e + e.getMessage());
		}

		return sheet;
	}

	public Sheet getSheetFromWorkbook(Workbook workbook, String sheetname) {

		try {
			if (workbook != null) {
				if (sheetname.trim() != null) {
					sheet = workbook.getSheet(sheetname);

					if (sheet == null) {
						throw new Exception("There is no sheet name '" + sheetname + "' in " + workbook.toString()
								+ "  workbook !");
					}
				} else {
					throw new Exception("Input sheetname is null!");
				}
			} else {
				throw new Exception("Input workbook is null!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occured : " + e + e.getMessage());
		}

		return sheet;
	}

	public Sheet getSheetFromWorkbook(String sheetname) {

		try {
			if (workbook != null) {
				if (sheetname.trim() != null) {
					sheet = workbook.getSheet(sheetname);

					if (sheet == null) {
						throw new Exception("There is no sheet name '" + sheetname + "' in " + workbook.toString()
								+ "  workbook !");
					}
				} else {
					throw new Exception("Input sheetname is null!");
				}
			} else {
				throw new Exception("Input workbook is null!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occured : " + e + e.getMessage());
		}

		return sheet;
	}

	public int getNumberOfRows(String fileName, String sheetname) {

		try {

			sheet = getSheetFromWorkbook(fileName, sheetname);
			if (sheet != null) {
				numberofRows = sheet.getLastRowNum() + 1;
			} else {
				throw new Exception("Input sheetname is null!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occured : " + e + e.getMessage());
		}
		return numberofRows;
	}

	public int getNumberOfRows(Workbook workbook, String sheetname) {

		try {

			sheet = getSheetFromWorkbook(workbook, sheetname);
			if (sheet != null) {
				numberofRows = sheet.getLastRowNum() + 1;
			} else {
				throw new Exception("Input sheetname is null!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occured : " + e + e.getMessage());
		}
		return numberofRows;
	}

	public int getNumberOfRows(Sheet sheetname) {

		try {
			if (sheetname != null) {
				numberofRows = sheetname.getLastRowNum() + 1;
			} else {
				throw new Exception("Input sheetname is null!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occured : " + e + e.getMessage());
		}
		return numberofRows;
	}

}
