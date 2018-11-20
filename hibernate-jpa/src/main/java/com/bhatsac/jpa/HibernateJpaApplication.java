package com.bhatsac.jpa;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bhatsac.jpa.entity.Person;
import com.bhatsac.jpa.repository.PersonJpaRepository;
//TODO  : what is CommandLineRunner? This is method is like a main method which is instantiated by spring boot after launch.
@SpringBootApplication
public class HibernateJpaApplication implements CommandLineRunner{

	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	PersonJpaRepository personJpaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HibernateJpaApplication.class, args);
	}

	// This is method is like a main method which is instantiated b spring boot after launch
	@Override
	public void run(String... args) throws Exception {
		/*logger.info("All users -> {}" + dao.findAll());
		logger.info("Find Users by id 10001-> {}"+ dao.findById(10001));
		logger.info("Delete Users by id 10001-> {}"+ dao.deleteById(10001));
		logger.info("Delete Users by id 10003-> {}"+ dao.deleteById(10003));
		logger.info("Inserting the values in "+ dao.insertPerson(new Person(1004,"svb","kum",new Date())));
		logger.info("Updating the values in "+ dao.updatePerson(new Person(1004,"svb","bang",new Date())));
		logger.info("All users after delete-> {}" + dao.findAll());*/
		
		logger.info("Find Users by id 10001-> {}"+ personJpaRepository.findById(10001));
		Person person = personJpaRepository.findById(10001);person.setLocation("kumta!");
		logger.info("update User with id 10001-> {}"+ personJpaRepository.updatePerson(person));
		logger.info("insert New User -> {}"+ personJpaRepository.insertPerson(person));
		logger.info("insert New User -> {}"+ personJpaRepository.insertPerson( personJpaRepository.insertPerson(new Person(1004,"svb","skumta",new Date()))));
		logger.info("insert New User -> {}"+ personJpaRepository.insertPerson(personJpaRepository.insertPerson(new Person("vin","vkumta",new Date()))));
		logger.info("insert New User -> {}");personJpaRepository.deletePerson(person);
		logger.info("insert New User -> {}"+ personJpaRepository.findAll());
		
	}
}
