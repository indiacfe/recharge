package com.cfeindia.b2bserviceapp.dao.customer;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.TokenDetails;

public interface TokenDao {

	public TokenDetails getToken(Long tokenId);

	public List<TokenDetails> getTokens(Long userId);

	public TokenDetails getToken(String ip, String token);

	public void generateToken(TokenDetails tokenDetails);
	public void deleteToken(TokenDetails tokenDetails);
}
