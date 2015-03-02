package com.cfeindia.b2bserviceapp.service.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.customer.CustomerBalanceCheckingDao;
import com.cfeindia.b2bserviceapp.dao.customer.CustomerFundTransferDao;
import com.cfeindia.b2bserviceapp.dao.customer.CustomerInfoDao;
import com.cfeindia.b2bserviceapp.dao.distributor.FundTransferDao;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CompanyCustomerTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CustomerBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class CustomerFundtransferServiceImpl implements
		CustomerFundTransferService {
	@Autowired
	private CommonDao commonDao;

	@Autowired
	CustomerBalanceCheckingDao customerBalanceCheckingDao;

	@Autowired
	private FundTransferDao fundTransferDao;

	@Autowired
	CustomerFundTransferDao customerFundTransferDao;
	@Autowired
	private CustomerInfoDao customerInfoDao;
	@Autowired
	private AdminUtilityService adminUtilityService;

	public Users getCustomer(String userIdOrMobnum) {

		Users users;

		if (userIdOrMobnum != null
				&& (userIdOrMobnum.startsWith("C") || userIdOrMobnum
						.startsWith("c"))) {
			String userId = ExtractorUtil.extractIdFromString(userIdOrMobnum);
			String str = commonDao.checkTypeOfUser(userId);
			if (CommonConstants.ROLE_CUSTOMER.equalsIgnoreCase(str)) {
				users = commonDao.searchUserBasedOnIdOrMobNum(userIdOrMobnum);
				return users;
			} else {
				return null;
			}
		} else {
			Long userId = (long) commonDao.getUserId(userIdOrMobnum);
			String str = commonDao.checkTypeOfUser(userId.toString());
			if (CommonConstants.ROLE_CUSTOMER.equalsIgnoreCase(str)) {
				return commonDao.searchUserBasedOnIdOrMobNum(userIdOrMobnum);
			} else {
				return null;
			}
		}

	}

	@Override
	public CustomerCurrentBalance getCustCurrBal(Long custId) {

		return customerBalanceCheckingDao.getBalance(custId);
	}

	@Override
	public String companyBalanceTransToCustService(Long customerId,
			double transferAmount, String companyName, Long userId) {
		CompanyCustomerTransactionLog companyCustomerTransactionLog = new CompanyCustomerTransactionLog();
		CustomerBalanceTransferLog customerBalanceTransferLog = new CustomerBalanceTransferLog();
		customerBalanceTransferLog.setCustomerId(customerId);
		CustomerCurrentBalance customerCurrentBalance = customerBalanceCheckingDao
				.getBalance(customerId);
		double dCurrBal = customerCurrentBalance.getCurrAcBalance();
		customerBalanceTransferLog.setPrecurrbal(dCurrBal);
		CompanyBalance companyBalance = fundTransferDao
				.getCompanyBalance(companyName);
		double compCurrBal = companyBalance.getCurrBalance();
		if (compCurrBal >= transferAmount) {
			companyCustomerTransactionLog.setPreBalance(compCurrBal);

			dCurrBal = dCurrBal + transferAmount;
			customerBalanceTransferLog.setNewCurrBal(dCurrBal);
			customerBalanceTransferLog.setTransactionId(CyberTelUtil
					.generateTransId(
							CommonConstants.COMPANY_TO_CUST_TRANSID_PREFIX,
							null));
			customerBalanceTransferLog.setTransferAmount(transferAmount);
			customerBalanceTransferLog
					.setTransferFrom(CommonConstants.COMPANY_TRANSFER_FROM);
			customerBalanceTransferLog
					.setTransferTo(CommonConstants.CUST_CURR_BAL);
			customerBalanceTransferLog
					.setTransferType(CommonConstants.TRANSFER_TYPE_CREDIT);
			customerBalanceTransferLog.setCreatedAt(TimeStampUtil
					.getTimestamp());
			UserDetail userDetails = new UserDetail();
			Users user1 = new Users();
			user1.setUserId(userId);
			userDetails.setUsers(user1);
			customerBalanceTransferLog.setUserDetail(userDetails);
			compCurrBal = compCurrBal - transferAmount;
			companyCustomerTransactionLog.setNewBalance(compCurrBal);
			customerCurrentBalance.setCurrAcBalance(dCurrBal);
			companyBalance.setCurrBalance(compCurrBal);
			Users user = new Users();
			user.setUserId(customerId);
			companyCustomerTransactionLog.setCustomerId(user);
			companyCustomerTransactionLog.setTransferAmount(transferAmount);
			companyCustomerTransactionLog
					.setTransferType(CommonConstants.TRANSFER_TYPE_DEBIT);
			companyCustomerTransactionLog.setTransId(CyberTelUtil
					.generateTransId(
							CommonConstants.COMPANY_TO_CUST_TRANSID_PREFIX,
							null));
			companyCustomerTransactionLog.setCreatedAt(TimeStampUtil
					.getTimestamp());
			companyCustomerTransactionLog
					.setTransferFrom(CommonConstants.COMPANY_TRANSFER_FROM);

			companyCustomerTransactionLog
					.setTransferTo(CommonConstants.CUST_CURR_BAL);

			commonDao.updateCompanybalance(companyBalance);
			customerFundTransferDao.updateCustomerB2bAdunitBal(customerId);
			customerFundTransferDao
					.companyCustomerTransactionLog(companyCustomerTransactionLog);
			customerFundTransferDao
					.setCustomerBalTransferLog(customerBalanceTransferLog);
			return CommonConstants.SUCCESS;
		} else {
			return CommonConstants.FAILURE;
		}
	}

	@Override
	public List<UserDetailDto> getUsers() {
		List<UserDetailDto> dto = new ArrayList<UserDetailDto>();
		List<Users> users = customerInfoDao.getUsers();
		Iterator<Users> itr = users.iterator();
		while (itr.hasNext()) {
			UserDetailDto udto = new UserDetailDto();
			Users u = itr.next();
			udto.setUserId(String.valueOf(u.getUserId()));
			udto.setFirmName(u.getUserDetail().getFirmName());
			dto.add(udto);
		}

		return dto;
	}

	@Override
	public List<CustomerCommision> getCommisions(Long custId) {

		return customerInfoDao.getCustomerCommisions(custId);
	}

	@Override
	public String deductAmountFromApiAccount(Long customerId,
			double deductAmount, String remark) {
		CompanyCustomerTransactionLog companyCustomerTransactionLog = new CompanyCustomerTransactionLog();

		CustomerCurrentBalance customerCurrentBalance = customerBalanceCheckingDao
				.getBalance(customerId);
		double customerCurrBal = customerCurrentBalance.getCurrAcBalance();
		CompanyBalance companyBalance = fundTransferDao
				.getCompanyBalance(CommonConstants.companyName);
		double companyCurrBal = companyBalance.getCurrBalance();
		if (customerCurrBal >= deductAmount) {
			companyCustomerTransactionLog.setPreBalance(companyCurrBal);
			companyCustomerTransactionLog.setNewBalance(companyCurrBal
					+ deductAmount);
			Users users = new Users();
			users.setUserId(customerId);
			companyCustomerTransactionLog.setCustomerId(users);
			companyCustomerTransactionLog.setTransferAmount(deductAmount);
			companyCustomerTransactionLog
					.setTransferType(CommonConstants.TRANSFER_TYPE_CREDIT);
			companyCustomerTransactionLog.setRemark(remark);
			companyCustomerTransactionLog.setTransId(CyberTelUtil
					.generateTransId(
							CommonConstants.CUST_TO_COMPANY_TRANSID_PREFIX,
							null));
			companyCustomerTransactionLog.setCreatedAt(TimeStampUtil
					.getTimestamp());  
			companyCustomerTransactionLog
					.setTransferFrom(CommonConstants.CUST_CURR_BAL);
			companyCustomerTransactionLog
					.setTransferTo(CommonConstants.COMPANY_TRANSFER_FROM);
			adminUtilityService.updateCompanyAccount(deductAmount);
			customerFundTransferDao.deductCustomerBusinessBalance(customerId,
					deductAmount);
			customerFundTransferDao
					.companyCustomerTransactionLog(companyCustomerTransactionLog);

			return CommonConstants.SUCCESS;
		} else {
			return CommonConstants.FAILURE;
		}

	}
}