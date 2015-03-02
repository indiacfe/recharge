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
import com.cfeindia.b2bserviceapp.dto.accountstatement.CustomerAccountStatementDto;


public class CustomerRechargeHistoryReport extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Admin Recharge History Report List.xlsx");
		
		List<CustomerAccountStatementDto> list = (List<CustomerAccountStatementDto>) model
				.get("list");
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
		
		header.createCell(2).setCellValue(" TID");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("CLIENT TID");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("OPERATOR");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("AMOUNT");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("MOBILE/CONNECTION NO.");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("STATUS");
		header.getCell(7).setCellStyle(style);

		header.createCell(8).setCellValue("COMMISSION");
		header.getCell(8).setCellStyle(style);

		int rowCount = 1;

		for (CustomerAccountStatementDto customerstmt : list) {
			int sn = rowCount;
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(sn);
			aRow.createCell(1).setCellValue(
					customerstmt.getCreatedAt().toString());
			aRow.createCell(2).setCellValue(customerstmt.getTransactionId());
			aRow.createCell(3).setCellValue(customerstmt.getClientTransId());
			aRow.createCell(4).setCellValue(customerstmt.getOperator());
			aRow.createCell(5).setCellValue(customerstmt.getDisplayAmount());
			aRow.createCell(6).setCellValue(customerstmt.getMobileNo());
			aRow.createCell(7).setCellValue(customerstmt.getStatus());
			aRow.createCell(8).setCellValue(customerstmt.getCreditAmountCustomer());

		}
	}

}

