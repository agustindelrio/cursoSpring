package com.ternium.springboo.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.ternium.springboo.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao  extends CrudRepository<Usuario, Long>{

	Usuario findByUsername(String username);
	
}
