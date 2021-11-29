package com.parameta.api.dominio.excepcion;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ManejadorExcepcion extends ResponseEntityExceptionHandler {
 
	@ExceptionHandler({ ConstraintViolationException.class}) 
	public final ResponseEntity<ApiExcepcion> manejadorExcepciones(ConstraintViolationException exception) {
 
		String excepcionNombre = exception.getClass().getSimpleName();
		String mensaje = exception.getMessage();
		Integer codigo = 400;
 
		ApiExcepcion error = new ApiExcepcion(excepcionNombre, mensaje);
		return new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
	}
}
