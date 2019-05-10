package com.ibm.training.bootcamp.rest.sample01.service;

import java.util.List;

import com.ibm.training.bootcamp.rest.sample01.domain.Developer;

public interface DeveloperService {
	
    public List<Developer> findAll();
	
	public Developer find(Long devID);
	
	public List<Developer> findByName(String firstname, String lastname);
	
	public void addDeveloper(Developer dev);
	
	public void delDeveloper(Long devID);
}
