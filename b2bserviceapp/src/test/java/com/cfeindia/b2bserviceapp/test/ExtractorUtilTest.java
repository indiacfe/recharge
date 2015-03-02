package com.cfeindia.b2bserviceapp.test;

import com.cfeindia.b2bserviceapp.util.ExtractorUtil;
import junit.framework.TestCase;

public class ExtractorUtilTest extends TestCase {

	
	public void testExtractorUtil()
	{
		String substr=ExtractorUtil.extractIdFromString("D0000043000565");
		System.out.println("substr is"+substr);
		assertEquals("43000565", substr);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
