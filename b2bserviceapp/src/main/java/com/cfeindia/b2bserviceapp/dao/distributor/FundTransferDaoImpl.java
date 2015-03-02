package com.cfeindia.b2bserviceapp.dao.distributor;

import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Repository("fundTransferDaoImpl")
public class FundTransferDaoImpl implements FundTransferDao {
	@Autowired
	SessionFactory sessionFactory;

	public String fundToRetailerDao() {
		return null;
	}

	public FranchiseeCurrBal getCurrentDetail(Long franchID) {
		FranchiseeCurrBal f = (FranchiseeCurrBal) sessionFactory
				.getCurrentSession().createCriteria(FranchiseeCurrBal.class)
				.add(Restrictions.eq("franchiseeid", franchID)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(f);
		sessionFactory.getCurrentSession().refresh(f);
		return f;
	}


	public DistributorCurrbal getDistCurrBal(Long distriID) {
		// long distributorId=Long.parseLong(distriID);
		DistributorCurrbal distributorCurrbal = (DistributorCurrbal) sessionFactory
				.getCurrentSession().createCriteria(DistributorCurrbal.class)
				.add(Restrictions.eq("distributorId", distriID)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(distributorCurrbal);
		sessionFactory.getCurrentSession().refresh(distributorCurrbal);
		return distributorCurrbal;
	}
	public void setDistributorBalTransferLog(
			DistributorBalanceTransferLog distributorBalanceTransferLog) {
		sessionFactory.getCurrentSession().save(distributorBalanceTransferLog);
		return;
	}

	public void setFranchiseeBalTransferLog(
			FranchiseeBalanceTransferLog franchiseeBalanceTransferLog) {
		sessionFactory.getCurrentSession().save(franchiseeBalanceTransferLog);
		return;
	}

	public DistributorCommision getDistributorCommision(Long disID) {
		DistributorCommision distributorCommision = (DistributorCommision) sessionFactory
				.getCurrentSession().createCriteria(DistributorCommision.class)
				.add(Restrictions.eq("distributorId", disID)).uniqueResult();
		return distributorCommision;
	}

	public String updateDistributorCommision(
			DistributorCommision distributorCommision) {
		sessionFactory.getCurrentSession().update(distributorCommision);
		return null;
	}

	public CompanyBalance getCompanyBalance(String companyName) {
		CompanyBalance companyBalance = (CompanyBalance) sessionFactory
				.getCurrentSession().createCriteria(CompanyBalance.class)
				.add(Restrictions.eq("companyName", companyName))
				.uniqueResult();

		return companyBalance;
	}

	public long companyDistributorTransactionLog(
			CompanyDistributorTransactionLog companyDistributorTransactionLog) {
		long id = (Long) sessionFactory.getCurrentSession().save(
				companyDistributorTransactionLog);
		return id;
	}

	public void updateFranchiseeCurrBal(Long fId, double amount) {
		FranchiseeCurrBal franchiseeCurrBal = (FranchiseeCurrBal) sessionFactory
				.getCurrentSession().createCriteria(FranchiseeCurrBal.class)
				.add(Restrictions.eq("franchiseeid", fId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		sessionFactory.getCurrentSession().refresh(franchiseeCurrBal);
		franchiseeCurrBal.setB2bcurrbal(franchiseeCurrBal.getB2bcurrbal()+amount);
		franchiseeCurrBal.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(franchiseeCurrBal);
	}

	public void reduceDistributorCurrBal(Long dId, double amount) {
		DistributorCurrbal distributorCurrbal = (DistributorCurrbal) sessionFactory
				.getCurrentSession().createCriteria(DistributorCurrbal.class)
				.add(Restrictions.eq("distributorId", dId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(distributorCurrbal);
		sessionFactory.getCurrentSession().refresh(distributorCurrbal);
		distributorCurrbal.setB2bCurrAcBalance(distributorCurrbal
				.getB2bCurrAcBalance() - amount);
		distributorCurrbal.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(distributorCurrbal);
	}
	
	public void addDistributorCurrBal(Long dId, double amount) {
		DistributorCurrbal distributorCurrbal = (DistributorCurrbal) sessionFactory
				.getCurrentSession().createCriteria(DistributorCurrbal.class)
				.add(Restrictions.eq("distributorId", dId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(distributorCurrbal);
		sessionFactory.getCurrentSession().refresh(distributorCurrbal);
		distributorCurrbal.setB2bCurrAcBalance(distributorCurrbal
				.getB2bCurrAcBalance() + amount);
		distributorCurrbal.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(distributorCurrbal);
	}
	
	public void updateDistributorB2bAdunitBal(Long dId) {
		DistributorCurrbal distributorCurrbal = (DistributorCurrbal) sessionFactory
				.getCurrentSession().createCriteria(DistributorCurrbal.class)
				.add(Restrictions.eq("distributorId", dId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(distributorCurrbal);
		sessionFactory.getCurrentSession().refresh(distributorCurrbal);
		distributorCurrbal.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(distributorCurrbal);
	}

	public void updateDistributorB2bToCurrBal(Long dId, double dCurrBal,
			double dAdBal)
	{
		DistributorCurrbal distributorCurrbal = (DistributorCurrbal) sessionFactory
				.getCurrentSession().load(DistributorCurrbal.class, dId);
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(distributorCurrbal);
		sessionFactory.getCurrentSession().refresh(distributorCurrbal);
		distributorCurrbal.setB2bCurrAcBalance(dCurrBal);
		distributorCurrbal.setB2bCurrAcAdUnitBal(dAdBal);
		distributorCurrbal.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(distributorCurrbal);
	}
	
	public void addDistributorBusinessBal(Long dId, double amount) {
		DistributorCurrbal distributorCurrbal = (DistributorCurrbal) sessionFactory
				.getCurrentSession().createCriteria(DistributorCurrbal.class)
				.add(Restrictions.eq("distributorId", dId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(distributorCurrbal);
		sessionFactory.getCurrentSession().refresh(distributorCurrbal);
		distributorCurrbal.setCurrAcBalance(distributorCurrbal
				.getCurrAcBalance() + amount);
		distributorCurrbal.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(distributorCurrbal);
	}

	public void updateCompanyBalance(long cId, double amount) {
		// TODO Auto-generated method stub
		
	}

}
