package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="first_name",length = 50,nullable = false)
	private String firstName;
	
	@Column(name="last_name",length = 50,nullable=false)
	private String lastName;
	
	private String role;
	
	@Column(name = "ssn",length = 25,nullable = false,unique = true)
	private String ssn;
}
