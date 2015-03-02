package com.cfeindia.b2bserviceapp.dao.distributor;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.FranchiseeDetailAsDist;
import com.cfeindia.b2bserviceapp.entity.Users;

public interface DistributorSearchFranchiseeDao 
{
	public Users searchUserBasedOnIdOrMobNum(String userIdOrMobnum);
	public List<FranchiseeDetailAsDist> allFranAsDist(String distId);

}
