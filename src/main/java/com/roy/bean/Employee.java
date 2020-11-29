package com.roy.bean;

import java.io.Serializable;

/**
 * description：
 * 员工实体
 * author：dingyawu
 * date：created in 21:36 2020/11/27
 * history:
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = 1633443422482560719L;
	private Integer id;
	private String lastName;
	private String email;
	private String gender;

	public Employee() {
	}

	public Employee(Integer id, String lastName, String email, String gender) {
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email="
				+ email + ", gender=" + gender + "]";
	}
	
	

}
