package com.cfeindia.b2bserviceapp.dao.distributor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.CompanyBalance;
import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.DistributorCommision;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.NewIdCreationBalanceLog;
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

	/**
	 * Don't use this function if balance is going to be changed in a
	 * transaction
	 * 
	 * @param franchID
	 * @return
	 */
	public FranchiseeCurrBal getCurrentDetailWithoutLockingForDisplay(
			Long franchID) {
		FranchiseeCurrBal f = (FranchiseeCurrBal) sessionFactory
				.getCurrentSession().createCriteria(FranchiseeCurrBal.class)
				.add(Restrictions.eq("franchiseeid", franchID)).uniqueResult();
		return f;
	}

	/**
	 * Don't use this function if balance is going to be changed in a
	 * transaction
	 * 
	 * @param distriID
	 * @return
	 */
	public DistributorCurrbal getDistCurrBalWithoutLockingForDisplay(
			Long distriID) {
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

	public double updateFranchiseeCurrBal(Long fId, double amount) {
		FranchiseeCurrBal franchiseeCurrBal = (FranchiseeCurrBal) sessionFactory
				.getCurrentSession().createCriteria(FranchiseeCurrBal.class)
				.add(Restrictions.eq("franchiseeid", fId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		sessionFactory.getCurrentSession().refresh(franchiseeCurrBal);
		double updatedB2bcurrbal = franchiseeCurrBal.getB2bcurrbal() + amount;
		franchiseeCurrBal.setB2bcurrbal(updatedB2bcurrbal);
		franchiseeCurrBal.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(franchiseeCurrBal);
		return updatedB2bcurrbal;
	}

	public double updateFranchiseeAddUnitBal(Long fId, double amount) {
		FranchiseeCurrBal franchiseeCurrBal = (FranchiseeCurrBal) sessionFactory
				.getCurrentSession().createCriteria(FranchiseeCurrBal.class)
				.add(Restrictions.eq("franchiseeid", fId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		sessionFactory.getCurrentSession().refresh(franchiseeCurrBal);
		double updatedB2bcurrbal = franchiseeCurrBal.getB2bcurrbaladunit()
				+ amount;
		franchiseeCurrBal.setB2bcurrbaladunit(updatedB2bcurrbal);
		franchiseeCurrBal.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(franchiseeCurrBal);
		return updatedB2bcurrbal;
	}

	public void reduceDistributorB2bBal(Long dId, double amount) {
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

	public void reduceDistributorAddUnitBal(Long dId, double amount) {
		DistributorCurrbal distributorCurrbal = (DistributorCurrbal) sessionFactory
				.getCurrentSession().createCriteria(DistributorCurrbal.class)
				.add(Restrictions.eq("distributorId", dId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(distributorCurrbal);
		sessionFactory.getCurrentSession().refresh(distributorCurrbal);
		distributorCurrbal.setB2bCurrAcAdUnitBal(distributorCurrbal
				.getB2bCurrAcAdUnitBal() - amount);
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
			double dAdBal) {
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
	
	@Override
	public void reduceDistributorCurrBal(Long dId, double amount) {
		DistributorCurrbal distributorCurrbal = (DistributorCurrbal) sessionFactory
				.getCurrentSession().createCriteria(DistributorCurrbal.class)
				.add(Restrictions.eq("distributorId", dId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(distributorCurrbal);
		sessionFactory.getCurrentSession().refresh(distributorCurrbal);
		distributorCurrbal.setCurrAcBalance(distributorCurrbal
				.getCurrAcBalance() - amount);
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

	public void reduceDistributorAdUnitBal(Long dId, Double amount) {
		DistributorCurrbal distributorCurrbal = (DistributorCurrbal) sessionFactory
				.getCurrentSession().createCriteria(DistributorCurrbal.class)
				.add(Restrictions.eq("distributorId", dId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(distributorCurrbal);
		sessionFactory.getCurrentSession().refresh(distributorCurrbal);
		distributorCurrbal.setB2bCurrAcAdUnitBal(distributorCurrbal
				.getB2bCurrAcAdUnitBal() - amount);
		distributorCurrbal.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(distributorCurrbal);

	}

	public void reduceFranchiseCurrBal(Long fId, Double amount,
			String paymentType) {
		FranchiseeCurrBal franchiseeCurrBal = (FranchiseeCurrBal) sessionFactory
				.getCurrentSession().createCriteria(FranchiseeCurrBal.class)
				.add(Restrictions.eq("franchiseeid", fId)).uniqueResult();
		sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		sessionFactory.getCurrentSession().refresh(franchiseeCurrBal);
		if ("CURRENT".equalsIgnoreCase(paymentType)) {
			franchiseeCurrBal.setB2bcurrbal(franchiseeCurrBal.getB2bcurrbal()
					- amount);

		} else {
			franchiseeCurrBal.setB2bcurrbaladunit(franchiseeCurrBal
					.getB2bcurrbaladunit() - amount);
		}

		franchiseeCurrBal.setUpdatedAt(TimeStampUtil.getTimestamp());
		sessionFactory.getCurrentSession().update(franchiseeCurrBal);

	}

	public void updateCompanyBalance(long cId, double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public NewIdCreationBalanceLog getValidUser(Long distId, Long retailerId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(NewIdCreationBalanceLog.class)
				.add(Restrictions.eq("creatorid", distId))
				.add(Restrictions.eq("newFranchiseeId", retailerId));
		return (NewIdCreationBalanceLog) criteria.uniqueResult();
	}

	@Override
	public boolean checkDuplicate(Long franchiseeId) {
		String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				DistributorBalanceTransferLog.class);

		criteria.add(Restrictions.eq("retailerDetail.users.userId",
				franchiseeId));
		
		criteria.add(Restrictions.between("createdAt",
				TimeStampUtil.getTimeStampFromStringFromdate(date),
				TimeStampUtil.getTimeStampFromStringTodate(date)));
		criteria.addOrder(Order.desc("createdAt"));
		List<DistributorBalanceTransferLog> list = criteria.list();
		if (list.size() >0) {
			DistributorBalanceTransferLog bean = list.get(0);
			long diff = TimeStampUtil.getTimestamp().getTime()
					- bean.getCreatedAt().getTime();
			long diffInMinute = diff / (60 * 1000) % 60;
			if (diffInMinute < 2) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}


}
