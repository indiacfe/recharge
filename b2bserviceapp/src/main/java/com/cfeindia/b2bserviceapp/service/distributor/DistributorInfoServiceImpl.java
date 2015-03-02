package com.cfeindia.b2bserviceapp.service.distributor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.dao.CommonDao;
import com.cfeindia.b2bserviceapp.common.dao.DistributorCurrentBalanceDao;
import com.cfeindia.b2bserviceapp.common.dao.FranchiseeCurrentBalListByDistributorIdDao;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.recharge.mobile.dto.DistributorInfo;

@Service("distributorInfoService")
@Transactional
public class DistributorInfoServiceImpl implements DistributorInfoService {
	@Autowired
	private FranchiseeCurrentBalListByDistributorIdDao franchiseeCurrentBalListByDistributorIdDao;
	@Autowired
	private DistributorCurrentBalanceDao distributorCurrentBalanceDao;
	@Autowired
	private CommonDao commonDao;

	public DistributorInfo distributorInfo(String userId) {
		DistributorInfo distributorInfo = new DistributorInfo();
		Long distributorId = Long.parseLong(userId);
		List<FranchiseeCurrBal> franchiseeCurrBalList = franchiseeCurrentBalListByDistributorIdDao
				.getFranchiseeCurrentBal(distributorId);
		Integer numberOfFranchisee = 0;
		for (FranchiseeCurrBal franchiseeCurrBal : franchiseeCurrBalList) {
			numberOfFranchisee++;
		}
		distributorInfo.setNumberofFranchisee(numberOfFranchisee);
		DistributorCurrbal distributorCurrbal = distributorCurrentBalanceDao
				.getDistributorCurrbal(distributorId);
		if (distributorCurrbal != null) {
			distributorInfo.setB2bCurrAcAdUnitBal(distributorCurrbal
					.getB2bCurrAcAdUnitBal());
			distributorInfo.setB2bCurrAcBalance(distributorCurrbal
					.getB2bCurrAcBalance());
			distributorInfo.setCurrAcBalance(distributorCurrbal
					.getCurrAcBalance());
		}
		UserDetail userDetail = commonDao.getUserDetail(distributorId);
		if (userDetail != null) {
			distributorInfo.setName(userDetail.getFirmName());
		}
		return distributorInfo;
	}

}
