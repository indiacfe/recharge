package com.cfeindia.b2bserviceapp.service.distributor;


import java.util.List;

import com.cfeindia.b2bserviceapp.dto.FranchiseeDetailAsDist;
import com.cfeindia.b2bserviceapp.entity.Users;

public interface DistributorSearchFranchiseeService 
{
	public Users searchUserBasedOnIdOrMobNum(String userIdOrMobnum);
	public Users searchDistributorBasedOnIdOrMobNum(String userIdOrMobnum);
	public List<FranchiseeDetailAsDist> searchAllFranchisee(String distId);
	public Users searchRetailerBasedOnIdOrMobNum(String userIdOrMobnumber);
}
