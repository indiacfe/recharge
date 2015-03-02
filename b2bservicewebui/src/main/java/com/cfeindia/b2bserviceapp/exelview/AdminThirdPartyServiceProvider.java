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

import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyServiceProvider;
import com.cfeindia.b2bserviceapp.dto.ReportBeanDto;

public class AdminThirdPartyServiceProvider extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Admin ThirdParty Service Provider Summary.xlsx");
		List<ThirdPartyServiceProvider> list = (List<ThirdPartyServiceProvider>) model.get("list");
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
		header.createCell(0).setCellValue("SERVICE TYPE");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("OPERATOR NAME");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("CURRENT SERVICE PROVIDER");
		header.getCell(2).setCellStyle(style);

		
		int rowCount = 1;

		for (ThirdPartyServiceProvider customerstmt : list) {
			
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(customerstmt.getServiceType());
			aRow.createCell(1).setCellValue(
					customerstmt.getOperatorName());
			aRow.createCell(2).setCellValue(customerstmt.getServiceProvider());
			
		}
	}

}
