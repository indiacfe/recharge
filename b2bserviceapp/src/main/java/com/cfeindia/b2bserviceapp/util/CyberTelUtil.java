package com.cfeindia.b2bserviceapp.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cfeindia.b2bserviceapp.transport.bean.TransactionTransportBean;

public class CyberTelUtil 
{
	public static String generateTransId(String param1,String param2)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate=sdf.format(new Date());
		StringBuilder builder=new StringBuilder();
		if(param1!=null)
		{	
			builder.append(param1);
		}
		if(param2!=null)
		{
			builder.append(param2);
		}
		builder.append(formattedDate);
		return builder.toString();
		
	}
	public static long getStrInLong(String str)
	{
		long l=Long.parseLong(str);
		return l;
	}
}
