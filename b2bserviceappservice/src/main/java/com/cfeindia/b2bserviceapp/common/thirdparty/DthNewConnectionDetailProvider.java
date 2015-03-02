package com.cfeindia.b2bserviceapp.common.thirdparty;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfeindia.b2bserviceapp.common.CacheManager;
import com.cfeindia.b2bserviceapp.dto.DthNewConnectionModel;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.impl.DthConnectionDetailsServiceInterface;

@Service
public class DthNewConnectionDetailProvider implements DthNewConnectionDetails {
	
	
	@Autowired
	private DthConnectionDetailsServiceInterface dthConnectionDetailsService;
	public List<DthNewConnectionModel> getDthDetails() {
		List<DthNewConnectionModel> dthDetails=null;
		ThirdPartyDetailObject thirdPartyDetailObject  = CacheManager
				.getCurrentThirdPartyApi("instantpay");
		String token = thirdPartyDetailObject.getToken();
		try {
			dthDetails =dthConnectionDetailsService.getDetails(token);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dthDetails;

	}

}
