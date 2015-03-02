package com.cfeindia.b2bserviceapp.service.customer;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.TokenDetails;

public interface TokenService {

	public List<TokenDetails> getTokens(Long userId);

	public void generateToken(String ip, Long userId);

	public int changeTokenStatus(Long tokenId);

	public void deleteToken(Long tokenId);

}
