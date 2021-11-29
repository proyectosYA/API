package com.parameta.api.dominio.excepcion;

public class ApiExcepcion {

	private String nombreExcepcion;
	private String mensaje; 
	
	public ApiExcepcion() {}
	
	public ApiExcepcion(String nombreExcepcion, String mensaje ) {

		this.nombreExcepcion = nombreExcepcion;
		this.mensaje = mensaje; 
	}

	public String getNombreExcepcion() {
		return nombreExcepcion;
	}
  
	public String getMensaje() {
		return mensaje;
	} 
}
