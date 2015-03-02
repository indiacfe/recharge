package com.cfeindia.apps.b2bserviceapp.thirdparty.cyberplate;

import java.net.*;
import java.io.*;
import java.util.*;

import org.CyberPlat.*;

/**
 *
 * @author CyberPlat.Com
 */
public class transact {
    
    /** Ð˜Ð½Ð¸Ñ†Ð¸Ð°Ð»Ð¸Ð·Ð°Ñ†Ð¸Ñ� Ð¾Ð±ÑŠÐµÐºÑ‚Ð° */
    public transact() {
    }
    
    /** ÐšÐ¾Ð´Ð¾Ð²Ð°Ñ� Ñ�Ñ‚Ñ€Ð°Ð½Ð¸Ñ†Ð° Ñ�ÐµÑ€Ð²ÐµÑ€Ð° */
    private static final String ENC="windows-1251";
    /** ÐšÐ¾Ð´ Ð´Ð¸Ð»ÐµÑ€Ð° */
    private static final String SD="17031";
    /** ÐšÐ¾Ð´ Ñ‚Ð¾Ñ‡ÐºÐ¸ Ð¿Ñ€Ð¸ÐµÐ¼Ð° */
    private static final String AP="17032";
    /** ÐšÐ¾Ð´ Ñ‚Ð¾Ñ‡ÐºÐ¸ Ð¾Ð¿ÐµÑ€Ð°Ñ‚Ð¾Ñ€Ð° */
    private static final String OP="17034";
    /** ÐŸÑƒÑ‚ÑŒ Ðº ÐºÐ»ÑŽÑ‡Ð°Ð¼ */
    private static final String KEYS="./test";
    /** ÐŸÐ°Ñ€Ð¾Ð»ÑŒ Ð¾Ñ‚ Ð·Ð°ÐºÑ€Ñ‹Ñ‚Ð¾Ð³Ð¾ ÐºÐ»ÑŽÑ‡Ð° */
    private static final String PASS="1111111111";
    /** Ð¡ÐµÑ€Ð¸Ð¹Ð½Ñ‹Ð¹ Ð½Ð¾Ð¼ÐµÑ€ Ð±Ð°Ð½ÐºÐ¾Ð²Ñ�ÐºÐ¾Ð³Ð¾ ÐºÐ»ÑŽÑ‡Ð° */
    private static int BANK_KEY_SERIAL=64182;
    
    /** Ð—Ð°ÐºÑ€Ñ‹Ñ‚Ñ‹Ð¹ ÐºÐ»ÑŽÑ‡ Ð´Ð»Ñ� Ñ„Ð¾Ñ€Ð¼Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ñ� Ð¿Ð¾Ð´Ð¿Ð¸Ñ�ÐµÐ¹ */
    private IPrivKey sec=null;

    /** ÐžÑ‚ÐºÑ€Ñ‹Ñ‚Ñ‹Ð¹ ÐºÐ»ÑŽÑ‡ Ð±Ð°Ð½ÐºÐ° Ð´Ð»Ñ� Ð¿Ñ€Ð¾Ð²ÐµÑ€ÐºÐ¸ Ð¿Ð¾Ð´Ð¿Ð¸Ñ�ÐµÐ¹ Ð² Ð¾Ñ‚Ð²ÐµÑ‚Ð°Ñ… */
    private IPrivKey pub=null;
    

    /** Ð“ÐµÐ½ÐµÑ€Ð°Ñ†Ð¸Ñ� Ð½Ð¾Ð¼ÐµÑ€Ð° Ñ�ÐµÑ�Ñ�Ð¸Ð¸ */
    String genSession()
    {
        String rc=new String();
        rc+="JAVA"+Calendar.getInstance().getTimeInMillis()/1000;
        return rc;
    }

