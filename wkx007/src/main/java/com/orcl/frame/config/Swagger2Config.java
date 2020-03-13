package com.orcl.frame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by weikaixiang
 * @date 2019/8/1 0001
 * @DESC:
 */
@Component
@Configuration
@EnableSwagger2
public class Swagger2Config {
    private List<Parameter> globalOperation() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        //第一个token为传参的key，第二个token为swagger页面显示的值
        tokenPar.name("Token").description("Token").modelRef(new ModelRef("String")).parameterType("head")
                .required(false).defaultValue("kjshbammma").build();
        pars.add(tokenPar.build());
        return pars;
    }
        @Bean
        public Docket petApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.orcl.frame.controller")) //指定提供接口所在的基包
                    .paths(PathSelectors.any())
                    .build()
                    .globalOperationParameters(globalOperation());
        }

        /**
         * 该套 API 说明，包含作者、简介、版本、host、服务URL
         * @return
         */
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("demo api 说明")
                    .description("demo api")
                    .contact("原创")
                    .version("1.0")
                    .build();
        }


}
