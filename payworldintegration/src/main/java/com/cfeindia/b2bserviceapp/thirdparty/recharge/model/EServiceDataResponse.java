package com.cfeindia.b2bserviceapp.thirdparty.recharge.model;

import java.util.ArrayList;
import java.util.List;

public class EServiceDataResponse {
	
	List<EServiceDataItem> eserviceDataItem =new ArrayList<EServiceDataItem>();

	public List<EServiceDataItem> getEserviceDataItem() {
		return eserviceDataItem;
	}

	public void setEserviceDataItem(List<EServiceDataItem> eserviceDataItem) {
		this.eserviceDataItem = eserviceDataItem;
	}

}
