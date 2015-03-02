package com.cfeindia.b2bserviceapp.service.customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyServiceProvider;
import com.cfeindia.b2bserviceapp.converter.CustomerBalTransferLogEntityToDtoConverter;
import com.cfeindia.b2bserviceapp.converter.CustomerTransactionTransportEntityToDtoConverter;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.recharge.mobile.CustomerAccountStatementDao;
import com.cfeindia.b2bserviceapp.dto.accountstatement.CustomerAccountStatementDto;
import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class CustAccountStatementServiceImpl implements
		CustAccountStatementService {

	@Autowired
	private CustomerAccountStatementDao customerAccountStatementDao;

	@Autowired
	private CommonDao commonDao;

	@Override
	public List<CustomerAccountStatementDto> generateAccountStatementReport(
			Long userId, String fromDate, String toDate) {
		CustomerTransactionTransportEntityToDtoConverter customerTransactionTransportconverter = new CustomerTransactionTransportEntityToDtoConverter();
		CustomerBalTransferLogEntityToDtoConverter customerBalTransferConverter = new CustomerBalTransferLogEntityToDtoConverter();
		List<CustomerTransactionTransportBean> customerTransactionTransportList = new ArrayList<CustomerTransactionTransportBean>();
		List<CustomerAccountStatementDto> custAccountStatmentDtoList = new ArrayList<CustomerAccountStatementDto>();
		customerTransactionTransportList = customerAccountStatementDao
				.getCustomerTransactionTransportBeanListByCustomerId(userId,
						fromDate, toDate);
		Iterator<CustomerTransactionTransportBean> itr = customerTransactionTransportList
				.iterator();
		while (itr.hasNext()) {
			CustomerTransactionTransportBean customertransactionTransportBean = itr
					.next();

			CustomerAccountStatementDto customerAccountStatementDto1 = customerTransactionTransportconverter
					.convert(customertransactionTransportBean);
			CustomerAccountStatementDto customerAccountStatementDto2 = customerTransactionTransportconverter
					.crConvert(customertransactionTransportBean);
			custAccountStatmentDtoList.add(customerAccountStatementDto2);
			custAccountStatmentDtoList.add(customerAccountStatementDto1);

		}

		List<CustomerBalanceTransferLog> customerBalanceTransferLogs = customerAccountStatementDao
				.getCustomerBalTransferLogList(userId, fromDate, toDate);

		for (CustomerBalanceTransferLog customerBalanceTransferLog : customerBalanceTransferLogs) {
			CustomerAccountStatementDto customerAccountStatementDto = customerBalTransferConverter
					.convert(customerBalanceTransferLog);
			if (customerBalanceTransferLog.getTransactionId() != null) {
				if (customerBalanceTransferLog.getTransactionId().startsWith(
						"C")) {
					customerAccountStatementDto
							.setParticulars("Fund Transfer from Company");

				}
			}
			custAccountStatmentDtoList.add(customerAccountStatementDto);
		}
		Collections.sort(custAccountStatmentDtoList,
				new CustomerAccountStatementComparator());
		return custAccountStatmentDtoList;
	}

	class CustomerAccountStatementComparator implements
			Comparator<CustomerAccountStatementDto> {
		@Override
		public int compare(CustomerAccountStatementDto o1,
				CustomerAccountStatementDto o2) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm:ss");
				return sdf.parse(o2.getCreatedAt()).compareTo(
						sdf.parse(o1.getCreatedAt()));
			} catch (Exception e) {
				e.printStackTrace();
				return 2;
			}
		}

	}

	@Override
	public List<CustomerAccountStatementDto> generateRechargeHistoryReport(
			Long userId, String fromDate, String toDate, String number) {
		CustomerTransactionTransportBean customerTransactionTransportBean = new CustomerTransactionTransportBean();
		List<CustomerTransactionTransportBean> customerTransactionTransportList = new ArrayList<CustomerTransactionTransportBean>();
		List<CustomerAccountStatementDto> custAccountStatmentDtoList = new ArrayList<CustomerAccountStatementDto>();
		customerTransactionTransportList = customerAccountStatementDao
				.getCustomerTransactionTransportBeanListCustomerByNumber(
						userId, fromDate, toDate, number);

		Iterator<CustomerTransactionTransportBean> itr = customerTransactionTransportList
				.iterator();
		while (itr.hasNext()) {
			customerTransactionTransportBean = itr.next();
			CustomerAccountStatementDto dto = new CustomerAccountStatementDto();
			dto.setAmount(customerTransactionTransportBean.getAmount());
			dto.setCreatedAt(TimeStampUtil
					.getStringFromTimeStamp(customerTransactionTransportBean
							.getCreatedAt()));
			dto.setCreditAmountCustomer(customerTransactionTransportBean
					.getCreditAmountFranchisee());
			dto.setMobileNo(customerTransactionTransportBean.getMobileNo());
			dto.setConnectionid(customerTransactionTransportBean
					.getConnectionid());
			dto.setOperator(customerTransactionTransportBean.getOperator());
			dto.setTransactionType(CommonConstants.TRANSFER_TYPE_DEBIT);
			dto.setTransactionId(customerTransactionTransportBean.getTransid());
			dto.setStatus(customerTransactionTransportBean.getStatus());
			dto.setClientTransId(customerTransactionTransportBean
					.getClientTransId());
			custAccountStatmentDtoList.add(dto);

		}
		Collections.sort(custAccountStatmentDtoList,
				new CustomerAccountStatementComparator());
		return custAccountStatmentDtoList;
	}

	@Override
	public List<CustomerCommision> getCustomerCommisions(Long userId) {
		List<CustomerCommision> customerCommisions = customerAccountStatementDao
				.getCustomerCommisions(userId);
		Map<String, Double> allThirdPartyCommission = new HashMap<String, Double>();
		for (CustomerCommision commision : customerCommisions) {
			allThirdPartyCommission.put(
					createKey(commision.getOperatorName(),
							commision.getRechargeType(),
							commision.getThirdpartyServiceProvider()),
					commision.getCustomerCommission());
		}
		List<ThirdPartyServiceProvider> currentThirdParty = commonDao
				.findAll(ThirdPartyServiceProvider.class);
		List<CustomerCommision> currentCustomerCommission = new ArrayList<CustomerCommision>();
		for (ThirdPartyServiceProvider tProvider : currentThirdParty) {

			if (allThirdPartyCommission.get(createKey(
					tProvider.getOperatorName(), tProvider.getServiceType(),
					tProvider.getServiceProvider())) != null) {
				CustomerCommision newCommision = new CustomerCommision();
				newCommision.setCustomerCommission(allThirdPartyCommission
						.get(createKey(tProvider.getOperatorName(),
								tProvider.getServiceType(),
								tProvider.getServiceProvider())));
				newCommision.setOperatorName(tProvider.getOperatorName());
				newCommision.setRechargeType(tProvider.getServiceType());
				newCommision
						.setThirdpartyServiceProvider(CommonConstants.companyName);
				currentCustomerCommission.add(newCommision);
			}

		}
		return currentCustomerCommission;
	}

	private String createKey(String s1, String s2, String s3) {
		StringBuilder sb = new StringBuilder();
		return sb.append(s1).append(s2).append(s3).toString();
	}
}
