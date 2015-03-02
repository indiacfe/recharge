package com.cfeindia.b2bserviceapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "new_notice")
public class NoticeInfo {

	@Id
	@Column(name = "id")
	private int noticeId;
	@Column(name = "notice_name")
	private String name;
	@Column(name = "Description")
	private String description;
	/*
	 * @Column(name = "Enable") private char activeInactive;
	 */
	@Column(name = "active_news")
	private String activeNews = "Y";
	@Column(name = "retailer")
	private int retailerEnable;
	@Column(name = "distributor")
	private int distributorEnable;
	@Transient
	private boolean retailer;
	@Transient
	private boolean distributor;
	@Transient
	private boolean homePage;
	@Column(name="homePage")
	private int homePageEnable;

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * public char getActiveInactive() { return activeInactive; }
	 * 
	 * public void setActiveInactive(char activeInactive) { this.activeInactive
	 * = activeInactive; }
	 */

	public String getActiveNews() {
		return activeNews;
	}

	public void setActiveNews(String activeNews) {
		this.activeNews = activeNews;
	}

	public boolean isRetailer() {
		return retailer;
	}

	public void setRetailer(boolean retailer) {
		this.retailer = retailer;
	}

	public boolean isDistributor() {
		return distributor;
	}

	public void setDistributor(boolean distributor) {
		this.distributor = distributor;
	}

	public int getRetailerEnable() {
		return retailerEnable;
	}

	public void setRetailerEnable(int retailerEnable) {
		this.retailerEnable = retailerEnable;
	}

	public int getDistributorEnable() {
		return distributorEnable;
	}

	public void setDistributorEnable(int distributorEnable) {
		this.distributorEnable = distributorEnable;
	}

	public boolean isHomePage() {
		return homePage;
	}

	public void setHomePage(boolean homePage) {
		this.homePage = homePage;
	}

	public int getHomePageEnable() {
		return homePageEnable;
	}

	public void setHomePageEnable(int homePageEnable) {
		this.homePageEnable = homePageEnable;
	}

}
