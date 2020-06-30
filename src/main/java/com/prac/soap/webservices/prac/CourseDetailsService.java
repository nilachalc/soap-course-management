package com.prac.soap.webservices.prac;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soapcoursemanagement.coursedetails.CourseDetails;

@Service
public class CourseDetailsService {
	
	public enum StatusBean{
		SUCCESS,
		FAILURE
	}
	private static List<CourseDetails> courses = new ArrayList<CourseDetails>();
	
	static {
		courses.add(new CourseDetails(new BigInteger("1"), new String("Course1"), new String("This is the Course1")));
		
		courses.add(new CourseDetails(new BigInteger("2"), new String("Course2"), new String("This is the Course2")));
		
		courses.add(new CourseDetails(new BigInteger("3"), new String("Course3"), new String("This is the Course3")));
		
		courses.add(new CourseDetails(new BigInteger("4"), new String("Course4"), new String("This is the Course4")));
		
		courses.add(new CourseDetails(new BigInteger("5"), new String("Course5"), new String("This is the Course5")));
	}
	
	public CourseDetails findById(BigInteger id) {
		CourseDetails course = null;
		for (CourseDetails details : courses) {
			if (details.getId().equals(id)) {
				course = details;
				break;
			}
		}
		return course;
	}
	
	public List<CourseDetails> findAll() {
		return courses;
	}
	
	public StatusBean deleteById(BigInteger id) {
		Iterator<CourseDetails> iterator = courses.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getId().equals(id)) {
				iterator.remove();
				return StatusBean.SUCCESS;
			}
		}
		return StatusBean.FAILURE;
	}
}
