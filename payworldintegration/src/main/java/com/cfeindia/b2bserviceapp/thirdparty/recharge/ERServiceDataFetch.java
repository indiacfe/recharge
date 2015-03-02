package com.cfeindia.b2bserviceapp.thirdparty.recharge;

import com.cfeindia.b2bserviceapp.thirdparty.recharge.model.BaseThirdPartyInput;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.model.EServiceDataResponse;

public interface ERServiceDataFetch {

	EServiceDataResponse getERServiceData(BaseThirdPartyInput input);

}
