package com.cfeindia.b2bserviceapp.franchisee.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.accountstatement.Dto.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.dao.CommonDao;
import com.cfeindia.b2bserviceapp.converter.FranchiseeBalTransferLogEntityToDtoConverter;
import com.cfeindia.b2bserviceapp.converter.TransactionTransportEntityToDtoConverter;
import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.recharge.mobile.dao.FranchiseeAccountStatementDao;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service("franAccountStatmentService")
@Transactional
public class FranAccountStatmentServiceImpl implements
		FranAccountStatmentService {

	@Autowired
	FranchiseeAccountStatementDao franchiseeAccountStatementDao;

	@Autowired
	CommonDao commonDao;
	
	public FranchiseeAccountStatementDao getFranchiseeAccountStatementDao() {
		return franchiseeAccountStatementDao;
	}

	public void setFranchiseeAccountStatementDao(
			FranchiseeAccountStatementDao franchiseeAccountStatementDao) {
		this.franchiseeAccountStatementDao = franchiseeAccountStatementDao;
	}

	public List<FranchiseeAccountStatementDto> generateAccountStatementReport(
			Long userId, String fromDate, String toDate) {
		TransactionTransportEntityToDtoConverter transactionTransportconverter = new TransactionTransportEntityToDtoConverter();
		FranchiseeBalTransferLogEntityToDtoConverter franchiseeBalTransferConverter = new FranchiseeBalTransferLogEntityToDtoConverter();
		List<TransactionTransportBean> transactionTransportList = new ArrayList<TransactionTransportBean>();
		List<FranchiseeBalanceTransferLog> FranchiseeBalTransferLogList = new ArrayList<FranchiseeBalanceTransferLog>();
		List<FranchiseeAccountStatementDto> franAccountStatmentDtoList = new ArrayList<FranchiseeAccountStatementDto>();
		transactionTransportList = franchiseeAccountStatementDao
				.getTransactionTransportBeanListByRetailerId(userId, fromDate,
						toDate);
		FranchiseeBalTransferLogList = franchiseeAccountStatementDao
				.getFranchiseeBalTransferLogList(userId, fromDate, toDate);

		Iterator<TransactionTransportBean> itr = transactionTransportList
				.iterator();
		while (itr.hasNext()) {
			TransactionTransportBean transactionTransportBean = itr.next();
			FranchiseeAccountStatementDto franchiseeAccountStatementDto1 = transactionTransportconverter
					.convert(transactionTransportBean);

			FranchiseeAccountStatementDto franchiseeAccountStatementDto2 = transactionTransportconverter
					.convert(transactionTransportBean);

			franchiseeAccountStatementDto2
					.setAmount(franchiseeAccountStatementDto1
							.getCreditAmountFranchisee());
			franchiseeAccountStatementDto2
					.setTransactionType(CommonConstants.TRANSFER_TYPE_CREDIT);
			if (franchiseeAccountStatementDto2.getNewB2bCurrBal() != null)
				franchiseeAccountStatementDto2
						.setNewB2bCurrBal(franchiseeAccountStatementDto2
								.getNewB2bCurrBal()
								+ franchiseeAccountStatementDto1
										.getCreditAmountFranchisee());

			franAccountStatmentDtoList.add(franchiseeAccountStatementDto2);
			franAccountStatmentDtoList.add(franchiseeAccountStatementDto1);
		}
		Iterator<FranchiseeBalanceTransferLog> franBalIterator = FranchiseeBalTransferLogList
				.iterator();
		while (franBalIterator.hasNext()) {
			FranchiseeBalanceTransferLog franchiseeBalanceTransferLog = franBalIterator
					.next();
			System.out.println(franchiseeBalanceTransferLog.getSenderId());
				FranchiseeAccountStatementDto franchiseeAccountStatementDto = franchiseeBalTransferConverter
					.convert(franchiseeBalanceTransferLog);
				if(franchiseeBalanceTransferLog.getTransactionId().startsWith("D"))
				{
					franchiseeAccountStatementDto.setParticulars(franchiseeBalanceTransferLog.getTransactionFrom() + " To " + franchiseeBalanceTransferLog.getTransactionTo() 
							+ " - " + ExtractorUtil.generateIdFromString(franchiseeBalanceTransferLog.getSenderId().toString(), "D"));
				}	
				else if(franchiseeBalanceTransferLog.getTransactionId().startsWith("C"))
				{
					franchiseeAccountStatementDto.setParticulars(franchiseeBalanceTransferLog.getTransactionFrom() + " To " + franchiseeBalanceTransferLog.getTransactionTo() 
							+ " - " + ExtractorUtil.generateIdFromString(franchiseeBalanceTransferLog.getSenderId().toString(), "C"));	
				}
				franchiseeAccountStatementDto.setDistributorId(franchiseeBalanceTransferLog.getSenderId());
			franAccountStatmentDtoList.add(franchiseeAccountStatementDto);
		}
		Collections.sort(franAccountStatmentDtoList,
				new FranchiseeAccountStatementComparator());
		return franAccountStatmentDtoList;
	}

	public List<FranchiseeAccountStatementDto> generateRechargeHistoryReport(
			Long userId, String fromDate, String toDate, String number) {
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		List<TransactionTransportBean> transactionTransportList = new ArrayList<TransactionTransportBean>();
		List<FranchiseeAccountStatementDto> franAccountStatmentDtoList = new ArrayList<FranchiseeAccountStatementDto>();
		transactionTransportList = franchiseeAccountStatementDao
				.getTransactionTransportBeanListRetailerByNumber(userId,
						fromDate, toDate, number);

		Iterator<TransactionTransportBean> itr = transactionTransportList
				.iterator();
		while (itr.hasNext()) {
			transactionTransportBean = itr.next();
			FranchiseeAccountStatementDto dto = new FranchiseeAccountStatementDto();
			dto.setAmount(transactionTransportBean.getAmount());
			dto.setCreatedAt(TimeStampUtil
					.getStringFromTimeStamp(transactionTransportBean
							.getCreatedAt()));
			dto.setCreditAmountFranchisee(transactionTransportBean
					.getCreditAmountFranchisee());
			dto.setMobileNo(transactionTransportBean.getMobileNo());
			dto.setConnectionid(transactionTransportBean.getConnectionid());
			dto.setOperator(transactionTransportBean.getOperator());
			dto.setTransactionType(CommonConstants.TRANSFER_TYPE_DEBIT);
			dto.setTransactionId(transactionTransportBean.getTransid());
			dto.setStatus(transactionTransportBean.getStatus());
			franAccountStatmentDtoList.add(dto);

		}
		Collections.sort(franAccountStatmentDtoList,
				new FranchiseeAccountStatementComparator());
		return franAccountStatmentDtoList;
	}
}

class FranchiseeAccountStatementComparator implements
		Comparator<FranchiseeAccountStatementDto> {
	public int compare(FranchiseeAccountStatementDto o1,
			FranchiseeAccountStatementDto o2) {
		try {
			// TODO Auto-generated method stub
			return o2.getCreatedAt().compareTo(o1.getCreatedAt());
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}
}