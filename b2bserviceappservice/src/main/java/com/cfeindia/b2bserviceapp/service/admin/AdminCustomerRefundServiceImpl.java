package com.cfeindia.b2bserviceapp.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.converter.CustomerTransTransportToDtoCoverter;
import com.cfeindia.b2bserviceapp.dao.admin.AdminCustomerRefundDao;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.common.RegistrationDao;
import com.cfeindia.b2bserviceapp.dao.customer.CustomerBalanceCheckingDao;
import com.cfeindia.b2bserviceapp.dto.RefundAmountDto;
import com.cfeindia.b2bserviceapp.entity.CompanyBalTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.CustomerRefundRequest;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class AdminCustomerRefundServiceImpl implements
		AdminCustomerRefundService {

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private RegistrationDao registrationDao;

	@Autowired
	private AdminCustomerRefundDao customerRefundDao;

	@Autowired
	private CustomerBalanceCheckingDao customerBalanceCheckingDao;

	@Override
	public RefundAmountDto searchcustomerTransactionId(String tid) {
		CustomerTransTransportToDtoCoverter tranTransportBeanToDtoConverter = new CustomerTransTransportToDtoCoverter();
		RefundAmountDto refundAmountDto = new RefundAmountDto();
		CustomerTransactionTransportBean transactionTransportBean = customerRefundDao
				.getEntityByKey(tid);
		CustomerRefundRequest franchiseeRefundRequests = customerRefundDao
				.getCustomerRefundRequestBean(tid);
		if (transactionTransportBean != null
				&& franchiseeRefundRequests != null) {
			String customerId = transactionTransportBean.getCustomerId();
			UserDetail userDetail = commonDao.getUserDetail(Long
					.parseLong(customerId));
			Users user = commonDao.getUsers(Long.parseLong(customerId));
			refundAmountDto = tranTransportBeanToDtoConverter
					.convert(transactionTransportBean);
			refundAmountDto.setRetailerName(userDetail.getUserName());
			refundAmountDto.setRetailerMobNo(user.getUsername());
			return refundAmountDto;
		} else {
			return refundAmountDto;
		}
	}

	@Override
	public List<RefundAmountDto> getAllRefundRequests() {
		CustomerTransTransportToDtoCoverter customerTransTransportToDtoCoverter = new CustomerTransTransportToDtoCoverter();
		RefundAmountDto refundAmountDto = null;
		List<RefundAmountDto> refundAmountDtoList = new ArrayList<RefundAmountDto>();
		List<CustomerRefundRequest> customerRefundRequestsList = (List<CustomerRefundRequest>) customerRefundDao
				.getAllRefundRequest();
		for (CustomerRefundRequest customerRefundRequest : customerRefundRequestsList) {
			CustomerTransactionTransportBean customerTransactionTransportBean = customerRefundDao
					.getEntityByKey(customerRefundRequest.getClientTransId());
			String customerId = customerTransactionTransportBean
					.getCustomerId();
			UserDetail userDetail = commonDao.getUserDetail(Long
					.parseLong(customerId));
			Users user = commonDao.getUsers(Long.parseLong(customerId));
			refundAmountDto = customerTransTransportToDtoCoverter
					.convert(customerTransactionTransportBean);
			refundAmountDto.setRetailerName(userDetail.getFirmName());
			refundAmountDto.setRetailerMobNo(user.getUsername());
			refundAmountDto.setCreatedAt(customerTransactionTransportBean
					.getCreatedAt());
			refundAmountDto.setUpdatedAt(customerRefundRequest.getCreatedat());
			refundAmountDtoList.add(refundAmountDto);
		}
		return refundAmountDtoList;
	}

	@Override
	public void rejectedRemark(Long id, String remark) {
		CustomerTransactionTransportBean customerTransactionTransportBean = (CustomerTransactionTransportBean) commonDao
				.getEntityByPrimaryKey(CustomerTransactionTransportBean.class,
						id);
		customerTransactionTransportBean.setStatus(CommonConstants.SUCCESS);
		updateCustomerRefundRequests(
				customerTransactionTransportBean.getClientTransId(),
				CommonConstants.REFUND_REJECT, remark);
		customerTransactionTransportBean.setUpdatedAt(TimeStampUtil
				.getTimestamp());

		commonDao.updateEntity(customerTransactionTransportBean);

	}

	private void updateCustomerRefundRequests(String transId, String status,
			String remark) {
		CustomerRefundRequest customerRefundRequest = customerRefundDao
				.getCustomerRefundRequestBean(transId);
		customerRefundRequest.setRemark(remark);
		customerRefundRequest.setStatus(status);
		customerRefundRequest.setUpdatedAt(TimeStampUtil.getTimestamp());
		commonDao.updateEntity(customerRefundRequest);

	}

	@Override
	public String refundAmount(Long id, String senderId, Double refundedAmount,
			String custId) {
		updateCompanyBalance(refundedAmount, custId);
		double newB2bBal = updateCustomerAccount(custId, senderId,
				refundedAmount);
		updateTranBean(id, refundedAmount, newB2bBal);
		return CommonConstants.SUCCESS;

	}

	private void updateTranBean(Long id, Double refundedAmount, double newB2bBal) {
		CustomerTransactionTransportBean customerTransactionTransportBean = (CustomerTransactionTransportBean) commonDao
				.getEntityByPrimaryKey(CustomerTransactionTransportBean.class,
						id);
		customerTransactionTransportBean.setStatus(CommonConstants.REFUND);
		updateCustomerRefundRequests(
				customerTransactionTransportBean.getClientTransId(),
				CommonConstants.REFUND, "");
		customerTransactionTransportBean.setUpdatedAt(TimeStampUtil
				.getTimestamp());
		customerTransactionTransportBean.setRefundAmount(refundedAmount); 
		customerTransactionTransportBean.setRetailerNewBal(newB2bBal);
		commonDao.updateEntity(customerTransactionTransportBean);

	}

	private double updateCustomerAccount(String custId, String senderId,
			Double refundedAmount) {
		long customerID = Long.parseLong(custId);
		CustomerCurrentBalance customerCurrentBalance = customerBalanceCheckingDao
				.getBalance(customerID);
		CustomerBalanceTransferLog log = new CustomerBalanceTransferLog();
		log.setCustomerId(customerID);
		log.setTransferType(CommonConstants.TRANSFER_TYPE_CREDIT);
		log.setTransferFrom(CommonConstants.COMPANY_TRANSFER_FROM);
		log.setTransferTo(CommonConstants.COMP_TRANSFER_TO);
		log.setCreatedAt(TimeStampUtil.getTimestamp());
		log.setTransactionId(CyberTelUtil.generateTransId(
				CommonConstants.COMP_TO_CUST, null));
		Users users = new Users();
		users.setUserId(Long.valueOf(senderId));
		UserDetail userDetail = new UserDetail();
		userDetail.setUsers(users);
		log.setUserDetail(userDetail);
		log.setPrecurrbal(customerCurrentBalance.getCurrAcBalance());
		log.setTransferAmount(refundedAmount);
		log.setNewCurrBal(customerCurrentBalance.getCurrAcBalance()+refundedAmount);

		customerRefundDao.setCustomerBalTransferLog(log);
		double finalAmount = customerRefundDao.updateCustomerCurrBal(
				customerCurrentBalance.getCustomerId(), refundedAmount);
		return finalAmount;
	}

	private void updateCompanyBalance(Double refundedAmount, String custId) {
		CompanyBalance companyBalance = registrationDao
				.loadCompanyAccountBalance();
		CompanyBalTransactionLog cLog = new CompanyBalTransactionLog();
		Users users = new Users();
		users.setUserId(CyberTelUtil.getStrInLong(custId));
		cLog.setUserType(CommonConstants.ROLE_CUSTOMER);
		cLog.setUserId(users);
		cLog.setTransferAmount(refundedAmount);
		cLog.setTransferType(CommonConstants.TRANSFER_TYPE_DEBIT);
		cLog.setCreatedAt(TimeStampUtil.getTimestamp());
		cLog.setTransactionId(CyberTelUtil.generateTransId(
				CommonConstants.COMP_TO_CUST, null));
		cLog.setPreBalance(companyBalance.getCurrBalance());
		cLog.setNewBalance(companyBalance.getCurrBalance() - refundedAmount);
		companyBalance.setCurrBalance(companyBalance.getCurrBalance()
				- refundedAmount);
		commonDao.updateEntity(companyBalance);
		commonDao.saveEntity(cLog);

	}
}
