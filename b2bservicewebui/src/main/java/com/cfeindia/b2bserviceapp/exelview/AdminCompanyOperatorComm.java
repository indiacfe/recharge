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
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;

public class AdminCompanyOperatorComm extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Admin Company Operator Commission Report.xlsx");
		List<CompanyOperatorComission> list = (List<CompanyOperatorComission>) model
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
		header.createCell(0).setCellValue("OPERATOR NAME");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("RECHARGE TYPE");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("THIRD PARTY SERVICE PROVIDER");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("RETAILER COMMISSION");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("COMMISSION TYPE");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("DEDUCTION TYPE");
		header.getCell(5).setCellStyle(style);

		int rowCount = 1;

		for (CompanyOperatorComission customerstmt : list) {
			
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(customerstmt.getOperatorName());
			aRow.createCell(1).setCellValue(customerstmt.getRechargeType());
			aRow.createCell(2).setCellValue(
					customerstmt.getThirdpartyServiceProvider());
			aRow.createCell(3)
					.setCellValue(customerstmt.getRetailercommision());
			aRow.createCell(4).setCellValue(customerstmt.getComissionType());
			aRow.createCell(5).setCellValue(customerstmt.getDeductionType());

		}
	}

}
