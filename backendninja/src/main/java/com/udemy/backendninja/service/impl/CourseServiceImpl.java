package com.udemy.backendninja.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.udemy.backendninja.converter.CourseConverter;
import com.udemy.backendninja.entity.Course;
import com.udemy.backendninja.model.CourseModel;
import com.udemy.backendninja.repository.CourseJpaRepository;
import com.udemy.backendninja.service.CourseService;

@Service("courseServiceImpl")
public class CourseServiceImpl  implements CourseService{
	
	private static final Log LOG = LogFactory.getLog(CourseServiceImpl.class);
	
	@Autowired
	@Qualifier("courseJpaRepository")
	private CourseJpaRepository courseJpaRepository;
	
	@Autowired
	@Qualifier("courseConverter")
	private CourseConverter courseConverter;

	@Override
	public List<CourseModel> listAllCourses() {
		LOG.info("Call: " + "listAllCourses()");
		List<CourseModel> list = new ArrayList<CourseModel>();
		for(Course c : courseJpaRepository.findAll()){
			list.add(courseConverter.entity2model(c));
		}
		return list;
	}

	@Override
	public CourseModel addCourse(CourseModel course) {
		LOG.info("Call: " + "addCourse()");
		return courseConverter.entity2model(courseJpaRepository.save(courseConverter.model2Entity(course)));
	}

	@Override
	public int removeCourse(int id) {
		courseJpaRepository.delete(id);
		return 0;
	}

	@Override
	public CourseModel updateCourse(CourseModel course) {
		return courseConverter.entity2model(courseJpaRepository.save(courseConverter.model2Entity(course)));
	}

}
