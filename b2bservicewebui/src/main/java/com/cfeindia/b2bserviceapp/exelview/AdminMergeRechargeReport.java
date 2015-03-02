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


public class AdminMergeRechargeReport extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Customer Recharge History Summary.xlsx");
		List<ReportBeanDto> list = (List<ReportBeanDto>) model.get("list");
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

		header.createCell(2).setCellValue("CLIENT ID");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("SERVICE PROVIDER");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("MOBILE NO.");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("CONNECTION NO.");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("OPERATOR");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("TRANSACTION TYPE");
		header.getCell(7).setCellStyle(style);

		header.createCell(8).setCellValue("TRANSACTION ID");
		header.getCell(8).setCellStyle(style);

		header.createCell(9).setCellValue("DEBIT");
		header.getCell(9).setCellStyle(style);

		header.createCell(10).setCellValue("CREDIT");
		header.getCell(10).setCellStyle(style);

		header.createCell(11).setCellValue("STATUS");
		header.getCell(11).setCellStyle(style);

		int rowCount = 1;

		for (ReportBeanDto customerstmt : list) {
			int sn = rowCount;
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(sn);
			aRow.createCell(1).setCellValue(
					customerstmt.getCreatedAt().toString());
			aRow.createCell(2).setCellValue(customerstmt.getClientId());
			aRow.createCell(3).setCellValue(
					customerstmt.getThirdPartyServiceProvider());
			aRow.createCell(4).setCellValue(customerstmt.getMobileNo());
			aRow.createCell(5).setCellValue(customerstmt.getConnectionid());
			aRow.createCell(6).setCellValue(customerstmt.getOperator());
			aRow.createCell(7).setCellValue(customerstmt.getTransactionName());
			aRow.createCell(8).setCellValue(customerstmt.getThirdpartytransid());
			aRow.createCell(9).setCellValue(customerstmt.getAmount());
			aRow.createCell(10).setCellValue(
					customerstmt.getCreditAmountFranchisee());
			aRow.createCell(11).setCellValue(customerstmt.getStatus());

		}
	}

}
