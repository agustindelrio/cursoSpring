package com.ternium.springboo.backend.apirest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ternium.springboo.backend.apirest.models.dao.IUsuarioDao;
import com.ternium.springboo.backend.apirest.models.entity.Usuario;

@SpringBootTest
class SpringBootBackendApirestApplicationTests {

	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	void crearUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId((long) 4);
		usuario.setUsername("eduardo");
		usuario.setPassword(encoder.encode("2123"));
		Usuario usuarioNuevo = usuarioDao.save(usuario);
		assertTrue(usuarioNuevo.getPassword().equalsIgnoreCase(usuario.getPassword()));
	}

}
