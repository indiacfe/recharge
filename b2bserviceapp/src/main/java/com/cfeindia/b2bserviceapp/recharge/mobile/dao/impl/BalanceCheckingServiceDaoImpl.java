package com.cfeindia.b2bserviceapp.recharge.mobile.dao.impl;

import org.hibernate.LockOptions;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.common.dao.BaseDao;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.recharge.mobile.dao.BalanceCheckingServiceDao;

@Repository
public class BalanceCheckingServiceDaoImpl extends BaseDao implements BalanceCheckingServiceDao {

	public FranchiseeCurrBal getFrachiseeCurrbalObj(long retailerId) {
		
		FranchiseeCurrBal franchiseeCurrBal = (FranchiseeCurrBal)getSessionFactory().getCurrentSession().createCriteria(FranchiseeCurrBal.class).add(Restrictions.eq("franchiseeid", retailerId)).uniqueResult();
		getSessionFactory().getCurrentSession().buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		getSessionFactory().getCurrentSession().refresh(franchiseeCurrBal);
		return franchiseeCurrBal;
	}

	public void reduceMobileBalance(long RetailerId, double transAmount) {
		FranchiseeCurrBal franchiseeCurrBal = getFrachiseeCurrbalObj(RetailerId);
		getSessionFactory().getCurrentSession().buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		getSessionFactory().getCurrentSession().refresh(franchiseeCurrBal);
		franchiseeCurrBal.setB2bcurrbal(franchiseeCurrBal.getB2bcurrbal() - transAmount);
		updateEntity(franchiseeCurrBal);
	}

}
