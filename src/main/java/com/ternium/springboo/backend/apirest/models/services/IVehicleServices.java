package com.ternium.springboo.backend.apirest.models.services;

import java.util.List;


import com.ternium.springboo.backend.apirest.models.entity.Cliente;
import com.ternium.springboo.backend.apirest.models.entity.Vehicle;

public interface IVehicleServices {
	public List<Vehicle> findAll();
	public Vehicle findById(Long id);
	public List<Vehicle> listByCliente(Cliente cliente);
	public Vehicle findByLicenceId(String id);
	public Vehicle save(Vehicle vehicle);
	public void delete(Long id);
	public Vehicle findByBrand(String brand);
}
