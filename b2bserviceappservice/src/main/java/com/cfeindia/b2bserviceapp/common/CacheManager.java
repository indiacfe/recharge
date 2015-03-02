package com.cfeindia.b2bserviceapp.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cfeindia.b2bserviceapp.common.thirdparty.ThirdPartyDetailObject;
import com.cfeindia.b2bserviceapp.dao.common.CommonDao;
import com.cfeindia.b2bserviceapp.dao.common.thirdparty.ThirdPartyDetailProviderDao;
import com.cfeindia.b2bserviceapp.entity.Circle;
import com.cfeindia.b2bserviceapp.entity.Users;
import com.cfeindia.b2bserviceapp.thirdparty.operators.ThirdpartyOperatorList;

@Service("cacheManager")
@Transactional
public class CacheManager implements InitializingBean {

	@Autowired
	ThirdPartyDetailProviderDao thirdPartyDetailProviderDao;

	@Autowired
	CommonDao commonDao;

	private static CommonDao commonDaoStaticInstance;

	private static Map<String, ThirdPartyDetailObject> thirdPartyDetail = new ConcurrentHashMap<String, ThirdPartyDetailObject>();
	// private static Map<String, String> thirdPartyAPISelection = new
	// HashMap<String, String>();
	private static Map<String, String> thirdPartyOperatorList = new ConcurrentHashMap<String, String>();
	private static Map<Long, String> customerUserName = new ConcurrentHashMap<Long, String>();
	// private static Map<String, Double> retailerCommission = new
	// HashMap<String, Double>();

	private static Map<String, String> operatorCircles = new HashMap<String, String>();

	public static ThirdPartyDetailObject getCurrentThirdPartyApi(
			String thirdpartyName) {
		ThirdPartyDetailObject thirdPartyDetailObject = thirdPartyDetail
				.get(thirdpartyName);
		if (thirdPartyDetailObject == null) {
			thirdPartyDetailObject = new ThirdPartyDetailObject();
			thirdPartyDetail.put(thirdpartyName, thirdPartyDetailObject);
		}
		return thirdPartyDetailObject;
	}

	// This method will load the all usernames for customers

	public void loadcustmerUserName() {
		List<Users> customerList = (List<Users>) commonDao.getCustomerList();
		for (Users user : customerList) {
			customerUserName.put(user.getUserId(), user.getUsername());
		}

	}

	public static String getcustmerUserName(Long customerId) {
		return (customerUserName.get(customerId));

	}

	public static void refreshCustmerUserName() {
		List<Users> customerList = (List<Users>) commonDaoStaticInstance
				.getCustomerList();
		for (Users user : customerList) {
			customerUserName.put(user.getUserId(), user.getUsername());

		}

	}

	/*
	 * public static String getThirdPartyAPISelection(String serviceType,String
	 * operatorName) { StringBuilder key = new StringBuilder();
	 * key.append(serviceType).append("_").append(operatorName); String
	 * thirdPartyName = thirdPartyAPISelection.get(key.toString()); return
	 * thirdPartyName; }
	 * 
	 * public void loadThirdpartyRetailerCommission() {
	 * List<CompanyOperatorComission> companyOperatorComissions
	 * =(List<CompanyOperatorComission
	 * >)commonDao.findAll(CompanyOperatorComission.class);
	 * for(CompanyOperatorComission companyOperatorComission :
	 * companyOperatorComissions) { StringBuilder key = new StringBuilder();
	 * key.
	 * append(companyOperatorComission.getThirdpartyServiceProvider()).append
	 * ("_"
	 * ).append(companyOperatorComission.getRechargeType()).append("_").append
	 * (companyOperatorComission.getOperatorName());
	 * retailerCommission.put(key.toString(),
	 * companyOperatorComission.getRetailercommision()); } }
	 */

	public void loadThirdpartyOperators() {
		List<ThirdpartyOperatorList> thirdpartyOperatorList = (List<ThirdpartyOperatorList>) commonDao
				.findAll(ThirdpartyOperatorList.class);
		for (ThirdpartyOperatorList operatorList : thirdpartyOperatorList) {
			StringBuilder key = new StringBuilder();
			key.append(operatorList.getThirdpartyServiceProvider()).append("_")
					.append(operatorList.getRechargeType()).append("_")
					.append(operatorList.getOperatorName());
			thirdPartyOperatorList.put(key.toString(),
					operatorList.getOperatorCode());
		}

	}

