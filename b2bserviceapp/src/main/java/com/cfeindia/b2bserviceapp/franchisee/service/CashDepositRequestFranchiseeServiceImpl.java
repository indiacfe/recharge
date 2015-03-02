package com.cfeindia.b2bserviceapp.franchisee.service;

import com.cfeindia.b2bserviceapp.converter.CashDepoReqToFranOrderProcessingConverter;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;
import com.cfeindia.b2bserviceapp.franchisee.dao.CashDepositRequestFranchiseeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cfeindia.b2bserviceapp.franchisee.model.CashDepositRequestFran;

@Service("CashDepositRequestFranchiseeService")
@Transactional
public class CashDepositRequestFranchiseeServiceImpl implements
		CashDepositRequestFranchiseeService {
@Autowired
CashDepositRequestFranchiseeDao cashDepositRequestFranchiseeDao;
	
	public void saveCashDepositRequest(String userId,
			CashDepositRequestFran cashDepositRequestFran)
	{
		CashDepoReqToFranOrderProcessingConverter converter=new CashDepoReqToFranOrderProcessingConverter();
		FranchiseeCashDepositRequest franchiseeOrderProcessing=converter.convert(cashDepositRequestFran);
		franchiseeOrderProcessing.setRequesterId(Long.valueOf(userId));		
		cashDepositRequestFranchiseeDao.saveCashDepositRequest(franchiseeOrderProcessing);
	}

}
