package com.bhatsac.jpa.hibernate.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.bhatsac.jpa.hibernate.JpaHibernateAdvancedApplication;
import com.bhatsac.jpa.hibernate.entity.Course;
import com.bhatsac.jpa.hibernate.entity.Passport;
import com.bhatsac.jpa.hibernate.entity.Student;

@RunWith(SpringRunner.class)// TODO: Check this class for testing.
@SpringBootTest(classes=JpaHibernateAdvancedApplication.class) // TODO: check SpringBootTest, this would load entire context
public class StudentRepositoryTest {
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	StudentRepository sr;
	
	@Autowired
	EntityManager em;
	
	@Test
	@DirtiesContext  // TODO: hibernate will reset the operation performed in this method, by using this annotation.
	public void deleteById_basic() {
		logger.info("<><><><><><><><><><><><><>Running Testes on: "+this.getClass().getSimpleName()+"<><><><><><><><><><><><><>");
		sr.deleteById(20001L);
		assertEquals(null,sr.findById(20001L) );
		assertNull(sr.findById(20001L));
	}
	
	@Test
	public void findById_basic() {
		logger.info("<><><><><><><><><><><><><>Running Testes on: "+this.getClass().getSimpleName()+"<><><><><><><><><><><><><>");
		logger.info(sr.findById(20002L).toString());
		Student findById = sr.findById(20002L);
		Hibernate.initialize(findById);
		logger.info(findById.toString());
		assertEquals("kee", sr.findById(20002L).getName());
		//assertEquals("JPA Full Tutorials!", sr.findById(10001L).getName());
	}
	
	//@Test
	//@DirtiesContext 
	public void save_basic() {
		logger.info("<><><><><><><><><><><><><>Running Testes on: "+this.getClass().getSimpleName()+"<><><><><><><><><><><><><>");
		Student student = new Student("Angular!");
		student.setPassport(new Passport("KA-XYZ6"));
		assertEquals("Angular!", sr.save(student).getName());
		//assertEquals("JPA Full Tutorials!", sr.findById(10001L).getName());
	}

	@Test
	public void emPlayGround() {
		sr.entityManagerPlayground();
	}
	
	@Test
	public void jpqlTest() {
		List resultList = em.createQuery("select c from Course c").getResultList();
		logger.info("<><><><><><><><><><><><><><><><> JPQL <><><><><><><><><><><><><><><><><>"+resultList.toString());
		
		TypedQuery<Course> createQuery = em.createQuery("select c from Course c where name like '%B%'", Course.class);
		logger.info("Max results!"+createQuery.getResultList());
	}
	
	
}
