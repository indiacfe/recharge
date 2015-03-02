package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.ReportBeanDto;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface HistoricalDataService {

	List<TransactionTransportBean> generateHistoricalReport(String fromDate,
			String toDate, String sel, String status);

	List<ReportBeanDto> generateHistoricalMergeReport(String fromDate,
			String toDate, String customerId, String status,
			String thirdPartyName);

	List<ReportBeanDto> generateCustomerRechargeHistoricalReport(
			String fromDate, String toDate, String customerId, String status,
			String thirdPartyName);

	List<UserDetailDto> customerHistoricalDetailList();

	String getEntityByPrimaryKeyHistoricalData(String customerId);

}
