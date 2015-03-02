package com.cfeindia.b2bserviceapp.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.style.ToStringCreator;

public class TimeStampUtil {
	public static Timestamp getTimestamp() {
		Date date = new Date();
		long l = date.getTime();
		Timestamp t = new Timestamp(l);
		return t;

	}

	public static Timestamp getTimeStampFromString(String str) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time = date.getTime();
		Timestamp timeStamp = new Timestamp(time);
		return timeStamp;
	}
	public static String getStringFromTimeStamp(Timestamp timestamp)
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date=(Date) timestamp.clone();
		String newDateString="";
		try
		{
			newDateString=df.format(date);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		  return newDateString;
	}
	
	public static Timestamp getTimeStampFromStringFromdate(String str) {
		str += "000001";
		DateFormat df = new SimpleDateFormat("MM/dd/yyyyHHmmss");
		Date date = null;
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time = date.getTime();
		Timestamp timeStamp = new Timestamp(time);
		return timeStamp;
	}

	public static Timestamp getTimeStampFromStringTodate(String str) {
		str += "235959";
		DateFormat df = new SimpleDateFormat("MM/dd/yyyyHHmmss");
		Date date = null;
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time = date.getTime();
		Timestamp timeStamp = new Timestamp(time);
		return timeStamp;
	}
	public static Date getDateFromString(String str)
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date date=null;
		try
		{
			date=df.parse(str);
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}
	
	
	public static Timestamp getTimestampFromtransId(String transId)
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyyHHmmss");
		Date date = null;
		try {
			date = df.parse(transId);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time = date.getTime();
		Timestamp timeStamp = new Timestamp(time);
		return timeStamp;
	}


}
