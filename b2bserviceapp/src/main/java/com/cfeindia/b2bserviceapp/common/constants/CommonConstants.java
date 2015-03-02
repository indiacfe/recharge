package com.cfeindia.b2bserviceapp.common.constants;

public interface CommonConstants {

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_FRANCHISEE = "ROLE_FRANCHISEE";
	public static final String ROLE_RETAILER = "ROLE_RETAILER";
	public static final String ROLE_DISTRIBUTOR = "ROLE_DISTRIBUTOR";
	public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
	public static final String companyName = "CyberTel";
	public static final String COMPANY_TO_DIST_TRANSID_PREFIX = "CTD";
	public static final String DIST_CURR_AD_UNIT_TO_DIST_CURR_TRANSID_PREFIX="ATC";
	public static final String DIST_INTER_TRANSFER_FROM="DISTCURRADUNIT";
	public static final String DIST_INTER_TRANSFER_TO="DISTCURR";
	public static final String TRANSFER_TYPE_DEBIT="DR";
	public static final String TRANSFER_TYPE_CREDIT="CR";
	public static final String COMPANY_TRANSFER_FROM="COMP";
	public static final String COMPANY_TRANSFER_TO="DIST_AD_UNIT";
	public static final String DISTRIBUTOR_TO_RETAILER="DTR";
	public static final String DIST_TRANSFER_FROM="DIST";
	public static final String DIST_TRANSFER_TO="FRAN";
	public static final double DEFAULT_DISTRIBUTOR_COMMISSION = 0.5;
	public static final double ZERO = 0.0;
	public static final int RETAILER_CREATION_CHARGE = 500;
	public static final int DISTRIBUTOR_CREATION_CHARGE = 5000;
	public static final String NEW_ID_CREATION = "NEWIDCREATION";
	public static final String SUCCESS="SUCCESS";
	public static final String FAILURE="FAILURE";
	public static final String COMPLETE="COMPLETE";
	public static final String IN_PROCESS="IN_PROCESS";
	public static final String REFUND="REFUND";
	public static final String COMP_TO_FRAN="CTR";
	public static final String COMP_TO_DIST_CURRBAL="DIST_BUSINESSBAL";
}
