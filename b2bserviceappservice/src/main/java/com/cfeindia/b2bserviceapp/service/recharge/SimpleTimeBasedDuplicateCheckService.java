package com.cfeindia.b2bserviceapp.service.recharge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.recharge.DuplicateCheckServiceDao;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@Service
@Transactional
public class SimpleTimeBasedDuplicateCheckService implements DuplicateCheckService {

	@Autowired
	DuplicateCheckServiceDao duplicateCheckServiceDao;
	
	@Override
	public boolean checkDuplicate(
			TransactionTransportBean transactionTransportBean) {
		return duplicateCheckServiceDao.checkDuplicate(transactionTransportBean);
	}

}
