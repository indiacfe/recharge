package com.cfeindia.b2bserviceapp.dao.common;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

@Repository("franchiseeCurrentBalListByDistributorIdDao")
public class FranchiseeCurrentBalListByDistributorIdDaoImpl extends BaseDao implements
		FranchiseeCurrentBalListByDistributorIdDao {

	public List<FranchiseeCurrBal> getFranchiseeCurrentBal(Long distributorId) {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(FranchiseeCurrBal.class).add(Restrictions.eq("creatorId", distributorId));
		List<FranchiseeCurrBal> list=criteria.list();
		return list;
	}

}
