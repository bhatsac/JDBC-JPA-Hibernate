package com.bhatsac.jpa;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bhatsac.jpa.entity.Person;
import com.bhatsac.jpa.jdbc.PersonJdbcDAO;


//TODO : Uncomment this annotation to check the features of jdbctemplate.
//@SpringBootApplication
public class JdbcApplication implements CommandLineRunner{

	private Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	PersonJdbcDAO dao;
	
	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	// This is method is like a main method which is instantiated b spring boot after launch
	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}" + dao.findAll());
		logger.info("Find Users by id 10001-> {}"+ dao.findById(10001));
		logger.info("Delete Users by id 10001-> {}"+ dao.deleteById(10001));
		logger.info("Delete Users by id 10003-> {}"+ dao.deleteById(10003));
		logger.info("Inserting the values in "+ dao.insertPerson(new Person(1004,"svb","kum",new Date())));
		logger.info("Updating the values in "+ dao.updatePerson(new Person(1004,"svb","bang",new Date())));
		logger.info("All users after delete-> {}" + dao.findAll());
		
		
	}
}
