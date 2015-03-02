package com.cfeindia.b2bserviceapp.sms.thirdparty;

public class SMSSendRequest {
	private String userName;
	private String password;
	private String senderId;
	private String destination;
	private String message;
	private String apiUrl;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SMSSendRequest [userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", senderId=");
		builder.append(senderId);
		builder.append(", destination=");
		builder.append(destination);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	public SMSSendRequest(String userName, String password, String senderId,
			String destination, String message, String apiUrl) {
		super();
		this.userName = userName;
		this.password = password;
		this.senderId = senderId;
		this.destination = destination;
		this.message = message;
		this.apiUrl = apiUrl;
	}
	public SMSSendRequest() {
		
	}

}
