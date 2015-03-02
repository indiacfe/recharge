package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.ObjectMapper;
import com.cfeindia.b2bserviceapp.dto.RemittanceUserDto;
import com.cfeindia.b2bserviceapp.exception.thirdparty.ThirdPartyRechargeCallException;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.model.ItemJson;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.model.RegistrationJson;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class InstaPayMoneyTransferETransactionServiceImpl implements
		ETransactionRequestService {

	{
		/*
		 * fix for Exception in thread "main"
		 * javax.net.ssl.SSLHandshakeException:
		 * sun.security.validator.ValidatorException: PKIX path building failed:
		 * sun.security.provider.certpath.SunCertPathBuilderException: unable to
		 * find valid certification path to requested target
		 */
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs,
					String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs,
					String authType) {
			}

		} };

		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		/*
		 * end of the fix
		 */
	}

	public void doTransaction(TransactionTransportBean transportBean)
			throws MalformedURLException {
		RegistrationJson finalData = null;
		System.out.println("hit on instantpay");
		try {
			finalData = getDataFromThirdParty(transportBean);
		} catch (ThirdPartyRechargeCallException e) {
			e.printStackTrace();
			transportBean
					.setMessage("Transaction pending from thirdparty: ThirdPartyRechargeCallException");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof MalformedURLException)
				throw (MalformedURLException) e;
			transportBean.setMessage("Transaction pending from thirdparty");
			return;
		}
		if (finalData != null) {
			Map<String, String> data = new HashMap<String, String>();
			Map<String, Object> dataMap = finalData.getAdditionalProperties();
			for (Entry<String, Object> element : dataMap.entrySet()) {
				if (isValidate(element.getValue().toString())) {
					data.put(element.getKey(), element.getValue().toString());
				}
			}
			String status = data.get("status");
			String statuscode = data.get("statuscode");
			if (finalData != null
					&& ("SUCCESS".equalsIgnoreCase(status)
							|| "0".equalsIgnoreCase(statuscode)
							|| "Beneficiary Added Successfully"
									.equalsIgnoreCase(status) || "PENDING"
								.equalsIgnoreCase(status))) {
				RemittanceUserDto dto = new RemittanceUserDto();
				if (isValidate(status)) {
					dto.setStatus(decodeData(status));
					transportBean.setMessage(decodeData(status));
				}
				if (isValidate(statuscode)) {
					dto.setStatusCode(statuscode);
					transportBean.setResponsecode(statuscode);
				}

				String cardno = data.get("cardno");
				if (cardno != null && isValidate(cardno)) {
					dto.setCardno(cardno);
				}
				String responsecode = data.get("responsecode");
				if (responsecode != null && isValidate(responsecode)) {
				}

				String name = data.get("name");
				if (name != null && isValidate(name)) {
					dto.setFirstName(decodeData(name));
				}
				String middlename = data.get("middlename");
				if (middlename != null && isValidate(middlename)) {
					dto.setMiddleName(decodeData(middlename));
				}
				String lastname = data.get("lastname");
				if (lastname != null && isValidate(lastname)) {
					dto.setLastName(decodeData(lastname));
				}
				String mothersmaidenname = data.get("mothersmaidenname");
				if (mothersmaidenname != null && isValidate(mothersmaidenname)) {
					dto.setMotherMaidenName(decodeData(mothersmaidenname));
				}
				String dob = data.get("dob");
				if (dob != null && isValidate(dob)) {
					dto.setDateOfBirth(decodeData(dob));
				}

				String emailed = data.get("emailid");
				if (emailed != null && isValidate(emailed)) {
					dto.setEmailId(decodeData(emailed));
				}
				String mobile = data.get("mobile");
				if (mobile != null && isValidate(mobile)) {
					dto.setMobileNO(mobile);
				}
				String state = data.get("state");
				if (state != null && isValidate(state)) {
					dto.setState(decodeData(state));
				}
				String city = data.get("city");
				if (city != null && isValidate(city)) {
					dto.setCity(decodeData(city));
				}
				String address = data.get("address");
				if (address != null && isValidate(address)) {
					dto.setAddress(decodeData(address));
				}
				String pincode = data.get("pincode");
				if (pincode != null && isValidate(pincode)) {
					dto.setPinCode(decodeData(pincode));
				}

				String idprooftype = data.get("idprooftype");
				if (idprooftype != null && isValidate(idprooftype)) {
					dto.setIdProofType(decodeData(idprooftype));
				}
				String idproof = data.get("idproof");
				if (idproof != null && isValidate(idproof)) {
					dto.setIdProof(decodeData(idproof));
				}
				String idproofurl = data.get("idproofurl");
				if (idproofurl != null && isValidate(idproofurl)) {
					dto.setIdProofURL(decodeData(idproofurl));
				}
				String addressprooftype = data.get("addressprooftype");
				if (addressprooftype != null && isValidate(addressprooftype)) {
					dto.setAddressProofType(decodeData(addressprooftype));
				}
				String addressproof = data.get("addressproof");
				if (addressproof != null && isValidate(addressproof)) {
					dto.setAddressProof(decodeData(addressproof));
				}
				String addressproofurl = data.get("addressproofurl");
				if (addressproofurl != null && isValidate(addressproofurl)) {
					dto.setAddressProofURL(decodeData(addressproofurl));
				}
				String kycstatus = data.get("kycstatus");
				if (kycstatus != null && isValidate(kycstatus)) {
					dto.setKycStatus(kycstatus);
				}
				String kycremarks = data.get("kycremarks");
				if (kycremarks != null && isValidate(kycremarks)) {
					dto.setKycRemarks(decodeData(kycremarks));
				}
				String cardstatus = data.get("cardstatus");
				if (cardstatus != null && isValidate(cardstatus)) {
					dto.setCardStatus(decodeData(cardstatus));
				}
				String otpstatus = data.get("otpstatus");
				if (otpstatus != null && isValidate(otpstatus)) {
					dto.setOTPStatus(decodeData(otpstatus));
				}
				String balance = data.get("balance");
				if (balance != null && isValidate(balance)) {
					dto.setAmount(balance);
				}
				String transactionlimit = data.get("transactionlimit");
				if (transactionlimit != null && isValidate(transactionlimit)) {
					dto.setTransactionLimit(transactionlimit);
				}
				String mmid = data.get("mmid");
				if (mmid != null && isValidate(mmid)) {
					dto.setMmId(mmid);
				}
				String mmidstatus = data.get("mmidstatus");
				if (mmidstatus != null && isValidate(mmidstatus)) {
					dto.setMmIdStatus(mmidstatus);
				}
				String transactionid = data.get("transactionid");
				if (transactionid != null && isValidate(transactionid)) {
					dto.setTransactionId(transactionid);
					transportBean.setThirdpartytransid(transactionid);
				}
				if (finalData.getItemJsons() != null) {

					List<RemittanceUserDto> list = new ArrayList<RemittanceUserDto>();

					for (ItemJson element : finalData.getItemJsons()) {
						RemittanceUserDto userDto = new RemittanceUserDto();
						if (element.getBENENAME() != null) {
							userDto.setBenename(decodeData(element.getBENENAME()));
						}
						if (element.getBANKNAME() != null) {
							userDto.setBankname(decodeData(element.getBANKNAME()));
						}

						userDto.setBeneId(element.getBENEID());
						if (element.getBRANCHNAME() != null) {
							userDto.setBranchname(decodeData(element.getBRANCHNAME()));
						}

						userDto.setAccountno(element.getACCOUNTNO());
						userDto.setBenemobile(element.getMOBILE());
						if (element.getCITY() != null) {
							userDto.setCity(decodeData(element.getCITY()));
						}

						userDto.setDeleteStatus(element.getDELETESTATUS());
						userDto.setIFSCCode(element.getIFSCCODE());
						userDto.setIFSCStatus(element.getIFSCSTATUS());
						userDto.setImpsifscenable(element.getIMPSIFSCENABLE());
						userDto.setImpsmmidenable(element.getIMPSMMIDENABLE());
						userDto.setImpsneftenable(element.getIMPSNEFTENABLE());
						userDto.setMmId(element.getMMID());
						userDto.setMmIdStatus(element.getMMIDSTATUS());
						if (element.getSTATE() != null) {
							userDto.setState(decodeData(element.getSTATE()));
						}
						userDto.setTransactionId(element.getTRANSACTIONID());
						// userDto.setBranchname(element.getMOBILE());
						userDto.setDateTime(element.getDATETIME());
						if(element.getSENDERNAME()!=null){
							userDto.setSenderName(decodeData(element.getSENDERNAME()));
						}
						userDto.setSenderMobile(element.getSENDERMOBILE());
						userDto.setToaccountno(element.getTOACCOUNTNO());
						userDto.setFromaccountno(element.getFROMACCOUNTNO());
						userDto.setTransactionamount(element.getTRANSACTIONAMOUNT());
						userDto.setServiceCharge(element.getSERVICECHARGE());
						userDto.setRemarks(element.getREMARKS());
						if (element.getACCOUNTTYPE()!=null) {
							userDto.setAccountType(decodeData(element.getACCOUNTTYPE()));
						}
						userDto.setTransactionstatus(element.getTRANSACTIONSTATUS());
						userDto.setStatustype(element.getSTATUSTYPE());
						userDto.setMerchanttransid(element.getMERCHANTRANSID());
						userDto.setImpsreferenceNO(element.getIMPSREFERENCENO());
						list.add(userDto);
					}
					transportBean.setRemittanceUserDtoList(list);
				}

				transportBean.setRemittanceUserDto(dto);
				String transactionstatus = data.get("transactionstatus");
				if (transactionstatus != null && isValidate(transactionstatus)) {
					dto.setTransactionstatus(decodeData(transactionstatus));
				}

				String beneid = data.get("beneid");
				if (beneid != null && isValidate(beneid)) {
					dto.setBeneId(beneid);
				}

				String benename = data.get("benename");
				if (benename != null && isValidate(benename)) {
					dto.setBenename(decodeData(benename));
				}
				String benestatus = data.get("benestatus");
				if (benestatus != null && isValidate(benestatus)) {
					dto.setBenestatus(decodeData(benestatus));
				}
				String bankname = data.get("bankname");
				if (bankname != null && isValidate(bankname)) {
					dto.setBankname(decodeData(bankname));
				}
				String branchname = data.get("branchname");
				if (branchname != null && isValidate(branchname)) {
					dto.setBranchname(decodeData(branchname));
				}
				String ifsccode = data.get("ifsccode");
				if (ifsccode != null && isValidate(ifsccode)) {
					dto.setIFSCCode(ifsccode);
				}
				String ifscstatus = data.get("ifscstatus");
				if (ifscstatus != null && isValidate(ifscstatus)) {
					dto.setIFSCStatus(decodeData(ifscstatus));
				}
				String accountno = data.get("accountno");
				if (accountno != null && isValidate(accountno)) {
					dto.setAccountno(accountno);
				}
				String impsmmidenable = data.get("impsmmidenable");
				if (impsmmidenable != null && isValidate(impsmmidenable)) {
					dto.setImpsmmidenable(decodeData(impsmmidenable));
				}
				String impsifscenable = data.get("impsifscenable");
				if (impsifscenable != null && isValidate(impsifscenable)) {
					dto.setImpsifscenable(decodeData(impsifscenable));
				}
				String impsneftenable = data.get("impsneftenable");
				if (impsneftenable != null && isValidate(impsneftenable)) {
					dto.setImpsneftenable(decodeData(impsneftenable));
				}
				String deletestatus = data.get("deletestatus");
				if (deletestatus != null && isValidate(deletestatus)) {
					dto.setDeleteStatus(deletestatus);
				}
				String toaccountno = data.get("toaccountno");
				if (toaccountno != null && isValidate(toaccountno)) {
					dto.setToaccountno(toaccountno);
				}
				String transactionamount = data.get("transactionamount");
				if (transactionamount != null && isValidate(transactionamount)) {
					dto.setTransactionamount(transactionamount);
				}
				String trans_amt = data.get("trans_amt");
				if (trans_amt != null && isValidate(trans_amt)) {
					dto.setTransactionamount(trans_amt);
				}
				String account_no = data.get("account_no");
				if (account_no != null && isValidate(account_no)) {
					dto.setAccountno(account_no);
				}
				String ipay_id = data.get("ipay_id");
				if (ipay_id != null && isValidate(ipay_id)) {
					dto.setIpayId(ipay_id);
				}

				String charged_amt = data.get("charged_amt");
				if (charged_amt != null && isValidate(charged_amt)) {
					dto.setChargedAmount(charged_amt);
				}
				String remarks = data.get("remarks");
				if (remarks != null && isValidate(remarks)) {
					dto.setRemarks(decodeData(remarks));
				}
				String fromaccountno = data.get("fromaccountno");
				if (fromaccountno != null && isValidate(fromaccountno)) {
					dto.setFromaccountno(decodeData(fromaccountno));
				}
				String accounttype = data.get("accounttype");
				if (accounttype != null && isValidate(accounttype)) {
					dto.setAccountType(decodeData(accounttype));
				}
				String statustype = data.get("statustype");
				if (statustype != null && isValidate(statustype)) {
					dto.setStatustype(decodeData(statustype));
				}
				String merchanttransid = data.get("merchanttransid");
				if (merchanttransid != null && isValidate(merchanttransid)) {
					dto.setMerchanttransid(decodeData(merchanttransid));
				}

				if ("PENDING".equalsIgnoreCase(status)) {
					transportBean.setErrorcode(statuscode);
					transportBean.setErrorMessage(status);
				}
			} else if (finalData != null
					&& (statuscode.equalsIgnoreCase("RPI") || Integer
							.parseInt(statuscode) != 0)) {
				transportBean.setErrorCode(2);
				transportBean.setStdCode(statuscode);
				transportBean.setErrorcode(statuscode);
				if (!(status != null && status.contains("Agent")))
					transportBean.setMessage(status);
				else
					transportBean.setMessage("Problem from operator");
				transportBean.setErrorMessage(status);

			}
		}

	}

	static Boolean isValidate(String data) {
		Boolean val = true;
		if (data == null || data.isEmpty() || data.equalsIgnoreCase("[]")
				|| data.contains("{}")) {
			val = false;
		}
		return val;
	}

	private RegistrationJson getDataFromThirdParty(
			TransactionTransportBean requestInput)
			throws ThirdPartyRechargeCallException,
			UnsupportedEncodingException {
		StringBuilder url = new StringBuilder();
		String connectURL[] = requestInput.getOperatorCode().split(";");
		String baseUrl = null;
		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("getDataFromURL")) {
			System.out.println("Url-" + requestInput.getOperatorCode());
			baseUrl = connectURL[0];
			url = registerationURL(url, requestInput);
		}

		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("verifyOTPURL")) {
			System.out.println("Url" + requestInput.getOperatorCode());
			baseUrl = connectURL[1];
			url = verifyOTPURL(url, requestInput);
		}

		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("resendOTPURL")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[0];
			url = resendOTPURL(url, requestInput);
		}
		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("senderLoginGetOTPURL")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[0];
			url = senderLoginGetOTPURL(url, requestInput);
		}
		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("forgotpin")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[1];
			url = forgotpin(url, requestInput);
		}
		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("addBeneficiaryforIFSC")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[0];
			url = addBeneficiaryforIFSC(url, requestInput);
		}

		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("addBeneficiaryForMMID")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[0];
			url = addBeneficiaryForMMID(url, requestInput);
		}

		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("verifyBeneficiaryOTP")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[1];
			url = verifyBeneficiaryOTP(url, requestInput);
		}
		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("viewBeneficiary")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[0];
			url = viewBeneficiary(url, requestInput);
		}
		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("removeBeneficiary")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[0];
			url = removeBeneficiary(url, requestInput);
		}
		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("verifyRemoveBeneficiaryOTP")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[1];
			url = verifyRemoveBeneficiaryOTP(url, requestInput);
		}
		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("checkSenderBalanceOTP")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[0];
			url = checkSenderBalanceOTP(url, requestInput);
		}

		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("topUpSenderWallet")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[1];
			url = topUpSenderWallet(url, requestInput);
		}
		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("fundTransferTransactionForMMID")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[1];
			url = fundTransferTransactionForMMID(url, requestInput);
		}
		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("fundTransferTransactionForIFSC")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[1];
			url = fundTransferTransactionForIFSC(url, requestInput);
		}

		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("transferHistory")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[0];
			url = transferHistory(url, requestInput);
		}
		if (requestInput.getRemittanceUserDto().getUrlStatus()
				.equalsIgnoreCase("upgradeSender")) {
			System.out.println("Url " + requestInput.getOperatorCode());
			baseUrl = connectURL[1];
			url = upgradeSender(url, requestInput);
		}

		url.append("&format=").append("json");

		System.out.println(url);
		ObjectMapper obj = new ObjectMapper();
		RegistrationJson jSonResponse = null;
		try {

			String dataFromURL = getDataFromURL(baseUrl, url.toString())
					.toString();
			jSonResponse = obj.readValue(dataFromURL, RegistrationJson.class);
			System.out.println("jSonResponse-" + jSonResponse);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ThirdPartyRechargeCallException(
					"Thirdparty call exception");
		}
		return jSonResponse;
	}

	private String encodeData(String data) {
		String encodedData = null;
		try {
			encodedData = URLEncoder.encode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodedData;
	}

	private String decodeData(String data) {
		String decodeData = null;
		try {
			decodeData = URLDecoder.decode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decodeData;
	}

	public String getDataFromURL(String url, String finalInput)
			throws MalformedURLException, ThirdPartyRechargeCallException {
		String inputLine = null;
		System.out.println(url);
		URL oracle = new URL(url);
		StringBuilder response = new StringBuilder();
		try {
			URLConnection urlCon = oracle.openConnection();
			HttpURLConnection con = null;
			if (urlCon instanceof HttpsURLConnection) {
				con = (HttpsURLConnection) oracle.openConnection();
			} else {
				con = (HttpURLConnection) oracle.openConnection();
			}

			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Length",
					String.valueOf(finalInput.length()));

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(finalInput);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + finalInput);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine).append("\n");
			}
			// response.delete(response.length() - 1, response.length());
			in.close();
			System.out.println("\n\nresponse: \n  " + response);

		} catch (IOException e) {
			e.printStackTrace();
			throw new ThirdPartyRechargeCallException("IOException ");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ThirdPartyRechargeCallException("IOException ");
		}
		// System.out.println("reponse" + response.toString());

		return response.toString();
	}

	public static void main(String[] args) throws MalformedURLException,
			ThirdPartyRechargeCallException {
		/*
		 * String finalInput =
		 * "1234%$A01000000000000000137759132297303600%$2013-08-27 13:45:22%$A14.SUCCESS%$Successfully Completed the Transaction$$$"
		 * ; Pattern p = Pattern.compile("%$", Pattern.LITERAL); String[] a =
		 * p.split(finalInput); System.out.println(a[0]);
		 * System.out.println(a[1]); System.out.println(a[2]);
		 * System.out.println(a[3]); System.out.println(a[4]);
		 */
		// String finalInputs =https://www.instantpay.in/ws/dmr/login
		// "token=33bee4ad5444135d47400d6567a6f343&username=Vikas&usermiddlename=Anand&userlastname=Anand&usermothersmaidenname=Mannu&userdateofbirth=05%2F01%2F1982&useremailid=vikas.cool%40gmail.com&usermobileno=9650021874&userstate=UTTAR%20PRADESH&usercity=Noida&useraddress=Cfe+india%2C+B-96%2C+sector-64%2C+Noida&pincode=201301&useridprooftype=&useridproof=&idproofurl=&useraddressprooftype=&useraddressproof=&addressproofurl=&format=json";
		// String url = "https://www.instantpay.in/ws/dmr/register";
		String url = "https://www.instantpay.in/ws/dmr/beneficiaryView";
		String finalInputs = "token=33bee4ad5444135d47400d6567a6f343&cardno=3333003433179366&format=json";
		String urlsubmit = "https://www.instantpay.in/ws/dmr/login";
		String finalInputssubmit = "token=33bee4ad5444135d47400d6567a6f343&usermobileno=7038367390&otp=285410&format=json";
		// new
		// InstaPayMoneyTransferETransactionServiceImpl().getDataFromURL(url,finalInputs);
		// new
		// InstaPayMoneyTransferETransactionServiceImpl().getDataFromURL(urlsubmit,finalInputssubmit);

		/*
		 * ObjectMapper obj = new ObjectMapper(); RegistrationJson jSonResponse
		 * = null; try { String dataFromURL = new
		 * InstaPayMoneyTransferETransactionServiceImpl() .getDataFromURL(url,
		 * finalInputs); if (dataFromURL.contains("[")) {
		 * System.out.println("hi"); dataFromURL =
		 * dataFromURL.replaceAll("(\\{\\})", "\"\""); } else { dataFromURL =
		 * dataFromURL.replaceAll("(\\{\\})", "\"\""); dataFromURL =
		 * dataFromURL.replaceAll("item", "single"); }
		 * 
		 * System.out.println(dataFromURL); jSonResponse =
		 * obj.readValue(dataFromURL, RegistrationJson.class); //
		 * System.out.println("jSonResponse-" + jSonResponse); } catch
		 * (Exception e) { e.printStackTrace(); throw new
		 * ThirdPartyRechargeCallException( "Thirdparty call exception");
		 * }"(\\[\\])"
		 */
		String s2="23/02/2015 17:30:00";
		System.out.println("hifg  " + "[]");

	}

	private static String removeTrailingZeros(double d) {
		return String.valueOf(d).replaceAll("[0]*$", "").replaceAll(".$", "");
	}

	private StringBuilder registerationURL(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&username=").append(
				encodeData(requestInput.getRemittanceUserDto().getFirstName()));
		url.append("&usermiddlename=")
				.append(encodeData(requestInput.getRemittanceUserDto()
						.getMiddleName()));
		url.append("&userlastname=").append(
				encodeData(requestInput.getRemittanceUserDto().getLastName()));
		if (requestInput.getRemittanceUserDto().getMotherMaidenName() != null) {
			url.append("&usermothersmaidenname=").append(
					requestInput.getRemittanceUserDto().getMotherMaidenName());
		} else {
			url.append("&usermothersmaidenname=").append("");
		}
		if (requestInput.getRemittanceUserDto().getDateOfBirth() != null) {
			url.append("&userdateofbirth=").append(
					requestInput.getRemittanceUserDto().getDateOfBirth());
		} else {
			url.append("&userdateofbirth=").append("");
		}
		if (requestInput.getRemittanceUserDto().getEmailId() != null) {
			url.append("&useremailid=")
					.append(encodeData(requestInput.getRemittanceUserDto()
							.getEmailId()));
		} else {
			url.append("&useremailid=").append("");
		}
		url.append("&usermobileno=").append(requestInput.getMobileNo());
		url.append("&userstate=").append(
				encodeData(requestInput.getRemittanceUserDto().getState()));
		url.append("&usercity=").append(
				encodeData(requestInput.getRemittanceUserDto().getCity()));
		url.append("&useraddress=").append(
				encodeData(requestInput.getRemittanceUserDto().getAddress()));
		url.append("&pincode=").append(
				encodeData(requestInput.getRemittanceUserDto().getPinCode()));
		url.append("&useridprooftype=").append("");
		url.append("&useridproof=").append("");
		url.append("&idproofurl=").append("");
		url.append("&useraddressprooftype=").append("");
		url.append("&useraddressproof=").append("");
		url.append("&addressproofurl=").append("");
		return url;
	}

	private StringBuilder verifyOTPURL(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&transactionid=").append(
				requestInput.getThirdpartytransid());
		url.append("&otp=")
				.append(requestInput.getRemittanceUserDto().getOTP());
		return url;
	}

	private StringBuilder resendOTPURL(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&transactionid=").append(
				requestInput.getRemittanceUserDto().getTransactionId());
		return url;
	}

	private StringBuilder senderLoginGetOTPURL(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&usermobileno=").append(
				(requestInput.getRemittanceUserDto().getMobileNO()));
		url.append("&param1=").append(
				(requestInput.getRemittanceUserDto().getPin()));
		return url;
	}

	/*
	 * private StringBuilder senderLoginURL(StringBuilder url,
	 * TransactionTransportBean requestInput) {
	 * url.append("token=").append(requestInput.getToken());
	 * url.append("&usermobileno=").append(
	 * (requestInput.getRemittanceUserDto().getMobileNO())); url.append("&otp=")
	 * .append(requestInput.getRemittanceUserDto().getOTP()); return url; }
	 */

	private StringBuilder forgotpin(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&usermobileno=").append(
				(requestInput.getRemittanceUserDto().getMobileNO()));
		return url;
	}

	private StringBuilder addBeneficiaryforIFSC(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		url.append("&benename=").append(
				requestInput.getRemittanceUserDto().getBenename());
		url.append("&bankname=").append(
				requestInput.getRemittanceUserDto().getBankname());
		url.append("&branchname=").append(
				requestInput.getRemittanceUserDto().getBranchname());
		url.append("&city=").append(
				requestInput.getRemittanceUserDto().getCity());
		url.append("&state=").append(
				requestInput.getRemittanceUserDto().getState());
		url.append("&ifsccode=").append(
				requestInput.getRemittanceUserDto().getIFSCCode());
		url.append("&accountno=").append(
				requestInput.getRemittanceUserDto().getAccountno());
		url.append("&bebemobile=").append(
				requestInput.getRemittanceUserDto().getBenemobile());
		return url;
	}

	private StringBuilder addBeneficiaryForMMID(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		url.append("&benename=").append(
				requestInput.getRemittanceUserDto().getBenename());
		url.append("&mmid=").append(
				requestInput.getRemittanceUserDto().getMmId());
		url.append("&bebemobile=").append(
				requestInput.getRemittanceUserDto().getBenemobile());
		return url;
	}

	private StringBuilder verifyBeneficiaryOTP(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		url.append("&otp=")
				.append(requestInput.getRemittanceUserDto().getOTP());
		url.append("&beneid=").append(
				requestInput.getRemittanceUserDto().getBeneId());
		return url;
	}

	private StringBuilder viewBeneficiary(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		return url;
	}

	private StringBuilder removeBeneficiary(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		url.append("&beneid=").append(
				requestInput.getRemittanceUserDto().getBeneId());
		return url;
	}

	private StringBuilder verifyRemoveBeneficiaryOTP(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		url.append("&beneid=").append(
				requestInput.getRemittanceUserDto().getBeneId());
		url.append("&otp=")
				.append(requestInput.getRemittanceUserDto().getOTP());
		return url;
	}

	private StringBuilder checkSenderBalanceOTP(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		return url;
	}

	private StringBuilder topUpSenderWallet(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		url.append("&agentid=").append(requestInput.getTransid());
		url.append("&topupamount=").append(
				requestInput.getRemittanceUserDto().getAmount());
		url.append("&mobile=").append(requestInput.getMobileNo());
		return url;
	}

	/*
	 * private StringBuilder fundTransferGetOTP(StringBuilder url,
	 * TransactionTransportBean requestInput) {
	 * url.append("token=").append(requestInput.getToken());
	 * url.append("&cardno=").append(
	 * requestInput.getRemittanceUserDto().getCardno()); return url; }
	 */

	private StringBuilder fundTransferTransactionForMMID(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		url.append("&beneid=").append(
				requestInput.getRemittanceUserDto().getBeneId());
		url.append("&transamount=").append(
				requestInput.getRemittanceUserDto().getAmount());
		url.append("&transtype=").append("1");
		url.append("&transtypedesc=").append(
				requestInput.getRemittanceUserDto().getMmId());
		url.append("&benemobile=").append(
				requestInput.getRemittanceUserDto().getBenemobile());

		if (requestInput.getRemittanceUserDto().getRemarks() != null) {
			url.append("&remarks=").append(
					requestInput.getRemittanceUserDto().getRemarks());
		} else {
			url.append("&remarks=").append("");
		}
		return url;
	}

	private StringBuilder fundTransferTransactionForIFSC(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		url.append("&beneid=").append(
				requestInput.getRemittanceUserDto().getBeneId());
		url.append("&transamount=").append(
				requestInput.getRemittanceUserDto().getAmount());
		url.append("&transtype=").append("2");
		url.append("&transtypedesc=").append(
				requestInput.getRemittanceUserDto().getAccountno());
		url.append("&ifsccode=").append(
				requestInput.getRemittanceUserDto().getIFSCCode());
		url.append("&benemobile=").append(
				requestInput.getRemittanceUserDto().getBenemobile());

		if (requestInput.getRemittanceUserDto().getRemarks() != null) {
			url.append("&remarks=").append(
					requestInput.getRemittanceUserDto().getRemarks());
		} else {
			url.append("&remarks=").append("");
		}
		return url;
	}

	private StringBuilder transferHistory(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		url.append("&fromdate=").append(
				requestInput.getRemittanceUserDto().getFromDate());
		url.append("&todate=").append(
				requestInput.getRemittanceUserDto().getToDate());
		return url;
	}

	private StringBuilder upgradeSender(StringBuilder url,
			TransactionTransportBean requestInput) {
		url.append("token=").append(requestInput.getToken());
		url.append("&cardno=").append(
				requestInput.getRemittanceUserDto().getCardno());
		url.append("&username=").append(
				requestInput.getRemittanceUserDto().getFirstName());
		url.append("&usermiddlename=").append(
				requestInput.getRemittanceUserDto().getMiddleName());
		url.append("&userlastname=").append(
				requestInput.getRemittanceUserDto().getLastName());
		url.append("&usermothersmaidenname=").append(
				encodeData(requestInput.getRemittanceUserDto()
						.getMotherMaidenName()));
		url.append("&userdateofbirth=").append(
				requestInput.getRemittanceUserDto().getDateOfBirth());
		url.append("&usermailid=").append(
				encodeData(requestInput.getRemittanceUserDto().getEmailId()));
		url.append("&userstate=").append(
				requestInput.getRemittanceUserDto().getState());
		url.append("&usercity=").append(
				requestInput.getRemittanceUserDto().getCity());
		url.append("&useraddress=").append(
				encodeData(requestInput.getRemittanceUserDto().getAddress()));
		url.append("&pincode=").append(
				requestInput.getRemittanceUserDto().getPinCode());
		url.append("&idprooftype=")
				.append(encodeData(requestInput.getRemittanceUserDto()
						.getIdProofType()));
		url.append("&idproof=").append(
				encodeData(requestInput.getRemittanceUserDto().getIdProof()));
		url.append("&idproofurl=").append(
				requestInput.getRemittanceUserDto().getIdProofURL());
		url.append("&addressprooftype=").append(
				requestInput.getRemittanceUserDto().getAddressProofType());
		url.append("&addressproof=").append(
				requestInput.getRemittanceUserDto().getAddressProof());
		url.append("&addressproofurl=").append(
				requestInput.getRemittanceUserDto().getAddressProofURL());
		return url;
	}

}
