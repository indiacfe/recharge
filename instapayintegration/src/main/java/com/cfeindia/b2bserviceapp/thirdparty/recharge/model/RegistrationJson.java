package com.cfeindia.b2bserviceapp.thirdparty.recharge.model;

import java.util.HashMap;
import java.util.List;
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
@JsonPropertyOrder({"item"})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class RegistrationJson{
	@JsonProperty("item")
	private List<ItemJson> itemJsons;
	 
	@JsonProperty("item")
	public List<ItemJson> getItemJsons() {
		return itemJsons;
	}
	@JsonProperty("item")
	public void setItemJsons(List<ItemJson> itemJsons) {
		this.itemJsons = itemJsons;
	}
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
}
	