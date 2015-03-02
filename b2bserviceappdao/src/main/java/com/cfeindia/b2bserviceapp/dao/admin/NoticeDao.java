package com.cfeindia.b2bserviceapp.dao.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.NoticeInfo;

public interface NoticeDao {
	public void createNews(NoticeInfo noticeInfo);

	public List<NoticeInfo> noticeList();

	public NoticeInfo getEntity(int noticeId);
	public void deleteEntity(NoticeInfo noticeInfo);

	public void updateNotice(NoticeInfo noticeInfo);

}
