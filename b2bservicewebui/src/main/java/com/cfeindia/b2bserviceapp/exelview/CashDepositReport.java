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
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;

public class CashDepositReport extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Cash Deposit Report Summary.xlsx");
		List<FranchiseeCashDepositRequest> list = (List<FranchiseeCashDepositRequest>) model
				.get("list");
		HSSFSheet sheet = workbook.createSheet("Cash Deposit Report");
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

		header.createCell(1).setCellValue("MOBILE NO.");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("FIRM NAME");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("REQUEST TYPE");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("PAYMENT MODE");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("AMOUNT");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("BANK");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("RECEIPT NO.");
		header.getCell(7).setCellStyle(style);

		header.createCell(8).setCellValue("DATE");
		header.getCell(8).setCellStyle(style);

		header.createCell(9).setCellValue("REMARK");
		header.getCell(9).setCellStyle(style);

		int rowCount = 1;

		for (FranchiseeCashDepositRequest customerstmt : list) {
			int sn = rowCount;
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(sn);
			aRow.createCell(1).setCellValue(
					customerstmt.getMobileNumber());
			aRow.createCell(2).setCellValue(customerstmt.getFirmName());
			aRow.createCell(3).setCellValue(
					customerstmt.getRequestType());
			aRow.createCell(4).setCellValue(customerstmt.getPaymentMode());
			aRow.createCell(5).setCellValue(customerstmt.getAmount());
			aRow.createCell(6).setCellValue(customerstmt.getBankName());
			aRow.createCell(7).setCellValue(customerstmt.getRecieptChequeDdNo());
			aRow.createCell(8)
					.setCellValue(customerstmt.getCreatedat().toString());
			aRow.createCell(9).setCellValue(customerstmt.getRemark());
			

		}
	}

}
