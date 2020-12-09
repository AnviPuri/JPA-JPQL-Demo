package com.example.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@NamedQuery(name = "findEmployeeByMonthsOfExperience", query = "SELECT employee FROM Employee employee WHERE experienceInMonths=?1")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private String gender;

	@Column(name = "experience_in_years")
	private int experienceInYears;

	@Column(name = "experience_in_months")
	private int experienceInMonths;

	@Column(name = "experience_in_days")
	private int experienceInDays;

	public Employee() {
		super();
	}

	public Employee(String name, String gender, int experienceInYears, int experienceInMonths, int experienceInDays) {
		super();
		this.name = name;
		this.gender = gender;
		this.experienceInYears = experienceInYears;
		this.experienceInMonths = experienceInMonths;
		this.experienceInDays = experienceInDays;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getExperienceInYears() {
		return experienceInYears;
	}

	public void setExperienceInYears(int experienceInYears) {
		this.experienceInYears = experienceInYears;
	}

	public int getExperienceInMonths() {
		return experienceInMonths;
	}

	public void setExperienceInMonths(int experienceInMonths) {
		this.experienceInMonths = experienceInMonths;
	}

	public int getExperienceInDays() {
		return experienceInDays;
	}

	public void setExperienceInDays(int experienceInDays) {
		this.experienceInDays = experienceInDays;
	}

}
