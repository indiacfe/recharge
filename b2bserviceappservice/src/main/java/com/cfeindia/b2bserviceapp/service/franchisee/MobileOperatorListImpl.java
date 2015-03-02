package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.model.franchisee.TelecomRecharge;

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
