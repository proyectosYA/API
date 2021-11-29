package com.parameta.api.dominio.service;

import java.util.Date;

import com.parameta.api.dominio.dto.EmpleadoAgregadoDto;
import com.parameta.api.dominio.dto.EmpleadoDto;

public interface IClienteSoap {

	EmpleadoAgregadoDto agregarEmpleado(EmpleadoDto empleado);
	
	int validarEdadEmpleado(Date fechaNac);
}
