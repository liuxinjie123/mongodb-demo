package com.mongodb.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan("com.mongodb.demo.controller")
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    protected ObjectMapper objectMapper;


    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container) -> {
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
        };
    }

//
//    @PostConstruct
//    private void jacksonConfig() {
//        objectMapper.registerModule(new Java8TimeModule());
//    }

//    @Bean(name = "validator")
//    public Validator createBeanValidator() {
//        return new LocalValidatorFactoryBean();
//    }


}
