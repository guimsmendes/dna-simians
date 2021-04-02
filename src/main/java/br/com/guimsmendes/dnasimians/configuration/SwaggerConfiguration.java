package br.com.guimsmendes.dnasimians.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket produceApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.guimsmendes.dnasimians.entrypoint.controller")).build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("Dna Simians").description(
                "This API REST has the goal to implement a service to check, for a given Dna Sequence, if the Dna Type is a Human or a Simian Dna. It also implements a check on the Dna Database to outcome the Ratio between the two types.")
                .contact(new Contact("Guilherme Mendes", "https://github.com/guimsmendes", "guimsmendes@gmail.com"))
                .build();
    }
}
