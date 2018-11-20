package com.bhatsac.jpa.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bhatsac.jpa.entity.Person;

@Repository
public class PersonJdbcDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class PersonRowMapper implements RowMapper<Person>{

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person= new Person();
			person.setId(rs.getInt("ID"));
			person.setName(rs.getString("NAME"));
			person.setLocation(rs.getString("LOCATION"));
			person.setBirthDate(rs.getDate("BIRTH_DATE"));
			return person;
		}
		
	}
	
	// select * from person
	public List<Person> findAll(){
		
	return	 jdbcTemplate.query("select * from person", new PersonRowMapper());	
	
	}
	
	// select * from person
	public Person findById(int id){
		return	 (Person) jdbcTemplate.queryForObject("select * from person where id=?",new Object[]{id}, new BeanPropertyRowMapper(Person.class) );	
	}
	
	
	// delete from person
	public int deleteById(int id){
		return	  jdbcTemplate.update("Delete from person where id=?",new Object[]{id});	
	}
	
	// insert into person
	public int insertPerson(Person person){
		return	  jdbcTemplate.update("INSERT INTO PERSON (id, name, location, birth_date) values (?,?,?,?)", 
				new Object[]{
						person.getId(),
						person.getName(),
						person.getLocation(),
						person.getBirthDate()							
				});
	}
	
	
	// select * from person
	public int updatePerson(Person person){
		return	  jdbcTemplate.update("UPDATE  PERSON SET location =? WHERE id=?", 
				new Object[]{
						person.getLocation(),
						person.getId()						
				});
	}
}
