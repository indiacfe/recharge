package com.cfeindia.b2bserviceapp.service.distributor;

import com.cfeindia.b2bserviceapp.entity.Users;

public interface DistributorSearchFranchiseeService 
{
	public Users searchUserBasedOnIdOrMobNum(String userIdOrMobnum);
	public Users searchDistributorBasedOnIdOrMobNum(String userIdOrMobnum);

}
