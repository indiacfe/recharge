package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.Timestamp;
import java.util.List;

import com.cfeindia.b2bserviceapp.entity.CompanyDistributorTransactionLog;

public interface TotalFundTransferDao {
	public List<CompanyDistributorTransactionLog> getAllUsers(
			Timestamp startDate, Timestamp endDate);

	public Double getTotalAmount(Long userId,Timestamp startDate, Timestamp endDate);

}
