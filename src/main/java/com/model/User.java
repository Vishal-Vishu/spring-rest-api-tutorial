package com.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;
	
	
	@NotBlank(message = "First Name cannot be empty")
	@Size(min = 2)
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	private String role;
	
	private String ssn;
	
	@OneToMany(mappedBy = "user")
	private List<Order> order;

	public Long getUserId() {
		return this.userid;
	}

	public void setUserId(Long userid) {
		this.userid = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	
	
}
