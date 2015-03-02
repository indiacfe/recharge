package com.cfeindia.b2bserviceapp.common.dao;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.UserRole;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface CommonDao {

	public void saveOrUpdateEntity(Class c,Long id);

	public void saveEntity(Object obj);
	public void updateEntity(Object obj);

	public TransactionTransportBean getEntityByKey(String tid);

	public Object getEntityByPrimaryKey(Class classObj, long id);

	public int getUserId(String username);

	public double getOperatorCommision(String operatorName);

	public List<CompanyOperatorComission> getTelecomOperator();

	public double getCompanyAccountBalance();

	public List<UserRole> getDistributorsList();

	public List<UserRole> getFranchiseeList();

	public UserDetail getUserDetail(Long userId);

	public List<FranchiseeCashDepositRequest> trackCashDepositRequests();

	public List<FranchiseeCashDepositRequest> trackAllCashDepositRequests();

	public CompanyBalance getCompanyBalance();

	public boolean updateCompanybalance(CompanyBalance combalance);

	public Users getUsers(Long userId);

	public boolean setDistributorCommission(
			DistributorCommision distributorCommision);

	public DistributorCommision getDistributorCommision(Long distributorId);

	public <T> List<T> findAll(Class<T> persistentClass);

	public double getRetailerCommission(String thirdpartyServiceProvider,
			String rechargeType, String operatorName);

	public String getThirdPartyAPISelection(String serviceType,
			String operatorName);

	public String getUsername(long userId);

	public String checkTypeOfUser(String userId);

	public UserRole searchdistributorId(String DistId);

	public CompanyOperatorComission getCompanyOperatorCommissionItem(
			String rechargeType, String thirdPartyName, String operatorName);

	public void updateThirdPartyAPISelection(String serviceType,
			String operatorName, String thirdPartyServiceProviderName);

	public void setStatusToSuccess(long id);

	public FranchiseeRefundRequests getFranchiseeRefundRequestBean(String tId);
	
	public List<FranchiseeRefundRequests> getFranchiseeRefundRequestsList();
	
	public Users searchUserBasedOnIdOrMobNum(String userIdOrMobnum);
	}
