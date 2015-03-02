package com.cfeindia.b2bserviceapp.service.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.admin.HistoricalDataDao;
import com.cfeindia.b2bserviceapp.dto.ReportBeanDto;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class HistoricalDataServiceImpl implements HistoricalDataService {
	
	@Autowired
	private HistoricalDataDao historicalDataDao;
	
	@Override
	public String getEntityByPrimaryKeyHistoricalData(String customerId) {
		return historicalDataDao.getEntityByPrimaryKeyHistorical(customerId);
	}
	@Override
	public List<UserDetailDto> customerHistoricalDetailList() {
	List<UserDetailDto> dtos=	historicalDataDao.getCustomerHistoricalList();
		return dtos;
	}

	@Override
	public List<TransactionTransportBean> generateHistoricalReport(
			String fromDate, String toDate, String sel, String status) {
		Timestamp frmDate= TimeStampUtil.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDte = TimeStampUtil.getTimeStampFromStringTodate(toDate);
		List<TransactionTransportBean> transactionTransportBeansList = null;
		transactionTransportBeansList = historicalDataDao
				.generateHistoricalReport(frmDate, toDte, sel, status);
		return transactionTransportBeansList;
	}
	
	@Override
	public List<ReportBeanDto> generateCustomerRechargeHistoricalReport(
			String fromDate, String toDate, String customerId, String status,
			String thirdPartyName) {
		Timestamp frmDate = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDte = TimeStampUtil.getTimeStampFromStringTodate(toDate);
	
		List<ReportBeanDto> bean = historicalDataDao
				.generateCustomerRechargeHistoricalReport(frmDate, toDte, customerId,
						status, thirdPartyName);
		return bean;
	}

	@Override
	public List<ReportBeanDto> generateHistoricalMergeReport(String fromDate,
			String toDate, String customerId, String status,
			String thirdPartyName) {
		List<ReportBeanDto> cList = generateCustomerRechargeHistoricalReport(
				fromDate, toDate, customerId, status, thirdPartyName);
		List<TransactionTransportBean> transportBeans = generateHistoricalReport(
				fromDate, toDate, thirdPartyName, status);
		List<ReportBeanDto> margeList = new ArrayList<ReportBeanDto>();

		
			margeList.addAll(cList);
		 
		for (TransactionTransportBean tBean : transportBeans) {
			ReportBeanDto second = new ReportBeanDto();
			second.setCreatedAt(tBean.getCreatedAt());
			second.setClientId(ExtractorUtil.generateIdFromString(
					tBean.getRetailerId(), "R"));
			second.setThirdPartyServiceProvider(tBean
					.getThirdPartyServiceProvider());
			second.setMobileNo(tBean.getMobileNo());
			second.setConnectionid(tBean.getConnectionid());
			second.setOperator(tBean.getOperator());
			second.setTransactionName(tBean.getTransactionName());
			second.setAmount(tBean.getAmount());
			second.setCreditAmountFranchisee(tBean.getCreditAmountFranchisee());
			second.setStatus(tBean.getStatus());
			second.setThirdpartytransid(tBean.getTransid());
			margeList.add(second);
		}
		return margeList;
	}

}
