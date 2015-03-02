package com.cfeindia.apps.b2bserviceapp.thirdparty.cyberplate;

import org.CyberPlat.IPriv;
import org.CyberPlat.IPrivKey;

public class CyberPlateIntegrationUtil
{
	
	private static IPrivKey sec=null;
	private static IPrivKey pub=null;
	public static void init(String secretKeyLocation, String secretKey, String publicKeyLocation, Integer pubKeys) throws Exception {
			//"D:/CFE-Projects/java_zip/secret.key", "1111111111", "D:/CFE-Projects/java_zip/pubkeys.key", 17033
		if(sec == null || pub == null) {
			sec=IPriv.openSecretKey(secretKeyLocation,secretKey);
	        pub=IPriv.openPublicKey(publicKeyLocation,pubKeys);
	        System.out.println("Key Opened " + secretKeyLocation + " " + secretKey + " " + publicKeyLocation + " " + pubKeys);
		}
	}
	public static String signMessage(String message) throws Exception {
		return sec.signText(message);
	}
	
	public static String verifyMessage(String message) throws Exception {
		return pub.verifyText(message);
	}

	public static String encryptMessage(String message) throws Exception {
		return pub.encryptText(message);
	}
	
	public static String decryptMessage(String message) throws Exception {
		return sec.decryptText(message);
	}
	
    public static void main(String args[])
    {
    IPrivKey sec=null;
    IPrivKey pub=null;
    try
    {
    	//System.setProperty("java.library.path", "C:/Users/rishu/Documents/workspace-sts-2.7.2.RELEASE/cyberplateintegration/src/main/resources");
        sec=IPriv.openSecretKey("D:/CFE-Projects/java_zip/secret.key","1111111111");
        pub=IPriv.openPublicKey("D:/CFE-Projects/java_zip/pubkeys.key",17033);
      
        String s1=sec.signText("Hello, world");

        String s2=pub.verifyText(s1);
        
        String s3=pub.encryptText("Hello, world");

        String s4=sec.decryptText(s3);

        String s5=sec.signText2("Hello, world");

        String s6=pub.verifyText2("Hello, world",s5);

        
    }
    catch(Exception e)
    {
    }
    
    if(sec!=null)
        IPriv.closeKey(sec);
    if(pub!=null)
        IPriv.closeKey(pub);
    }
}
