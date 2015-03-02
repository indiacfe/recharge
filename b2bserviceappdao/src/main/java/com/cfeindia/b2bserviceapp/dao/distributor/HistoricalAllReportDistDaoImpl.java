package com.cfeindia.b2bserviceapp.dao.distributor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

public class HistoricalAllReportDistDaoImpl implements HistoricalAllReportDistDao{
	
	private JdbcTemplate jdbcTemplate;

	public HistoricalAllReportDistDaoImpl(DataSource HistoricalAllReportDistDao) {
		jdbcTemplate = new JdbcTemplate(HistoricalAllReportDistDao);
	} 
	@Override
	public List<DistributorBalanceTransferLog> getHistoricalBalanceTransferStatement(
			Timestamp fromDate, Timestamp toDate, String distId) {
		StringBuffer sql=new StringBuffer("SELECT * FROM distributorbaltransferlog");
		 
		
		sql = sql.append(" where created_at >'" + fromDate + "' AND created_at <'"
				+ toDate+"'");
		
		List<DistributorBalanceTransferLog> distributorBalanceTransferLogs = jdbcTemplate.query(sql.toString(),
						new RowMapper<DistributorBalanceTransferLog>() {
					
							@Override
							public DistributorBalanceTransferLog mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								DistributorBalanceTransferLog bean = new DistributorBalanceTransferLog();
								bean.setCreatedAt(rs.getTimestamp("created_at"));
								String dateTime = TimeStampUtil.getStringFromTimeStamp(bean
										.getCreatedAt());
								bean.setDateTime(dateTime);
								bean.setNewB2bAdUnitBal(rs.getDouble("newb2badunitbal"));
								bean.setNewB2bCurrBal(rs.getDouble("newb2bcurrbal"));
								bean.setTransactionId(rs.getString("transaction_id"));
								bean.setTransferAmount(rs.getDouble("transfer_amount"));
								bean.setTransferType(rs.getString("transfer_type"));
								bean.setTransferTo(rs.getString("transfer_from") +" to "+rs.getString("transfer_to"));
								bean.setDistId(rs.getLong("distributorid"));
								bean.setFranchiseeid(rs.getLong("franchiseeid"));
								return bean;
							}
						});

		return distributorBalanceTransferLogs;
	}

}
