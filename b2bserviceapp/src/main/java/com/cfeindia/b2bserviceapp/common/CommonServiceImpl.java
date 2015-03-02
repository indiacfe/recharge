package com.cfeindia.b2bserviceapp.common;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.dao.CommonDao;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.UserRole;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.service.distributor.DistributorSearchFranchiseeDao;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {
	@Autowired
	CommonDao commondao;

	@Autowired
	DistributorSearchFranchiseeDao distributorSearchFranchiseeDao;

	public CommonDao getCommondao() {
		return commondao;
	}

	public void setCommondao(CommonDao commondao) {
		this.commondao = commondao;
	}

	public DistributorSearchFranchiseeDao getDistributorSearchFranchiseeDao() {
		return distributorSearchFranchiseeDao;
	}

	public void setDistributorSearchFranchiseeDao(
			DistributorSearchFranchiseeDao distributorSearchFranchiseeDao) {
		this.distributorSearchFranchiseeDao = distributorSearchFranchiseeDao;
	}

	public void saveEntity(Object obj) {
		commondao.saveEntity(obj);
	}

	public Object getEntityByPrimaryKey(Class classObj, long id) {
		return commondao.getEntityByPrimaryKey(classObj, id);
	}

	public int getUserId(String username) {
		int userId = 0;
		try {
			userId = commondao.getUserId(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userId;
	}

	public double getOperatorCommision(String operatorName) {
		return commondao.getOperatorCommision(operatorName);
	}

	public String getThirdPartyAPISelection(String serviceType,
			String operatorName) {
		return commondao.getThirdPartyAPISelection(serviceType, operatorName);
	}

	public double getRetailerCommission(String thirdpartyServiceProvider,
			String rechargeType, String operatorName) {
		return commondao.getRetailerCommission(thirdpartyServiceProvider,
				rechargeType, operatorName);
	}

	public String getUsername(String userId) {
		long userid = Long.valueOf(userId);
		return commondao.getUsername(userid);
	}

	public Users searchdistributorId(String DistId) {

		// UserRole user= commondao.searchdistributorId(DistId);
		Users username = distributorSearchFranchiseeDao
				.searchUserBasedOnIdOrMobNum(DistId);
		/*
		 * if(user.getAuthority().equalsIgnoreCase("ROLE_DISTRIBUTOR")) {
		 * 
		 * long u=user.getUserId(); username=
		 * distributorSearchFranchiseeDao.searchUserBasedOnIdOrMobNum
		 * (String.valueOf(u)); return username;
		 * 
		 * }
		 */
		return username;

	}

	public List<FranchiseeCashDepositRequest> trackCashDepositRequests() {
		List<FranchiseeCashDepositRequest> franchiseeCashDepositRequestList = commondao
				.trackCashDepositRequests();
		for (FranchiseeCashDepositRequest franchiseeCashDepositRequest : franchiseeCashDepositRequestList) {
			Users user = commondao.getUsers(franchiseeCashDepositRequest
					.getRequesterId());
			if(user!=null)
			{
				franchiseeCashDepositRequest.setMobileNumber(user.getUsername());
			}
			franchiseeCashDepositRequest.setMobileNumber(user.getUsername());
			UserDetail userDetail=user.getUserDetail();
			if (userDetail != null)
				franchiseeCashDepositRequest.setFirmName(userDetail
						.getFirmName());
		}
		return franchiseeCashDepositRequestList;
	}

	public List<FranchiseeCashDepositRequest> trackAllCashDepositRequests() {
		List<FranchiseeCashDepositRequest> franchiseeCashDepositRequestList = commondao
				.trackAllCashDepositRequests();
		return franchiseeCashDepositRequestList;
	}

	public UserDetail getUserDetail(String userId) {
		long id = CyberTelUtil.getStrInLong(userId);
		return commondao.getUserDetail(id);
	}

	public String checkTypeOfUser(String userId) {
		String str = commondao.checkTypeOfUser(userId);
		return str;
	}

	public void setStatusToSuccess(long id) {
		commondao.setStatusToSuccess(id);
	}

	public List<DistributorBalanceTransferLog> getBalanceTransferLogs() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<DistributorBalanceTransferLog> balanceTransferLogs = commondao
				.findAll(DistributorBalanceTransferLog.class);
		PrintWriter pr = null;
		try {
			pr = new PrintWriter("D:/distributorBalTransferLogs.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (DistributorBalanceTransferLog bean : balanceTransferLogs) {
			String transId = bean.getTransactionId();
			transId.trim();
			String date1 = transId.substring(transId.length() - 12);
			Date date = null;
			try {
				date = sdf1.parse(date1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String formatted = sdf2.format(date);
			String s1 = "UPDATE distributorbaltransferlog SET created_at = '"
					+ formatted + "' WHERE id=" + bean.getId() + ";";
			pr.println(s1);
			long time = date.getTime();
			Timestamp timeStamp = new Timestamp(time);
			System.out.println("formatted date" + date);
			bean.setCreatedAt(timeStamp);
		}
		pr.close();
		return balanceTransferLogs;
	}
}
