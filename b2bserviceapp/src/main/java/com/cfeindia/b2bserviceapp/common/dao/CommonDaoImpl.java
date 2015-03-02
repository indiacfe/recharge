package com.cfeindia.b2bserviceapp.common.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyServiceProvider;
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
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Repository("commonDao")
@Transactional
public class CommonDaoImpl extends BaseDao implements CommonDao {

	public int getUserId(String username) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(Users.class)
				.add(Restrictions.eq("username", username));
		Users user = (Users) criteria.uniqueResult();
		return (int) user.getUserId();
	}
	
	public void updateEntity(Object obj)
	{
		sessionFactory.getCurrentSession().update(obj);
	}
	
	public void saveEntity(Object obj)
	{
		sessionFactory.getCurrentSession().save(obj);
	}

	public double getOperatorCommision(String operatorName) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(CompanyOperatorComission.class)
				.add(Restrictions.eq("operatorName", operatorName));
		CompanyOperatorComission commision = (CompanyOperatorComission) criteria
				.uniqueResult();
		return commision.getRetailercommision();
	}

	public List<CompanyOperatorComission> getTelecomOperator() {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(CompanyOperatorComission.class);
		criteria = criteria.add(Restrictions.eq("rechargeType",
				"MOBILE_PREPAID"));
		List<CompanyOperatorComission> mobileOperatorList = criteria.list();
		return mobileOperatorList;
	}

	public double getCompanyAccountBalance() {
		CompanyBalance companyBalance = (CompanyBalance) getSessionFactory()
				.getCurrentSession().load(CompanyBalance.class, 1L);
		double cBalance = companyBalance.getCurrBalance();
		return cBalance;
	}

	public List<UserRole> getDistributorsList() {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(UserRole.class);
		criteria = criteria.add(Restrictions
				.eq("authority", "ROLE_DISTRIBUTOR"));
		List<UserRole> userList = criteria.list();
		return userList;
	}

	public List<UserRole> getFranchiseeList() {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(UserRole.class);
		criteria = criteria
				.add(Restrictions.eq("authority", "ROLE_FRANCHISEE"));
		List<UserRole> userList = criteria.list();
		return userList;
	}

	public UserDetail getUserDetail(Long userId) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(UserDetail.class);
		criteria = criteria.add(Restrictions.eq("users.userId", userId));
		UserDetail userDetail = (UserDetail) criteria.uniqueResult();
		return userDetail;
	}

	public CompanyBalance getCompanyBalance() {
		CompanyBalance companyBalance = (CompanyBalance) getSessionFactory()
				.getCurrentSession().load(CompanyBalance.class, 1L);
		return companyBalance;
	}

	public boolean updateCompanybalance(CompanyBalance companyBalance) {
		boolean ret = false;
		try {
			getSessionFactory().getCurrentSession().update(companyBalance);
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}

	public Users getUsers(Long userId) {
		Users users = (Users) getSessionFactory().getCurrentSession().load(Users.class, userId);

		return users;
	}

	public boolean setDistributorCommission(
			DistributorCommision distributorCommision) {
		boolean ret = false;
		try {
			getSessionFactory().getCurrentSession()
					.update(distributorCommision);
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}

	public DistributorCommision getDistributorCommision(Long distributorId) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(DistributorCommision.class)
				.add(Restrictions.eq("distributorId", distributorId));
		DistributorCommision distributorCommision = (DistributorCommision) criteria
				.uniqueResult();

		return distributorCommision;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> persistentClass) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(persistentClass);
		List<T> objList = criteria.list();
		return objList;
	}

	public double getRetailerCommission(String thirdpartyServiceProvider,
			String rechargeType, String operatorName) {
		CompanyOperatorComission comission = (CompanyOperatorComission) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CompanyOperatorComission.class)
				.add(Restrictions.eq("thirdpartyServiceProvider",
						thirdpartyServiceProvider))
				.add(Restrictions.eq("rechargeType", rechargeType))
				.add(Restrictions.eq("operatorName", operatorName))
				.uniqueResult();
		double commissionVal = 0.0;
		if(comission!=null) {
			commissionVal = comission.getRetailercommision();
		} else {
			if("MOBILE_PREPAID".equalsIgnoreCase(rechargeType))
				commissionVal = 1.5;
			else 
				commissionVal = -1.5;
		}			
		return commissionVal;
	}

	public String getThirdPartyAPISelection(String serviceType,
			String operatorName) {
		ThirdPartyServiceProvider thirdpartyServiceProvider = (ThirdPartyServiceProvider) getSessionFactory()
				.getCurrentSession()
				.createCriteria(ThirdPartyServiceProvider.class)
				.add(Restrictions.eq("serviceType", serviceType))
				.add(Restrictions.eq("operatorName", operatorName))
				.uniqueResult();
		return thirdpartyServiceProvider.getServiceProvider();
	}

	public void updateThirdPartyAPISelection(String serviceType,
			String operatorName, String thirdPartyServiceProviderName) {
		ThirdPartyServiceProvider thirdpartyServiceProvider = (ThirdPartyServiceProvider) getSessionFactory()
				.getCurrentSession()
				.createCriteria(ThirdPartyServiceProvider.class)
				.add(Restrictions.eq("serviceType", serviceType))
				.add(Restrictions.eq("operatorName", operatorName))
				.uniqueResult();
		thirdpartyServiceProvider
				.setServiceProvider(thirdPartyServiceProviderName);
		updateEntity(thirdpartyServiceProvider);
	}

	public String getUsername(long userId) {

		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Users.class)
				.add(Restrictions.eq("userId", userId));
		Users users=(Users) criteria.uniqueResult();
		return users.getUsername().toString();
	}

	public String checkTypeOfUser(String userId) {
		UserRole userRole = (UserRole) getSessionFactory().getCurrentSession()
				.createCriteria(UserRole.class)
				.add(Restrictions.eq("userId.userId", Long.parseLong(userId)))
				.uniqueResult();

		return userRole.getAuthority().toString();
	}

	public CompanyOperatorComission getCompanyOperatorCommissionItem(
			String rechargeType, String thirdPartyName, String operatorName) {
		CompanyOperatorComission companyOperatorComission = (CompanyOperatorComission) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CompanyOperatorComission.class)
				.add(Restrictions.eq("rechargeType", rechargeType))
				.add(Restrictions.eq("thirdpartyServiceProvider",
						thirdPartyName))
				.add(Restrictions.eq("operatorName", operatorName))
				.uniqueResult();
		return companyOperatorComission;
	}

	public UserRole searchdistributorId(String DistId) {

				Criteria criteria = getSessionFactory()
				.getCurrentSession()
				.createCriteria(UserRole.class)
				.add(Restrictions.and(
						Restrictions.eq("userId", Long.parseLong(DistId)),
						Restrictions.eq("authority", "ROLE_DISTRIBUTOR")));

		UserRole u = null;
		if (criteria.uniqueResult() != null)
			u = (UserRole) criteria.uniqueResult();
		return u;

	}

	public TransactionTransportBean getEntityByKey(String tid){
		TransactionTransportBean transactionTransportBean=(TransactionTransportBean)getSessionFactory().getCurrentSession().createCriteria(TransactionTransportBean.class).add(Restrictions.eq("transid", tid)).uniqueResult();
		return  transactionTransportBean;
	}

	public void saveEntityById(Class c, Long id) {
		getSessionFactory().getCurrentSession().load(c, id);
		
	}

	public List<FranchiseeCashDepositRequest> trackCashDepositRequests() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				FranchiseeCashDepositRequest.class)
				.add(Restrictions.eq("status",CommonConstants.IN_PROCESS));
		List<FranchiseeCashDepositRequest> list = criteria.list();
		return list;
	}

	public List<FranchiseeCashDepositRequest> trackAllCashDepositRequests() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				FranchiseeCashDepositRequest.class)
				.add(Restrictions.eq("status",CommonConstants.COMPLETE));
		List<FranchiseeCashDepositRequest> list = criteria.list();
		return list;
	}	

	public void setStatusToSuccess(long id) 
	{
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(FranchiseeCashDepositRequest.class)
		.add(Restrictions.eq("id", id));
		FranchiseeCashDepositRequest franchiseeCashDepositRequest=(FranchiseeCashDepositRequest)criteria.uniqueResult();
		franchiseeCashDepositRequest.setStatus(CommonConstants.COMPLETE);
		franchiseeCashDepositRequest.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().save(franchiseeCashDepositRequest);
	}

	public FranchiseeRefundRequests getFranchiseeRefundRequestBean(String tId) {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(FranchiseeRefundRequests.class)
		.add(Restrictions.eq("transId", tId));
		return (FranchiseeRefundRequests) criteria.uniqueResult();
	}

	public void saveOrUpdateEntity(Class c, Long id) {
		// TODO Auto-generated method stub		
	}

	public List<FranchiseeRefundRequests> getFranchiseeRefundRequestsList() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(FranchiseeRefundRequests.class)
				.add(Restrictions.eq("status", CommonConstants.IN_PROCESS));
		
		return criteria.list();
	}
	
	public Users searchUserBasedOnIdOrMobNum(String userIdOrMobnum) {
		long userid = 0;
		try {
			userid = Long.parseLong(userIdOrMobnum);
		} catch (Exception e) {

		}
		Criteria criteria = getSessionFactory()
				.getCurrentSession()
				.createCriteria(Users.class)
				.add(Restrictions.or(Restrictions.eq("userId", userid),
						Restrictions.eq("username", userIdOrMobnum)));
		return 	(Users) criteria.uniqueResult();
		
	}
	public Object getEntityByPrimaryKey(Class classObj, long id)
	{
		Object object=sessionFactory.getCurrentSession().get(classObj, id);
		return object;
		
	}

}
