/*
 * Caisse Enregistreuse App - API REST
 * COded By Alias King - Younes OUFRID !!
 * Mail : oufridyounes@gmail.com
 * MNS team coders
 * */

package com.lillygourmet.cash.register.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // Swagger configuration without JWT Token Authentification
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.lillygourmet.cash.register"))
                    .paths(regex("/api.*"))
                    .build();
        }



        // Swagger Configuration with JWT Token Authentification
/*
    @Configuration
    @EnableSwagger2
    @Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
    public class SwaggerConfiguration {

        public static final String AUTHORIZATION_HEADER = "Authorization";
        public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
        private final Logger log =  LoggerFactory.getLogger(SwaggerConfiguration.class);

        @Bean
        public Docket swaggerSpringfoxDocket() {
            log.debug("Starting Swagger");
            Contact contact = new Contact(
                    "Matyas Albert-Nagy",
                    "https://justrocket.de",
                    "matyas@justrocket.de");

            List<VendorExtension> vext = new ArrayList<>();
            ApiInfo apiInfo = new ApiInfo(
                    "Backend API",
                    "This is the best stuff since sliced bread - API",
                    "6.6.6",
                    "https://justrocket.de",
                    contact,
                    "MIT",
                    "https://justrocket.de",
                    vext);

            Docket docket = new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo)
                    .pathMapping("/")
                    .apiInfo(ApiInfo.DEFAULT)
                    .forCodeGeneration(true)
                    .genericModelSubstitutes(ResponseEntity.class)
                    .ignoredParameterTypes(Pageable.class)
                    .ignoredParameterTypes(java.sql.Date.class)
                    .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                    .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                    .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                    .securityContexts(Lists.newArrayList(Collections.singleton(securityContext())))
                    .securitySchemes(Lists.newArrayList(Collections.singleton(apiKey())))
                    .useDefaultResponseMessages(false);

            docket = docket.select()
                    .paths(regex(DEFAULT_INCLUDE_PATTERN))
                    .build();
            StopWatch watch = null;
            watch.stop();
            log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
            return docket;
        }


        private ApiKey apiKey() {
            return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
        }

        private SecurityContext securityContext() {
            return SecurityContext.builder()
                    .securityReferences(defaultAuth())
                    .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                    .build();
        }

        List<SecurityReference> defaultAuth() {
            AuthorizationScope authorizationScope
                    = new AuthorizationScope("global", "accessEverything");
            AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
            authorizationScopes[0] = authorizationScope;
            return Lists.newArrayList(
                    new SecurityReference("JWT", authorizationScopes));
        }
    }
*/

}
