package com.cfeindia.b2bserviceapp.common.dao;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

public interface FranchiseeCurrentBalListByDistributorIdDao {
	List<FranchiseeCurrBal> getFranchiseeCurrentBal(Long distributorId);
}
