package com.parameta.api.dominio.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.parameta.api.dominio.dto.EmpleadoAgregadoDto;
import com.parameta.api.dominio.dto.EmpleadoDto;
import com.parameta.empleado_ws.AgregarEmpleadoRequest;
import com.parameta.empleado_ws.AgregarEmpleadoResponse;

@Primary
@Service("GuardarEmpleado")
@Qualifier("ServicioEmpleado")
public class ClienteSoap extends WebServiceGatewaySupport implements IClienteSoap {

	@Autowired
	private Environment env;

	private Log log = LogFactory.getLog(getClass());

	@Override
	public EmpleadoAgregadoDto agregarEmpleado(EmpleadoDto empleado) {

		String endpoint = env.getProperty("url.endponit.soap");
		EmpleadoAgregadoDto respuestaDto = new EmpleadoAgregadoDto();
		try {
			XMLGregorianCalendar fechaNacimiento = convertirDateAXmlGregorianCalendar(empleado.getFechaNacimiento());
			XMLGregorianCalendar fechaVinculacionCompania = convertirDateAXmlGregorianCalendar(
					empleado.getFechaVinculacionCompania());

			AgregarEmpleadoRequest request = new AgregarEmpleadoRequest();
			request.setNombres(empleado.getNombres());
			request.setApellidos(empleado.getApellidos());
			request.setNumeroDocumento(empleado.getNumeroDocumento());
			request.setTipoDocumento(empleado.getTipoDocumento());
			request.setCargo(empleado.getCargo());
			request.setSalario(empleado.getSalario());
			request.setFechaNacimiento(fechaNacimiento);
			request.setFechaVinculacionCompania(fechaVinculacionCompania);

			AgregarEmpleadoResponse response = (AgregarEmpleadoResponse) getWebServiceTemplate()
					.marshalSendAndReceive(endpoint, request);

			if (response.getEstadoServicio().getCodigo() == 200) {

				SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

				// TODO Convertir XMLGregorianCalendar a Date
				Date fechaNacimientoAlmacenada = response.getEmpleadoInfo().getFechaNacimiento().toGregorianCalendar()
						.getTime();
				Date fechaVinculacionEmpresaAlmacenada = response.getEmpleadoInfo().getFechaVinculacionCompania()
						.toGregorianCalendar().getTime();

				// TODO fecha tipo date a String
				String fechaNacimientoTexto = formato.format(fechaNacimientoAlmacenada);
				String fechaVinculacionTexto = formato.format(fechaVinculacionEmpresaAlmacenada);

				  
				respuestaDto.setNombres(response.getEmpleadoInfo().getNombres());
				respuestaDto.setApellidos(response.getEmpleadoInfo().getApellidos());
				respuestaDto.setTipoDocumento(response.getEmpleadoInfo().getTipoDocumento());
				respuestaDto.setNumeroDocumento(response.getEmpleadoInfo().getNumeroDocumento());
				respuestaDto.setFechaNacimiento(fechaNacimientoTexto);
				respuestaDto.setFechaVinculacionCompania(fechaVinculacionTexto);
				respuestaDto.setCargo(response.getEmpleadoInfo().getCargo());
				respuestaDto.setSalario(response.getEmpleadoInfo().getSalario());
				respuestaDto.setEdadActualEmpleado(calcularEdadActual(fechaNacimientoAlmacenada));
				respuestaDto.setTiempoVinculacion(calcularTiempoVinculacion(fechaVinculacionEmpresaAlmacenada));

				return respuestaDto;
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return respuestaDto;
	}

	private XMLGregorianCalendar convertirDateAXmlGregorianCalendar(Date fecha) {
		XMLGregorianCalendar fechaGregorianCalendar = null;
		GregorianCalendar calendario = new GregorianCalendar();
		calendario.setTime(fecha);
		try {
			fechaGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendario);
			return fechaGregorianCalendar;
		} catch (DatatypeConfigurationException e) {
			log.info("LOG ERROR: " + e.getMessage());

			return fechaGregorianCalendar;
		}

	}

	private String calcularEdadActual(Date fecha) {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaNacimiento = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		int años = fechaActual.getYear() - fechaNacimiento.getYear();
		int meses = fechaActual.getMonthValue() - fechaNacimiento.getMonthValue();
		int dias = fechaActual.getDayOfMonth() - fechaNacimiento.getDayOfMonth();

		String edadActual = String.format("%d años  %d meses  %d dias", años, meses, dias);
		return edadActual;
	}

	private String calcularTiempoVinculacion(Date fecha) {

		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaVinculacionCompania = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		int años = fechaActual.getYear() - fechaVinculacionCompania.getYear();
		int meses = fechaActual.getMonthValue() - fechaVinculacionCompania.getMonthValue();
		int dias = fechaActual.getDayOfMonth() - fechaVinculacionCompania.getDayOfMonth();

		String tiempEmpleadoEnCompania = String.format("%d años  %d meses  %d dias", años, meses, dias);
		return tiempEmpleadoEnCompania;
	}
	
	@Override
	public int validarEdadEmpleado(Date fechaNac) {
		try {
			LocalDate fechaActual = LocalDate.now();
			LocalDate fechaNacimineto = fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int años = fechaActual.getYear() - fechaNacimineto.getYear();
			return años;
		} catch (Exception e) {
			Log log = LogFactory.getLog(getClass());
			log.info(e.getMessage());
			 return 0;
		}
		
	}
}
