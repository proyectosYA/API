package com.parameta.api.dominio.endpoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.parameta.api.dominio.model.Empleado;
import com.parameta.api.dominio.service.IEmpleadoService;
import com.parameta.api.dominio.util.Constante;
import com.parameta.empleado_ws.ActualizarEmpleadoRequest;
import com.parameta.empleado_ws.ActualizarEmpleadoResponse;
import com.parameta.empleado_ws.AgregarEmpleadoRequest;
import com.parameta.empleado_ws.AgregarEmpleadoResponse;
import com.parameta.empleado_ws.EliminarEmpleadoRequest;
import com.parameta.empleado_ws.EliminarEmpleadoResponse;
import com.parameta.empleado_ws.EmpleadoInfo;
import com.parameta.empleado_ws.EstadoServicio;
import com.parameta.empleado_ws.GetEmpleadoPorIdRequest;
import com.parameta.empleado_ws.GetEmpleadoPorIdResponse;
import com.parameta.empleado_ws.GetEmpleadosResponse;

@Endpoint
public class EmpleadoEndpoint {

	@Autowired
	private IEmpleadoService serviceEmpleado;

	@PayloadRoot(namespace = Constante.NAME_SPACE, localPart = "agregarEmpleadoRequest")
	@ResponsePayload
	public AgregarEmpleadoResponse agregarEmpleado(@RequestPayload AgregarEmpleadoRequest request) {
		AgregarEmpleadoResponse respuesta = new AgregarEmpleadoResponse();
		EstadoServicio estado = new EstadoServicio();
		Date fechaNacimiento = request.getFechaNacimiento().toGregorianCalendar().getTime();
		Date fechaVinculacionCompania = request.getFechaVinculacionCompania().toGregorianCalendar().getTime();

		Empleado empleado = new Empleado(request.getNombres(), request.getApellidos(), request.getTipoDocumento(),
				request.getNumeroDocumento(), fechaNacimiento, fechaVinculacionCompania, request.getCargo(),
				request.getSalario());
		boolean empreadoAgregado = serviceEmpleado.agregarEmpleado(empleado);
		if (empreadoAgregado) {
			EmpleadoInfo empleadoinfo = new EmpleadoInfo();
			XMLGregorianCalendar fechaNacimientoXmlGregorian = serviceEmpleado.convertirDateAXmlGregorianCalendar(empleado.getFechaNacimiento());
			XMLGregorianCalendar fechaVinculacionCompaniaXmlGregorian = serviceEmpleado.convertirDateAXmlGregorianCalendar(empleado.getFechaVinculacionCompania());
 
			 BeanUtils.copyProperties(empleado, empleadoinfo );

			 empleadoinfo.setFechaNacimiento(fechaNacimientoXmlGregorian);
			 empleadoinfo.setFechaVinculacionCompania(fechaVinculacionCompaniaXmlGregorian);
			estado.setCodigo(200);
			estado.setMensaje(Constante.MENSAJE_EXITO);
			respuesta.setEstadoServicio(estado);
			respuesta.setEmpleadoInfo(empleadoinfo);
			return respuesta;
		}
		estado.setCodigo(500);
		estado.setMensaje(Constante.MENSAJE_ERROR);
		return respuesta;
	}

	@PayloadRoot(namespace = Constante.NAME_SPACE, localPart = "actualizarEmpleadoRequest")
	@ResponsePayload
	public ActualizarEmpleadoResponse actualizarEmpleado(@RequestPayload ActualizarEmpleadoRequest request) {
		ActualizarEmpleadoResponse respuesta = new ActualizarEmpleadoResponse();
		Empleado empleado = new Empleado();
		BeanUtils.copyProperties(request.getEmpleadoInfo(),empleado);
		boolean empleadoActualizado = serviceEmpleado.actualizarEmpleado(empleado);
		EstadoServicio estado = new EstadoServicio();
		if(empleadoActualizado) {
			estado.setCodigo(200);
			estado.setMensaje(Constante.MENSAJE_EXITO);
			  respuesta.setEstadoServicio(estado);
			  return respuesta;
		}  
		 estado.setCodigo(500);
		 estado.setMensaje(Constante.MENSAJE_ERROR);
		 respuesta.setEstadoServicio(estado);
		return respuesta;
	}

	@PayloadRoot(namespace = Constante.NAME_SPACE, localPart = "eliminarEmpleadoRequest")
	@ResponsePayload
	public EliminarEmpleadoResponse eliminarEmpleado(@RequestPayload EliminarEmpleadoRequest request) {
		EliminarEmpleadoResponse respuesta = new EliminarEmpleadoResponse();
		EstadoServicio estado = new EstadoServicio();
		boolean empleadoEliminado = serviceEmpleado.eliminarEmpleado(request.getId());
		if(empleadoEliminado) {
			estado.setCodigo(200);
			estado.setMensaje(Constante.MENSAJE_ERROR);
			respuesta.setEstadoServicio(estado);
			return respuesta;
		}
		estado.setCodigo(500);
		estado.setMensaje(Constante.MENSAJE_ERROR);
		respuesta.setEstadoServicio(estado);
		return respuesta;
	}

	@PayloadRoot(namespace = Constante.NAME_SPACE, localPart = "getEmpleadosRequest")
	@ResponsePayload
	public GetEmpleadosResponse obtenerEmpleados() {
		GetEmpleadosResponse respuesta = new GetEmpleadosResponse();
		List<EmpleadoInfo> listaEmpleados = new ArrayList<>();
		List<Empleado> empleados = serviceEmpleado.obtenerEmpleados();
		
		if(!empleados.isEmpty()) {
			for(int i=0 ; i < empleados.size(); i++ ) {
				EmpleadoInfo empleado = new EmpleadoInfo();
				BeanUtils.copyProperties(empleados.get(i),empleado );
				listaEmpleados.add(empleado);
			}
			respuesta.getEmpleadoInfo().addAll(listaEmpleados);
			return respuesta;
		}
		 respuesta.getEmpleadoInfo().addAll(listaEmpleados);
		return respuesta;
	}

	@PayloadRoot(namespace = Constante.NAME_SPACE, localPart = "getEmpleadoPorIdRequest")
	@ResponsePayload
	public GetEmpleadoPorIdResponse obtenerEmpleadoPorId(@RequestPayload GetEmpleadoPorIdRequest request) {
		GetEmpleadoPorIdResponse respuesta = new GetEmpleadoPorIdResponse();
		Empleado empleado = serviceEmpleado.obtenerEmpleadoPorId(request.getIdEmpleado());
		EstadoServicio estado = new EstadoServicio();
		if(empleado != null) {
			EmpleadoInfo empleadoInfo = new EmpleadoInfo();
			BeanUtils.copyProperties(empleado, empleadoInfo);
			estado.setCodigo(200);
			estado.setMensaje(Constante.MENSAJE_EXITO);
			respuesta.setEmpleadoInfo(empleadoInfo);
			respuesta.setEstadoServicio(estado);
			return respuesta;
		}
		estado.setCodigo(500);
		estado.setMensaje(Constante.MENSAJE_ERROR);
		
		return respuesta;
	}
}
