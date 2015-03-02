package com.cfeindia.b2bserviceapp.dao.distributor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.dto.FranchiseeDetailAsDist;
import com.cfeindia.b2bserviceapp.entity.Users;

@Repository
public class DistributorSearchFranchiseeDaoImpl implements
		DistributorSearchFranchiseeDao {

	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Users searchUserBasedOnIdOrMobNum(String userIdOrMobnum) {
		long userid = 0;
		try {
			userid = Long.parseLong(userIdOrMobnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Criteria criteria = getSessionFactory()
				.getCurrentSession()
				.createCriteria(Users.class)
				.add(Restrictions.or(Restrictions.eq("userId", userid),
						Restrictions.eq("username", userIdOrMobnum)));
		return (Users) criteria.uniqueResult();

	}

	public List<FranchiseeDetailAsDist> allFranAsDist(String distId) {
		Query query = getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(
						"SELECT u1.USERNAME,u1.USER_ID,u2.FIRM_NAME,u3.b2bcurrbal,u3.b2bcurrbaladunit FROM users u1,user_detail u2,franchiseecurrbal u3 WHERE u3.franchiseeid=u1.USER_ID AND u3.franchiseeid=u2.USER_ID AND u3.creator_id=:creator_id");
		query.setString("creator_id", distId);
		List list=query.list();
		List<FranchiseeDetailAsDist> franAsDist=new ArrayList<FranchiseeDetailAsDist>();
		for(Object obj : list) {
			FranchiseeDetailAsDist franchiseeDetailAsDist = new FranchiseeDetailAsDist();
			Object[] valSet = (Object[])obj;
			franchiseeDetailAsDist.setMobileNo((String)valSet[0]);
			franchiseeDetailAsDist.setFranId(valSet[1].toString());
			franchiseeDetailAsDist.setFirmName((String)valSet[2]);
			franchiseeDetailAsDist.setBalance(Double.parseDouble(valSet[3].toString()));
			franchiseeDetailAsDist.setAdUnitBalance(Double.parseDouble(valSet[4].toString()));
			franAsDist.add(franchiseeDetailAsDist);
		}
		return franAsDist;
	}
}
