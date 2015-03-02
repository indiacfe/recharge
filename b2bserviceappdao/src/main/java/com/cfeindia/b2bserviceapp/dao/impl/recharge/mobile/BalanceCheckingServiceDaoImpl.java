package com.cfeindia.b2bserviceapp.dao.impl.recharge.mobile;

import java.sql.Timestamp;

import org.hibernate.LockOptions;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.BaseDao;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.distributor.FundTransferDao;
import com.cfeindia.b2bserviceapp.dao.recharge.mobile.BalanceCheckingServiceDao;
import com.cfeindia.b2bserviceapp.entity.CompanyBalTransactionLog;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.CyberTelUtil;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Repository
public class BalanceCheckingServiceDaoImpl extends BaseDao implements
		BalanceCheckingServiceDao {
	@Autowired
	CommonDao commonDao;
	@Autowired
	FundTransferDao fundTransferDao;

	public FranchiseeCurrBal getFrachiseeCurrbalObj(long retailerId) {

		FranchiseeCurrBal franchiseeCurrBal = (FranchiseeCurrBal) getSessionFactory()
				.getCurrentSession().createCriteria(FranchiseeCurrBal.class)
				.add(Restrictions.eq("franchiseeid", retailerId))
				.uniqueResult();
		getSessionFactory().getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		getSessionFactory().getCurrentSession().refresh(franchiseeCurrBal);
		return franchiseeCurrBal;
	}

	public DistributorCurrbal getDistributorCurrbalObj(long distributorId) {

		DistributorCurrbal distributorCurrbal = (DistributorCurrbal) getSessionFactory()
				.getCurrentSession().createCriteria(DistributorCurrbal.class)
				.add(Restrictions.eq("distributorId", distributorId))
				.uniqueResult();
		getSessionFactory().getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(distributorCurrbal);
		getSessionFactory().getCurrentSession().refresh(distributorCurrbal);
		return distributorCurrbal;
	}

	public void reduceFranchiseeCurrentAccountBalance(long RetailerId,
			double transAmount) {
		FranchiseeCurrBal franchiseeCurrBal = getFrachiseeCurrbalObj(RetailerId);
		getSessionFactory().getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		getSessionFactory().getCurrentSession().refresh(franchiseeCurrBal);
		franchiseeCurrBal.setB2bcurrbal(franchiseeCurrBal.getB2bcurrbal()
				- transAmount);
		updateEntity(franchiseeCurrBal);
	}

	@Override
	public void reduceFranchiseeAdUnitAccountBalance(long franchiseeId,
			double transAmount) {
		FranchiseeCurrBal franchiseeCurrBal = getFrachiseeCurrbalObj(franchiseeId);
		getSessionFactory().getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		getSessionFactory().getCurrentSession().refresh(franchiseeCurrBal);
		franchiseeCurrBal.setB2bcurrbaladunit(franchiseeCurrBal
				.getB2bcurrbaladunit() - transAmount);
		updateEntity(franchiseeCurrBal);

	}

	public Long addToDistributorAdUnitBalanceByFranchisee(long distributorId,
			double amount) {
		DistributorCurrbal distributorCurrbal = getDistributorCurrbalObj(distributorId);
		double dCurrBal = distributorCurrbal.getB2bCurrAcAdUnitBal();
		distributorCurrbal.setB2bCurrAcAdUnitBal(dCurrBal + amount);
		DistributorBalanceTransferLog distributorBalanceTransferLog = new DistributorBalanceTransferLog();
		distributorBalanceTransferLog.setPreB2bCurrAdUnitBal(dCurrBal);
		distributorBalanceTransferLog.setNewB2bAdUnitBal(dCurrBal + amount);
		distributorBalanceTransferLog
				.setTransactionId(CyberTelUtil.generateTransId(
						CommonConstants.RETAILER_TO_DISTRIBUTOR, null));
		distributorBalanceTransferLog
				.setCreatedAt(TimeStampUtil.getTimestamp());
		distributorBalanceTransferLog.setDistId(distributorId);
		distributorBalanceTransferLog
				.setTransferTo(CommonConstants.COMPANY_TRANSFER_TO);
		distributorBalanceTransferLog
				.setTransferFrom(CommonConstants.DIST_TRANSFER_TO);
		distributorBalanceTransferLog
				.setTransferType(CommonConstants.TRANSFER_TYPE_CREDIT);
		distributorBalanceTransferLog.setTransferAmount(amount);
		Long id = commonDao.save(distributorBalanceTransferLog);
		commonDao.updateEntity(distributorCurrbal);
		return id;
	}

	public void addUnitDistributorPerBalance(long RetailerId, double transAmount) {
		FranchiseeCurrBal franchiseeCurrBal = getFrachiseeCurrbalObj(RetailerId);
		long creatorid = franchiseeCurrBal.getCreatorId();
		DistributorCurrbal distributorCurrbal = getDistributorCurrbalObj(creatorid);
		DistributorCommision distributorCommission = commonDao
				.getDistributorCommision(creatorid);
		double distCommission = distributorCommission.getCommision();
		getSessionFactory().getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		getSessionFactory().getCurrentSession().refresh(franchiseeCurrBal);
		distributorCurrbal.setB2bCurrAcAdUnitBal(distributorCurrbal
				.getB2bCurrAcAdUnitBal()
				+ (transAmount * (distCommission / 100)));
		franchiseeCurrBal.setB2bcurrbaladunit(franchiseeCurrBal
				.getB2bcurrbaladunit()
				- (transAmount + (transAmount * (distCommission / 100))));
		updateEntity(distributorCurrbal);
		updateEntity(franchiseeCurrBal);
	}

	@Override
	public CustomerCurrentBalance getCustomerCurrbalObj(long customerId) {

		CustomerCurrentBalance customerCurrentBalance = (CustomerCurrentBalance) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CustomerCurrentBalance.class)
				.add(Restrictions.eq("customerId", customerId)).uniqueResult();
		getSessionFactory().getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE)
				.lock(customerCurrentBalance);
		getSessionFactory().getCurrentSession().refresh(customerCurrentBalance);
		return customerCurrentBalance;
	}

	@Override
	public void reduceCustomerMobileBalance(long userId, double transAmount) {
		CustomerCurrentBalance customerCurrentBalance = (CustomerCurrentBalance) getSessionFactory()
				.getCurrentSession()
				.createCriteria(CustomerCurrentBalance.class)
				.add(Restrictions.eq("customerId", userId)).uniqueResult();
		getSessionFactory().getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE)
				.lock(customerCurrentBalance);
		getSessionFactory().getCurrentSession().refresh(customerCurrentBalance);
		customerCurrentBalance.setCurrAcBalance(customerCurrentBalance
				.getCurrAcBalance() - transAmount);
		updateEntity(customerCurrentBalance);

	}

}
