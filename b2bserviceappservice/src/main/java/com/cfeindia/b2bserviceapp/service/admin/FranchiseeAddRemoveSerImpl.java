package com.cfeindia.b2bserviceapp.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.dao.admin.FranchiseeAddRemovedao;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dto.UserDetailDto;
import com.cfeindia.b2bserviceapp.entity.FranchiseeCurrBal;
import com.cfeindia.b2bserviceapp.entity.UserDetail;
import com.cfeindia.b2bserviceapp.entity.UserRole;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.util.ExtractorUtil;

@Service("franchiseeAddRemoveSerImpl")
@Transactional
public class FranchiseeAddRemoveSerImpl implements FranchiseeAddRemoveService {
	@Autowired
	private FranchiseeAddRemovedao franchiseeAddRemovedao;
	@Autowired
	private CommonDao commonDao;

	public void addFranchisee(Long disId, Long retailId) {
		FranchiseeCurrBal franchiseeCurrBal = franchiseeAddRemovedao
				.removeFranchiseeCreator(retailId);
		franchiseeCurrBal.setCreatorId(disId);
		franchiseeAddRemovedao.updateDetails(franchiseeCurrBal);

	}

	public void removeFranchisee(Long userId) {
		FranchiseeCurrBal franchiseeCurrBal = franchiseeAddRemovedao
				.removeFranchiseeCreator(userId);
		franchiseeCurrBal.setCreatorId(0L);
		franchiseeAddRemovedao.updateDetails(franchiseeCurrBal);

	}

	public List<UserDetailDto> getAllRetailers() {

		List<FranchiseeCurrBal> franCurrBal = commonDao
				.findAll(FranchiseeCurrBal.class);

		UserDetailDto userDetailDto = null;
		List<UserDetailDto> userDetailsDto = new ArrayList<UserDetailDto>();

		for (FranchiseeCurrBal fruser : franCurrBal) {
			userDetailDto = new UserDetailDto();

			userDetailDto.setB2bCurrAcBal(fruser.getB2bcurrbal().toString());
			userDetailDto.setB2bCurrAdUnitBal(fruser.getB2bcurrbaladunit()
					.toString());
			userDetailDto.setCreatedAt(fruser.getCreatedAt());
			if (fruser.getCreatorId() > 0l && fruser.getCreatorId() != null) {
				Users createdByUser = (Users) commonDao.getEntityByPrimaryKey(
						Users.class, fruser.getCreatorId()); 
				userDetailDto.setCreatedBy(createdByUser.getUserDetail()
						.getUserName());
			}

			if (fruser.getUsers() != null) {

				Users users = fruser.getUsers();
				UserRole userRole = users.getUserRole();
				Long userId = users.getUserId();
				if ("ROLE_FRANCHISEE".equals(userRole.getAuthority())) {
					userDetailDto.setUserId(ExtractorUtil.generateIdFromString(
							userId.toString(), "R"));
					UserDetail userDetail = users.getUserDetail();
					if (userDetail != null) {
						userDetailDto.setEmailId(userDetail.getEmailId());
						userDetailDto.setFirmName(userDetail.getFirmName());
						userDetailDto.setMobileNo(users.getUsername());
						userDetailDto.setEnabled(users.getEnabled());
						userDetailDto.setCreatorId(ExtractorUtil
								.generateIdFromString(fruser.getCreatorId()
										.toString(), "D"));
						if (fruser.getCreatorId() > 0L
								|| fruser.getCreatorId() != null) {
							userDetailDto.setFlag(1);
						} else {
							userDetailDto.setFlag(0);
						}

					}
					userDetailsDto.add(userDetailDto);

				}
			}
		}

		return userDetailsDto;

	}

	public UserDetailDto getDistributor(Long distId) {
		Users users = commonDao.getDistributor(distId);
		UserDetailDto udto = new UserDetailDto();
		udto.setUserId(ExtractorUtil.generateIdFromString(
				((Long) (users.getUserId())).toString(), "D"));
		udto.setUserName(users.getUserDetail().getUserName());
		udto.setMobileNo(users.getUsername());

		return udto;
	}

}
