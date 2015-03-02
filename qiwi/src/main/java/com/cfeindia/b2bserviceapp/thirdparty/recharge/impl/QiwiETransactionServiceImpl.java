package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.cfeindia.apps.b2bserviceapp.thirdparty.qiwi.Request;
import com.cfeindia.apps.b2bserviceapp.thirdparty.qiwi.RequestSerializer;
import com.cfeindia.apps.b2bserviceapp.thirdparty.qiwi.Response;
import com.cfeindia.apps.b2bserviceapp.thirdparty.qiwi.ResponseSerializer;
import com.cfeindia.b2bserviceapp.exception.thirdparty.ThirdPartyRechargeCallException;
import com.cfeindia.b2bserviceapp.thirdparty.recharge.ETransactionRequestService;
import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public class QiwiETransactionServiceImpl implements ETransactionRequestService {
	public void doTransaction(TransactionTransportBean transportBean)
			throws MalformedURLException, IOException {
		//Long timesmills = System.currentTimeMillis();
		//transportBean.setTransid(timesmills.toString());
		try {

			// step 1 request done.
			Request request = null;
			Response response1 = null;
			Request request2 = null;
			Response response2 = null;
			Request request3 = null;
			Response response3 = null;
			boolean transactionSuccess = false;
			transportBean.setErrorCode(2);
			request = setAuthorizePayment(transportBean);
			response1 = getResponse(transportBean, request);
			if (response1 == null) {
				transportBean
				.setMessage("Null.Transaction Issue from thirdparty: ThirdPartyRechargeCallException");
				return;
			}
			// step 2 request done.
			if (response1 != null
					&& response1.getProviders() != null 
					&& response1.getProviders().getAuthorizePayment()
							.getResult() == 0) {
				if (response1.getProviders().getAuthorizePayment()
						.getResult().equals(0)
						&& (response1.getProviders().getAuthorizePayment()
								.getPayment().getStatus().equals(3)  || 	(response1.getProviders().getAuthorizePayment()
										.getPayment().getStatus().equals(0) && response1.getProviders().getAuthorizePayment()
										.getPayment().getResult().equals(90))
										)) {
					request2 = getConfirmPayment(transportBean);
					response2 = getResponse(transportBean, request2);
					if (response1.getProviders().getAuthorizePayment()
							.getPayment().getStatus().equals(0) && response1.getProviders().getAuthorizePayment()
							.getPayment().getResult().equals(90) && response2.getResult().equals(0)) {
						/*transportBean.setTransid(response2.getProviders()
								.getGetPaymentStatus().getPayment()
								.getUid().toString());*/
						transportBean.setThirdpartytransid("response_90");
						Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
						transportBean.setThirdPartyTransDateTime(time
								.toString());
						transportBean.setMessage("success" + " : "
								+ "completed");
						transactionSuccess = true;
						transportBean.setErrorCode(0);

					} else if (response2.getProviders().getConfirmPayment().getResult().equals(0)
							&& response2.getProviders().getConfirmPayment()
									.getPayment().getStatus().equals(1)) {
						request3 = getPaymentStatus(transportBean);
						response3 = getResponse(transportBean, request3);
						while (true) {

							if (response3.getProviders().getAddOfflinePayment()
									.getResult() > 0) {
								transportBean.setErrorMessage("Transaction failed " + response3.getProviders().getAddOfflinePayment()
										.getResult());
								transportBean.setMessage("Transaction failed " + response3.getProviders().getAddOfflinePayment()
										.getResult());
								break;
							} else if (response3.getResult().equals(0)
									&& response3.getProviders()
											.getAddOfflinePayment().getPayment()
											.getStatus().equals(1)) {
								transportBean.setErrorMessage("Transaction failed " + response3.getProviders().getAddOfflinePayment()
										.getResult());
								transportBean.setMessage("Transaction failed " + response3.getProviders().getAddOfflinePayment()
										.getResult());
								break;

							} else if (response3.getProviders().getConfirmPayment()
									.getResult().equals(0)
									&& response3.getProviders().getConfirmPayment()
											.getPayment().getStatus().equals(2)) {
								/*transportBean.setTransid(response2.getProviders()
										.getGetPaymentStatus().getPayment()
										.getUid().toString());*/
								transportBean.setThirdpartytransid(response3
										.getProviders().getGetPaymentStatus()
										.getPayment().getUid().toString());
								XMLGregorianCalendar cal = response3.getProviders()
										.getGetPaymentStatus().getPayment()
										.getDate();
								Timestamp time = new Timestamp(cal.getMillisecond());
								transportBean.setThirdPartyTransDateTime(time
										.toString());
								transportBean.setMessage("success" + " : "
										+ "completed");
								transactionSuccess = true;
								transportBean.setErrorCode(0);
								break;

							} else if (response3.getProviders().getConfirmPayment()
									.getResult().equals(0)
									&& response3.getProviders().getConfirmPayment()
											.getPayment().getStatus().equals(1)) {
								continue;

							}
						}

					}
					if(!transactionSuccess) {
						transportBean.setErrorCode(2);
						if(response3.getProviders()!=null && response3.getProviders().getAddOfflinePayment()!=null) {
						transportBean.setErrorMessage("Transaction failed " + response3.getProviders().getAddOfflinePayment()
								.getResult());
						}
					}
				} else {
					transportBean.setErrorCode(2);
				}
				
				
			} else {
				StringBuilder result = new StringBuilder("Null.Transaction Issue from thirdparty: ThirdPartyRechargeCallException");
				if(response1.getProviders() != null 
					&& response1.getProviders().getAuthorizePayment()!=null) {
					result.append(response1.getProviders().getAuthorizePayment()
							.getResult());
				}
				transportBean.setMessage("Not able to Process " + response1.getProviders().getAuthorizePayment()
						.getResult());
				transportBean.setErrorCode(2);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			transportBean
			.setMessage("error. Transaction pending from thirdparty: ThirdPartyRechargeCallException");
		}

	}

	// This method set aut and client for each request
	public static Request setAuthClient(TransactionTransportBean transBean,
			Request request) {
		Request.Auth auth = new Request.Auth();
		Request.Client client = new Request.Client();
		auth.setLogin(transBean.getLoginStatus());
		auth.setSign(transBean.getToken());
		auth.setSignAlg(transBean.getToken3());
		client.setSerial(Integer.parseInt(transBean.getToken2()));
		client.setSoftware("xml");
		client.setTerminal(Integer.parseInt(transBean.getToken1()));
		request.setAuth(auth);
		request.setClient(client);
		return request;
	}

	public static Request setAuthorizePayment(
			TransactionTransportBean requestInput) {
		Request request = new Request();
		setAuthClient(requestInput, request);
		Request.Providers providers = new Request.Providers();
		Request.Providers.AuthorizePayment authorize = new Request.Providers.AuthorizePayment();
		Request.Providers.AuthorizePayment.Payment payment = new Request.Providers.AuthorizePayment.Payment();
		Request.Providers.AuthorizePayment.Payment.From from = new Request.Providers.AuthorizePayment.Payment.From();
		Request.Providers.AuthorizePayment.Payment.To to = new Request.Providers.AuthorizePayment.Payment.To();
		Request.Providers.AuthorizePayment.Payment.Receipt receipt = new Request.Providers.AuthorizePayment.Payment.Receipt();
		from.setAmount(requestInput.getAmount().intValue());
		from.setCurrency(643);
		to.setService(Integer.parseInt(requestInput.getOperatorCode()));
		if (requestInput.getMobileNo() != null) {
			to.setAccount(Long.parseLong(requestInput.getMobileNo()));
		} else {
			to.setAccount(Long.parseLong(requestInput.getConnectionid()));
		}
		to.setAmount(requestInput.getAmount().intValue());
		to.setCurrency((short) 643);
		//receipt.setId(Long.parseLong(requestInput.getToken2()));
		//payment.setId(Long.parseLong(requestInput.getToken2()));
		receipt.setId(Long.parseLong(requestInput.getTransid().substring(8)));
		payment.setId(Long.parseLong(requestInput.getTransid().substring(8)));
		payment.setFrom(from);
		payment.setTo(to);
		Timestamp time = new Timestamp(new Date().getTime());
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
		gc.setTimeInMillis(time.getTime());
		try {
			XMLGregorianCalendar xmgc = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gc);
			receipt.setDate(xmgc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		payment.setReceipt(receipt);

		request.setProviders(providers);
		providers.setAuthorizePayment(authorize);
		authorize.setPayment(payment);
		return request;

	}

	public static Request getConfirmPayment(
			TransactionTransportBean transportBean) {
		Request request = new Request();
		setAuthClient(transportBean, request);
		Request.Providers providers = new Request.Providers();
		Request.Providers.ConfirmPayment cnfrmPayment = new Request.Providers.ConfirmPayment();
		Request.Providers.ConfirmPayment.Payment payment = new Request.Providers.ConfirmPayment.Payment();
		//payment.setId(Long.parseLong(transportBean.getToken2()));
		payment.setId(Long.parseLong(transportBean.getTransid().substring(8)));
		cnfrmPayment.setPayment(payment);
		providers.setConfirmPayment(cnfrmPayment);
		request.setProviders(providers);
		return request;

	}

	public static Request getPaymentStatus(TransactionTransportBean transBean) {
		Request request = new Request();
		setAuthClient(transBean, request);
		Request.Providers providers = new Request.Providers();
		Request.Providers.GetPaymentStatus paymentStatus = new Request.Providers.GetPaymentStatus();
		Request.Providers.GetPaymentStatus.Payment payment = new Request.Providers.GetPaymentStatus.Payment();
		//payment.setId(Long.parseLong(transBean.getToken2()));
		payment.setId(Long.parseLong(transBean.getTransid().substring(8)));
		paymentStatus.setPayment(payment);
		providers.setGetPaymentStatus(paymentStatus);
		request.setProviders(providers);
		return request;
	}

	private Response getResponse(TransactionTransportBean requestInput,
			Request request) throws DatatypeConfigurationException, ThirdPartyRechargeCallException {
		String finalInput = null;

		// String finalInput =
		// "<?xml version=\"1.0\" encoding=\"utf-8\"?><request><auth login=\"cybert\" sign=\"75ebb02f92fc30a8040bbd625af999f1\" signAlg=\"MD5\"/><client terminal=\"9347825\" software=\"XML\" serial=\"858478\"/><providers><authorizePayment><payment id=\"00000000123\"><from amount=\"10.00\" currency=\"643\"/><to service=\"4973\" account=\"8586818344\" amount=\"10.00\" currency=\"643\"/><receipt id=\"00000000123\" date=\"2014-04-14T17:54:16\"/></payment></authorizePayment></providers></request>";
		String inputLine = null;
		StringBuilder response = new StringBuilder();
		Response xmlresponse = null;
		try {
			RequestSerializer rserializer = new RequestSerializer();
			finalInput = rserializer.serialize(request);
			//System.out.println(finalInput);

			URL oracle = new URL(requestInput.getConnectURL());
			HttpURLConnection con = null;
			con = (HttpURLConnection) oracle.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(finalInput);
			wr.flush();
			wr.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				//System.out.println(inputLine);
				response.append(inputLine).append("\n");
			}
			ResponseSerializer respondeserial = new ResponseSerializer();
			xmlresponse = respondeserial.deserialize(response.toString());

			in.close();

		} catch (Exception e) {

			e.printStackTrace();
			throw new ThirdPartyRechargeCallException(
					"Thirdparty call exception");
		}

		return xmlresponse;
	}
	public static void main (String[] args) {
		TransactionTransportBean transactionTransportBean = new TransactionTransportBean();
		transactionTransportBean.setMobileNo("8586818344");
		transactionTransportBean.setOperatorCode("4973");
		transactionTransportBean.setAgentId("4688963");
		transactionTransportBean.setLoginStatus("cybert");
		transactionTransportBean.setAppver("xml");
		transactionTransportBean.setConnectURL("http://gate.qiwi.in/term2/xml.ashx");
		transactionTransportBean.setToken("75ebb02f92fc30a8040bbd625af999f1");
		transactionTransportBean.setToken1("9347825");
		transactionTransportBean.setToken2("858478");
		transactionTransportBean.setToken3("MD5");
		transactionTransportBean.setAmount(10.0);
		transactionTransportBean.setTransid("MOB965002187419042014011525");
		try {
			new QiwiETransactionServiceImpl().doTransaction(transactionTransportBean);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
