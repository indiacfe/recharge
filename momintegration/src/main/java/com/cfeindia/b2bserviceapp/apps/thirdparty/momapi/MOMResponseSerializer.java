package com.cfeindia.b2bserviceapp.apps.thirdparty.momapi;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class MOMResponseSerializer {

	private final JAXBContext context;
	private final Marshaller marshaller;
	private final Unmarshaller unmarshaller;

	public MOMResponseSerializer() {
		try {
			context = JAXBContext.newInstance(BillObjectsDHBVNBillPaymentResult.class);
			marshaller = context.createMarshaller();
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			throw new IllegalStateException("Failed to create jaxb context", e);
		}
	}

	public String serialize(final BillObjectsDHBVNBillPaymentResult response) {
		try {
			final StringWriter sw = new StringWriter();
			marshaller.marshal(response, sw);
			return sw.toString();
		} catch (JAXBException e) {
			throw new IllegalStateException("Failed to serialize object");
		}
	}

	public BillObjectsDHBVNBillPaymentResult deserialize(final String xml) {
		try {
			return unmarshaller.unmarshal(
					new StreamSource(new StringReader(xml)), BillObjectsDHBVNBillPaymentResult.class)
					.getValue();
		} catch (JAXBException e) {
			throw new IllegalStateException(
					"Failed to deserialize from source xml", e);
		}
	}

}
