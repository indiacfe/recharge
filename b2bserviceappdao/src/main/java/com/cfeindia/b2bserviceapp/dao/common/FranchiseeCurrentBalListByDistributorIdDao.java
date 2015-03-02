package com.cfeindia.b2bserviceapp.dao.common;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;

public interface FranchiseeCurrentBalListByDistributorIdDao {
	List<FranchiseeCurrBal> getFranchiseeCurrentBal(Long distributorId);
}
