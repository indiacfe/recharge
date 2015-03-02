package com.cfeindia.b2bserviceapp.dao.customer;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cfeindia.b2bserviceapp.entity.TokenDetails;

@Repository
public class TokenDaoImpl implements TokenDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public TokenDetails getToken(Long tokenId) {

		return (TokenDetails) sessionFactory.getCurrentSession()
				.createCriteria(TokenDetails.class)
				.add(Restrictions.eq("id", tokenId)).uniqueResult();
	}

	@Override
	public List<TokenDetails> getTokens(Long userId) {
		List<TokenDetails> tokenDetailsList = sessionFactory
				.getCurrentSession().createCriteria(TokenDetails.class)
				.add(Restrictions.eq("users.id", userId)).list();
		return tokenDetailsList;
	}

	@Override
	public void generateToken(TokenDetails tokenDetails) {
		sessionFactory.getCurrentSession().save(tokenDetails);

	}

	@Override
	public TokenDetails getToken(String ip, String token) {
		return (TokenDetails) sessionFactory.getCurrentSession()
				.createCriteria(TokenDetails.class)
				.add(Restrictions.eq("ipAddress", ip))
				.add(Restrictions.eq("token", token)).uniqueResult();
	}

	@Override
	public void deleteToken(TokenDetails tokenDetails) {
		sessionFactory.getCurrentSession().delete(tokenDetails);

	}

}
