package com.bhatsac.jpa.hibernate.repository.springdatarepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhatsac.jpa.hibernate.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{

	
}
