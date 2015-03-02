package com.cfeindia.b2bserviceapp.franchisee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.dao.CommonDao;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.franchisee.model.TelecomRecharge;

@Service("MobileOperatorListImpl")
@Transactional
public class MobileOperatorListImpl implements MobileOperatorList {

	@Autowired
	CommonDao commonDao;

	public List<TelecomRecharge> mobileOperators() {
		List<TelecomRecharge> mobileOperatorList = new ArrayList<TelecomRecharge>();
		TelecomRecharge telecomRecharge=null;
		List<CompanyOperatorComission> telecomOperator = commonDao.getTelecomOperator();
		for (CompanyOperatorComission operator : telecomOperator) {
			telecomRecharge=new TelecomRecharge();
			telecomRecharge.setOperator(operator.getOperatorName());
			mobileOperatorList.add(telecomRecharge);
		}

		return mobileOperatorList;
	}

}
