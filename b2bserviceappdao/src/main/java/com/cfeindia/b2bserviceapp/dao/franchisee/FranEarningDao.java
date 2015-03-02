package com.cfeindia.b2bserviceapp.dao.franchisee;

import java.sql.Timestamp;
import java.util.List;

public interface FranEarningDao {
	public List<Object> getEarningDetails(Timestamp fromDate,Timestamp toDate,Long franId);
	

}
