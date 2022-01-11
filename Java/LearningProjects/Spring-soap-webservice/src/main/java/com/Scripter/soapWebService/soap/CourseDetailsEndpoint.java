package com.Scripter.soapWebService.soap;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.Scripter.soapWebService.bean.Course;
import com.Scripter.soapWebService.soap.service.CourseDetailsService;
import com.thelastscripter.courses.CourseDetails;
import com.thelastscripter.courses.DeleteCourseDetailsRequest;
import com.thelastscripter.courses.DeleteCourseDetailsResponse;
import com.thelastscripter.courses.GetAllCourseDetailsRequest;
import com.thelastscripter.courses.GetAllCourseDetailsResponse;
import com.thelastscripter.courses.GetCourseDetailsRequest;
import com.thelastscripter.courses.GetCourseDetailsResponse;
import com.thelastscripter.courses.Status;

//Endpoint - This can accept a request and send a response
//
@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailsService service;

	@PayloadRoot(namespace = "http://theLastScripter.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		System.out.println("InputXML:"+javaObjectsToXML(request));
		Course course = service.findById(request.getId());
		if(course == null) {
			throw new CourseNotFoundException("Invalid Course ID:"+request.getId());
		}
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(transformtoCourseDetails(course));
		return response;
	}

	@PayloadRoot(namespace = "http://theLastScripter.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processGetAllCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {
		return getAllCourseDetailsResponse(service.findAll());
	}

	@PayloadRoot(namespace = "http://theLastScripter.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseById(@RequestPayload DeleteCourseDetailsRequest request) {
		return deleteCourseDetailsResponse(request.getId());
	}

	private DeleteCourseDetailsResponse deleteCourseDetailsResponse(int id) {
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(statusTrasnformer(service.deleteByid(id)));
		return response;
	}

	private Status statusTrasnformer(com.Scripter.soapWebService.soap.service.CourseDetailsService.Status status) {
		if (status == com.Scripter.soapWebService.soap.service.CourseDetailsService.Status.FAILURE)
			return Status.FAILURE;
		return Status.SUCCESS;
	}

	private CourseDetails transformtoCourseDetails(Course course) {
		CourseDetails coDetails = new CourseDetails();
		coDetails.setId(course.getId());
		coDetails.setName(course.getName());
		coDetails.setDescription(course.getDescription());
		return coDetails;
	}

	private GetAllCourseDetailsResponse getAllCourseDetailsResponse(List<Course> list) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : list) {
			response.getCourseDetails().add(transformtoCourseDetails(course));
		}
		return response;
	}
	
	private String javaObjectsToXML(Object ob) {
		StringWriter xml = new StringWriter();
		JAXB.marshal(ob, xml);
		return xml.toString();
	}
}
