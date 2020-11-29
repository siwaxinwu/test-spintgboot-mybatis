package com.roy.bean;

public class EmpPlusDept {

	private Integer id;
	private String lastName;
	private String email;
	private String gender;
	private Dept dept;

	public EmpPlusDept() {
	}

	public EmpPlusDept(Integer id, String lastName, String email, String gender, Dept dept) {
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.dept = dept;
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

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "EmpPlusDept{" +
				"id=" + id +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", gender='" + gender + '\'' +
				", dept=" + dept +
				'}';
	}
}
