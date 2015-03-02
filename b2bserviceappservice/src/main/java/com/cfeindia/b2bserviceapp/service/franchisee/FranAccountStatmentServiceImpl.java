package com.cfeindia.b2bserviceapp.service.franchisee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.converter.FranchiseeBalTransferLogEntityToDtoConverter;
import com.cfeindia.b2bserviceapp.converter.TransactionTransportEntityToDtoConverter;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.franchisee.RefundRequestFranchiseeDao;
import com.cfeindia.b2bserviceapp.dao.recharge.mobile.FranchiseeAccountStatementDao;
import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
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
	RefundRequestFranchiseeDao refundRequestFranchiseeDao;
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
		List<FranchiseeAccountStatementDto> franAccountStatmentDtoList = new ArrayList<FranchiseeAccountStatementDto>();
		transactionTransportList = franchiseeAccountStatementDao
				.getTransactionTransportBeanListByRetailerId(userId, fromDate,
						toDate);
		Iterator<TransactionTransportBean> itr = transactionTransportList
				.iterator();
		while (itr.hasNext()) {
			TransactionTransportBean transactionTransportBean = itr.next();

			FranchiseeAccountStatementDto franchiseeAccountStatementDto1 = transactionTransportconverter
					.convert(transactionTransportBean);
			FranchiseeAccountStatementDto franchiseeAccountStatementDto2 = transactionTransportconverter
					.crConvert(transactionTransportBean);
			franAccountStatmentDtoList.add(franchiseeAccountStatementDto2);
			franAccountStatmentDtoList.add(franchiseeAccountStatementDto1);

		}
		transactionTransportList = franchiseeAccountStatementDao
				.getTransactionTransportBeanListForRefundByRetailerId(userId,
						fromDate, toDate);
		itr = transactionTransportList.iterator();
		while (itr.hasNext()) {
			TransactionTransportBean transactionTransportBean = itr.next();
			FranchiseeAccountStatementDto franchiseeAccountStatementDto3 = transactionTransportconverter
					.RefundConvert(transactionTransportBean);
			franAccountStatmentDtoList.add(franchiseeAccountStatementDto3);
		}
		List<FranchiseeBalanceTransferLog> franchiseeBalanceTransferLogs = franchiseeAccountStatementDao
				.getFranchiseeBalTransferLogList(userId, fromDate, toDate);

		for (FranchiseeBalanceTransferLog franchiseeBalanceTransferLog : franchiseeBalanceTransferLogs) {
			if (franchiseeBalanceTransferLog.getTransactionId().startsWith(
					"CTR"))
				continue;
			FranchiseeAccountStatementDto franchiseeAccountStatementDto = franchiseeBalTransferConverter
					.convert(franchiseeBalanceTransferLog);
			if (franchiseeBalanceTransferLog.getTransactionId() != null) {
				if (franchiseeBalanceTransferLog.getTransactionId().startsWith(
						"D")) {
					StringBuilder strBuilder = new StringBuilder();
					strBuilder.append("Fund transfer from - ");
					String firmName = commonDao.getUserDetail(
							franchiseeBalanceTransferLog.getSenderId())
							.getFirmName();
					strBuilder.append(firmName);
					franchiseeAccountStatementDto.setParticulars(strBuilder
							.toString());
				} else if (franchiseeBalanceTransferLog.getTransactionId()
						.startsWith("C")) {
					franchiseeAccountStatementDto
							.setParticulars(franchiseeBalanceTransferLog
									.getTransactionFrom()
									+ " To "
									+ franchiseeBalanceTransferLog
											.getTransactionTo()
									+ " - "
									+ ExtractorUtil.generateIdFromString(
											franchiseeBalanceTransferLog
													.getSenderId().toString(),
											"C"));
				}
			}
			if (franchiseeBalanceTransferLog.getSenderId() != null) {
				franchiseeAccountStatementDto
						.setSenderId(franchiseeBalanceTransferLog.getSenderId());
			}

			if (franchiseeBalanceTransferLog.getTransactionId() != null
					&& !franchiseeBalanceTransferLog.getTransactionId()
							.startsWith("RTC"))
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
			dto.setAccount(transactionTransportBean.getCommissionType());
			franAccountStatmentDtoList.add(dto);

		}
		Collections.sort(franAccountStatmentDtoList,
				new FranchiseeAccountStatementComparator());
		return franAccountStatmentDtoList;
	}

	@Override
	public List<FranchiseeAccountStatementDto> newgenerateAccountStatementReport(
			Long userId, String fromDate, String toDate,
			String thirdPartyServiceProvider) {

		TransactionTransportEntityToDtoConverter transactionTransportconverter = new TransactionTransportEntityToDtoConverter();
		FranchiseeBalTransferLogEntityToDtoConverter franchiseeBalTransferConverter = new FranchiseeBalTransferLogEntityToDtoConverter();
		List<TransactionTransportBean> transactionTransportList = new ArrayList<TransactionTransportBean>();
		List<FranchiseeAccountStatementDto> franAccountStatmentDtoList = new ArrayList<FranchiseeAccountStatementDto>();
		transactionTransportList = franchiseeAccountStatementDao
				.newgenerateAccountStatementReport(userId, fromDate, toDate,
						thirdPartyServiceProvider);
		Iterator<TransactionTransportBean> itr = transactionTransportList
				.iterator();
		while (itr.hasNext()) {
			TransactionTransportBean transactionTransportBean = itr.next();

			FranchiseeAccountStatementDto franchiseeAccountStatementDto1 = transactionTransportconverter
					.convert(transactionTransportBean);
			FranchiseeAccountStatementDto franchiseeAccountStatementDto2 = transactionTransportconverter
					.crConvert(transactionTransportBean);
			franAccountStatmentDtoList.add(franchiseeAccountStatementDto2);
			franAccountStatmentDtoList.add(franchiseeAccountStatementDto1);

		}
		transactionTransportList = franchiseeAccountStatementDao
				.getTransactionTransportBeanListForRefundByRetailerId(userId,
						fromDate, toDate);
		itr = transactionTransportList.iterator();
		while (itr.hasNext()) {
			TransactionTransportBean transactionTransportBean = itr.next();
			FranchiseeAccountStatementDto franchiseeAccountStatementDto3 = transactionTransportconverter
					.RefundConvert(transactionTransportBean);
			franAccountStatmentDtoList.add(franchiseeAccountStatementDto3);
		}
		List<FranchiseeBalanceTransferLog> franchiseeBalanceTransferLogs = franchiseeAccountStatementDao
				.getFranchiseeBalTransferLogList(userId, fromDate, toDate);

		for (FranchiseeBalanceTransferLog franchiseeBalanceTransferLog : franchiseeBalanceTransferLogs) {
			if (franchiseeBalanceTransferLog.getTransactionId().startsWith(
					"CTR"))
				continue;
			FranchiseeAccountStatementDto franchiseeAccountStatementDto = franchiseeBalTransferConverter
					.convert(franchiseeBalanceTransferLog);
			if (franchiseeBalanceTransferLog.getTransactionId() != null) {
				if (franchiseeBalanceTransferLog.getTransactionId().startsWith(
						"D")) {
					StringBuilder strBuilder = new StringBuilder();
					strBuilder.append("Fund transfer from - ");
					String firmName = commonDao.getUserDetail(
							franchiseeBalanceTransferLog.getSenderId())
							.getFirmName();
					strBuilder.append(firmName);
					franchiseeAccountStatementDto.setParticulars(strBuilder
							.toString());
				} else if (franchiseeBalanceTransferLog.getTransactionId()
						.startsWith("C")) {
					franchiseeAccountStatementDto
							.setParticulars(franchiseeBalanceTransferLog
									.getTransactionFrom()
									+ " To "
									+ franchiseeBalanceTransferLog
											.getTransactionTo()
									+ " - "
									+ ExtractorUtil.generateIdFromString(
											franchiseeBalanceTransferLog
													.getSenderId().toString(),
											"C"));
				}
			}
			if (franchiseeBalanceTransferLog.getSenderId() != null) {
				franchiseeAccountStatementDto
						.setSenderId(franchiseeBalanceTransferLog.getSenderId());
			}

			if (franchiseeBalanceTransferLog.getTransactionId() != null
					&& !franchiseeBalanceTransferLog.getTransactionId()
							.startsWith("RTC"))
				franAccountStatmentDtoList.add(franchiseeAccountStatementDto);
		}
		Collections.sort(franAccountStatmentDtoList,
				new FranchiseeAccountStatementComparator());
		return franAccountStatmentDtoList;

	}

	@Override
	public Map<String, FranchiseeRefundRequests> getFranchiseeRefundRequest(
			long franchiseeId) {
		List<FranchiseeRefundRequests> list = refundRequestFranchiseeDao
				.getAllList(franchiseeId);
		Map<String, FranchiseeRefundRequests> map = new HashMap<String, FranchiseeRefundRequests>();
		if (list.size() != 0) {
			for (FranchiseeRefundRequests requests : list) {
				map.put(requests.getTransId(), requests);
			}
		}
		return map;
	}
}

class FranchiseeAccountStatementComparator implements
		Comparator<FranchiseeAccountStatementDto> {
	public int compare(FranchiseeAccountStatementDto o1,
			FranchiseeAccountStatementDto o2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			return sdf.parse(o2.getCreatedAt()).compareTo(
					sdf.parse(o1.getCreatedAt()));
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}
}