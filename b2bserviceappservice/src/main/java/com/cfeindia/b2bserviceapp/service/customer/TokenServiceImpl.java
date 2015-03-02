package com.cfeindia.b2bserviceapp.service.customer;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.customer.TokenDao;
import com.cfeindia.b2bserviceapp.entity.TokenDetails;
import com.cfeindia.b2bserviceapp.entity.Users;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenDao tokenDao;
	@Autowired
	private CommonDao commonDao;

	@Override
	public List<TokenDetails> getTokens(Long userId) {

		return tokenDao.getTokens(userId);
	}

	@Override
	public void generateToken(String ip, Long userId) {
		TokenDetails tokenDetails = new TokenDetails();
		String token = UUID.randomUUID().toString();
		tokenDetails.setIpAddress(ip);
		tokenDetails.setToken(token);
		Users users = new Users();
		users.setUserId(userId);
		tokenDetails.setUsers(users);
		tokenDetails.setEnabled(1);
		tokenDao.generateToken(tokenDetails);
	}

	@Override
	public int changeTokenStatus(Long tokenId) {
		TokenDetails tokenDetails = tokenDao.getToken(tokenId);
		if (tokenDetails.getEnabled() == 1) {
			tokenDetails.setEnabled(0);
			return 0;
		} else {
			tokenDetails.setEnabled(1);
			return 1;
		}

	}

	@Override
	public void deleteToken(Long tokenId) {
		TokenDetails tokenDetails = (TokenDetails) commonDao
				.getEntityByPrimaryKey(TokenDetails.class, tokenId);
		tokenDao.deleteToken(tokenDetails);
		
	}

}
