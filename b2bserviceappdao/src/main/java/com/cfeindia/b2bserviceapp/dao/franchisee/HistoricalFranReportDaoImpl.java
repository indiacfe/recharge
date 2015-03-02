package com.cfeindia.b2bserviceapp.dao.franchisee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.dto.accountstatement.FranchiseeAccountStatementDto;
import com.cfeindia.b2bserviceapp.util.TimeStampUtil;

public class HistoricalFranReportDaoImpl implements HistoricalFranReportDao{
	
	private JdbcTemplate jdbcTemplate;
	public HistoricalFranReportDaoImpl(DataSource HistoricalFranReportDao) {
		jdbcTemplate=new JdbcTemplate(HistoricalFranReportDao);
	}
	@Override
	public List<FranchiseeAccountStatementDto> getHistoricalTransactionTransportBeanListRetailerByNumber(
			Long userId, String fromDate, String toDate, String number) {
		Timestamp fromDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringFromdate(fromDate);
		Timestamp toDateTimeStamp = TimeStampUtil
				.getTimeStampFromStringTodate(toDate);
		StringBuffer sql=new StringBuffer("SELECT * FROM recharge_transaction");
		
		sql = sql.append(" where created_at >'" + fromDateTimeStamp + "' AND created_at <'"
				+ toDateTimeStamp+"'");
		if (number != null && number.length() > 0) {
			sql =  sql.append(" AND connection_no= '"+number+"'").append(" OR mobile_no= '"+number+"'");
	     }
		sql=sql.append(" ORDER BY created_at ");
		List<FranchiseeAccountStatementDto> franchiseeAccountStatementDtos = jdbcTemplate.query(sql.toString(),
				new RowMapper<FranchiseeAccountStatementDto>() {
			
					@Override
					public FranchiseeAccountStatementDto mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						FranchiseeAccountStatementDto bean = new FranchiseeAccountStatementDto();
						bean.setAmount(rs.getDouble("amount"));
						bean.setCreatedAt(TimeStampUtil
								.getStringFromTimeStamp(rs.getTimestamp("created_at")));
						bean.setCreditAmountFranchisee(rs.getDouble("credit_amount_franchisee"));
						bean.setMobileNo(rs.getString("mobile_no"));
						bean.setConnectionid(rs.getString("connection_no"));
						bean.setOperator(rs.getString("operator"));
						bean.setTransactionType(CommonConstants.TRANSFER_TYPE_DEBIT);
						bean.setTransactionId(rs.getString("transid"));
						bean.setStatus(rs.getString("STATUS"));
						bean.setAccount(rs.getString("commission_type"));
						return bean;
					}
				});		
		
		
		return franchiseeAccountStatementDtos;
	}
}
