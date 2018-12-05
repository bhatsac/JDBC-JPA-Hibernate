package com.bhatsac.jpa.hibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
public class Student {
	//TODO :Check for composite key 
		@Id
		@GeneratedValue
		private Long id;
		@Column(name="name", nullable=false, length=50)
		private String name;
		
		//TODO: hibernate and not JPA provides timestamps on saving
		@OneToOne(fetch=FetchType.LAZY)
		private Passport passport;
		
		@UpdateTimestamp
		private LocalDateTime lastUpdatedDate;
		
		@CreationTimestamp
		private LocalDateTime createdDate;
		
		@ManyToMany(fetch=FetchType.LAZY)
		@JoinTable(
					name="STUDENT_COURSE",
					joinColumns=@JoinColumn(name="STUDENT_ID"),
					inverseJoinColumns=@JoinColumn(name="COURSE_ID")
				   )
		private List<Course> courseList= new ArrayList<Course>();

		public LocalDateTime getLastUpdatedDate() {
			return lastUpdatedDate;
		}

		public LocalDateTime getCreatedDate() {
			return createdDate;
		}

		protected Student(){}
		
		public Student(String name) {
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

		public Passport getPassport() {
			return passport;
		}

		public void setPassport(Passport passport) {
			this.passport = passport;
		}

		
		public List<Course> getCourseList() {
			return courseList;
		}

		public void setCourseList(List<Course> courseList) {
			this.courseList = courseList;
		}

		@Override
		public String toString() {
			return "Student [id=" + id + ", name=" + name + ", passport=" + passport + ", lastUpdatedDate="
					+ lastUpdatedDate + ", createdDate=" + createdDate + ", courseList=" + courseList + "]";
		}

		
}
