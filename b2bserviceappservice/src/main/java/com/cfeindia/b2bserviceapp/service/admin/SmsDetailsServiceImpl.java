package com.cfeindia.b2bserviceapp.service.admin;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.admin.SmsDetailsDao;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.entity.SmsRechargeDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

@Service
@Transactional
public class SmsDetailsServiceImpl implements SmsDetailsService {
	@Autowired
	private SmsDetailsDao smsDetailsDao;
	@Autowired
	private CommonDao commonDao;

	public List<SmsRechargeDetail> getMessageDetailsservice(String fromdate,
			String todate) throws ParseException {

		Timestamp fromTS1 = TimeStampUtil.getTimeStampFromString(fromdate);
		Timestamp fromTS2 = TimeStampUtil.getTimeStampFromString(todate);
		List<SmsRechargeDetail> list = smsDetailsDao.getDetailsdao(fromTS1,
				fromTS2);
		for (SmsRechargeDetail smsRechargeDetail : list) {
			Users user = commonDao.getsender(smsRechargeDetail.getSender());

			if (user != null) {
				smsRechargeDetail.setSenderName(user.getUserDetail()
						.getUserName());
			}

		}

		return list;
	}
}
