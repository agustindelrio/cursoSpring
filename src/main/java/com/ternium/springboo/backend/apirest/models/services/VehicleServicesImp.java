package com.ternium.springboo.backend.apirest.models.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ternium.springboo.backend.apirest.models.dao.IVehicleDao;
import com.ternium.springboo.backend.apirest.models.entity.Cliente;
import com.ternium.springboo.backend.apirest.models.entity.Vehicle;
@Service
public class VehicleServicesImp implements IVehicleServices {

	@Autowired
	private IVehicleDao vehicleDao;
	
	@Override
	public List<Vehicle> findAll() {
		return (List<Vehicle>)vehicleDao.findAll();
	}

	@Override
	public Vehicle findById(Long id) {
		return vehicleDao.findById(id).orElseGet(null);
	}

	@Override
	public List<Vehicle> listByCliente(Cliente cliente) {
		return null;
	}

	@Override
	public Vehicle findByLicenceId(String id) {
		return null;
	}

	@Override
	public Vehicle save(Vehicle vehicle) {
		return vehicleDao.save(vehicle);
	}

	@Override
	public void delete(Long id) {
		vehicleDao.deleteById(id);
	}

	@Override
	public Vehicle findByBrand(String brand) {
		return vehicleDao.findByBrand(brand);
	}
}
