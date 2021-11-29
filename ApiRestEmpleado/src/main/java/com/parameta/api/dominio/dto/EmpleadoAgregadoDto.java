package com.parameta.api.dominio.dto;

import java.io.Serializable;

public class EmpleadoAgregadoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5450102915524559389L;

	private String nombres;
	private String apellidos;
	private String tipoDocumento;
	private String numeroDocumento;
	private String fechaNacimiento;
	private String fechaVinculacionCompania;
	private String cargo;
	private String salario;

	private String tiempoVinculacion;

	private String edadActualEmpleado;

	 

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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaVinculacionCompania() {
		return fechaVinculacionCompania;
	}

	public void setFechaVinculacionCompania(String fechaVinculacionCompania) {
		this.fechaVinculacionCompania = fechaVinculacionCompania;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getTiempoVinculacion() {
		return tiempoVinculacion;
	}

	public void setTiempoVinculacion(String tiempoVinculacion) {
		this.tiempoVinculacion = tiempoVinculacion;
	}

	public String getEdadActualEmpleado() {
		return edadActualEmpleado;
	}

	public void setEdadActualEmpleado(String edadActualEmpleado) {
		this.edadActualEmpleado = edadActualEmpleado;
	}

}
