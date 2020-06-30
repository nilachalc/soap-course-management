package com.prac.soap.webservices.prac.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://soapcoursemanagement.com/coursedetails}001_INVALID_COURSE_ID")
public class CourseNotFoundException extends RuntimeException {

	public CourseNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public CourseNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CourseNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CourseNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CourseNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
