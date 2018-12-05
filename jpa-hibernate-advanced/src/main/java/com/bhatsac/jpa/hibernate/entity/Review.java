package com.bhatsac.jpa.hibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Review {

	//TODO :Check for composite key 
			@Id
			@GeneratedValue
			private Long id;
			
			private String rating;
			
			
			@ManyToOne
			private Course course;
			
			
			
			@Column(name="description", nullable=true, length=50)
			private String description;
			
			//TODO: hibernate and not JPA provides timestamps on saving
			
			@UpdateTimestamp
			private LocalDateTime lastUpdatedDate;
			
			@CreationTimestamp
			private LocalDateTime createdDate;
			
			public Review(){}
			
			public Review(String rating, String description) {
				super();
				this.rating = rating;
				this.description = description;
			}

			public Course getCourse() {
				return course;
			}

			public void setCourse(Course course) {
				this.course = course;
			}


			public LocalDateTime getLastUpdatedDate() {
				return lastUpdatedDate;
			}

			public LocalDateTime getCreatedDate() {
				return createdDate;
			}

			public String getRating() {
				return rating;
			}

			public void setRating(String rating) {
				this.rating = rating;
			}

			@Override
			public String toString() {
				return "Review [id=" + id + ", rating=" + rating + ", description=" + description + ", lastUpdatedDate="
						+ lastUpdatedDate + ", createdDate=" + createdDate + "]";
			}
			
			
			
}
