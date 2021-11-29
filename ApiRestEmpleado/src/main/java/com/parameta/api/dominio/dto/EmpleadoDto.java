package com.parameta.api.dominio.dto;

import java.io.Serializable;
import java.util.Date;
 

public class EmpleadoDto implements Serializable {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombres;

	private String apellidos;

	private String tipoDocumento;

	private String numeroDocumento;
	 
	private Date fechaNacimiento;
	 
	private Date fechaVinculacionCompania;
	
	private String cargo;
	
	private double salario;

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaVinculacionCompania() {
		return fechaVinculacionCompania;
	}

	public void setFechaVinculacionCompania(Date fechaVinculacionCompania) {
		this.fechaVinculacionCompania = fechaVinculacionCompania;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	

}
