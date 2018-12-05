package com.bhatsac.jpa.hibernate;



import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bhatsac.jpa.hibernate.entity.Course;
import com.bhatsac.jpa.hibernate.entity.FullTimeEmployee;
import com.bhatsac.jpa.hibernate.entity.PartTimeEmployee;
import com.bhatsac.jpa.hibernate.entity.Review;
import com.bhatsac.jpa.hibernate.entity.Student;
import com.bhatsac.jpa.hibernate.repository.CourseRepository;
import com.bhatsac.jpa.hibernate.repository.EmployeeRepository;
import com.bhatsac.jpa.hibernate.repository.StudentRepository;

@SpringBootApplication
public class JpaHibernateAdvancedApplication implements CommandLineRunner {
	@Autowired
	private CourseRepository courepository;
	
	@Autowired
	private StudentRepository stucourepository;
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Logger logger= LoggerFactory.getLogger(JpaHibernateAdvancedApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateAdvancedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*logger.info("----------Starting DB Calls---------");
		Course findById = courepository.findById(10001L);
		logger.info("find By Id: " + findById);
		
		//courepository.deleteById(10001L);
		
		logger.info("saved value: "+courepository.save(new Course("Big data!")).toString());
		Course save = courepository.save(new Course("Analytics"));
		save.setName("Machine Learning!");
		courepository.save(save);
		try{
		courepository.entityManagerPlayground();
		}catch(Exception e){
			
		}
		
		courepository.save(new Course("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
		logger.info("Named Query "+courepository.namedQueryDemo().toString());
		//logger.info("Native Query "+courepository.nativeQueryDemo().toString());
		
		
		
		//One to One demo --
		logger.info("<><><><><><><><><><><><><>One to One demo begins<><><><><><><><><><><><><><><>");
		//logger.info(stucourepository.findById(20001L).toString()); //throws error as if lazy fetch
		stucourepository.save(new Student("Ram"));
		
		
		//many to one
		courepository.addReviewsForCourse();
		
		
		
		///
		Course course1= courepository.findById(10003L);
		Review review1 =new Review("4", "This is one of the best i have seen!");
		Review review2 =new Review("4", "This is a great course!");
		
		// Adding review to course
		//course1.getReview().add(review1);
		//course1.getReview().add(review2);
		
		
		// Many to many 
		//courepository.addReviewsForCourse(course1, course1.getReview()); 
		stucourepository.manyToManyTest();
		
		stucourepository.manyToManyInsertTest();
		
		// Inheritance below
		employeeRepository.insert(new FullTimeEmployee("Sac", new BigDecimal(99999999)));
		
		employeeRepository.insert(new PartTimeEmployee("Kee", new BigDecimal(55555555)));
		
		logger.info(employeeRepository.retrieveAllFullTimeEmployees().toString());
		logger.info(employeeRepository.retrieveAllPartTimeEmployees().toString());*/
		
		// JPQL begins below
		
		courepository.courses_without_students();
		courepository.courses_with_2_students();
		stucourepository.students_without_courses();
		List<Student> students_with_pass_pattern = stucourepository.students_with_pass_pattern();
		students_with_pass_pattern.get(0).getCourseList();
		
		///Criteria Quries.		
		courepository.criteria_quries();
		courepository.criteria_quries_like();
		courepository.criteria_quries_stu_without_courses();
		courepository.criteria_quries_joins();
	}
}
