package com.cfeindia.b2bserviceapp.service.distributor;

import com.cfeindia.b2bserviceapp.entity.Users;

public interface DistributorSearchFranchiseeDao 
{
	public Users searchUserBasedOnIdOrMobNum(String userIdOrMobnum);

}
