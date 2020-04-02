package com.ternium.springboo.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ternium.springboo.backend.apirest.models.entity.Vehicle;

public interface IVehicleDao extends CrudRepository<Vehicle, Long> {
	
	Vehicle findByBrand(String brand);

}
