package com.parameta.api.controller;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parameta.api.dominio.dto.EmpleadoAgregadoDto;
import com.parameta.api.dominio.dto.EmpleadoDto;
import com.parameta.api.dominio.dto.MensajeRespuestaDto;
import com.parameta.api.dominio.service.IClienteSoap;
import com.parameta.api.dominio.utilitarios.Constante;

@RestController
@RequestMapping("/")
@Validated
public class EmpleadoController {

	@Autowired
	@Qualifier("ServicioEmpleado")
	private IClienteSoap cliente;

	@GetMapping(value = "empleado", params = { "nombres", "apellidos", "tipoDocumento", "numeroDocumento",
			"fechaNacimiento", "fechaVinculacionCompania", "cargo", "salario" })
	public ResponseEntity<Object> agregarEmpleado(@RequestParam @NotBlank String nombres,
			@RequestParam @NotBlank String apellidos, @RequestParam @NotBlank String tipoDocumento,
			@RequestParam @NotBlank String numeroDocumento,
			@RequestParam("fechaNacimiento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaNacimiento,
			@RequestParam("fechaVinculacionCompania") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaVinculacionCompania,
			@RequestParam @NotBlank String cargo, @RequestParam double salario) {

		MensajeRespuestaDto mensaje = new MensajeRespuestaDto();
		int añosEmpleado = cliente.validarEdadEmpleado(fechaNacimiento);
		if (añosEmpleado < 18 || añosEmpleado == 0) {
			mensaje.setCodigo(403);
			mensaje.setMensaje(Constante.MENOR_DE_EDAD);
			return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
		}

		EmpleadoDto empleado = new EmpleadoDto();
		empleado.setNombres(nombres);
		empleado.setApellidos(apellidos);
		empleado.setTipoDocumento(tipoDocumento);
		empleado.setNumeroDocumento(numeroDocumento);
		empleado.setFechaNacimiento(fechaNacimiento);
		empleado.setFechaVinculacionCompania(fechaVinculacionCompania);
		empleado.setCargo(cargo);
		empleado.setSalario(salario);

		EmpleadoAgregadoDto empleadoAgregado = cliente.agregarEmpleado(empleado);

		if (empleadoAgregado != null) {
			return new ResponseEntity<>(empleadoAgregado, HttpStatus.OK);
		}
		mensaje.setCodigo(500);
		mensaje.setMensaje(Constante.MENSAJE);
		return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
