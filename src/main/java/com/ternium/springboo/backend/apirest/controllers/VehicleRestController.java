package com.ternium.springboo.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ternium.springboo.backend.apirest.models.entity.Cliente;
import com.ternium.springboo.backend.apirest.models.entity.Vehicle;
import com.ternium.springboo.backend.apirest.models.services.IClienteServices;
import com.ternium.springboo.backend.apirest.models.services.IVehicleServices;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class VehicleRestController {
	
	@Autowired
	private IVehicleServices vehicleService;
	
	@GetMapping("/vehicles")
	public List<Vehicle> clienteList(){
		return vehicleService.findAll();
	}
	
	@GetMapping("/vehicles/{brand}")
	public ResponseEntity<?> findVehicleByBrand(@PathVariable String brand) {
		return new ResponseEntity<Vehicle>(vehicleService.findByBrand(brand), HttpStatus.OK);
	}
	
	
	@PostMapping("/vehicles")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Vehicle body, BindingResult result){
		Vehicle vehicle = null;
		Map<String, Object> response = new HashMap<>(); 

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(error -> error.getField().concat(" ").concat(error.getDefaultMessage())).collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			vehicle = vehicleService.save(body);

		} catch(final DataAccessException ex) {
			response.put("mensaje", "error al dar de alta el vehicle");
			response.put("error", ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Se dio de alta el vehicle");
		response.put("cliente", vehicle);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
}
