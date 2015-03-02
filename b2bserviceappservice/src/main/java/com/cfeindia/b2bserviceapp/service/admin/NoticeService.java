package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.NoticeInfo;

public interface NoticeService {
	public String createNews(NoticeInfo noticeInfo);
	public List<NoticeInfo> getAllNotice();
	public String activeDeactive(int noticeId);
	public String deleteNotice(int noticeId);
 
}
