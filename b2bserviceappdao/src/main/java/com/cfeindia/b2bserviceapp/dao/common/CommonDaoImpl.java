package com.cfeindia.b2bserviceapp.dao.common;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
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
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Repository("commonDao")
@Transactional
public class CommonDaoImpl extends BaseDao implements CommonDao {

	public int getUserId(String username) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(Users.class)
				.add(Restrictions.eq("username", username));
		Users user = (Users) criteria.uniqueResult();
		if (user == null)
			return 0;
		return (int) user.getUserId();
	}

	public Users getsender(String username) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(Users.class)
				.add(Restrictions.eq("username", username));
		Users user = (Users) criteria.uniqueResult();
		return user;
	}

	public void updateEntity(Object obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	public void saveEntity(Object obj) {
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

	public Users getDistributor(Long userId) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(Users.class);
		criteria = criteria.add(Restrictions.eq("userId", userId));
		Users users = (Users) criteria.uniqueResult();
		return users;
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
		Users users = (Users) getSessionFactory().getCurrentSession().load(
				Users.class, userId);

		return users;
	}

	public boolean userUpdate(Users user) {
		boolean ret = false;
		try {
			getSessionFactory().getCurrentSession().update(user);
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		return ret;

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

	public <T> List<T> Distributors(String authority) {
		org.hibernate.Query query = getSessionFactory().getCurrentSession()
				.createQuery(
						"from Users as u where u.userRole.authority=:urole");
		query.setString("urole", authority);
		List<T> objList = query.list();
		return objList;
	}

	public <T> List<T> userDetailListRetailers(Long disId) {
		org.hibernate.Query query = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from FranchiseeCurrBal as fcb where fcb.creatorId=:distid");
		query.setLong("distid", disId);
		List<T> objList = query.list();
		return objList;
	}

	public CompanyOperatorComission getRetailerCommission(
			String thirdpartyServiceProvider, String rechargeType,
			String operatorName, String commissionType) {
		CompanyOperatorComission comission = (CompanyOperatorComission) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CompanyOperatorComission.class)
				.add(Restrictions.eq("thirdpartyServiceProvider",
						thirdpartyServiceProvider))
				.add(Restrictions.eq("rechargeType", rechargeType))
				.add(Restrictions.eq("operatorName", operatorName))
				.add(Restrictions.eq("comissionType", commissionType))
				.uniqueResult();
		return comission;

	}

	/* this method give the third party service provider name if exist */
	public String getThirdPartyAPISelection(String serviceType,
			String operatorName) {

		ThirdPartyServiceProvider thirdpartyServiceProvider = (ThirdPartyServiceProvider) getSessionFactory()
				.getCurrentSession()
				.createCriteria(ThirdPartyServiceProvider.class)
				.add(Restrictions.eq("serviceType", serviceType))
				.add(Restrictions.eq("operatorName", operatorName))
				.uniqueResult();
		if (thirdpartyServiceProvider != null) {
			return thirdpartyServiceProvider.getServiceProvider();
		} else {
			return null;
		}

	}

	/* this method give the third party service provider object if exist */
	public ThirdPartyServiceProvider getThirdPartyServiceProvider(
			String serviceType, String operatorName,
			String thirdPartyServiceProviderName) {
		ThirdPartyServiceProvider thirdpartyServiceProvider = (ThirdPartyServiceProvider) getSessionFactory()
				.getCurrentSession()
				.createCriteria(ThirdPartyServiceProvider.class)
				.add(Restrictions.eq("serviceType", serviceType))
				.add(Restrictions.eq("operatorName", operatorName))
				.uniqueResult();
		return thirdpartyServiceProvider;
	}

	public String getUsername(long userId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Users.class)
				.add(Restrictions.eq("userId", userId));
		Users users = (Users) criteria.uniqueResult();
		return users.getUsername().toString();
	}

	public String checkTypeOfUser(String userId) {
		UserRole userRole = (UserRole) getSessionFactory().getCurrentSession()
				.createCriteria(UserRole.class)
				.add(Restrictions.eq("userId.userId", Long.parseLong(userId)))
				.uniqueResult();
		if (userRole == null)
			return "";
		return userRole.getAuthority().toString();
	}

	public CompanyOperatorComission getCompanyOperatorCommissionItem(
			String rechargeType, String thirdPartyName, String operatorName,
			String commissionType) {
		CompanyOperatorComission companyOperatorComission = (CompanyOperatorComission) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CompanyOperatorComission.class)
				.add(Restrictions.eq("rechargeType", rechargeType))
				.add(Restrictions.eq("thirdpartyServiceProvider",
						thirdPartyName))
				.add(Restrictions.eq("operatorName", operatorName))
				.add(Restrictions.eq("comissionType", commissionType))
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

	public TransactionTransportBean getEntityByKey(String tid) {
		TransactionTransportBean transactionTransportBean = (TransactionTransportBean) getSessionFactory()
				.getCurrentSession()
				.createCriteria(TransactionTransportBean.class)
				.add(Restrictions.eq("transid", tid)).uniqueResult();
		return transactionTransportBean;
	}

	public void saveEntityById(Class c, Long id) {
		getSessionFactory().getCurrentSession().load(c, id);

	}

	public List<FranchiseeCashDepositRequest> trackCashDepositRequests() {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(FranchiseeCashDepositRequest.class)
				.add(Restrictions.eq("status", CommonConstants.IN_PROCESS));
		List<FranchiseeCashDepositRequest> list = criteria.list();
		return list;
	}

	public List<FranchiseeCashDepositRequest> trackAllCashDepositRequests() {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(FranchiseeCashDepositRequest.class)
				.add(Restrictions.eq("status", CommonConstants.COMPLETE));
		List<FranchiseeCashDepositRequest> list = criteria.list();
		return list;
	}

	public void setStatusToSuccess(long id) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(FranchiseeCashDepositRequest.class)
				.add(Restrictions.eq("id", id));
		FranchiseeCashDepositRequest franchiseeCashDepositRequest = (FranchiseeCashDepositRequest) criteria
				.uniqueResult();
		franchiseeCashDepositRequest.setStatus(CommonConstants.COMPLETE);
		franchiseeCashDepositRequest.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().save(franchiseeCashDepositRequest);
	}

	public FranchiseeRefundRequests getFranchiseeRefundRequestBean(String tId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(FranchiseeRefundRequests.class)
				.add(Restrictions.eq("transId", tId));
		return (FranchiseeRefundRequests) criteria.uniqueResult();
	}

	public void saveOrUpdateEntity(Class c, Long id) {

	}

	public List<FranchiseeRefundRequests> getFranchiseeRefundRequestsList() {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(FranchiseeRefundRequests.class)
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
		return (Users) criteria.uniqueResult();

	}

	public Object getEntityByPrimaryKey(Class classObj, long id) {
		Object object = sessionFactory.getCurrentSession().get(classObj, id);
		return object;

	}

	public UserDetail getUserByEmail(String emailId) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(UserDetail.class)
				.add(Restrictions.eq("emailId", emailId));
		UserDetail userDetail = (UserDetail) criteria.uniqueResult();

		return userDetail;
	}

	public List<NoticeInfo> getNoticeRetailer() {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(NoticeInfo.class)
				.add(Restrictions.eq("activeNews", "Y"))
				.add(Restrictions.eq("retailerEnable", 1));
		return (List<NoticeInfo>) criteria.list();

	}

	public List<NoticeInfo> getNoticeDistributor() {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(NoticeInfo.class)
				.add(Restrictions.eq("activeNews", "Y"))
				.add(Restrictions.eq("distributorEnable", 1));
		return (List<NoticeInfo>) criteria.list();

	}

	public List<NoticeInfo> getNoticeHome() {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(NoticeInfo.class)
				.add(Restrictions.eq("activeNews", "Y"))
				.add(Restrictions.eq("homePageEnable", 1));
		return (List<NoticeInfo>) criteria.list();
	}

	public void saveOrUpdateEntity(Object obj) {
		getSessionFactory().getCurrentSession().saveOrUpdate(obj);
	}

	public OutletDetail getOutletDetail(Long retailerId, String operator,
			String thirdpartyname) {
		Criteria criteria = getSessionFactory()
				.getCurrentSession()
				.createCriteria(OutletDetail.class)
				.add(Restrictions.eq("outletDetailPK.franchiseeId", retailerId))
				.add(Restrictions.eq("outletDetailPK.operator", operator))
				.add(Restrictions.eq("outletDetailPK.thirdpartyname",
						thirdpartyname));

		return (OutletDetail) criteria.uniqueResult();
	}

	public double getLicRetailerCommission(String comissionType,
			String rechargeType, String operatorName) {
		CompanyOperatorComission comission = (CompanyOperatorComission) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CompanyOperatorComission.class)
				.add(Restrictions.eq("thirdpartyServiceProvider", "CYBERTEL"))
				.add(Restrictions.eq("rechargeType", rechargeType))
				.add(Restrictions.eq("operatorName", operatorName))
				.uniqueResult();
		double commissionVal = 0.0;
		if (comission != null) {
			commissionVal = comission.getRetailercommision();
		}
		return commissionVal;
	}

	@Override
	public double getCustomerCommission(String thirdpartyServiceProvider,
			String rechargeType, String operatorName, Long userId) {
		CustomerCommision comission = (CustomerCommision) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CustomerCommision.class)
				.add(Restrictions.eq("thirdpartyServiceProvider",
						thirdpartyServiceProvider))
				.add(Restrictions.eq("rechargeType", rechargeType))
				.add(Restrictions.eq("operatorName", operatorName))
				.add(Restrictions.eq("userId", userId)).uniqueResult();
		double commissionVal = 0.0;
		if (comission != null) {
			commissionVal = comission.getCustomerCommission();
		} else {
			if ("MOBILE_PREPAID".equalsIgnoreCase(rechargeType))
				commissionVal = 1.5;
			else
				commissionVal = -1.5;
		}
		return commissionVal;
	}

	@Override
	public CustomerCommision getCustomerCommissionObject(
			String thirdpartyServiceProvider, String rechargeType,
			String operatorName, Long userId) {
		CustomerCommision comission = (CustomerCommision) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CustomerCommision.class)
				.add(Restrictions.eq("thirdpartyServiceProvider",
						thirdpartyServiceProvider))
				.add(Restrictions.eq("rechargeType", rechargeType))
				.add(Restrictions.eq("operatorName", operatorName))
				.add(Restrictions.eq("userId", userId)).uniqueResult();

		return comission;
	}

	@Override
	public CustomerCommision getCommission(CustomerCommision customerCommision) {
		CustomerCommision customerComission = (CustomerCommision) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CustomerCommision.class)
				.add(Restrictions.eq("thirdpartyServiceProvider",
						customerCommision.getThirdpartyServiceProvider()))
				.add(Restrictions.eq("rechargeType",
						customerCommision.getRechargeType()))
				.add(Restrictions.eq("operatorName",
						customerCommision.getOperatorName()))
				.add(Restrictions.eq("comissionType",
						customerCommision.getComissionType()))
				.add(Restrictions.eq("userId", customerCommision.getUserId()))
				.uniqueResult();
		return customerComission;
	}

	@Override
	public List<Users> getCustomerList() {
		Query query = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from Users as users where  users.userRole.authority=:authority");
		query.setParameter("authority", CommonConstants.ROLE_CUSTOMER);
		return (List<Users>) query.list();
	}

	@Override
	public TransactionTransportBean getFranTranstionDetail(String transId,
			Long franId) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(TransactionTransportBean.class)
				.add(Restrictions.eq("retailerId", franId.toString()))
				.add(Restrictions.eq("transid", transId));
		return (TransactionTransportBean) criteria.uniqueResult();
	}

	@Override
	public CustomerTransactionTransportBean getCustTransationDetails(
			String clientTransId, Long userId) {
		Criteria criteria = getSessionFactory()
				.getCurrentSession()
				.createCriteria(CustomerTransactionTransportBean.class)
				.add(Restrictions.eq("customerId", userId.toString()))
				.add(Restrictions.or(
						Restrictions.eq("clientTransId", clientTransId),
						Restrictions.eq("transid", clientTransId)));
		return (CustomerTransactionTransportBean) criteria.uniqueResult();
	}

	@Override
	public AirTelUserDetail getAirtelUserName(String retailerMobNoOrUserName) {
		Criteria criteria = getSessionFactory()
				.getCurrentSession()
				.createCriteria(AirTelUserDetail.class)
				.add(Restrictions.or(Restrictions.eq("airtelUserName",
						retailerMobNoOrUserName), Restrictions.eq(
						"mobileNumber", retailerMobNoOrUserName)));
		AirTelUserDetail airTelUserDetail = (AirTelUserDetail) criteria
				.uniqueResult();
		return airTelUserDetail;
	}

	public Long save(Object obj) {
		Long id = (Long) sessionFactory.getCurrentSession().save(obj);
		return id;
	}

	@Override
	public void delete(Object object) {
		sessionFactory.getCurrentSession().delete(object);

	}

	public List<FranchiseeRefundRequests> getFranchiseeRejectedRefundRequestsList(
			Timestamp fromDate, Timestamp toDate) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(FranchiseeRefundRequests.class)
				.add(Restrictions.eq("status", CommonConstants.REFUND_REJECT));
		criteria.addOrder(Order.desc("createdat")).add(
				Restrictions.between("createdat", fromDate, toDate));
		return criteria.list();
	}
}
