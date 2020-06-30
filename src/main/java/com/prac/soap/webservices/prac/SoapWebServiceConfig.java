package com.prac.soap.webservices.prac;

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

@Configuration
@EnableWs
public class SoapWebServiceConfig {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(applicationContext);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}
	
	@Bean
	public XsdSchema xsdSchema() {
		return new SimpleXsdSchema(new ClassPathResource("Course_Details.xsd"));
	}
	
	@Bean(name = "Course_Details")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("CoursePort");
		definition.setTargetNamespace("http://soapcoursemanagement.com/coursedetails");
		definition.setLocationUri("/ws");
		definition.setSchema(schema);
		
		return definition;
	}
}
