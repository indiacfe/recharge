package com.cfeindia.apps.b2bserviceapp.thirdparty.qiwi;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class ResponseSerializer {

	private final JAXBContext context;
	private final Marshaller marshaller;
	private final Unmarshaller unmarshaller;

	public ResponseSerializer() {
		try {
			context = JAXBContext.newInstance(Response.class);
			marshaller = context.createMarshaller();
			unmarshaller = context.createUnmarshaller();
		} catch (JAXBException e) {
			throw new IllegalStateException("Failed to create jaxb context", e);
		}
	}

	public String serialize(final Response response) {
		try {
			final StringWriter sw = new StringWriter();
			marshaller.marshal(response, sw);
			return sw.toString();
		} catch (JAXBException e) {
			throw new IllegalStateException("Failed to serialize object");
		}
	}

	public Response deserialize(final String xml) {
		try {
			return unmarshaller.unmarshal(
					new StreamSource(new StringReader(xml)), Response.class)
					.getValue();
		} catch (JAXBException e) {
			throw new IllegalStateException(
					"Failed to deserialize from source xml", e);
		}
	}

}
