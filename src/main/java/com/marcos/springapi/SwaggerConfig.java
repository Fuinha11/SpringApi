package com.marcos.springapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private List<ResponseMessage> responseMessages()
    {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Erro nos dados da requisição.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Obeto não encontrado.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(409)
                    .message("Conflito com a lógica de negócio.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Erro Interno.")
                    .build());
        }};
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST API with H2 and ActiveMQ")
                .description("Uma aplicação Spring Boot REST API com Banco H2 e filas por ActiveMQ")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Marcos Di Giovanni", "https://github.com/Fuinha11", "marcosdgmarques@gmail.com"))
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.marcos.springapi"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages())
                .globalResponseMessage(RequestMethod.POST, responseMessages())
                .apiInfo(apiInfo());
    }
}