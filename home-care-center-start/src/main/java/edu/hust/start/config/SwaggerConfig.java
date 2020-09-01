package edu.hust.start.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public Docket createRestApi(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .protocols(Sets.newHashSet("http"))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "edu.hust.start.config.controller"
                ))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("REST接口定义")
                .version("1.0")
                .description("RESTful API")
                .build();

    }

}
