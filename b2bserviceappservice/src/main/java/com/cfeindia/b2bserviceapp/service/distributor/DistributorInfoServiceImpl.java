package com.cfeindia.b2bserviceapp.service.distributor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.common.DistributorCurrentBalanceDao;
import com.cfeindia.b2bserviceapp.dao.common.FranchiseeCurrentBalListByDistributorIdDao;
import com.cfeindia.b2bserviceapp.dto.recharge.mobile.DistributorInfo;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.NoticeInfo;
import com.cfeindia.b2bserviceapp.entity.UserDetail;

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
		Integer numberOfFranchisee = franchiseeCurrBalList.size();
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
			Long distId=userDetail.getUsers().getUserId();
			distributorInfo.setDistId(distId.toString());
			distributorInfo.setFirmName(userDetail.getFirmName());
			distributorInfo.setName(userDetail.getFirmName());
		}
		return distributorInfo;
	}
	public List<NoticeInfo> getNoticeInfo() {
		List<NoticeInfo> list=commonDao.getNoticeDistributor();
		return list;
	}
	

}
