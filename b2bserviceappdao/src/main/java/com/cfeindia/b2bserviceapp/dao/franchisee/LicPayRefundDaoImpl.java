package com.cfeindia.b2bserviceapp.dao.franchisee;

import org.hibernate.LockOptions;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.dao.common.BaseDao;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

@Repository
public class LicPayRefundDaoImpl extends BaseDao implements LicPayRefundDao{

	private FranchiseeCurrBal getFrachiseeCurrbalObj(Long retailerId) {
		FranchiseeCurrBal franchiseeCurrBal = (FranchiseeCurrBal)getSessionFactory().getCurrentSession().createCriteria(FranchiseeCurrBal.class).add(Restrictions.eq("franchiseeid", retailerId)).uniqueResult();
		getSessionFactory().getCurrentSession().buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		getSessionFactory().getCurrentSession().refresh(franchiseeCurrBal);
		return franchiseeCurrBal;
		
	}

	public void refundUpdate(Long retailerId, Double transAmount) {
		
		FranchiseeCurrBal franchiseeCurrBal = getFrachiseeCurrbalObj(retailerId);
		
		getSessionFactory().getCurrentSession().buildLockRequest(LockOptions.UPGRADE).lock(franchiseeCurrBal);
		getSessionFactory().getCurrentSession().refresh(franchiseeCurrBal);
		franchiseeCurrBal.setB2bcurrbal(franchiseeCurrBal.getB2bcurrbal() +transAmount);
		updateEntity(franchiseeCurrBal);
	}
	
}
