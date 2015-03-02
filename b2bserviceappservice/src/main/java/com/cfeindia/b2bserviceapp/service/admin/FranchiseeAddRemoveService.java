package com.cfeindia.b2bserviceapp.service.admin;

import java.util.List;

import com.cfeindia.b2bserviceapp.dto.UserDetailDto;

public interface FranchiseeAddRemoveService {
	public void addFranchisee(Long userId,Long franchiseeId);
	public void removeFranchisee(Long userId);
	public List<UserDetailDto> getAllRetailers();
	public UserDetailDto getDistributor(Long distId);
}
