package com.ibm.training.bootcamp.rest.sample01.domain;

import java.sql.Date;

public class Developer {

    private Long  devID;
	private String firstname;
	private String middlename;
	private String lastname;
	private String  birthdate;
	private String position;
	
	public Developer(Long devID, String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public Developer(Long devID, String firstname, String middlename, String lastname, String birthdate,
			String position) {
		this.devID = devID;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.position = position;
		
	}
	public Long getDevID() {
		return devID;
	}
	public void setDevID(Long devID) {
		this.devID = devID;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
