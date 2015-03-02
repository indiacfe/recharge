package com.cfeindia.b2bserviceapp.dao.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cfeindia.b2bserviceapp.dto.ReportBeanDto;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

public class HistoricalDataDaoImpl implements HistoricalDataDao {

	private JdbcTemplate jdbcTemplate;

	public HistoricalDataDaoImpl(DataSource historicalDataDao) {
		jdbcTemplate = new JdbcTemplate(historicalDataDao);
	}/*

	public List<Users> list(Class classObj, long id) {
		String sql = "SELECT * FROM" + classObj;
		List<Object> listContact = jdbcTemplate.query(sql,
				new RowMapper<Object>() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Object aContact = new Object();
						return aContact;
					}
				});
		return null;
	}*/

	public int getById(int contactId) {

		return contactId;
	}

	@Override
	public List<UserDetailDto> getCustomerHistoricalList() {
		String sql = "SELECT A.USER_ID, A.USERNAME AS CreatedBy,A.ENABLED,A.displaypassword,B.FIRM_NAME,B.USER_NAME,B.EMAIL_ID,B.CREATED_AT,C.curracbalance FROM users A, user_detail B,customercurrbal C WHERE A.USER_ID IN ( SELECT USER_ID FROM user_roles WHERE AUTHORITY = 'ROLE_CUSTOMER') AND B.USER_ID = A.USER_ID AND B.USER_ID=C.customer_id";

		List<UserDetailDto> uDetailDtos = jdbcTemplate.query(sql,
				new RowMapper<UserDetailDto>() {

					@Override
					public UserDetailDto mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						UserDetailDto usersDto = new UserDetailDto();
						usersDto.setPassword(rs.getString("displaypassword"));
						usersDto.setEnabled(rs.getInt("enabled"));
						usersDto.setUserId(Integer.toString(rs
								.getInt("USER_ID")));
						usersDto.setCurrBal(Long.toString(rs
								.getLong("curracbalance")));
						usersDto.setUserName(rs.getString("USER_NAME"));
						usersDto.setCreatedBy(rs.getString("CreatedBy"));
						usersDto.setFirmName(rs.getString("FIRM_NAME"));
						usersDto.setEmailId(rs.getString("EMAIL_ID"));
						usersDto.setCreatedAt(rs.getTimestamp("CREATED_AT"));
						return usersDto;
					}
				});
		return uDetailDtos;
	}

	@Override
	public List<TransactionTransportBean> generateHistoricalReport(
			Timestamp fromDate, Timestamp toDate, String serviceProvider,
			String status) {
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM recharge_transaction where ");
		if (!"ALL".equalsIgnoreCase(serviceProvider)) {
			sql = sql.append("thirdPartyServiceProvider='" + serviceProvider
					+ "' AND ");
		}
		if (!"ALL".equalsIgnoreCase(status)) {
			sql = sql.append("STATUS='" + status + "' AND ");
		}
		sql = sql.append("created_at >'" + fromDate + "' AND created_at <'"
				+ toDate+"'");
		List<TransactionTransportBean> transactionTransportBeans = jdbcTemplate
				.query(sql.toString(),
						new RowMapper<TransactionTransportBean>() {
							double totalCredit = 0.0;
							double totalDebit = 0.0;

							@Override
							public TransactionTransportBean mapRow(
									ResultSet rs, int rowNum)
									throws SQLException {
								TransactionTransportBean bean = new TransactionTransportBean();
								bean.setCreatedAt(rs.getTimestamp("created_at"));
								bean.setAccountNumber(rs
										.getLong("accountNumber"));
								bean.setCreditAmountFranchisee((rs
										.getDouble("credit_amount_franchisee")));
								totalCredit = totalCredit
										+ bean.getCreditAmountFranchisee();
								bean.setAmount(rs.getDouble("amount"));
								totalDebit = totalDebit + bean.getAmount();
								bean.setRetailerId(ExtractorUtil
										.generateIdFromString(
												rs.getString("franchisee_id"),
												"R"));
								bean.setMobileNo(rs.getString("mobile_no"));
								bean.setThirdPartyServiceProvider(rs
										.getString("thirdpartyserviceprovider"));
								bean.setConnectionid(rs
										.getString("connection_no"));
								bean.setOperator(rs.getString("operator"));
								bean.setTransactionName(rs
										.getString("transaction_name"));
								bean.setCommissionType(rs
										.getString("commission_type"));
								bean.setStatus(rs.getString("status"));
								return bean;
							}

						});

		return transactionTransportBeans;
	}

	@Override
	public List<ReportBeanDto> generateCustomerRechargeHistoricalReport(
			Timestamp fromDate, Timestamp toDate, String custId, String status,
			String thirdPartyName) {
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM customer_rechargetransaction where ");
		if (!"ALL".equalsIgnoreCase(thirdPartyName)) {
			sql = sql.append("thirdpartyserviceprovider='" + thirdPartyName
					+ "' AND ");
		}
		if (!"ALL".equalsIgnoreCase(status)) {
			sql = sql.append("STATUS='" + status + "' AND ");
		}
		
		if (!"ALL".equalsIgnoreCase(custId)) {
			sql = sql.append("customer_id='" + custId + "' AND ");
		}
		sql = sql.append("created_at >'" + fromDate + "' AND created_at <'"
				+ toDate+"'");
		List<ReportBeanDto> reportBeanDtos =jdbcTemplate.query(sql.toString(), new RowMapper<ReportBeanDto>() {
			
			@Override
			public ReportBeanDto mapRow(ResultSet rs,
					int rowNo) throws SQLException {
				 ReportBeanDto bean=new ReportBeanDto();
				bean.setCreatedAt(rs.getTimestamp("created_at"));
				bean.setClientId(ExtractorUtil.generateIdFromString(rs.getString("customer_id"),"C"));
				bean.setThirdPartyServiceProvider(rs.getString("thirdpartyserviceprovider"));
				bean.setMobileNo(rs.getString("mobile_no"));
				bean.setConnectionid(rs.getString("connection_no"));
				bean.setOperator(rs.getString("operator"));
				bean.setTransactionName(rs
						.getString("transaction_name"));
				bean.setAmount(rs.getDouble("amount"));
				bean.setTransid(rs.getString("transid"));
				bean.setAccountNumber(rs
						.getLong("accountNumber"));
				bean.setCreditAmountFranchisee((rs
						.getDouble("credit_amount_franchisee")));
				bean.setStatus(rs.getString("status"));
				bean.setThirdpartytransid(rs.getString("clienttransid"));
				return bean;
			}
		});
		return reportBeanDtos;
	}

	@Override
	public String getEntityByPrimaryKeyHistorical(String customerId) {
		String sql="SELECT FIRM_NAME FROM user_detail WHERE USER_ID = ?";
		String firstName = jdbcTemplate.queryForObject(sql, new Object[] {customerId},String.class);
		return firstName;
	}
}
