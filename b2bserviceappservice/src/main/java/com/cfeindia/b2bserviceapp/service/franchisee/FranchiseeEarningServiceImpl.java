package com.cfeindia.b2bserviceapp.service.franchisee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.franchisee.FranEarningDao;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class FranchiseeEarningServiceImpl implements FranchiseeEarningService {
	@Autowired
	private FranEarningDao franEarningDao;

	@Override
	public Double getAmountEarned(String fromDate, String toDate, Long franId) {
List<TransactionTransportBean> tranBean=new ArrayList<TransactionTransportBean>();
	List<Object> list= franEarningDao.getEarningDetails(
				TimeStampUtil.getTimeStampFromStringFromdate(fromDate),
				TimeStampUtil.getTimeStampFromStringTodate(toDate), franId);
	Iterator<Object> itr=list.iterator();
	while(itr.hasNext())
	{
		Object tb=itr.next();
		System.out.println(tb);
		
	}
	return 0.0;
	}

}
