package com.ternium.springboo.backend.apirest.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ternium.springboo.backend.apirest.models.dao.IClienteDao;
import com.ternium.springboo.backend.apirest.models.dao.IVehicleDao;
import com.ternium.springboo.backend.apirest.models.entity.Cliente;
import com.ternium.springboo.backend.apirest.models.entity.Vehicle;

@Service
public class ClienteServiceImp implements IClienteServices{

	@Autowired
	private IClienteDao clienteDao;

	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {		
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	public Cliente changeEmail(Long id, String email) {
		Cliente cliente = clienteDao.findById(id).orElseGet(null);
		if (cliente !=null) {
			cliente.setEmail(email);
			clienteDao.save(cliente);
		}
		return cliente;
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}




}