    /** Ð¤-Ñ†Ð¸Ñ� Ð¾Ñ‚Ð¿Ñ€Ð°Ð²ÐºÐ¸ Ð·Ð°Ð¿Ñ€Ð¾Ñ�Ð°
     * @param url Ð°Ð´Ñ€ÐµÑ�
     * @param number Ð½Ð¾Ð¼ÐµÑ€ Ñ‚ÐµÐ»ÐµÑ„Ð¾Ð½Ð°
     * @param amount Ñ�ÑƒÐ¼Ð¼Ð° Ð² Ñ€ÑƒÐ±Ð»Ñ�Ñ…
     */
    String sendRequest(String url,String number,double amount) throws Exception
    {
        /* Ð¤Ð¾Ñ€Ð¼Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ðµ Ð·Ð°Ð¿Ñ€Ð¾Ñ�Ð° */
        String req=
                "SD="+SD+"\r\n"+
                "AP="+AP+"\r\n"+
                "OP="+OP+"\r\n"+
                "SESSION="+genSession()+"\r\n"+
                "NUMBER="+number+"\r\n"+
                "AMOUNT="+amount+"\r\n";

        /* ÐšÐ¾Ð´Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ðµ Ð·Ð°Ð¿Ñ€Ð¾Ñ�Ð° */
        req="inputmessage="+URLEncoder.encode(sec.signText(req));

        URL u=new URL(url);
        URLConnection con=u.openConnection();
        con.setDoOutput(true);

//        con.connect();

        /* ÐžÑ‚Ð¿Ñ€Ð°Ð²ÐºÐ° Ð·Ð°Ð¿Ñ€Ð¾Ñ�Ð° */
        con.getOutputStream().write(req.getBytes());
        con.getOutputStream().close();

        /* Ð§Ñ‚ÐµÐ½Ð¸Ðµ Ð¾Ñ‚Ð²ÐµÑ‚Ð° */
        BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream(),ENC));
        char[] raw_resp=new char[1024];
        int raw_resp_len=in.read(raw_resp);
        StringBuffer s=new StringBuffer();
        s.append(raw_resp,0,raw_resp_len);
        String resp=s.toString();

        /* ÐŸÑ€Ð¾Ð²ÐµÑ€ÐºÐ° Ð¿Ð¾Ð´Ð¿Ð¸Ñ�Ð¸ Ñ�ÐµÑ€Ð²ÐµÑ€Ð° */
        resp=pub.verifyText(resp);


        
        return resp;
    }
    
    /** Ð¤-Ñ†Ð¸Ñ� Ð·Ð°ÐºÑ€Ñ‹Ñ‚Ð¸Ñ� ÐºÐ»ÑŽÑ‡ÐµÐ¹ Ð¿Ð¾ Ð¾ÐºÐ¾Ð½Ñ‡Ð°Ð½Ð¸Ð¸ Ñ€Ð°Ð±Ð¾Ñ‚Ñ‹ */
    void done()
    {
        if(sec!=null)
            sec.closeKey();
        if(pub!=null)
            pub.closeKey();
    }
    
    
    /**
     * @param args Ð°Ñ€Ð³ÑƒÐ¼ÐµÐ½Ñ‚Ñ‹ ÐºÐ¾Ð¼Ð¼Ð°Ð½Ð´Ð½Ð¾Ð¹ Ñ�Ñ‚Ñ€Ð¾ÐºÐ¸
     */
    public static void main(String[] args) {
        
        /* Ð¡Ð¾Ð·Ð´Ð°Ð½Ð¸Ðµ Ð³Ð»Ð°Ð²Ð½Ð¾Ð³Ð¾ Ð¾Ð±ÑŠÐµÐºÑ‚Ð° */
        transact m=new transact();
        
        /* ÐžÐ±Ñ�Ð·Ð°Ñ‚ÐµÐ»ÑŒÐ½Ð¾ Ð²Ñ‹Ð·Ñ‹Ð²Ð°Ñ‚ÑŒ Ð´Ð»Ñ� ÑƒÐºÐ°Ð·Ð°Ð½Ð¸Ñ� ÐºÐ¾Ð´Ð¾Ð²Ð¾Ð¹ Ñ�Ñ‚Ñ€Ð°Ð½Ð¸Ñ†Ñ‹ Ð¸Ð·Ð½Ð°Ñ‡Ð°Ð»ÑŒÐ½Ð¾Ð³Ð¾ Ð´Ð¾ÐºÑƒÐ¼ÐµÐ½Ñ‚Ð° */
        IPriv.setCodePage(ENC);
        
        try
        {
            /* Ð—Ð°Ð³Ñ€ÑƒÐ·ÐºÐ° Ð·Ð°ÐºÑ€Ñ‹Ñ‚Ð¾Ð³Ð¾ ÐºÐ»ÑŽÑ‡Ð° Ð´Ð»Ñ� Ñ„Ð¾Ñ€Ð¼Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ñ� Ð¿Ð¾Ð´Ð¿Ð¸Ñ�ÐµÐ¹ */
            m.sec=IPriv.openSecretKey(KEYS+"/secret.key",PASS);

            /* Ð—Ð°Ð³Ñ€ÑƒÐ·ÐºÐ° Ð¾Ñ‚ÐºÑ€Ñ‹Ñ‚Ð¾Ð³Ð¾ ÐºÐ»ÑŽÑ‡Ð° Ð±Ð°Ð½ÐºÐ° (Ð² Ð±Ð¾ÐµÐ²Ð¾Ð¹ Ñ�Ð¸Ñ�Ñ‚ÐµÐ¼Ðµ Ð±ÑƒÐ´ÐµÑ‚ Ð´Ñ€ÑƒÐ³Ð¾Ð¹) */
            m.pub=IPriv.openPublicKey(KEYS+"/pubkeys.key",BANK_KEY_SERIAL);
            
            m.sendRequest("http://payment.cyberplat.ru/cgi-bin/es/es_pay_check.cgi","8888888888",12.5);
        }
        catch(Exception e)
        {
        }
        
        m.done();
    }
    
}
