package com.Scripter.soapWebService.soap;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://theLastScripter.com/courses}FAILURE")
public class CourseNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2510809455627028976L;

	public CourseNotFoundException(String message) {
		super(message);
	}
}
