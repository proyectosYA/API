package com.parameta.api.dominio.dto;

import java.io.Serializable;

public class MensajeRespuestaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7070009823979007526L;
	private String mensaje;
	private int codigo;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
