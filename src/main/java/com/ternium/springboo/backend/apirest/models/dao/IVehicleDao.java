package com.ternium.springboo.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ternium.springboo.backend.apirest.models.entity.Vehicle;

public interface IVehicleDao extends CrudRepository<Vehicle, Long> {
	
	Vehicle findByBrand(String brand);
	
	@Query(value="select * FROM VEHICLE WHERE LICENCE_PLATE = ?1 AND ZERO_KM = ?2", nativeQuery=true)
	List<Vehicle> findByLicencePlateAndZeroKm(String licencePlate, Boolean zeroKm);
}
