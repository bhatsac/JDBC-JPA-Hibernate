package com.bhatsac.jpa.hibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.ConstraintComposition;

@Entity
public class Passport {
	//TODO :Check for composite key 
			@Id
			@GeneratedValue
			private Long id;
			
			@Column(name="number", nullable=false, length=50)
			private String number;
			
			@OneToOne(fetch=FetchType.LAZY, mappedBy="passport")
			private Student student;
			
			
			//TODO: hibernate and not JPA provides timestamps on saving
			
			@UpdateTimestamp
			private LocalDateTime lastUpdatedDate;
			
			@CreationTimestamp
			private LocalDateTime createdDate;
			
			

			public LocalDateTime getLastUpdatedDate() {
				return lastUpdatedDate;
			}

			public LocalDateTime getCreatedDate() {
				return createdDate;
			}

			protected Passport(){}
			
			public Passport(String number) {
				super();
				this.number = number;
			}

			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}

			

			public String getNumber() {
				return number;
			}

			public void setNumber(String number) {
				this.number = number;
			}

			@Override
			public String toString() {
				return "Passport [id=" + id + ", number=" + number + ", lastUpdatedDate=" + lastUpdatedDate
						+ ", createdDate=" + createdDate + "]";
			}

			
}
