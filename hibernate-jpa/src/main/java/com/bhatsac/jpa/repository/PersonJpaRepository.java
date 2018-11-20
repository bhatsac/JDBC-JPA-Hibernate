package com.bhatsac.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


import org.springframework.stereotype.Repository;

import com.bhatsac.jpa.entity.Person;

//Repository
@Repository
//Transaction Management
@Transactional
public class PersonJpaRepository {

	// connect to the database
	@PersistenceContext
	EntityManager entityManager; //TODO  : what is actually Entity manager
	    // select * from person
		public Person findById(int id){
			return	 entityManager.find(Person.class, id);	
		}
		public Person updatePerson(Person person){
			return	 entityManager.merge(person);	 //XXX : INFO : EntityManager.merge(person) is used for insert and update 
		}
		public Person insertPerson(Person person){
			return	 entityManager.merge(person);	  
		}
		public void deletePerson(Person person){
				 person= findById(person.getId());
				 entityManager.remove(person);	    //FIXME : Removing a detached instance com.bhatsac.jpa.entity.Person#10001, error is occurring if we do not user fidnById
		}
		//TODO: Named query is defined on the entity(Person), also check on create named query
		public List<Person> findAll(){
				TypedQuery<Person> createNamedQuery = entityManager.createNamedQuery("find_all_persons",Person.class);
				return createNamedQuery.getResultList();
		}
	
}
