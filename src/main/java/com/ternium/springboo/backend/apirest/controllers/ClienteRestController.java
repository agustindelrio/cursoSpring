package com.ternium.springboo.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ternium.springboo.backend.apirest.models.entity.Cliente;
import com.ternium.springboo.backend.apirest.models.services.IClienteServices;
import com.ternium.springboo.backend.apirest.models.services.IVehicleServices;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteServices clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> clienteList(){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	//@ResponseStatus(HttpStatus.OK) // por defecto
	public ResponseEntity<?> show(@PathVariable Long id){
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>(); 

		try {
			cliente = clienteService.findById(id);
		} catch (final DataAccessException ex) {
			response.put("mensaje", ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (cliente == null) {
			response.put("mensaje","El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);

	}
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Cliente body, BindingResult result){
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>(); 

		if (result.hasErrors()) {			
			List<String> errors = result.getFieldErrors().stream().map(error -> error.getField().concat(" ").concat(error.getDefaultMessage())).collect(Collectors.toList());

			response.put("errors", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			cliente = clienteService.save(body);

		} catch(final DataAccessException ex) {
			response.put("mensaje", "error al dar de alta el cliente");
			response.put("error", ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Se dio de alta el cliente");
		response.put("cliente", cliente);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@GetMapping("/clientes/cambiarEmail")
	public Cliente changeName(Long id, String email){
		return clienteService.changeEmail(id, email);
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		
		Cliente clienteToUpdate = null;
		Map<String, Object> response = new HashMap<>(); 
		if (result.hasErrors()) {
			/*List<String> errors = new ArrayList<>();
			result.getFieldErrors().forEach(error ->
			errors.add(error.getField().concat(" ").concat(error.getDefaultMessage())));
			
			response.put("errors", errors);
			*/
			
			List<String> errors = result.getFieldErrors().stream().map(error -> error.getField().concat(" ").concat(error.getDefaultMessage())).collect(Collectors.toList());
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			clienteToUpdate = clienteService.findById(id);
			if (clienteToUpdate == null) {
				response.put("mensaje","El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			clienteToUpdate.update(cliente.getNombre(), cliente.getApellido(), cliente.getEmail());
			clienteService.save(clienteToUpdate);
			response.put("cliente", clienteToUpdate);
			response.put("mensaje", "Se Actualizo el cliente");

			
		} catch (final DataAccessException ex) {
			response.put("mensaje", "Error al actualizar");
			response.put("error", ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>(); 

		try {
			clienteService.delete(id);
			response.put("mensaje","El cliente ID: ".concat(id.toString().concat(" se elimino en la base de datos")));
		} catch (Exception e) {
			response.put("mensaje","El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
