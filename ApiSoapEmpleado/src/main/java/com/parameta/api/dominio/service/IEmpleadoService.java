package com.parameta.api.dominio.service;

import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.parameta.api.dominio.model.Empleado;

public interface IEmpleadoService {

	List<Empleado> obtenerEmpleados();
	Empleado obtenerEmpleadoPorId(long idEmpleado);
	boolean agregarEmpleado(Empleado empleado);
	boolean eliminarEmpleado(long id);
	boolean actualizarEmpleado(Empleado empleado);
	
	 XMLGregorianCalendar convertirDateAXmlGregorianCalendar(Date fecha);
}
