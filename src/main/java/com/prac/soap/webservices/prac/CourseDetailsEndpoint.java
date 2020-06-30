package com.prac.soap.webservices.prac;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.prac.soap.webservices.prac.CourseDetailsService.StatusBean;
import com.prac.soap.webservices.prac.exception.CourseNotFoundException;
import com.soapcoursemanagement.coursedetails.CourseDeletionStatus;
import com.soapcoursemanagement.coursedetails.CourseDetails;
import com.soapcoursemanagement.coursedetails.DeleteCourseDetailsRequest;
import com.soapcoursemanagement.coursedetails.DeleteCourseDetailsResponse;
import com.soapcoursemanagement.coursedetails.FetchAllCourseDetailsRequest;
import com.soapcoursemanagement.coursedetails.FetchAllCourseDetailsResponse;
import com.soapcoursemanagement.coursedetails.FetchCourseDetailsRequest;
import com.soapcoursemanagement.coursedetails.FetchCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {
	@Autowired
	CourseDetailsService courseDetailsService;
	
	@PayloadRoot(namespace = "http://soapcoursemanagement.com/coursedetails"
			, localPart = "FetchCourseDetailsRequest")
	public @ResponsePayload FetchCourseDetailsResponse getCourseDetailsForSoapCourseManagement(
			@RequestPayload FetchCourseDetailsRequest fetchCourseDetailsRequest) {
		CourseDetails courseDetails = courseDetailsService.findById(fetchCourseDetailsRequest.getId());
		
		if (courseDetails == null) {
			throw new CourseNotFoundException("Invalid Course Id Provided.  " + fetchCourseDetailsRequest.getId());
		}
		
		FetchCourseDetailsResponse fetchCourseDetailsResponse = new FetchCourseDetailsResponse();
		fetchCourseDetailsResponse.setCourseDetails(courseDetails);
		return fetchCourseDetailsResponse;
	}
	
	@PayloadRoot(namespace = "http://soapcoursemanagement.com/coursedetails"
			, localPart = "FetchAllCourseDetailsRequest")
	public @ResponsePayload FetchAllCourseDetailsResponse getAllCourseDetailsForSoapCourseManagement(
			@RequestPayload FetchAllCourseDetailsRequest fetchAllCourseDetailsRequest) {
		List<CourseDetails> courseDetails = courseDetailsService.findAll();
		
		FetchAllCourseDetailsResponse fetchAllCourseDetailsResponse = new FetchAllCourseDetailsResponse();
		fetchAllCourseDetailsResponse.setCourseDetails(courseDetails);
		return fetchAllCourseDetailsResponse;
	}
	
	@PayloadRoot(namespace = "http://soapcoursemanagement.com/coursedetails"
			, localPart = "DeleteCourseDetailsRequest")
	public @ResponsePayload DeleteCourseDetailsResponse deleteCourseDetailsForSoapCourseManagement(
			@RequestPayload DeleteCourseDetailsRequest deleteCourseDetailsRequest) {
		StatusBean statusBean = courseDetailsService.deleteById(deleteCourseDetailsRequest.getId());
		
		DeleteCourseDetailsResponse deleteCourseDetailsResponse = new DeleteCourseDetailsResponse();
		if (statusBean == StatusBean.SUCCESS) {
			deleteCourseDetailsResponse.setCourseDeletionStatus(CourseDeletionStatus.SUCCESS);
		} else {
			deleteCourseDetailsResponse.setCourseDeletionStatus(CourseDeletionStatus.FAILURE);
		}
		
		return deleteCourseDetailsResponse;
	}
}
