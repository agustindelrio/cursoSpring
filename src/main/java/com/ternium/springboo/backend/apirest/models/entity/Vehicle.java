package com.ternium.springboo.backend.apirest.models.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

@Entity
@Table(name="vehicle")
public class Vehicle  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = {CascadeType.ALL})
	//@Column(name="cliente_id",nullable = true)
	private Cliente cliente;
	
	@Column(nullable = false)
	private String brand;
	
	@Column(nullable = false)
	private String model;
	
	@Column(nullable = false)
	private Integer year;
	
	@Column(nullable = false)
	private String version;
	
	@Size(min=4, max=12, message = "Debe tener entre 6 y 8 caracteres")
	@Column(nullable = true)
	private String licencePlate;
	
	@Column(nullable = true)
	@Size(min=4, max=12, message = "Debe tener entre 4 y 20 caracteres")
	private String chassis;
	
	@Column(nullable = true)
	private String sumAssured;
	
	@Column(nullable = true)
	private Boolean zeroKm;
	
	@Column(name="create_at", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date createAT;
	

	
	public void update(String licencePlate, String chassis, String sumAssured) {
		this.licencePlate = licencePlate;
		this.chassis = chassis;
		this.sumAssured = sumAssured;
	}

	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Date getCreateAT() {
		return createAT;
	}


	public void setCreateAT(Date createAT) {
		this.createAT = createAT;
	}


	@PrePersist
	public void prePersist() {
		createAT = new Date();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLicencePlate() {
		return licencePlate;
	}
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	public String getChassis() {
		return chassis;
	}
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	public String getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(String sumAssured) {
		this.sumAssured = sumAssured;
	}
	public Boolean getZeroKm() {
		return zeroKm;
	}
	public void setZeroKm(Boolean zeroKm) {
		this.zeroKm = zeroKm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
