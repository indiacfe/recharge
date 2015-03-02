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

import com.cfeindia.b2bserviceapp.dto.ReportBeanDto;
import com.cfeindia.b2bserviceapp.model.admin.AdminFundTrasferReportDTO;

public class AdminFundTransferReport extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AdminFundTrasferReportDTO> list = (List<AdminFundTrasferReportDTO>) model.get("list");
		HSSFSheet sheet = workbook.createSheet("Merge Recharge History");
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

		header.createCell(2).setCellValue("FIRM NAME");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("MOBILE NO.");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("TID");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("TYPE");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("LOAD TYPE");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("TRANSFER AMOUNT");
		header.getCell(7).setCellStyle(style);

		header.createCell(8).setCellValue("NEW AMOUNT");
		header.getCell(8).setCellStyle(style);

		header.createCell(9).setCellValue("OLD BALANCE");
		header.getCell(9).setCellStyle(style);

		

		int rowCount = 1;

		for (AdminFundTrasferReportDTO customerstmt : list) {
			int sn = rowCount;
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(sn);
			aRow.createCell(1).setCellValue(
					customerstmt.getCreatedAt().toString());
			aRow.createCell(2).setCellValue(customerstmt.getFirmName());
			aRow.createCell(3).setCellValue(
					customerstmt.getMobileNumber());
			aRow.createCell(4).setCellValue(customerstmt.getTransactionId());
			aRow.createCell(5).setCellValue(customerstmt.getTransferType());
			aRow.createCell(6).setCellValue(customerstmt.getTransferTo());
			aRow.createCell(7).setCellValue(customerstmt.getTransferAmount());
			aRow.createCell(8).setCellValue(customerstmt.getNewBalance());
			aRow.createCell(9).setCellValue(customerstmt.getPreBalance());
			

		}
	}

}