	public static String getOperatorCode(String thirdPartyServiceProvider,
			String rechargeType, String operatorName) {
		StringBuilder key = new StringBuilder();
		key.append(thirdPartyServiceProvider).append("_").append(rechargeType)
				.append("_").append(operatorName);
		return thirdPartyOperatorList.get(key.toString());
	}

	/*
	 * public static Double getRetailerCommission(String
	 * thirdPartyServiceProvider, String rechargeType, String operatorName) {
	 * StringBuilder key = new StringBuilder();
	 * key.append(thirdPartyServiceProvider
	 * ).append("_").append(rechargeType).append("_").append(operatorName);
	 * return retailerCommission.get(key.toString()); }
	 */

	public void afterPropertiesSet() throws Exception {
		setThirdPartyAPIDetails();
		loadThirdpartyOperators();
		loadAllOperatorCircles();
		loadcustmerUserName();
		// loadThirdpartyRetailerCommission();
		commonDaoStaticInstance = commonDao;
	}

	private void setThirdPartyAPIDetails() {
		List<ThirdPartyDetailObject> thirdPartyDetailObjects = (List<ThirdPartyDetailObject>) commonDao
				.findAll(ThirdPartyDetailObject.class);
		for (ThirdPartyDetailObject thirdPartyDetailObject : thirdPartyDetailObjects) {
			thirdPartyDetail.put(
					thirdPartyDetailObject.getThirpartyprovidername(),
					thirdPartyDetailObject);
		}
	}

	private void loadAllOperatorCircles() {
		List<Circle> circles = (List<Circle>) commonDao.findAll(Circle.class);
		String operatorCircle;
		Set<String> operatorCircleSet;
		Map<String, Set<String>> operatorCirclesTempMap = new HashMap<String, Set<String>>();
		for (Circle circle : circles) {

			operatorCircleSet = operatorCirclesTempMap.get(circle
					.getOperatorName());
			if (operatorCircleSet == null) {
				operatorCircleSet = new HashSet<String>();
			}
			operatorCircle = circle.getCircleName() + "#"
					+ circle.getCircleCode();
			operatorCircleSet.add(operatorCircle);
			operatorCirclesTempMap.put(circle.getOperatorName(),
					operatorCircleSet);
		}

		for (String keyVal : operatorCirclesTempMap.keySet()) {
			Set<String> operatorCircleIndSet = operatorCirclesTempMap
					.get(keyVal);
			List<String> operatorCircleIndList = new ArrayList<String>(
					operatorCircleIndSet);
			Collections.sort(operatorCircleIndList);
			operatorCircle = "";
			for (String tempVal : operatorCircleIndList) {
				if (operatorCircle.isEmpty())
					operatorCircle = tempVal;
				else
					operatorCircle = operatorCircle + "@" + tempVal;
			}
			operatorCircles.put(keyVal, operatorCircle);
		}

	}

	public static String getOperatorCircles(String operatorName,
			String rechargeType) {
		return operatorCircles.get(operatorName);
	}

	public static void refreshThirdpartyAPIDetails() {
		List<ThirdPartyDetailObject> thirdPartyDetailObjects = (List<ThirdPartyDetailObject>) commonDaoStaticInstance
				.findAll(ThirdPartyDetailObject.class);
		for (ThirdPartyDetailObject thirdPartyDetailObject : thirdPartyDetailObjects) {
			thirdPartyDetail.put(
					thirdPartyDetailObject.getThirpartyprovidername(),
					thirdPartyDetailObject);
		}

	}

	public static void refreshThirdpartyOperatorList() {
		List<ThirdpartyOperatorList> thirdpartyOperatorList = (List<ThirdpartyOperatorList>) commonDaoStaticInstance
				.findAll(ThirdpartyOperatorList.class);
		for (ThirdpartyOperatorList operatorList : thirdpartyOperatorList) {
			StringBuilder key = new StringBuilder();
			key.append(operatorList.getThirdpartyServiceProvider()).append("_")
					.append(operatorList.getRechargeType()).append("_")
					.append(operatorList.getOperatorName());
			thirdPartyOperatorList.put(key.toString(),
					operatorList.getOperatorCode());
		}
	}

}
