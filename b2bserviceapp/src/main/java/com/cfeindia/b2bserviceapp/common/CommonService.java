package com.cfeindia.b2bserviceapp.common;

import java.util.List;

import com.cfeindia.b2bserviceapp.common.constants.CommonConstants;
import com.cfeindia.b2bserviceapp.entity.DistributorBalanceTransferLog;
import com.cfeindia.b2bserviceapp.entity.DistributorCurrbal;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCashDepositRequest;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public interface CommonService {

	public void saveEntity(Object obj);

	public Object getEntityByPrimaryKey(Class classObj, long id);

	public int getUserId(String username);

	public double getOperatorCommision(String operatorName);

	public double getRetailerCommission(String thirdpartyServiceProvider,
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

}
