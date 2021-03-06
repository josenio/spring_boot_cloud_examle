package spring.microservice.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

    @Bean
    public Docket newsApi() {
	    //Swagger UI: http://localhost:8080/swagger-ui.html
        //Swagger JSON: http://localhost:8080/v2/api-docs?group=demo_group
	    ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Spring REST Sample with Swagger")
                .description("Spring REST Sample with Swagger")
                .contact(new Contact("Contact Name",
                        "site", "email"))
                .license("Apache License Version 2.0")
                .version("1.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo_group")
                .apiInfo(apiInfo)
                .select()
                .paths(regex("/demo.*"))
                .build();
    }

}
