package com.bhatsac.jpa.hibernate.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

@RunWith(SpringRunner.class)// TODO: Check this class for testing.
@SpringBootTest(classes=JpaHibernateAdvancedApplication.class) // TODO: check SpringBootTest, this would load entire context
public class CourseRepositoryTest {
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	CourseRepository cr;
	
	@Autowired
	EntityManager em;
	
	@Test
	@DirtiesContext  // TODO: hibernate will reset the operation performed in this method, by using this annotation.
	public void deleteById_basic() {
		logger.info("<><><><><><><><><><><><><>Running Testes on: "+this.getClass().getSimpleName()+"<><><><><><><><><><><><><>");
		cr.deleteById(10001L);
		assertEquals(null,cr.findById(10001L) );
		assertNull(cr.findById(10001L));
	}
	
	@Test
	public void findById_basic() {
		logger.info("<><><><><><><><><><><><><>Running Testes on: "+this.getClass().getSimpleName()+"<><><><><><><><><><><><><>");
		assertEquals("JPA Full Tutorials", cr.findById(10001L).getName());
		//assertEquals("JPA Full Tutorials!", cr.findById(10001L).getName());
	}
	
	@Test
	@DirtiesContext 
	public void save_basic() {
		logger.info("<><><><><><><><><><><><><>Running Testes on: "+this.getClass().getSimpleName()+"<><><><><><><><><><><><><>");
		assertEquals("Angular!", cr.save(new Course("Angular!")).getName());
		//assertEquals("JPA Full Tutorials!", cr.findById(10001L).getName());
	}

	@Test
	public void emPlayGround() {
		cr.entityManagerPlayground();
	}
	
	@Test
	public void jpqlTest() {
		List resultList = em.createQuery("select c from Course c").getResultList();
		logger.info("<><><><><><><><><><><><><><><><> JPQL <><><><><><><><><><><><><><><><><>"+resultList.toString());
		
		TypedQuery<Course> createQuery = em.createQuery("select c from Course c where name like '%B%'", Course.class);
		logger.info("Max results!"+createQuery.getResultList());
	}
	
	
}
