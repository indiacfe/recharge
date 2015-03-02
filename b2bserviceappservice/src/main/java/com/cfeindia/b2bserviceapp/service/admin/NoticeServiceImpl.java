package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.admin.NoticeDao;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.entity.NoticeInfo;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private CommonDao commonDao;

	public String createNews(NoticeInfo noticeInfo) {
		if (noticeInfo.isRetailer()) {
			noticeInfo.setRetailerEnable(1);
		} else {
			noticeInfo.setRetailerEnable(0);
		}
		if (noticeInfo.isDistributor()) {
			noticeInfo.setDistributorEnable(1);
		} else {
			noticeInfo.setDistributorEnable(0);
		}
		if(noticeInfo.isHomePage())
		{
			noticeInfo.setHomePageEnable(1);
		}else {
			noticeInfo.setHomePageEnable(0);
		}
		
		
		/* NoticeInfo ntinfo = */noticeDao.createNews(noticeInfo);
		/*
		 * if (ntinfo != null) { return "success"; } else { return "failure"; }
		 */return "success";
	}

	public List<NoticeInfo> getAllNotice() {

		return noticeDao.noticeList();
	}

	public String activeDeactive(int noticeId) {
		NoticeInfo noticeInfo = noticeDao.getEntity(noticeId);

		if (noticeInfo.getActiveNews().equalsIgnoreCase("Y")) {
			noticeInfo.setActiveNews("N");

		} else {
			noticeInfo.setActiveNews("Y");

		}

		noticeDao.updateNotice(noticeInfo);

		return "success";
	}

	@Override
	public String deleteNotice(int noticeId) {
		noticeDao.deleteEntity(noticeDao.getEntity(noticeId));
		return null;
	}

}
