package com.zk.sfservice.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket defaultApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	    		.apiInfo(apiInfo())
	    		.useDefaultResponseMessages(false)
	    		.forCodeGeneration(false)
	    		.groupName("DEFAULT")
	    		.select()
				.apis(RequestHandlerSelectors.basePackage("com.zk.sfservice.controller"))
				.build();
	}
	
	@Bean
	public Docket umapiApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	    		.apiInfo(apiInfo())
	    		.useDefaultResponseMessages(false)
	    		.forCodeGeneration(false)
	    		.groupName("UMAPI")
	    		.select()
				.apis(RequestHandlerSelectors.basePackage("com.picc.umapi.controller"))
				.build();
	}
	
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("")
				.version("1.0")
				.contact(new Contact("picc", "", null))
				.build();
	}
	
	@Bean
	UiConfiguration uiConfig() {
	    return UiConfigurationBuilder.builder()
		        .deepLinking(true)
		        .displayOperationId(false)
		        .defaultModelsExpandDepth(1)
		        .defaultModelExpandDepth(1)
		        .defaultModelRendering(ModelRendering.EXAMPLE)
		        .displayRequestDuration(false)
		        .docExpansion(DocExpansion.NONE)
		        .filter(false)
		        .maxDisplayedTags(null)
		        .operationsSorter(OperationsSorter.ALPHA)
		        .showExtensions(false)
		        .tagsSorter(TagsSorter.ALPHA)
		        .validatorUrl(null)
		        .build();
	  }
}
