package com.parameta.api.dominio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.parameta.api.dominio.service.ClienteSoap;


@Configuration
public class ConfiguracionSoap {
	
	@Autowired  
	private ClienteSoap clienteSoap;

 
	@Bean 
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.parameta.empleado_ws");
		return marshaller;
	}
	
	@Bean
	public ClienteSoap clienteSoap(Jaxb2Marshaller marshaller) {
		clienteSoap.setMarshaller(marshaller);
		clienteSoap.setUnmarshaller(marshaller);
		return clienteSoap;
	}
}
