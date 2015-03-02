package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.cfeindia.b2bserviceapp.dto.DthNewConnectionModel;

public interface DthConnectionDetailsServiceInterface  {
	
	public List<DthNewConnectionModel> getDetails(String url) throws JsonParseException, JsonMappingException,
	IOException;

}
