package com.ibm.training.bootcamp.rest.sample01.dao;

import java.sql.Date;
import java.util.List;

import com.ibm.training.bootcamp.rest.sample01.domain.Developer;

public interface DeveloperDao {

	public List<Developer> findAll();
	
	public Developer find(Long devID);
	
	public List<Developer> findByName(String firstname, String lastname);
	
	public void addDeveloper(Developer dev);
	
	public void delDeveloper(Long devID);

	List<Developer> findAll(String firstname, String middlename, String lastname, String birthdate, String position);
}
