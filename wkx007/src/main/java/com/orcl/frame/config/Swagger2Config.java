package com.orcl.frame.config;

import io.swagger.models.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author by weikaixiang
 * @date 2019/8/1 0001
 * @DESC:
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
        @Bean
        public Docket petApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.orcl.frame.controller")) //指定提供接口所在的基包
                    .build();
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
