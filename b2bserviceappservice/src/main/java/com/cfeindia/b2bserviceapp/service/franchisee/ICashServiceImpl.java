package com.cfeindia.b2bserviceapp.service.franchisee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.CommonService;
import com.cfeindia.b2bserviceapp.dao.franchisee.ICashDao;
import com.cfeindia.b2bserviceapp.dao.franchisee.ICashDaoImpl;
import com.cfeindia.b2bserviceapp.entity.ICashRecharge;
import com.cfeindia.b2bserviceapp.service.recharge.mobile.RechargeTransactionService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service("iCashService")
@Transactional
public class ICashServiceImpl implements ICashService {
	@Autowired
	private RechargeTransactionService service;
	@Autowired
	private CommonService commonservice;
	@Autowired
	private ICashDao cashDao;

	@Override
	public TransactionTransportBean iCashRegistrationService(
			TransactionTransportBean transactionTransportBean,
			ICashRecharge iCashRecharge) {
		service.doRechargeService(transactionTransportBean);
		ICashRecharge checkICashdata = cashDao
				.checkICashRegistration(iCashRecharge.getMobileNumber().trim());
		if (checkICashdata == null) {
			iCashRecharge.setRegistrationStatus(transactionTransportBean
					.getStatus());
			commonservice.saveEntity(iCashRecharge);
		} else {
			checkICashdata.setRegistrationStatus(transactionTransportBean
					.getStatus());
			commonservice.saveOrUpdateEntity(checkICashdata);
		}

		return transactionTransportBean;
	}

	@Override
	public ICashRecharge checkICashRegistration(String mobileNo) {
		ICashRecharge iCashRecharge = cashDao.checkICashRegistration(mobileNo);
		return iCashRecharge;
	}

}
