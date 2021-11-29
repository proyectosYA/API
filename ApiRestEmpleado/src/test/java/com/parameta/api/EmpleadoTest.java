package com.parameta.api;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.parameta.api.dominio.dto.EmpleadoAgregadoDto;
import com.parameta.api.dominio.dto.EmpleadoDto;
import com.parameta.api.dominio.service.ClienteSoap;

@RunWith(MockitoJUnitRunner.class)
class EmpleadoTest {

	private EmpleadoDto empleado;

	private Log log;

	private Date fechaNac;

	private ClienteSoap clienteMock;

	@BeforeEach
	void setUp() throws Exception {

		clienteMock = mock(ClienteSoap.class);
		log = LogFactory.getLog(getClass());

		String dateEnStringFN = "15-02-1988";
		String dateEnStringFV = "19-08-2012";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		fechaNac = formatter.parse(dateEnStringFN);
		Date fechaVinculacion = formatter.parse(dateEnStringFV);

		empleado = new EmpleadoDto();
		empleado.setNombres("maria");
		empleado.setApellidos("caldas");
		empleado.setTipoDocumento("cedula");
		empleado.setNumeroDocumento("123456787");
		empleado.setFechaNacimiento(fechaNac);
		empleado.setFechaVinculacionCompania(fechaVinculacion);
		empleado.setCargo("Gerente general");
		empleado.setSalario(5000000);

	}

	  @Test
	void agregarEmpleadoTest() {

		EmpleadoAgregadoDto empleadoDto = new EmpleadoAgregadoDto();
		empleadoDto.setNombres("maria");
		empleadoDto.setApellidos("caldas");
		empleadoDto.setTipoDocumento("cedula");
		empleadoDto.setNumeroDocumento("123456787");
		empleadoDto.setFechaNacimiento("1974/02/15");
		empleadoDto.setFechaVinculacionCompania("2020/05/18");
		empleadoDto.setCargo("Gerente general");
		empleadoDto.setSalario("5000000");
		empleadoDto.setEdadActualEmpleado("33 años, 9 meses, 10 dias");
		empleadoDto.setFechaVinculacionCompania("1 año, 6 meses, 23 dias");

		//arrange
		when(clienteMock.agregarEmpleado(empleado)).thenReturn(empleadoDto);

		//act
		EmpleadoAgregadoDto clienteSoap = clienteMock.agregarEmpleado(empleado);

		//assert
		assertNotNull(clienteSoap);
		log.info("EMPLEADO: " + clienteSoap.getNombres());
	}

	@Test
	void validarEdadEmpleadoTest() {

		// arrange
		when(clienteMock.validarEdadEmpleado(fechaNac)).thenReturn(12);

		// act
		int edad = clienteMock.validarEdadEmpleado(fechaNac);
		boolean menorDeEdad = edad < 18 ? true : false;

		// assert
		assertNotNull(edad);
		assertTrue(menorDeEdad);
		assertFalse(!menorDeEdad);
		log.info("EDAD: " + fechaNac);

	}

	@Test
	void validarEdadEmpleadoFalloTest() {
		// arrange
		when(clienteMock.validarEdadEmpleado(null)).thenReturn(0);

		// act
		int edad = clienteMock.validarEdadEmpleado(fechaNac);

		// act
		assertEquals(0, edad);
		// fail("Se esperaba excepcion Exception");

	}
}
