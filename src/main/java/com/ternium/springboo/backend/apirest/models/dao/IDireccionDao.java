package com.ternium.springboo.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ternium.springboo.backend.apirest.models.entity.Direccion;

public interface IDireccionDao extends CrudRepository<Direccion, Long> {

}
