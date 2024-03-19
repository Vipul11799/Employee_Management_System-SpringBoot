package com.qsp.employee_management_system.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
@EnableSwagger2
public class ApplicationConfig {
	//url for swagger: http://localhost:8080/swagger-ui.html#
	@Bean // use to retrive data from thrird party it is mandetory annotation
	public Docket getDocket() {
		Contact contact = new Contact("vipul", "www.vipul.com", "vipul@gmail.com");
		List<VendorExtension> extensions = new ArrayList<VendorExtension>();
		ApiInfo apiInfo = new ApiInfo("EmployeeManagementSystem", "This apllication is use to manage the Employees",
				"Version 1.", "www.ems.com", contact, "QSP001", "www.qsplicence.com", extensions);
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.qsp.employee_management_system")).build()
				.apiInfo(apiInfo).useDefaultResponseMessages(false);
	}
}
