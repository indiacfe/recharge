package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.CustomerCommision;
import com.cfeindia.b2bserviceapp.entity.CustomerCurrentBalance;
import com.cfeindia.b2bserviceapp.entity.Users;

public interface CustomerFundTransferService {

	public Users getCustomer(String userIdOrMobnum);

	public CustomerCurrentBalance getCustCurrBal(Long custId);

	String companyBalanceTransToCustService(Long customerId,
			double transferAmount, String companyName, Long userId);

	public List<UserDetailDto> getUsers();

	public List<CustomerCommision> getCommisions(Long custId);

	public String deductAmountFromApiAccount(Long customerId,
			double deductAmount, String remark);

}
