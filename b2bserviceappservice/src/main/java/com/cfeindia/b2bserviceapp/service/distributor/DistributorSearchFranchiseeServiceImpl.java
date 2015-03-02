package com.cfeindia.b2bserviceapp.service.distributor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.distributor.DistributorSearchFranchiseeDao;
import com.cfeindia.b2bserviceapp.dto.FranchiseeDetailAsDist;
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

	public List<FranchiseeDetailAsDist> searchAllFranchisee(String distId) {
		List<FranchiseeDetailAsDist> franDetailAsDists = distributorSearchFranchiseeDao
				.allFranAsDist(distId);
		return franDetailAsDists;
	}

	public Users searchRetailerBasedOnIdOrMobNum(String userIdOrMobnumber) {

		if (userIdOrMobnumber != null
				&& (userIdOrMobnumber.startsWith("R")
						|| userIdOrMobnumber.startsWith("C") || userIdOrMobnumber
							.startsWith("D"))) {
			userIdOrMobnumber = ExtractorUtil
					.extractIdFromString(userIdOrMobnumber);
		}

		Users users = distributorSearchFranchiseeDao
				.searchUserBasedOnIdOrMobNum(userIdOrMobnumber);
		return users;

	}

}
