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
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;

public class AdminDistAndRetailer extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Admin Dist And Retailer Summary.xlsx");
		List<UserDetailDto> list = (List<UserDetailDto>) model.get("list");
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
		header.createCell(0).setCellValue("USER ID");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("FIRM NAME");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("MOBILE NO.");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("CURR BAL.");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("B2B CURR  BAL");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("B2B CURR ADUNIT BAL");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("A/C CREATED AT");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("CREATED BY");
		header.getCell(7).setCellStyle(style);

		

		
		int rowCount = 1;

		for (UserDetailDto customerstmt : list) {
			
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(customerstmt.getUserId());
			aRow.createCell(1).setCellValue(
					customerstmt.getFirmName());
			aRow.createCell(2).setCellValue(customerstmt.getMobileNo());
			aRow.createCell(3).setCellValue(
					customerstmt.getCurrBal());
			aRow.createCell(4).setCellValue(customerstmt.getB2bCurrBal());
			aRow.createCell(5).setCellValue(customerstmt.getB2bCurrAdUnitBal());
			//aRow.createCell(6).setCellValue(customerstmt.getCreatedAt().toString());
			aRow.createCell(7).setCellValue(customerstmt.getCreatedBy());
			

		}
	}

}

