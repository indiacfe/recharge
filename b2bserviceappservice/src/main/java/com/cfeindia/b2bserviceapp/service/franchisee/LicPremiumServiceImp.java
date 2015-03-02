package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dao.franchisee.LicPremiumDao;
import com.cfeindia.b2bserviceapp.entity.LicPremiumBean;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service
@Transactional
public class LicPremiumServiceImp implements LicPremiumService {
	@Autowired
	private LicPremiumDao licPremiumDao;

	@Autowired
	LicPayService licPayService;

	public String saveDetails(TransactionTransportBean transactionTransportBean) {

		licPayService.LicPayActivityService(transactionTransportBean);
		LicPremiumBean licPremiumBean = new LicPremiumBean();
		String result = null;
		if (CommonConstants.SUCCESS.equalsIgnoreCase(transactionTransportBean
				.getStatus())) {

			licPremiumBean.setPolicyNumber(transactionTransportBean
					.getConnectionid());
			licPremiumBean.setAmount(transactionTransportBean.getAmount());

			licPremiumBean.setDob(transactionTransportBean.getDob());
			licPremiumBean.setStatus(CommonConstants.IN_PROCESS);
			licPremiumBean.setUserId(Long.parseLong(transactionTransportBean
					.getRetailerId()));
			licPremiumBean.setTransactionId(transactionTransportBean
					.getTransid());
			licPremiumBean.setMobileNo(transactionTransportBean.getMobileNo());
			licPremiumBean.setCustomerName(transactionTransportBean
					.getCustomerName());

			result = licPremiumDao.saveLicPremiumDao(licPremiumBean);

		} else {
			licPremiumBean.setPolicyNumber(transactionTransportBean
					.getConnectionid());
			licPremiumBean.setAmount(transactionTransportBean.getAmount());

			licPremiumBean.setDob(transactionTransportBean.getDob());
			licPremiumBean.setStatus(CommonConstants.REJECTED);
			licPremiumBean.setUserId(Long.parseLong(transactionTransportBean
					.getRetailerId()));

			result = licPremiumDao.saveLicPremiumDao(licPremiumBean);
		}
		return result;
	}

	public List<LicPremiumBean> getLicPremiumDetails(Long userId) {

		return licPremiumDao.getLicPremiumDetails(userId);
	}

}
