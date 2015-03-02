package com.cfeindia.b2bserviceapp.thirdparty.recharge.impl;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.cfeindia.b2bserviceapp.dto.DthNewConnectionModel;

public class DthConnectionDetailsService implements
		DthConnectionDetailsServiceInterface {

	private static final String SERVICE_URL = "https://www.instantpay.in/ws/api/serviceproviders?format=json&type=CONNECTION&token=";
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
	
	public List<DthNewConnectionModel> getDetails(String token)
			throws JsonParseException, JsonMappingException, IOException {

	//	URL urlToConnect = new URL(url);
		ObjectMapper mapper = new ObjectMapper();

		 //String a1 = "[{\"service_provider\":\"Aircel\",\"provider_key\":\"ACP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"4.20\"},{\"service_provider\":\"Airtel\",\"provider_key\":\"ATP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account' and Outlet ID in 'optional5'\",\"margin\":\"2.25\"},{\"service_provider\":\"BSNL - Talktime\",\"provider_key\":\"BGP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"3.60\"},{\"service_provider\":\"Idea\",\"provider_key\":\"IDP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"2.70\"},{\"service_provider\":\"Loop Mobile\",\"provider_key\":\"LMP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"4.00\"},{\"service_provider\":\"MTNL - Special Tariff\",\"provider_key\":\"MSP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"4.00\"},{\"service_provider\":\"MTNL - Talktime\",\"provider_key\":\"MMP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"4.00\"},{\"service_provider\":\"MTS\",\"provider_key\":\"MTP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"4.60\"},{\"service_provider\":\"Reliance\",\"provider_key\":\"RGP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"3.70\"},{\"service_provider\":\"Tata Docomo CDMA\",\"provider_key\":\"TCP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"2.70\"},{\"service_provider\":\"Tata Docomo GSM - Talktime\",\"provider_key\":\"TGP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"2.70\"},{\"service_provider\":\"Uninor - Talktime\",\"provider_key\":\"UGP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"3.80\"},{\"service_provider\":\"Videocon - Talktime\",\"provider_key\":\"VGP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"4.20\"},{\"service_provider\":\"Vodafone\",\"provider_key\":\"VFP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"2.25\"},{\"service_provider\":\"BSNL - Special Tariff\",\"provider_key\":\"BVP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"3.60\"},{\"service_provider\":\"Tata Docomo GSM - Special Tariff\",\"provider_key\":\"TSP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"2.70\"},{\"service_provider\":\"Uninor - Special Tariff\",\"provider_key\":\"USP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"3.80\"},{\"service_provider\":\"Videocon - Special Tariff\",\"provider_key\":\"VSP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"4.20\"},{\"service_provider\":\"Airtel Digital TV\",\"provider_key\":\"ATV\",\"service_type\":\"DTH\",\"service_desc\":\"pass Customer ID in 'account' and Outlet ID in 'optional5'\",\"margin\":\"2.80\"},{\"service_provider\":\"Dish TV\",\"provider_key\":\"DTV\",\"service_type\":\"DTH\",\"service_desc\":\"pass Viewing Card Number in 'account'\",\"margin\":\"3.00\"},{\"service_provider\":\"Reliance Digital TV\",\"provider_key\":\"RTV\",\"service_type\":\"DTH\",\"service_desc\":\"pass Smart Card Number in 'account'\",\"margin\":\"3.80\"},{\"service_provider\":\"SUN Direct\",\"provider_key\":\"STV\",\"service_type\":\"DTH\",\"service_desc\":\"pass Smart Card Number in 'account'\",\"margin\":\"4.60\"},{\"service_provider\":\"Tata Sky\",\"provider_key\":\"TTV\",\"service_type\":\"DTH\",\"service_desc\":\"pass Subscriber ID in 'account'\",\"margin\":\"3.80\"},{\"service_provider\":\"Videocon d2h\",\"provider_key\":\"VTV\",\"service_type\":\"DTH\",\"service_desc\":\"pass Customer ID in 'account'\",\"margin\":\"4.80\"},{\"service_provider\":\"Tata Docomo GSM\",\"provider_key\":\"TDC\",\"service_type\":\"POSTPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"0.00\"},{\"service_provider\":\"Tata Docomo CDMA\",\"provider_key\":\"TTC\",\"service_type\":\"POSTPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"0.00\"},{\"service_provider\":\"Idea\",\"provider_key\":\"IDC\",\"service_type\":\"POSTPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"0.50\"},{\"service_provider\":\"BSNL\",\"provider_key\":\"BGC\",\"service_type\":\"POSTPAID\",\"service_desc\":\"pass Mobile Number in 'account'and Account Number in 'optional1'\",\"margin\":\"0.30\"},{\"service_provider\":\"Vodafone\",\"provider_key\":\"VFC\",\"service_type\":\"POSTPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"0.50\"},{\"service_provider\":\"Airtel\",\"provider_key\":\"ATL\",\"service_type\":\"LANDLINE\",\"service_desc\":\"pass Landline Number in 'account', STD Code in 'optional1' and Outlet ID in 'optional5'\",\"margin\":\"0.50\"},{\"service_provider\":\"MTNL Delhi\",\"provider_key\":\"MDL\",\"service_type\":\"LANDLINE\",\"service_desc\":\"pass Landline Number in 'account' and CA Number in 'optional1'\",\"margin\":\"0.00\"},{\"service_provider\":\"Reliance Energy Limited - MUMBAI\",\"provider_key\":\"REE\",\"service_type\":\"ELECTRICITY\",\"service_desc\":\"pass Customer Number in 'account' and Cycle Number in 'optional1'\",\"margin\":\"0.00\"},{\"service_provider\":\"BSES Rajdhani Power Limited - DELHI\",\"provider_key\":\"BRE\",\"service_type\":\"ELECTRICITY\",\"service_desc\":\"pass CA Number in 'account'\",\"margin\":\"0.00\"},{\"service_provider\":\"BSES Yamuna Power Limited - DELHI\",\"provider_key\":\"BYE\",\"service_type\":\"ELECTRICITY\",\"service_desc\":\"pass CA Number in 'account'\",\"margin\":\"0.00\"},{\"service_provider\":\"Tata Power Delhi Distribution Limited - DELHI\",\"provider_key\":\"NDE\",\"service_type\":\"ELECTRICITY\",\"service_desc\":\"pass CA Number in 'account'\",\"margin\":\"0.00\"},{\"service_provider\":\"Mahanagar Gas Limited\",\"provider_key\":\"MMG\",\"service_type\":\"GAS\",\"service_desc\":\"pass alphanumeric Customer Account Number in 'account'\",\"margin\":\"0.00\"},{\"service_provider\":\"ICICI Prudential Life Insurance\",\"provider_key\":\"IPI\",\"service_type\":\"INSURANCE\",\"service_desc\":\"pass Policy Number in 'account' and Date of Birth (DD-MM-YYYY) in 'optional1'\",\"margin\":\"0.00\"},{\"service_provider\":\"Tata AIA Life Insurance\",\"provider_key\":\"TAI\",\"service_type\":\"INSURANCE\",\"service_desc\":\"pass alphanumeric Policy Number in 'account' and Date of Birth (DD-MM-YYYY) in 'optional1'\",\"margin\":\"0.00\"},{\"service_provider\":\"T24 Mobile - Talktime\",\"provider_key\":\"TMP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"2.70\"},{\"service_provider\":\"Airtel\",\"provider_key\":\"ATC\",\"service_type\":\"POSTPAID\",\"service_desc\":\"pass Mobile Number in 'account' and Outlet ID in 'optional5'\",\"margin\":\"0.50\"},{\"service_provider\":\"BSNL\",\"provider_key\":\"BGL\",\"service_type\":\"LANDLINE\",\"service_desc\":\"pass Landline Number in 'account', STD Code in 'optional1' and Account Number in 'optional2'\",\"margin\":\"0.30\"},{\"service_provider\":\"T24 Mobile - Special Tariff\",\"provider_key\":\"TVP\",\"service_type\":\"PREPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"2.70\"},{\"service_provider\":\"Loop Mobile\",\"provider_key\":\"LMC\",\"service_type\":\"POSTPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"0.00\"},{\"service_provider\":\"Reliance\",\"provider_key\":\"RGC\",\"service_type\":\"POSTPAID\",\"service_desc\":\"pass Mobile Number in 'account'\",\"margin\":\"0.50\"},{\"service_provider\":\"Reliance\",\"provider_key\":\"RGL\",\"service_type\":\"LANDLINE\",\"service_desc\":\"pass STD Code and Landline Number jointly in 'account', do not prefix std code with '0'\",\"margin\":\"0.50\"},{\"service_provider\":\"Norton\",\"provider_key\":\"NOS\",\"service_type\":\"LICENSE\",\"service_desc\":\"pass Mobile Number in 'account', MRP in 'amount', Sub-Service Key in 'optional1', Email ID in 'optional2' and URL encoded Full Name in 'optional3'\",\"margins\":[{\"sub_service_name\":\"Norton Mobile Security - 1 Year\",\"sub_service_key\":\"NOS1\",\"mrp\":\"599\",\"margin\":\"20.00\",\"description\":\"License Key  will be sent to customer's registered mobile number\"},{\"sub_service_name\":\"Norton Internet Security - 1 Year\",\"sub_service_key\":\"NOS2\",\"mrp\":\"1399\",\"margin\":\"20.00\",\"description\":\"License Key  will be sent to customer's registered mobile number\"}]},{\"service_provider\":\"Quick Heal\",\"provider_key\":\"QHS\",\"service_type\":\"LICENSE\",\"service_desc\":\"pass Mobile Number in 'account', MRP in 'amount', Sub-Service Key in 'optional1', Email ID in 'optional2' and URL encoded Full Name in 'optional3'\",\"margins\":[{\"sub_service_name\":\"Quick Heal Total Security for Android - 1 Year\",\"sub_service_key\":\"QHS1\",\"mrp\":\"749\",\"margin\":\"20.00\",\"description\":\"License Key  will be sent to customer's registered mobile number\"},{\"sub_service_name\":\"Quick Heal Total Security for Android - 2 Year\",\"sub_service_key\":\"QHS2\",\"mrp\":\"1149\",\"margin\":\"20.00\",\"description\":\"License Key  will be sent to customer's registered mobile number\"},{\"sub_service_name\":\"Quick Heal Mobile Security for Android - 1 Year\",\"sub_service_key\":\"QHS3\",\"mrp\":\"599\",\"margin\":\"20.00\",\"description\":\"License Key  will be sent to customer's registered mobile number\"},{\"sub_service_name\":\"Quick Heal Mobile Security for Android - 2 Year\",\"sub_service_key\":\"QHS4\",\"mrp\":\"899\",\"margin\":\"20.00\",\"description\":\"License Key  will be sent to customer's registered mobile number\"},{\"sub_service_name\":\"Quick Heal Mobile Security for BlackBerry - 1 Year\",\"sub_service_key\":\"QHS5\",\"mrp\":\"599\",\"margin\":\"20.00\",\"description\":\"License Key  will be sent to customer's registered mobile number\"},{\"sub_service_name\":\"Quick Heal Mobile Security for BlackBerry - 2 Year\",\"sub_service_key\":\"QHS6\",\"mrp\":\"899\",\"margin\":\"20.00\",\"description\":\"License Key  will be sent to customer's registered mobile number\"},{\"sub_service_name\":\"Quick Heal Tablet Security for Android - 1 Year\",\"sub_service_key\":\"QHS7\",\"mrp\":\"849\",\"margin\":\"20.00\",\"description\":\"License Key  will be sent to customer's registered mobile number\"},{\"sub_service_name\":\"Quick Heal Tablet Security for Android - 2 Year\",\"sub_service_key\":\"QHS8\",\"mrp\":\"1249\",\"margin\":\"20.00\",\"description\":\"License Key  will be sent to customer's registered mobile number\"}]},{\"service_provider\":\"Dish TV\",\"provider_key\":\"DTK\",\"service_type\":\"CONNECTION\",\"service_desc\":\"pass Mobile Number in 'account', MRP in 'amount', Sub-Service Key in 'optional1', PIN Code in 'optional2', URL encoded Full Name in 'optional3' and URL encoded Full Installation Address in 'optional4'\",\"margins\":[{\"sub_service_name\":\"Dish TV Standard Set Top Box (ROI)\",\"sub_service_key\":\"DTK1\",\"mrp\":\"1999\",\"margin\":\"15.00\",\"description\":\"Includes 2 months Titanium\"},{\"sub_service_name\":\"Dish TV DISH+ Set Top Box with Recorder (ROI)\",\"sub_service_key\":\"DTK2\",\"mrp\":\"2099\",\"margin\":\"15.00\",\"description\":\"Includes 2 months Titanium\"},{\"sub_service_name\":\"Dish TV truHD+ Set Top Box with Recorder (ROI)\",\"sub_service_key\":\"DTK3\",\"mrp\":\"2499\",\"margin\":\"10.00\",\"description\":\"Includes 1 month Royale (HD)\"},{\"sub_service_name\":\"Dish TV Standard Set Top Box (SOUTH)\",\"sub_service_key\":\"DTK4\",\"mrp\":\"1799\",\"margin\":\"15.00\",\"description\":\"Includes 3 months South Family\"},{\"sub_service_name\":\"Dish TV DISH+ Set Top Box with Recorder (SOUTH)\",\"sub_service_key\":\"DTK5\",\"mrp\":\"1899\",\"margin\":\"15.00\",\"description\":\"Includes 3 months South Family\"},{\"sub_service_name\":\"Dish TV truHD+ Set Top Box with Recorder (SOUTH)\",\"sub_service_key\":\"DTK6\",\"mrp\":\"2499\",\"margin\":\"10.00\",\"description\":\"Includes 1 month South Royale (HD)\"}]},{\"service_provider\":\"Tata Sky\",\"provider_key\":\"TTK\",\"service_type\":\"CONNECTION\",\"service_desc\":\"pass Mobile Number in 'account', MRP in 'amount', Sub-Service Key in 'optional1', PIN Code in 'optional2', URL encoded Full Name in 'optional3' and URL encoded Full Installation Address in 'optional4'\",\"margins\":[{\"sub_service_name\":\"Tata Sky SD Set Top Box (ROI)\",\"sub_service_key\":\"TTK1\",\"mrp\":\"1820\",\"margin\":\"15.00\",\"description\":\"Includes 1 month Dhamaal Mix\"},{\"sub_service_name\":\"Tata Sky HD Set Top Box (ROI)\",\"sub_service_key\":\"TTK2\",\"mrp\":\"2220\",\"margin\":\"10.00\",\"description\":\"Includes 1 month Dhamaal Mix (HD)\"},{\"sub_service_name\":\"Tata Sky+ HD Set Top Box with Recorder (ROI)\",\"sub_service_key\":\"TTK3\",\"mrp\":\"7220\",\"margin\":\"10.00\",\"description\":\"Includes 1 month Dhamaal Mix (HD)\"},{\"sub_service_name\":\"Tata Sky+ HD Set Top Box with Recorder (SOUTH)\",\"sub_service_key\":\"TTK6\",\"mrp\":\"7200\",\"margin\":\"10.00\",\"description\":\"Includes 1 month South Special (HD)\"},{\"sub_service_name\":\"Tata Sky SD Set Top Box (SOUTH)\",\"sub_service_key\":\"TTK4\",\"mrp\":\"1600\",\"margin\":\"15.00\",\"description\":\"Includes 1 month South Special\"},{\"sub_service_name\":\"Tata Sky HD Set Top Box (SOUTH)\",\"sub_service_key\":\"TTK5\",\"mrp\":\"1990\",\"margin\":\"10.00\",\"description\":\"Includes 1 month South Special (HD)\"}]},{\"service_provider\":\"Videocon d2h\",\"provider_key\":\"VTK\",\"service_type\":\"CONNECTION\",\"service_desc\":\"pass Mobile Number in 'account', MRP in 'amount', Sub-Service Key in 'optional1', PIN Code in 'optional2', URL encoded Full Name in 'optional3' and URL encoded Full Installation Address in 'optional4'\",\"margins\":[{\"sub_service_name\":\"Videocon d2h SD Set Top Box (ROI)\",\"sub_service_key\":\"VTK1\",\"mrp\":\"1990\",\"margin\":\"15.00\",\"description\":\"Includes 1 month Super Gold\"},{\"sub_service_name\":\"Videocon d2h HD Set Top Box (ROI)\",\"sub_service_key\":\"VTK2\",\"mrp\":\"2305\",\"margin\":\"10.00\",\"description\":\"Includes 1 month Gold Entertainment (HD)\"},{\"sub_service_name\":\"Videocon d2h HD Set Top Box with Recorder (ROI)\",\"sub_service_key\":\"VTK3\",\"mrp\":\"2504\",\"margin\":\"10.00\",\"description\":\"Includes 1 month Gold Entertainment (HD)\"},{\"sub_service_name\":\"Videocon d2h SD Set Top Box (SOUTH)\",\"sub_service_key\":\"VTK4\",\"mrp\":\"1790\",\"margin\":\"15.00\",\"description\":\"Includes 2 month South Silver\"},{\"sub_service_name\":\"Videocon d2h HD Set Top Box (SOUTH)\",\"sub_service_key\":\"VTK5\",\"mrp\":\"2000\",\"margin\":\"10.00\",\"description\":\"Includes 1 month South Platinum (HD)\"},{\"sub_service_name\":\"Videocon d2h HD Set Top Box with Recorder (SOUTH)\",\"sub_service_key\":\"VTK6\",\"mrp\":\"2199\",\"margin\":\"10.00\",\"description\":\"Includes 1 month South Platinum (HD)\"}]},{\"service_provider\":\"Airtel Digital TV\",\"provider_key\":\"ATK\",\"service_type\":\"CONNECTION\",\"service_desc\":\"pass Mobile Number in 'account', MRP in 'amount', Sub-Service Key in 'optional1', PIN Code in 'optional2', URL encoded Full Name in 'optional3' and URL encoded Full Installation Address in 'optional4'\",\"margins\":[{\"sub_service_name\":\"Airtel Digital TV SD Set Top Box (ROI)\",\"sub_service_key\":\"ATK1\",\"mrp\":\"1920\",\"margin\":\"15.00\",\"description\":\"Includes 1 month Economy Sports Plus\"},{\"sub_service_name\":\"Airtel Digital TV SD Set Top Box (SOUTH)\",\"sub_service_key\":\"ATK2\",\"mrp\":\"1590\",\"margin\":\"15.00\",\"description\":\"Includes 1 month South Value Sports\"},{\"sub_service_name\":\"Airtel Digital TV HD Set Top Box (ROI)\",\"sub_service_key\":\"ATK3\",\"mrp\":\"2070\",\"margin\":\"10.00\",\"description\":\"Includes 1 month Economy Sports Plus (HD)\"},{\"sub_service_name\":\"Airtel Digital TV HD Set Top Box (SOUTH)\",\"sub_service_key\":\"ATK4\",\"mrp\":\"1850\",\"margin\":\"10.00\",\"description\":\"Includes 1 month Economy Sports (HD)\"},{\"sub_service_name\":\"Airtel Digital TV HD+ Set Top Box (ROI)\",\"sub_service_key\":\"ATK5\",\"mrp\":\"2070\",\"margin\":\"10.00\",\"description\":\"Includes 1 month Economy Sports Plus (HD)\"},{\"sub_service_name\":\"Airtel Digital TV HD+ Set Top Box (SOUTH)\",\"sub_service_key\":\"ATK6\",\"mrp\":\"1850\",\"margin\":\"10.00\",\"description\":\"Includes 1 month Economy Sports (HD)\"}]},{\"service_provider\":\"SUN Direct\",\"provider_key\":\"STK\",\"service_type\":\"CONNECTION\",\"service_desc\":\"pass Mobile Number in 'account', MRP in 'amount', Sub-Service Key in 'optional1', PIN Code in 'optional2', URL encoded Full Name in 'optional3' and URL encoded Full Installation Address in 'optional4'\",\"margins\":[{\"sub_service_name\":\"Sun Direct Plus SD Set Top Box with Recorder (SOUTH)\",\"sub_service_key\":\"STK1\",\"mrp\":\"1890\",\"margin\":\"15.00\",\"description\":\"Includes 2 months Tamil Cinema + Sports\"},{\"sub_service_name\":\"Sun Direct HD Set Top Box with Recorder (SOUTH)\",\"sub_service_key\":\"STK2\",\"mrp\":\"1990\",\"margin\":\"10.00\",\"description\":\"Includes 2 months Tamil Super Value (HD)\"}]}]";
		// List<DthNewConnectionModel> myObjects = mapper.readValue(a1,
		// mapper.getTypeFactory().constructCollectionType(List.class,
		// DthNewConnectionModel.class));
	//	Map<String, DthNewConnectionModel> dthMap = new HashMap<String, DthNewConnectionModel>();
		////System.out.println(urlToConnect);
		List<DthNewConnectionModel> myObjects = mapper.readValue(new URL(SERVICE_URL+token), new TypeReference<List<DthNewConnectionModel>>() {});
		/*List<DthNewConnetionMargines> ldtnm = null;
		for (DthNewConnectionModel dthNewConnectionModel : myObjects) {

			if (dthNewConnectionModel.getProviderKey().equalsIgnoreCase("TTK")) {
				ldtnm = dthNewConnectionModel.getMargines();

				dthMap.put("TTK", dthNewConnectionModel);
				dthNewConnectionModel.setServiceDesc(null);
				if(ldtnm!=null)
					for (DthNewConnetionMargines dth1 : ldtnm) {
						dth1.setDesCription(null);
					}
				//System.out.println(dthNewConnectionModel.getServiceProvider());

			} else if (dthNewConnectionModel.getProviderKey().equalsIgnoreCase(
					"ATK")) {
				ldtnm = dthNewConnectionModel.getMargines();
				dthNewConnectionModel.setServiceDesc(null);
				dthMap.put("ATK", dthNewConnectionModel);
				dthNewConnectionModel.setServiceDesc(null);
				if(ldtnm!=null)
					for (DthNewConnetionMargines dth1 : ldtnm) {
						dth1.setDesCription(null);
					}
				//System.out.println(dthNewConnectionModel.getServiceProvider());
			} else if (dthNewConnectionModel.getProviderKey().equalsIgnoreCase(
					"VTK")) {
				ldtnm = dthNewConnectionModel.getMargines();
				dthNewConnectionModel.setServiceDesc(null);
				dthMap.put("VTK", dthNewConnectionModel);
				dthNewConnectionModel.setServiceDesc(null);
				if(ldtnm!=null)
					for (DthNewConnetionMargines dth1 : ldtnm) {
						dth1.setDesCription(null);
					}
				//System.out.println(dthNewConnectionModel.getServiceProvider());

			} else if (dthNewConnectionModel.getProviderKey().equalsIgnoreCase(
					"DTK")) {
				ldtnm = dthNewConnectionModel.getMargines();
				dthNewConnectionModel.setServiceDesc(null);
				dthMap.put("DTK", dthNewConnectionModel);
				dthNewConnectionModel.setServiceDesc(null);
				if(ldtnm!=null)
					for (DthNewConnetionMargines dth1 : ldtnm) {
						dth1.setDesCription(null);
					}
				//System.out.println(dthNewConnectionModel.getServiceProvider());
			}

			else if (dthNewConnectionModel.getProviderKey().equalsIgnoreCase(
					"STK")) {
				ldtnm = dthNewConnectionModel.getMargines();
				dthNewConnectionModel.setServiceDesc(null);
				dthMap.put("STK", dthNewConnectionModel);
				dthNewConnectionModel.setServiceDesc(null);
				if(ldtnm!=null)
					for (DthNewConnetionMargines dth1 : ldtnm) {
						dth1.setDesCription(null);
					}
				//System.out.println(dthNewConnectionModel.getServiceProvider());
			}
		}*/
		return myObjects;
	}

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		DthConnectionDetailsService service = new DthConnectionDetailsService();
		service.getDetails("https://www.instantpay.in/ws/api/serviceproviders?format=json&token=12a934d08d9ffeaa0e322147b4ca1539");
	}
}
