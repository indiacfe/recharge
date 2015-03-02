package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.dto.ReportBeanDto;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface HistoricalDataDao {

	List<TransactionTransportBean> generateHistoricalReport(Timestamp fromDate,
			Timestamp toDate, String sel, String status);

	List<ReportBeanDto> generateCustomerRechargeHistoricalReport(
			Timestamp frmDate, Timestamp toDte, String custId, String status,
			String thirdPartyName);

	String getEntityByPrimaryKeyHistorical(String customerId);

	List<UserDetailDto> getCustomerHistoricalList();

}
