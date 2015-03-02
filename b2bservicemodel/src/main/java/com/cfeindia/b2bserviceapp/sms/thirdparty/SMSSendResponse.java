package com.cfeindia.b2bserviceapp.sms.thirdparty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sms_transaction")
public class SMSSendResponse {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String destMobileNumber;
	private String message;
	private String responseMessage;
	private String errorCode;
	private String errorMessage;
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SMSSendResponse [responseMessage=");
		builder.append(responseMessage);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append("]");
		return builder.toString();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDestMobileNumber() {
		return destMobileNumber;
	}
	public void setDestMobileNumber(String destMobileNumber) {
		this.destMobileNumber = destMobileNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
