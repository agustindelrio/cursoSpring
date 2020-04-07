package com.ternium.springboo.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ternium.springboo.backend.apirest.models.dao.IClienteDao;
import com.ternium.springboo.backend.apirest.models.dao.IVehicleDao;
import com.ternium.springboo.backend.apirest.models.entity.Cliente;
import com.ternium.springboo.backend.apirest.models.entity.Direccion;
import com.ternium.springboo.backend.apirest.models.entity.Vehicle;

@Service
public class ClienteServiceImp implements IClienteServices{

	@Autowired
	private IClienteDao clienteDao;

	@PersistenceContext
	private EntityManager entityManager;
	
	
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

	@Override
	public Cliente findByNameAndSurname(HashMap<String, Object> params) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cliente> query= cb.createQuery(Cliente.class);
		Root<Cliente> root = query.from(Cliente.class);
		List<Predicate> predicates = new ArrayList<>();

		params.forEach((field,value) -> {
			predicates.add(cb.like(root.get(field),"%"+(String)value+"%"));
		});
		
		query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
		return entityManager.createQuery(query).getSingleResult(); 
	}




}
