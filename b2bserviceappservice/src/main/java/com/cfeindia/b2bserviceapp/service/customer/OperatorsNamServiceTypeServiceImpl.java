package com.cfeindia.b2bserviceapp.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.customer.OperatorNameServiceTypeDao;
import com.cfeindia.b2bserviceapp.entity.B2bOperatorsCodeBean;

@Service
@Transactional
public class OperatorsNamServiceTypeServiceImpl implements
		OperatorsNamServiceTypeService {
	@Autowired
	private OperatorNameServiceTypeDao operatorNameServiceTypeDao;
	public B2bOperatorsCodeBean getServiceType(String opCode) {
		return(operatorNameServiceTypeDao.getOperatorAndServiceType(opCode));
	}

}
