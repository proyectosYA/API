package com.parameta.api.dominio.service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parameta.api.dominio.model.Empleado;
import com.parameta.api.dominio.puerto.repository.EmpleadoRepository;

@Service
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public List<Empleado> obtenerEmpleados() {
		List<Empleado> listaEmpleados = empleadoRepository.findAll();
		return listaEmpleados;
	}

	@Override
	public Empleado obtenerEmpleadoPorId(long idEmpleado) {
		Empleado empleado = empleadoRepository.findById(idEmpleado).get();

		return empleado != null ? empleado : null;
	}

	@Override
	public boolean agregarEmpleado(Empleado empleado) {
		Empleado empleadoAgregado = empleadoRepository.save(empleado);
		return empleadoAgregado != null ? true : false;
	}

	@Override
	public boolean eliminarEmpleado(long id) {
		Empleado empleado = empleadoRepository.findById(id).get();
		if (empleado != null) {
			empleadoRepository.delete(empleado);
			return true;
		}
		return false;
	}

	@Override
	public boolean actualizarEmpleado(Empleado empleado) {
		Empleado empleadoActualizado = empleadoRepository.save(empleado);

		return empleadoActualizado != null ? true : false;
	}

	@Override
	public XMLGregorianCalendar convertirDateAXmlGregorianCalendar(Date fecha) {

		XMLGregorianCalendar fechaGregorianCalendar = null;
		GregorianCalendar calendario = new GregorianCalendar();
		calendario.setTime(fecha);
		try {
			fechaGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendario);
			return fechaGregorianCalendar;
		} catch (DatatypeConfigurationException e) {
			Log log = LogFactory.getLog(getClass());
			log.info("LOG ERROR: " + e.getMessage());

			return fechaGregorianCalendar;
		} 
	}

}
