package com.jmrh.app.model.services;

import com.jmrh.app.model.entities.Verbo;

public interface VerboServiceInterface {
	
	public Verbo findOne(Integer id);
	public Long count();
	
}
