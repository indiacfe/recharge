package com.cfeindia.b2bserviceapp.thirdparty.recharge.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "BENEID", "BENENAME", "BANKNAME", "BRANCHNAME", "CITY",
		"STATE", "IFSCCODE", "ACCOUNTNO", "MMID", "MOBILE", "BENESTATUS",
		"IFSCSTATUS", "MMIDSTATUS", "IMPSMMIDENABLE", "IMPSIFSCENABLE",
		"DELETESTATUS", "IMPSNEFTENABLE", "TRANSACTIONID", "DATETIME",
		"SENDERNAME", "SENDERMOBILE", "BENEMOBILE",
		"TOACCOUNTNO", "TRANSACTIONAMOUNT", "SERVICECHARGE", "REMARKS",
		"FROMACCOUNTNO", "ACCOUNTTYPE", "STATUSTYPE", "TRANSACTIONSTATUS",
		"MERCHANTRANSID", "IMPSREFERENCENO" })
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ItemJson {

	@JsonProperty("BENEID")
	private String BENEID;
	@JsonProperty("BENENAME")
	private String BENENAME;
	@JsonProperty("BANKNAME")
	private String BANKNAME;
	@JsonProperty("BRANCHNAME")
	private String BRANCHNAME;
	@JsonProperty("CITY")
	private String CITY;
	@JsonProperty("STATE")
	private String STATE;
	@JsonProperty("IFSCCODE")
	private String IFSCCODE;
	@JsonProperty("ACCOUNTNO")
	private String ACCOUNTNO;
	@JsonProperty("MMID")
	private String MMID;
	@JsonProperty("MOBILE")
	private String MOBILE;
	@JsonProperty("BENESTATUS")
	private String BENESTATUS;
	@JsonProperty("IFSCSTATUS")
	private String IFSCSTATUS;
	@JsonProperty("MMIDSTATUS")
	private String MMIDSTATUS;
	@JsonProperty("IMPSMMIDENABLE")
	private String IMPSMMIDENABLE;
	@JsonProperty("IMPSIFSCENABLE")
	private String IMPSIFSCENABLE;
	@JsonProperty("DELETESTATUS")
	private String DELETESTATUS;
	@JsonProperty("IMPSNEFTENABLE")
	private String IMPSNEFTENABLE;
	@JsonProperty("TRANSACTIONID")
	private String TRANSACTIONID;
	@JsonProperty("DATETIME")
	private String DATETIME;
	@JsonProperty("SENDERNAME")
	private String SENDERNAME;
	@JsonProperty("SENDERMOBILE")
	private String SENDERMOBILE;
	@JsonProperty("BENEMOBILE")
	private String BENEMOBILE;
	@JsonProperty("TOACCOUNTNO")
	private String TOACCOUNTNO;
	@JsonProperty("TRANSACTIONAMOUNT")
	private String TRANSACTIONAMOUNT;
	@JsonProperty("SERVICECHARGE")
	private String SERVICECHARGE;
	@JsonProperty("REMARKS")
	private String REMARKS;
	@JsonProperty("FROMACCOUNTNO")
	private String FROMACCOUNTNO;
	@JsonProperty("ACCOUNTTYPE")
	private String ACCOUNTTYPE;
	@JsonProperty("STATUSTYPE")
	private String STATUSTYPE;
	@JsonProperty("TRANSACTIONSTATUS")
	private String TRANSACTIONSTATUS;
	@JsonProperty("MERCHANTRANSID")
	private String MERCHANTRANSID;
	@JsonProperty("IMPSREFERENCENO")
	private String IMPSREFERENCENO;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The BENEID
	 */
	@JsonProperty("BENEID")
	public String getBENEID() {
		return BENEID;
	}

	/**
	 * 
	 * @param BENEID
	 *            The BENEID
	 */
	@JsonProperty("BENEID")
	public void setBENEID(String BENEID) {
		this.BENEID = BENEID;
	}

	/**
	 * 
	 * @return The BENENAME
	 */
	@JsonProperty("BENENAME")
	public String getBENENAME() {
		return BENENAME;
	}

	/**
	 * 
	 * @param BENENAME
	 *            The BENENAME
	 */
	@JsonProperty("BENENAME")
	public void setBENENAME(String BENENAME) {
		this.BENENAME = BENENAME;
	}

	/**
	 * 
	 * @return The BANKNAME
	 */
	@JsonProperty("BANKNAME")
	public String getBANKNAME() {
		return BANKNAME;
	}

	/**
	 * 
	 * @param BANKNAME
	 *            The BANKNAME
	 */
	@JsonProperty("BANKNAME")
	public void setBANKNAME(String BANKNAME) {
		this.BANKNAME = BANKNAME;
	}

	/**
	 * 
	 * @return The BRANCHNAME
	 */
	@JsonProperty("BRANCHNAME")
	public String getBRANCHNAME() {
		return BRANCHNAME;
	}

	/**
	 * 
	 * @param BRANCHNAME
	 *            The BRANCHNAME
	 */
	@JsonProperty("BRANCHNAME")
	public void setBRANCHNAME(String BRANCHNAME) {
		this.BRANCHNAME = BRANCHNAME;
	}

	/**
	 * 
	 * @return The CITY
	 */
	@JsonProperty("CITY")
	public String getCITY() {
		return CITY;
	}

	/**
	 * 
	 * @param CITY
	 *            The CITY
	 */
	@JsonProperty("CITY")
	public void setCITY(String CITY) {
		this.CITY = CITY;
	}

	/**
	 * 
	 * @return The STATE
	 */
	@JsonProperty("STATE")
	public String getSTATE() {
		return STATE;
	}

	/**
	 * 
	 * @param STATE
	 *            The STATE
	 */
	@JsonProperty("STATE")
	public void setSTATE(String STATE) {
		this.STATE = STATE;
	}

	/**
	 * 
	 * @return The ACCOUNTNO
	 */
	@JsonProperty("ACCOUNTNO")
	public String getACCOUNTNO() {
		return ACCOUNTNO;
	}

	/**
	 * 
	 * @param ACCOUNTNO
	 *            The ACCOUNTNO
	 */
	@JsonProperty("ACCOUNTNO")
	public void setACCOUNTNO(String ACCOUNTNO) {
		this.ACCOUNTNO = ACCOUNTNO;
	}

	/**
	 * 
	 * @return The MMID
	 */
	@JsonProperty("MMID")
	public String getMMID() {
		return MMID;
	}

	/**
	 * 
	 * @param MMID
	 *            The MMID
	 */
	@JsonProperty("MMID")
	public void setMMID(String MMID) {
		this.MMID = MMID;
	}

	/**
	 * 
	 * @return The MOBILE
	 */
	@JsonProperty("MOBILE")
	public String getMOBILE() {
		return MOBILE;
	}

	/**
	 * 
	 * @param MOBILE
	 *            The MOBILE
	 */
	@JsonProperty("MOBILE")
	public void setMOBILE(String MOBILE) {
		this.MOBILE = MOBILE;
	}

	/**
	 * 
	 * @return The BENESTATUS
	 */
	@JsonProperty("BENESTATUS")
	public String getBENESTATUS() {
		return BENESTATUS;
	}

	/**
	 * 
	 * @param BENESTATUS
	 *            The BENESTATUS
	 */
	@JsonProperty("BENESTATUS")
	public void setBENESTATUS(String BENESTATUS) {
		this.BENESTATUS = BENESTATUS;
	}

	/**
	 * 
	 * @return The IFSCSTATUS
	 */
	@JsonProperty("IFSCSTATUS")
	public String getIFSCSTATUS() {
		return IFSCSTATUS;
	}

	/**
	 * 
	 * @param IFSCSTATUS
	 *            The IFSCSTATUS
	 */
	@JsonProperty("IFSCSTATUS")
	public void setIFSCSTATUS(String IFSCSTATUS) {
		this.IFSCSTATUS = IFSCSTATUS;
	}

	/**
	 * 
	 * @return The MMIDSTATUS
	 */
	@JsonProperty("MMIDSTATUS")
	public String getMMIDSTATUS() {
		return MMIDSTATUS;
	}

	/**
	 * 
	 * @param MMIDSTATUS
	 *            The MMIDSTATUS
	 */
	@JsonProperty("MMIDSTATUS")
	public void setMMIDSTATUS(String MMIDSTATUS) {
		this.MMIDSTATUS = MMIDSTATUS;
	}

	/**
	 * 
	 * @return The IMPSMMIDENABLE
	 */
	@JsonProperty("IMPSMMIDENABLE")
	public String getIMPSMMIDENABLE() {
		return IMPSMMIDENABLE;
	}

	/**
	 * 
	 * @param IMPSMMIDENABLE
	 *            The IMPSMMIDENABLE
	 */
	@JsonProperty("IMPSMMIDENABLE")
	public void setIMPSMMIDENABLE(String IMPSMMIDENABLE) {
		this.IMPSMMIDENABLE = IMPSMMIDENABLE;
	}

	/**
	 * 
	 * @return The IMPSIFSCENABLE
	 */
	@JsonProperty("IMPSIFSCENABLE")
	public String getIMPSIFSCENABLE() {
		return IMPSIFSCENABLE;
	}

	/**
	 * 
	 * @param IMPSIFSCENABLE
	 *            The IMPSIFSCENABLE
	 */
	@JsonProperty("IMPSIFSCENABLE")
	public void setIMPSIFSCENABLE(String IMPSIFSCENABLE) {
		this.IMPSIFSCENABLE = IMPSIFSCENABLE;
	}

	/**
	 * 
	 * @return The DELETESTATUS
	 */
	@JsonProperty("DELETESTATUS")
	public String getDELETESTATUS() {
		return DELETESTATUS;
	}

	/**
	 * 
	 * @param DELETESTATUS
	 *            The DELETESTATUS
	 */
	@JsonProperty("DELETESTATUS")
	public void setDELETESTATUS(String DELETESTATUS) {
		this.DELETESTATUS = DELETESTATUS;
	}

	/**
	 * 
	 * @return The IMPSNEFTENABLE
	 */
	@JsonProperty("IMPSNEFTENABLE")
	public String getIMPSNEFTENABLE() {
		return IMPSNEFTENABLE;
	}

	/**
	 * 
	 * @param IMPSNEFTENABLE
	 *            The IMPSNEFTENABLE
	 */
	@JsonProperty("IMPSNEFTENABLE")
	public void setIMPSNEFTENABLE(String IMPSNEFTENABLE) {
		this.IMPSNEFTENABLE = IMPSNEFTENABLE;
	}
	/**
	* 
	* @return
	* The TRANSACTIONID
	*/
	@JsonProperty("TRANSACTIONID")
	public String getTRANSACTIONID() {
	return TRANSACTIONID;
	}

	/**
	* 
	* @param TRANSACTIONID
	* The TRANSACTIONID
	*/
	@JsonProperty("TRANSACTIONID")
	public void setTRANSACTIONID(String TRANSACTIONID) {
	this.TRANSACTIONID = TRANSACTIONID;
	}

	/**
	* 
	* @return
	* The DATETIME
	*/
	@JsonProperty("DATETIME")
	public String getDATETIME() {
	return DATETIME;
	}

	/**
	* 
	* @param DATETIME
	* The DATETIME
	*/
	@JsonProperty("DATETIME")
	public void setDATETIME(String DATETIME) {
	this.DATETIME = DATETIME;
	}

	/**
	* 
	* @return
	* The SENDERNAME
	*/
	@JsonProperty("SENDERNAME")
	public String getSENDERNAME() {
	return SENDERNAME;
	}

	/**
	* 
	* @param SENDERNAME
	* The SENDERNAME
	*/
	@JsonProperty("SENDERNAME")
	public void setSENDERNAME(String SENDERNAME) {
	this.SENDERNAME = SENDERNAME;
	}

	/**
	* 
	* @return
	* The SENDERMOBILE
	*/
	@JsonProperty("SENDERMOBILE")
	public String getSENDERMOBILE() {
	return SENDERMOBILE;
	}

	/**
	* 
	* @param SENDERMOBILE
	* The SENDERMOBILE
	*/
	@JsonProperty("SENDERMOBILE")
	public void setSENDERMOBILE(String SENDERMOBILE) {
	this.SENDERMOBILE = SENDERMOBILE;
	}

	/**
	* 
	* @return
	* The BENEMOBILE
	*/
	@JsonProperty("BENEMOBILE")
	public String getBENEMOBILE() {
	return BENEMOBILE;
	}

	/**
	* 
	* @param BENEMOBILE
	* The BENEMOBILE
	*/
	@JsonProperty("BENEMOBILE")
	public void setBENEMOBILE(String BENEMOBILE) {
	this.BENEMOBILE = BENEMOBILE;
	}

	/**
	* 
	* @return
	* The IFSCCODE
	*/
	@JsonProperty("IFSCCODE")
	public String getIFSCCODE() {
	return IFSCCODE;
	}

	/**
	* 
	* @param IFSCCODE
	* The IFSCCODE
	*/
	@JsonProperty("IFSCCODE")
	public void setIFSCCODE(String IFSCCODE) {
	this.IFSCCODE = IFSCCODE;
	}

	/**
	* 
	* @return
	* The TOACCOUNTNO
	*/
	@JsonProperty("TOACCOUNTNO")
	public String getTOACCOUNTNO() {
	return TOACCOUNTNO;
	}

	/**
	* 
	* @param TOACCOUNTNO
	* The TOACCOUNTNO
	*/
	@JsonProperty("TOACCOUNTNO")
	public void setTOACCOUNTNO(String TOACCOUNTNO) {
	this.TOACCOUNTNO = TOACCOUNTNO;
	}

	/**
	* 
	* @return
	* The TRANSACTIONAMOUNT
	*/
	@JsonProperty("TRANSACTIONAMOUNT")
	public String getTRANSACTIONAMOUNT() {
	return TRANSACTIONAMOUNT;
	}

	/**
	* 
	* @param TRANSACTIONAMOUNT
	* The TRANSACTIONAMOUNT
	*/
	@JsonProperty("TRANSACTIONAMOUNT")
	public void setTRANSACTIONAMOUNT(String TRANSACTIONAMOUNT) {
	this.TRANSACTIONAMOUNT = TRANSACTIONAMOUNT;
	}

	/**
	* 
	* @return
	* The SERVICECHARGE
	*/
	@JsonProperty("SERVICECHARGE")
	public String getSERVICECHARGE() {
	return SERVICECHARGE;
	}

	/**
	* 
	* @param SERVICECHARGE
	* The SERVICECHARGE
	*/
	@JsonProperty("SERVICECHARGE")
	public void setSERVICECHARGE(String SERVICECHARGE) {
	this.SERVICECHARGE = SERVICECHARGE;
	}

	/**
	* 
	* @return
	* The REMARKS
	*/
	@JsonProperty("REMARKS")
	public String getREMARKS() {
	return REMARKS;
	}

	/**
	* 
	* @param REMARKS
	* The REMARKS
	*/
	@JsonProperty("REMARKS")
	public void setREMARKS(String REMARKS) {
	this.REMARKS = REMARKS;
	}

	/**
	* 
	* @return
	* The FROMACCOUNTNO
	*/
	@JsonProperty("FROMACCOUNTNO")
	public String getFROMACCOUNTNO() {
	return FROMACCOUNTNO;
	}

	/**
	* 
	* @param FROMACCOUNTNO
	* The FROMACCOUNTNO
	*/
	@JsonProperty("FROMACCOUNTNO")
	public void setFROMACCOUNTNO(String FROMACCOUNTNO) {
	this.FROMACCOUNTNO = FROMACCOUNTNO;
	}

	/**
	* 
	* @return
	* The ACCOUNTTYPE
	*/
	@JsonProperty("ACCOUNTTYPE")
	public String getACCOUNTTYPE() {
	return ACCOUNTTYPE;
	}

	/**
	* 
	* @param ACCOUNTTYPE
	* The ACCOUNTTYPE
	*/
	@JsonProperty("ACCOUNTTYPE")
	public void setACCOUNTTYPE(String ACCOUNTTYPE) {
	this.ACCOUNTTYPE = ACCOUNTTYPE;
	}

	/**
	* 
	* @return
	* The STATUSTYPE
	*/
	@JsonProperty("STATUSTYPE")
	public String getSTATUSTYPE() {
	return STATUSTYPE;
	}

	/**
	* 
	* @param STATUSTYPE
	* The STATUSTYPE
	*/
	@JsonProperty("STATUSTYPE")
	public void setSTATUSTYPE(String STATUSTYPE) {
	this.STATUSTYPE = STATUSTYPE;
	}

	/**
	* 
	* @return
	* The TRANSACTIONSTATUS
	*/
	@JsonProperty("TRANSACTIONSTATUS")
	public String getTRANSACTIONSTATUS() {
	return TRANSACTIONSTATUS;
	}

	/**
	* 
	* @param TRANSACTIONSTATUS
	* The TRANSACTIONSTATUS
	*/
	@JsonProperty("TRANSACTIONSTATUS")
	public void setTRANSACTIONSTATUS(String TRANSACTIONSTATUS) {
	this.TRANSACTIONSTATUS = TRANSACTIONSTATUS;
	}

	/**
	* 
	* @return
	* The MERCHANTRANSID
	*/
	@JsonProperty("MERCHANTRANSID")
	public String getMERCHANTRANSID() {
	return MERCHANTRANSID;
	}

	/**
	* 
	* @param MERCHANTRANSID
	* The MERCHANTRANSID
	*/
	@JsonProperty("MERCHANTRANSID")
	public void setMERCHANTRANSID(String MERCHANTRANSID) {
	this.MERCHANTRANSID = MERCHANTRANSID;
	}

	/**
	* 
	* @return
	* The IMPSREFERENCENO
	*/
	@JsonProperty("IMPSREFERENCENO")
	public String getIMPSREFERENCENO() {
	return IMPSREFERENCENO;
	}

	/**
	* 
	* @param IMPSREFERENCENO
	* The IMPSREFERENCENO
	*/
	@JsonProperty("IMPSREFERENCENO")
	public void setIMPSREFERENCENO(String IMPSREFERENCENO) {
	this.IMPSREFERENCENO = IMPSREFERENCENO;
	}
 
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
