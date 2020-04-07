package com.ternium.springboo.backend.apirest.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ternium.springboo.backend.apirest.models.dao.IDireccionDao;
import com.ternium.springboo.backend.apirest.models.entity.Direccion;
@Service
public class DireccionServicesImp implements IDireccionServices{

	@Autowired
	IDireccionDao dao;
	
	@Override
	@Transactional
	public Direccion save(Direccion direccion) {
		return dao.save(direccion);
	}

}
