package com.cfeindia.b2bserviceapp.service.distributor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.common.dao.CommonDao;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Transactional
@Service("distributorSearchFranchiseeService")
public class DistributorSearchFranchiseeServiceImpl implements
		DistributorSearchFranchiseeService {

	@Autowired
	DistributorSearchFranchiseeDao distributorSearchFranchiseeDao;

	@Autowired
	CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public DistributorSearchFranchiseeDao getDistributorSearchFranchiseeDao() {
		return distributorSearchFranchiseeDao;
	}

	public void setDistributorSearchFranchiseeDao(
			DistributorSearchFranchiseeDao distributorSearchFranchiseeDao) {
		this.distributorSearchFranchiseeDao = distributorSearchFranchiseeDao;
	}

	public Users searchUserBasedOnIdOrMobNum(String userIdOrMobnum) {
		
		if (userIdOrMobnum != null && (userIdOrMobnum.startsWith("R"))) {
			String userId = ExtractorUtil.extractIdFromString(userIdOrMobnum);
			String str = commonDao.checkTypeOfUser(userId);
			if (CommonConstants.ROLE_FRANCHISEE.equalsIgnoreCase(str)) {
				return distributorSearchFranchiseeDao
						.searchUserBasedOnIdOrMobNum(userId);

			} else {
				return null;
			}
		} else {
			Long userId = (long) commonDao.getUserId(userIdOrMobnum);
			String str = commonDao.checkTypeOfUser(userId.toString());
			if (CommonConstants.ROLE_FRANCHISEE.equalsIgnoreCase(str)) {
				return distributorSearchFranchiseeDao
						.searchUserBasedOnIdOrMobNum(userIdOrMobnum);
			} else {
				return null;
			}
		}

	}

	public Users searchDistributorBasedOnIdOrMobNum(String userIdOrMobnum) {
		Users users;
		if (userIdOrMobnum != null
				&& (userIdOrMobnum.startsWith("R")
						|| userIdOrMobnum.startsWith("A") || userIdOrMobnum
							.startsWith("E"))) {
			return null;
		}
		if (userIdOrMobnum != null && (userIdOrMobnum.startsWith("D"))) {
			String userId = ExtractorUtil.extractIdFromString(userIdOrMobnum);
			String str = commonDao.checkTypeOfUser(userId);
			if (CommonConstants.ROLE_DISTRIBUTOR.equalsIgnoreCase(str)) {
				users = distributorSearchFranchiseeDao
						.searchUserBasedOnIdOrMobNum(userId);
				return users;
			} else {
				return null;
			}
		} else {
			Long userId = (long) commonDao.getUserId(userIdOrMobnum);
			String str = commonDao.checkTypeOfUser(userId.toString());
			if (CommonConstants.ROLE_DISTRIBUTOR.equalsIgnoreCase(str)) {
				return distributorSearchFranchiseeDao
						.searchUserBasedOnIdOrMobNum(userIdOrMobnum);
			} else {
				return null;
			}
		}

	}
}
