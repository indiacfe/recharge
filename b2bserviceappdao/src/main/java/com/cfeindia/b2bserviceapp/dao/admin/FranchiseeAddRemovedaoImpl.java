package com.cfeindia.b2bserviceapp.dao.admin;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

@Repository
public class FranchiseeAddRemovedaoImpl implements FranchiseeAddRemovedao {
	@Autowired
	SessionFactory sessionFactory;

	public FranchiseeCurrBal removeFranchiseeCreator(Long franId) {

		Query query = sessionFactory.getCurrentSession().createQuery(
				" from FranchiseeCurrBal where franchiseeid=:franchiseeid ");
		query.setLong("franchiseeid", franId);
		FranchiseeCurrBal f = (FranchiseeCurrBal) query.uniqueResult();
	/*	sessionFactory.getCurrentSession()
				.buildLockRequest(LockOptions.UPGRADE).lock(f);
		sessionFactory.getCurrentSession().refresh(f);*/
		return f;
	}

	public void updateDetails(FranchiseeCurrBal franchiseeCurrBal) {
		sessionFactory.getCurrentSession().update(franchiseeCurrBal);

	}

}
