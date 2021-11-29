package com.parameta.api.dominio.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import com.parameta.api.dominio.util.Constante;

@EnableWs
@Configuration
public class ConfigSoapWS {

	@Bean
	public XsdSchema empleadoSchema() {
		return new SimpleXsdSchema(new ClassPathResource("empleado.xsd"));
	}
	
	@Bean(name = "empleados")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema empleadosSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("EmpleadosPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace(Constante.NAME_SPACE);
		wsdl11Definition.setSchema(empleadosSchema);
		return wsdl11Definition;
	}
	
 
	@Bean
	public ServletRegistrationBean mensajeDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet,"/soapws/*");
	}
}
