package com.cfeindia.b2bserviceapp.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DthNewConnectionModel {
	@JsonProperty("service_provider")
	private String serviceProvider;
	@JsonProperty("provider_key")
	private String providerKey;
	@JsonProperty("service_type")
	private String serviceType;
	@JsonProperty("service_desc")
	private String serviceDesc;
	@JsonProperty("margin")
	private String margin;
	@JsonProperty("sub_services")
	private List<DthNewConnetionSubService> subServices;
	
	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getProviderKey() {
		return providerKey;
	}

	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	
	public List<DthNewConnetionSubService> getSubServices() {
		return subServices;
	}

	public void setSubServices(List<DthNewConnetionSubService> subServices) {
		this.subServices = subServices;
	}

	

}
