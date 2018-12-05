package com.bhatsac.jpa.hibernate.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bhatsac.jpa.hibernate.JpaHibernateAdvancedApplication;
import com.bhatsac.jpa.hibernate.entity.Course;
import com.bhatsac.jpa.hibernate.repository.springdatarepo.CourseSpringDataRepository;

@RunWith(SpringRunner.class)// TODO: Check this class for testing.
@SpringBootTest(classes=JpaHibernateAdvancedApplication.class) // TODO: check SpringBootTest, this would load entire context
public class CourseSpringDataRepositoryTest {
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseSpringDataRepository csdr;
	
	@Autowired
	EntityManager em;
	
	
	@Test
	public void findById_basic1() {
		Optional<Course> courseOptional = csdr.findById(10001l);
		logger.info(String.valueOf(courseOptional.isPresent()));
		assertTrue(courseOptional.isPresent());
	}
	
	@Test
	public void findById_basic2() {
		Optional<Course> courseOptional = csdr.findById(10009l);
		logger.info("{This is awesome....}",courseOptional.isPresent());
		assertFalse(courseOptional.isPresent());
	}
	
	
	
}
