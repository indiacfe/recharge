package com.cfeindia.b2bserviceapp.service.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.admin.LicManagementDao;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dto.LicPremiumDetailsDto;
import com.cfeindia.b2bserviceapp.entity.LicPremiumBean;
import com.cfeindia.b2bserviceapp.service.franchisee.LicPayService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class LicMgmtServiceImpl implements LicManagementService {
	@Autowired
	private LicManagementDao licManagementDao;

	@Autowired
	CommonDao commonDao;

	@Autowired
	LicPayService licPayService;

	public List<LicPremiumDetailsDto> getDetailservice(String fromDate,
			String toDate, String status) {
		Timestamp fromdateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		List<LicPremiumDetailsDto> licList = new ArrayList<LicPremiumDetailsDto>();
		List<LicPremiumBean> list = licManagementDao.getDetailsdao(
				fromdateTimeStamp, toDateTimeStamp, status);
		for (LicPremiumBean licPremiumBean : list) {
			LicPremiumDetailsDto dto = new LicPremiumDetailsDto();
			dto.setId(licPremiumBean.getId());
			dto.setPolicyNumber(licPremiumBean.getPolicyNumber());
			dto.setCreatedAt(licPremiumBean.getCreatedAt());
			dto.setDob(licPremiumBean.getDob());
			dto.setStatus(licPremiumBean.getStatus());
			dto.setUserId(licPremiumBean.getUserId());
			dto.setTransactionId(licPremiumBean.getTransactionId());
			dto.setUserName(commonDao.getUsers(licPremiumBean.getUserId())
					.getUserDetail().getFirmName());
			dto.setAmount(licPremiumBean.getAmount());
			dto.setCustomerName(licPremiumBean.getCustomerName());
			dto.setMobileNo(licPremiumBean.getMobileNo());
			licList.add(dto);
		}

		return licList;
	}

	public List<LicPremiumBean> getDetailStatusRejected(Long Id) {
		// TransactionTransportBean transactionTransportBean
		LicPremiumBean licBean = (LicPremiumBean) commonDao
				.getEntityByPrimaryKey(LicPremiumBean.class, Id);
		licBean.setStatus(CommonConstants.REJECTED);
		licPayService.LicRefundActivityService(licBean);
		commonDao.updateEntity(licBean);
		return licManagementDao.getDetailStatusSuccess();
	}

	public List<LicPremiumBean> getDetailStatusSuccess(Long Id) {

		LicPremiumBean licBean = (LicPremiumBean) commonDao
				.getEntityByPrimaryKey(LicPremiumBean.class, Id);
		licBean.setStatus(CommonConstants.SUCCESS);
		commonDao.updateEntity(licBean);
		return licManagementDao.getDetailStatusSuccess();

	}

}
