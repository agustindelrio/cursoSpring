package com.ternium.springboo.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="direccion")
public class Direccion implements Serializable {
	private static final long serialVersionUID = -3413922897650503737L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id_direccion")
	private Long id;
	@Column
	private String calle;
	@Column
	private String numero;

	public Direccion(String calle, String numero) {
		this.calle = calle;
		this.numero = numero;
	}
	public Direccion() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	
}
