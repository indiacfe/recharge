package com.cfeindia.b2bserviceapp.dao.common;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyServiceProvider;
import com.cfeindia.b2bserviceapp.entity.AirTelUserDetail;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;
import com.cfeindia.b2bserviceapp.entity.FranchiseeRefundRequests;
import com.cfeindia.b2bserviceapp.entity.NoticeInfo;
import com.cfeindia.b2bserviceapp.entity.OutletDetail;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.UserRole;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface CommonDao {

	public void saveOrUpdateEntity(Class c, Long id);

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

	public List<Users> getCustomerList();

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

	public <T> List<T> Distributors(String roleauth);

	public <T> List<T> userDetailListRetailers(Long disId);

	public CompanyOperatorComission getRetailerCommission(
			String thirdpartyServiceProvider, String rechargeType,
			String operatorName, String comissionType);

	public double getCustomerCommission(String thirdpartyServiceProvider,
			String rechargeType, String operatorName, Long userId);

	public double getLicRetailerCommission(String comissionType,
			String rechargeType, String operatorName);

	public String getThirdPartyAPISelection(String serviceType,
			String operatorName);

	public String getUsername(long userId);

	public String checkTypeOfUser(String userId);

	public UserRole searchdistributorId(String DistId);

	public CompanyOperatorComission getCompanyOperatorCommissionItem(
			String rechargeType, String thirdPartyName, String operatorName,
			String commissionType);

	public ThirdPartyServiceProvider getThirdPartyServiceProvider(String serviceType,
			String operatorName, String thirdPartyServiceProviderName);

	public void setStatusToSuccess(long id);

	public FranchiseeRefundRequests getFranchiseeRefundRequestBean(String tId);

	public List<FranchiseeRefundRequests> getFranchiseeRefundRequestsList();

	public Users searchUserBasedOnIdOrMobNum(String userIdOrMobnum);

	public boolean userUpdate(Users user);

	public UserDetail getUserByEmail(String emailId);

	public Users getDistributor(Long userId);

	public List<NoticeInfo> getNoticeRetailer();

	public List<NoticeInfo> getNoticeDistributor();

	public List<NoticeInfo> getNoticeHome();

	public void saveOrUpdateEntity(Object obj);

	public OutletDetail getOutletDetail(Long retailerId, String operator,
			String thirdpartyname);

	public Users getsender(String username);

	public CustomerCommision getCommission(CustomerCommision customerCommision);

	public TransactionTransportBean getFranTranstionDetail(String transId,
			Long franId);

	public CustomerTransactionTransportBean getCustTransationDetails(
			String clientTransId, Long userId);

	public CustomerCommision getCustomerCommissionObject(
			String thirdpartyServiceProvider, String rechargeType,
			String operatorName, Long userId);

	public AirTelUserDetail getAirtelUserName(String retailerMobNoOrUserName);

	public Long save(Object obj);

	public void delete(Object obj);
	public List<FranchiseeRefundRequests> getFranchiseeRejectedRefundRequestsList(Timestamp fromDate, Timestamp toDate);

}
