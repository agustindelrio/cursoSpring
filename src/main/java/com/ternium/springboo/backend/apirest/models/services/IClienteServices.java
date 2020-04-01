package com.ternium.springboo.backend.apirest.models.services;

import java.util.List;
import org.springframework.lang.Nullable;

import com.ternium.springboo.backend.apirest.models.entity.Cliente;

public interface IClienteServices {
	public List<Cliente> findAll();
	@Nullable
	public Cliente findById(Long id);
	public Cliente changeEmail(Long id, String email);
	public Cliente save(Cliente cliente);
	public void delete(Long id);
	
}
