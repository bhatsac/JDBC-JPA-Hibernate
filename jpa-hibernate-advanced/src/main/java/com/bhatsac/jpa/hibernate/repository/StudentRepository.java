package com.bhatsac.jpa.hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bhatsac.jpa.hibernate.entity.Course;
import com.bhatsac.jpa.hibernate.entity.Passport;
import com.bhatsac.jpa.hibernate.entity.Student;
@Repository
@Transactional //TODO: check why we are using spring transaction
public class StudentRepository {

	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	EntityManager em;
	
	public Student findById(Long id){
		return em.find(Student.class,id);
	}
	/*public Student Save(Student student){
			
		}
	public Student findById(Long id){
		
	}*/
	public void deleteById(Long id){
		em.remove(em.find(Student.class,id));
	}
	
	public Student save(Student student){
		if(null == student.getId()){
			//insert
			if(null==student.getPassport()){
				Passport passport =new Passport("KA-XYZ5");
				student.setPassport(passport);
			}
			em.persist(student.getPassport());
			em.persist(student);
		}else{
			//update
			em.merge(student);
		}
		return student;
	}
	
	public void entityManagerPlayground(){
		Student student1 = new Student("Boom Boom!");
		em.persist(student1);
		em.flush();
		Student student2 = new Student("Nodejs");
		em.persist(student2);
		em.flush();
		
		//em.detach(student1); // Yoyo! will not be saved as the object is detached from em.
		em.clear();  //this will detach all the objects
		student2.setName("Angular Js");
		student1.setName("Yoyo!");	
		
		Student student3 = new Student("Analytics!");
		em.persist(student3);
		em.flush();
		Student student4 = new Student("Electron js");
		em.persist(student4);
		em.flush();
		
		student3.setName("Karma!");
		student4.setName("Jasmine!");
		em.refresh(student3);
	    ///throw new RuntimeException(); // with this nothing will be saved as the transactional will rollback will be reverted.
	}
	
	public List<Student> namedQueryDemo(){
		 TypedQuery<Student> createNamedQuery = em.createNamedQuery("name_get_all_students", Student.class);
		 return createNamedQuery.getResultList();
		 
	}
	public List<Student> nativeQueryDemo(){
		 TypedQuery<Student> createNamedQuery = em.createNamedQuery("native_get_all_students", Student.class);
		 return createNamedQuery.getResultList();
		 
	}
	
	
	public void manyToManyTest(){
		Student stud = em.find(Student.class,20001L);
		logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		logger.info(stud.toString());
		logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	}
	
	public void manyToManyInsertTest(){
		Student stud = new Student("Ram");
		Course cor = new Course("Hanuman");
		em.persist(stud);
		em.persist(cor);
		stud.getCourseList().add(cor);
		cor.getStudentList().add(stud);
		logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		logger.info(stud.toString());
		logger.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	}
	
	
	// jpql courses where it dosent have any students.
		public void students_without_courses(){
			TypedQuery<Student> createQuery = em.createQuery("SELECT s from Student s where s.courseList is empty", Student.class);
			Query createNativeQuery = em.createNativeQuery("SELECT * from Student", Student.class);
			logger.info("**********************************************"+createNativeQuery.getResultList().toString() +"*****************************************************");
			logger.info(createQuery.getResultList().toString());
		}

		// jpql courses where it dosent have any students.
				public List<Student> students_with_pass_pattern(){
					TypedQuery<Student> createQuery = em.createQuery("SELECT s from Student s where s.passport.number LIKE '%1%'", Student.class);
					logger.info(createQuery.getResultList().toString());
					return createQuery.getResultList();
				}
}
