package com.cfeindia.b2bserviceapp.exelview;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;

public class DistributorFundTransReport extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Distributor Fund Trans Report Summary.xlsx");
		
		List<DistributorBalanceTransferLog> list = (List<DistributorBalanceTransferLog>) model
				.get("list");
		HSSFSheet sheet = workbook.createSheet("Distributor Fund Transfer Report");
		sheet.setDefaultColumnWidth(20);
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.GREEN.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("DATE/TIME");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("TID");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("TRANSACTION NAME");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("TRANSACTION TYPE");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("AMOUNT");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("BALANCE");
		header.getCell(5).setCellStyle(style);

		int rowCount = 1;

		for (DistributorBalanceTransferLog customerstmt : list) {

			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(
					customerstmt.getCreatedAt().toString());
			aRow.createCell(1).setCellValue(customerstmt.getTransactionId());
			aRow.createCell(2).setCellValue(
					customerstmt.getDisplayTransactionName());
			aRow.createCell(3).setCellValue(customerstmt.getTransferType());
			aRow.createCell(4).setCellValue(customerstmt.getTransferAmount());
			aRow.createCell(5).setCellValue(
					"AdUnit Bal: Rs." + customerstmt.getNewB2bAdUnitBal()+ "- "
							+ "B2b Bal: Rs." + customerstmt.getNewB2bCurrBal());

		}
	}

}
