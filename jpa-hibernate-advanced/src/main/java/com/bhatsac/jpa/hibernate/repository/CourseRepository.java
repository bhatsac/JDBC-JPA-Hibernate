package com.bhatsac.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bhatsac.jpa.hibernate.entity.Course;
import com.bhatsac.jpa.hibernate.entity.Review;
import com.bhatsac.jpa.hibernate.entity.Student;
@Repository
@Transactional //TODO: check why we are using spring transaction
public class CourseRepository {

	Logger logger=LoggerFactory.getLogger(this.getClass());
	@PersistenceContext
	EntityManager em;
	
	public Course findById(Long id){
		return em.find(Course.class,id);
	}
	/*public Course Save(Course course){
			
		}
	public Course findById(Long id){
		
	}*/
	public void deleteById(Long id){
		em.remove(em.find(Course.class,id));
	}
	
	public Course save(Course course){
		if(null == course.getId()){
			//insert
			logger.info("#################################################"+course.toString()+"##########################################");
			em.persist(course);
			logger.info("#################################################"+course.toString()+"##########################################");
		}else{
			//update
			em.merge(course);
		}
		return course;
	}
	
	public void entityManagerPlayground(){
		Course course1 = new Course("Boom Boom!");
		em.persist(course1);
		em.flush();
		Course course2 = new Course("Nodejs");
		em.persist(course2);
		em.flush();
		
		//em.detach(course1); // Yoyo! will not be saved as the object is detached from em.
		em.clear();  //this will detach all the objects
		course2.setName("Angular Js");
		course1.setName("Yoyo!");	
		
		Course course3 = new Course("Analytics!");
		em.persist(course3);
		em.flush();
		Course course4 = new Course("Electron js");
		em.persist(course4);
		em.flush();
		
		course3.setName("Karma!");
		course4.setName("Jasmine!");
		em.refresh(course3);
	    ///throw new RuntimeException(); // with this nothing will be saved as the transactional will rollback will be reverted.
	}
	
	public List<Course> namedQueryDemo(){
		 TypedQuery<Course> createNamedQuery = em.createNamedQuery("name_get_all_courses", Course.class);
		 return createNamedQuery.getResultList();
		 
	}
	public List<Course> nativeQueryDemo(){
		 TypedQuery<Course> createNamedQuery = em.createNamedQuery("native_get_all_courses", Course.class);
		 return createNamedQuery.getResultList();
		 
	}
	
	/////////////////////////////////////////
	
	public void addReviewsForCourse(){
		Course course1=findById(10001L);
		
		Review review1 =new Review("4", "This is one of the best i have seen!");
		Review review2 =new Review("4", "This is a great course!");
		
		// Adding review to course
		course1.getReview().add(review1);
		course1.getReview().add(review2);
		
		
		
		// Since review object is owning side, we need to tell the review object about the course object
		review1.setCourse(course1);
		review2.setCourse(course1);
		//save(course1); no need to persist as its already in @ transaction contex....
		em.persist(review1);
		em.persist(review2);
		
		logger.info(course1.getReview().toString());
	}
	
	public void addReviewsForCourse(Course course, List<Review> reviews){
		
		//save(course);
		
		//logger.info(course.getReview().toString());
	}
	
	
	// jpql courses where it dosent have any students.
	public void courses_without_students(){
		TypedQuery<Course> createQuery = em.createQuery("SELECT c from Course c where c.studentList is empty", Course.class);
		logger.info(createQuery.getResultList().toString());
	}
	
	public void courses_with_2_students(){
		TypedQuery<Course> createQuery = em.createQuery("SELECT c from Course c where size(c.studentList)>2 ", Course.class);
		logger.info(createQuery.getResultList().toString());
	}
	
	//criteria quries
	public void criteria_quries(){
		//1. use criteria builder to build criteria query returning the expected result object.
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);
		//2. define roots of the table which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		//3. define predicates etc using criteria query
		
		//4.Add predicate etc to the query
		
		//5. build a typed query using entity manager and criteria query
		TypedQuery<Course> createQuery = em.createQuery(criteriaQuery.select(courseRoot));
		logger.info(createQuery.getResultList().toString());
	
	}
	public void criteria_quries_like(){
		//1. use criteria builder to build criteria query returning the expected result object.
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);
		//2. define roots of the table which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		//3. define predicates etc using criteria query
		Predicate like = cb.like(courseRoot.get("name"), "%JPA%");
		
		//4.Add predicate etc to the query
		criteriaQuery.where(like);
		
		//5. build a typed query using entity manager and criteria query
		TypedQuery<Course> createQuery = em.createQuery(criteriaQuery.select(courseRoot));
		logger.info(createQuery.getResultList().toString());
	
	}
	public void criteria_quries_stu_without_courses(){
		//1. use criteria builder to build criteria query returning the expected result object.
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);
		//2. define roots of the table which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		//3. define predicates etc using criteria query
		Predicate isEmpty = cb.isEmpty(courseRoot.get("studentList"));
		
		//4.Add predicate etc to the query
		criteriaQuery.where(isEmpty);
		
		//5. build a typed query using entity manager and criteria query
		TypedQuery<Course> createQuery = em.createQuery(criteriaQuery.select(courseRoot));
		logger.info(createQuery.getResultList().toString());
	
	}
	
	public void criteria_quries_joins(){
		//1. use criteria builder to build criteria query returning the expected result object.
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);
		//2. define roots of the table which are involved in the query
		Root<Course> courseRoot = criteriaQuery.from(Course.class);
		
		//3. define predicates etc using criteria query
		Predicate isEmpty = cb.isEmpty(courseRoot.get("studentList"));
		
		//4.Add predicate etc to the query
		courseRoot.join("studentList");
		
		//5. build a typed query using entity manager and criteria query
		TypedQuery<Course> createQuery = em.createQuery(criteriaQuery.select(courseRoot));
		logger.info("<><><><><><><><><><><><><><><><><><><><>"+createQuery.getResultList().toString()+"<><><><><><><><><><><><><><><><><><><><>");
	
	}
	
}
