package com.cfeindia.b2bserviceapp.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DthNewConnetionSubService {
	@JsonProperty("sub_service_name")
	private String subServiceName;
	@JsonProperty("sub_service_desc")
	private String subServiceDesc;
	@JsonProperty("packages")
	List<DthNewConnectionPackages> dthpackages;
	public String getSubServiceName() {
		return subServiceName;
	}
	public void setSubServiceName(String subServiceName) {
		this.subServiceName = subServiceName;
	}
	public String getSubServiceDesc() {
		return subServiceDesc;
	}
	public void setSubServiceDesc(String subServiceDesc) {
		this.subServiceDesc = subServiceDesc;
	}
	public List<DthNewConnectionPackages> getDthpackages() {
		return dthpackages;
	}
	public void setDthpackages(List<DthNewConnectionPackages> dthpackages) {
		this.dthpackages = dthpackages;
	}
	
	

	
	

}
