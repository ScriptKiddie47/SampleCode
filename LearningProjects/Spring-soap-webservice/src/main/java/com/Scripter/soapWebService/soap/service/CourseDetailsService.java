package com.Scripter.soapWebService.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.Scripter.soapWebService.bean.Course;

@Component
public class CourseDetailsService {
	
	public enum Status{
		SUCCESS,FAILURE
	}
	
	private static List<Course> courses = new ArrayList<>();
	
	static {
		courses.add(new Course(1, "A", "Action flick"));
		courses.add(new Course(2, "B", "B Grade Shit"));
		courses.add(new Course(3, "C", "Chick book"));
		courses.add(new Course(4, "D", "Die Hard"));
		courses.add(new Course(5, "E", "Evagenlist are a curse"));
		courses.add(new Course(6, "F", "Freking nice"));
	}
	
	public Course findById(int id) {
		for (Course c : courses) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public List<Course> findAll() {
		return courses;
	}
	
	public Status deleteByid(int id) {
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			Course course =  iterator.next();
			if(course.getId() == id) {
				iterator.remove();
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}
}
