package com.cfeindia.b2bserviceapp.common;

import java.util.List;

import com.cfeindia.b2bserviceapp.entity.CompanyOperatorComission;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerTransactionTransportBean;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;
import com.cfeindia.b2bserviceapp.entity.NoticeInfo;
import com.cfeindia.b2bserviceapp.entity.OutletDetail;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface CommonService {

	public void saveEntity(Object obj);

	public Object getEntityByPrimaryKey(Class classObj, long id);

	public int getUserId(String username);

	public double getOperatorCommision(String operatorName);

	public CompanyOperatorComission getRetailerCommission(String thirdpartyServiceProvider,
			String rechargeType, String operatorName, String comissionType);

	public double getCustomerCommission(String thirdpartyServiceProvider,
			String rechargeType, String operatorName, Long userId);

	public double getLicRetailerCommission(String comissionType,
			String rechargeType, String operatorName);

	public String getThirdPartyAPISelection(String serviceType,
			String operatorName);

	public String getUsername(String userId);

	public Users searchdistributorId(String DistId);

	public UserDetail getUserDetail(String userId);

	public String checkTypeOfUser(String userId);

	public List<FranchiseeCashDepositRequest> trackCashDepositRequests();

	public List<FranchiseeCashDepositRequest> trackAllCashDepositRequests();

	public void setStatusToSuccess(long id);

	public List<DistributorBalanceTransferLog> getBalanceTransferLogs();

	public List<NoticeInfo> getNoticeInfo();

	public String validateRetailer(String username);

	public void saveOrUpdateEntity(Object obj);

	public OutletDetail getOutletDetail(Long retailerId, String operator,
			String thirdpartyname);

	public void updateCustomerCommissionObject(
			CustomerCommision customerCommision);

	public TransactionTransportBean getFranTranstionDetail(String transId,
			Long franId);

	public CustomerTransactionTransportBean getCustTransationDetails(
			String clientTransId, Long userId);
	public Long save(Object obj);
}
