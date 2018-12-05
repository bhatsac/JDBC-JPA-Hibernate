package com.bhatsac.jpa.hibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity

@NamedQueries(value={@NamedQuery(name="name_get_all_courses" , query="SELECT c from Course c")})
//@NamedNativeQueries(value={@NamedNativeQuery(name="native_get_all_courses", query = "SELECT * FROM COURSE")}) // TODO: check this as its not working!
public class Course {

	//TODO :Check for composite key 
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="name", nullable=false, length=50)
	private String name;
	
	@OneToMany(mappedBy="course")  //TODO: here mapped by will be the course variable declared in Review class.
	private List<Review> review =new ArrayList<Review>();
	
	@ManyToMany(mappedBy="courseList")
	private List<Student> studentList= new ArrayList<Student>();
	//TODO: hibernate and not JPA provides timestamps on saving
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	

	public List<Review> getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review.add( review);
	}

	public void removeReview(Review review) {
		this.review.remove( review);
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	protected Course(){}
	
	public Course(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	/*@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", review=" + review + ", studentList=" + studentList
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", createdDate=" + createdDate + "]";
	}*/

	
	
	
}
