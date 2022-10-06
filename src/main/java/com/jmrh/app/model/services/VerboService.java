package com.jmrh.app.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmrh.app.model.dao.VerboDAOInterface;
import com.jmrh.app.model.entities.Verbo;

@Service
public class VerboService implements VerboServiceInterface {

	@Autowired
	private VerboDAOInterface verboDAO;
	
	@Transactional(readOnly = true)
	@Override
	public Verbo findOne(Integer id) {		
		return verboDAO.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public Long count() {
		return verboDAO.count();
	}

}
