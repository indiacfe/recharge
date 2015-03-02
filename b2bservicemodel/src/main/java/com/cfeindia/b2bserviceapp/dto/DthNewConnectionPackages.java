package com.cfeindia.b2bserviceapp.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DthNewConnectionPackages {
	@JsonProperty("package_name")
	private String packageName;
	@JsonProperty("package_key")
	private String packageKey;
	@JsonProperty("package_mrp")
	private String packageMrp;
	@JsonProperty("package_desc")
	private String packageDesc;
	@JsonProperty("margin")
	private String margin;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageKey() {
		return packageKey;
	}
	public void setPackageKey(String packageKey) {
		this.packageKey = packageKey;
	}
	public String getPackageMrp() {
		return packageMrp;
	}
	public void setPackageMrp(String packageMrp) {
		this.packageMrp = packageMrp;
	}
	public String getPackageDesc() {
		return packageDesc;
	}
	public void setPackageDesc(String packageDesc) {
		this.packageDesc = packageDesc;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	
	

}
