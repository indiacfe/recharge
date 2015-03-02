package com.cfeindia.apps.b2bserviceapp.thirdparty.qiwi;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class RequestSerializer {

	private final JAXBContext context;
	private final Marshaller marshaller;
	private final Unmarshaller unmarshaller;

	public RequestSerializer() {
		try {
			context = JAXBContext.newInstance(Request.class);
			marshaller = context.createMarshaller();
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			throw new IllegalStateException("Failed to create jaxb context", e);
		}
	}

	public String serialize(final Request request) {
		try {
			final StringWriter sw = new StringWriter();
			marshaller.marshal(request, sw);
			return sw.toString();

		} catch (JAXBException e) {
			throw new IllegalStateException("Failed to serialize object");
		}
	}

	public Request deserialize(final String xml) {
		try {
			return unmarshaller.unmarshal(
					new StreamSource(new StringReader(xml)), Request.class)
					.getValue();
		} catch (JAXBException e) {
			throw new IllegalStateException(
					"Failed to deserialize from source xml", e);
		}
	}

}
