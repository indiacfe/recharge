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

import com.cfeindia.b2bserviceapp.dto.CompanyDistributorTransactionDto;

public class AdminTotalFundTransferReport extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Admin Fund Transfer Summary.xlsx");
		List<CompanyDistributorTransactionDto> list = (List<CompanyDistributorTransactionDto>) model.get("list");
		HSSFSheet sheet = workbook.createSheet("Total Fund Transfer");
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
		header.createCell(0).setCellValue("SN");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("DATE/TIME");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("DISTRIBUTOR NAME");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("TRANSACTION ID");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("AMOUNT");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("PREVIOUS BALANCE");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("NEW BALANCE");
		header.getCell(6).setCellStyle(style);

		

		int rowCount = 1;

		for (CompanyDistributorTransactionDto customerstmt : list) {
			int sn = rowCount;
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(sn);
			aRow.createCell(1).setCellValue(
					customerstmt.getCreatedAt().toString());
			aRow.createCell(2).setCellValue(customerstmt.getUserName());
			aRow.createCell(3).setCellValue(
					customerstmt.getTransId());
			aRow.createCell(4).setCellValue(customerstmt.getTransferAmount());
			aRow.createCell(5).setCellValue(customerstmt.getPreBalance());
			aRow.createCell(6).setCellValue(customerstmt.getNewBalance());
			

		}
	}

}

