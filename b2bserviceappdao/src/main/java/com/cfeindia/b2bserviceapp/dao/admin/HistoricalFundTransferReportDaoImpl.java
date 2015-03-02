package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.model.admin.AdminFundTrasferReportDTO;

public class HistoricalFundTransferReportDaoImpl implements HistoricalFundTransferReportDao{
          
	private JdbcTemplate jdbcTemplate;

	public HistoricalFundTransferReportDaoImpl(DataSource HistoricalFundTransferReportDao) {
		jdbcTemplate = new JdbcTemplate(HistoricalFundTransferReportDao);
	} 
	
	@Override
	public List<AdminFundTrasferReportDTO> generateHistoricalFundTransferReport(Long userId, Timestamp fromdateTimeStamp,
			Timestamp toDateTimeStamp) {
			StringBuffer sql=new StringBuffer("SELECT * FROM company_bal_transaction_log");
		 
			
		sql = sql.append(" where created_at >'" + fromdateTimeStamp + "' AND created_at <'"
				+ toDateTimeStamp+"'");
		if (userId != null) {
			sql =  sql.append(" AND user_id= '"+userId+"'");
	}
		List<AdminFundTrasferReportDTO> adminFundTrasferReportDTOs = jdbcTemplate.query(sql.toString(),
						new RowMapper<AdminFundTrasferReportDTO>() {
					
							@Override
							public AdminFundTrasferReportDTO mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								AdminFundTrasferReportDTO bean = new AdminFundTrasferReportDTO();
								bean.setCreatedAt(rs.getTimestamp("created_at"));
								bean.setNewBalance(rs.getDouble("new_balance"));
								bean.setPreBalance(rs.getDouble("pre_balance"));
								bean.setTransactionId(rs.getString("transaction_id"));
								bean.setTransferAmount(rs.getDouble("transfer_amount"));
								bean.setTransferType(rs.getString("transfer_type"));
								bean.setUserType(rs.getString("user_type"));
								bean.setUserId(rs.getInt("user_id"));
								return bean;
							}
						});

		return adminFundTrasferReportDTOs;
	}
	
	@Override
	public UserDetailDto getUserDetailById(int userId) {
		String sql="SELECT users.USERNAME,user_detail.FIRM_NAME FROM user_detail,users WHERE user_detail.USER_ID= '"+userId+"'";
		 return jdbcTemplate.query(sql, new ResultSetExtractor<UserDetailDto>() {
			 
		        @Override
		        public UserDetailDto extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		            if (rs.next()) {
		            	UserDetailDto dto = new UserDetailDto();
		            	dto.setFirmName(rs.getString("FIRM_NAME"));
		            	dto.setUserName(rs.getString("USERNAME"));
		                return dto;
		            }
		            return null;
		        }
		 
		    });
		}
	
	@Override
	public int searchIdByMobNumofUser(String number) {
		String sql="SELECT USER_ID FROM users WHERE USERNAME = ?";
		int firstName = jdbcTemplate.queryForObject(sql, new Object[] {number},Integer.class);
		return firstName;
	}
	@Override
	public List<AdminFundTrasferReportDTO> generateHistoricalFundTransferReport1(
			Long userId, Timestamp fromdateTimeStamp, Timestamp toDateTimeStamp) {
		StringBuffer sql=new StringBuffer("SELECT * FROM company_distributor_transaction_log");
		 
		
		sql = sql.append(" where created_at >'" + fromdateTimeStamp + "' AND created_at <'"
				+ toDateTimeStamp+"'");
		if (userId != null) {
			sql =  sql.append(" AND user_id= '"+userId+"'");
	}
		List<AdminFundTrasferReportDTO> adminFundTrasferReportDTOs = jdbcTemplate.query(sql.toString(),
						new RowMapper<AdminFundTrasferReportDTO>() {
					
							@Override
							public AdminFundTrasferReportDTO mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								AdminFundTrasferReportDTO bean = new AdminFundTrasferReportDTO();
								bean.setCreatedAt(rs.getTimestamp("created_at"));
								bean.setNewBalance(rs.getDouble("new_balance"));
								bean.setPreBalance(rs.getDouble("pre_balance"));
								bean.setTransactionId(rs.getString("transaction_id"));
								bean.setTransferAmount(rs.getDouble("transfer_amount"));
								bean.setTransferType(rs.getString("transfer_type"));
								bean.setTransferFrom(rs.getString("transfer_from"));
								bean.setTransferTo(rs.getString("transfer_to"));
								bean.setUserId(rs.getInt("distributor_id"));
								return bean;
							}
						});

		return adminFundTrasferReportDTOs;
	}
	
	

}