package de.holhar.java_dev_kb.training.pcps.ch05_web_mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Q5.2:
 * To map incoming requests to a controller and its methods the following steps need to be applied:
 * - enable component scanning, see {@link ComponentScan} (enables auto-detection of @Controller-annotated classes)
 * - annotate one of the configuration classes of the application with {@link EnableWebMvc} (for Spring Boot
 *   applications it's sufficient to have one configuration class that implements {@link WebMvcConfigurer})
 * - implement a controller class with {@link Controller} (see {@link WebMvcController})
 * - implement at least on method of the controller class with {@link RequestMapping} (see {@link WebMvcController#index(Model)})
 *
 * When a request is issued to the application:
 * - the/a DispatcherServlet of the application receives the request
 * - the/a DispatcherServlet maps the request to a method in a controller (the DispatcherServlet holds a list of
 *   classes implementing the HandlerMapping interface)
 * - the/a DispatcherServlet dispatches the request to the controller
 * - the method in the controller is executed
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "de.holhar.java_dev_kb.training.pcps.ch05_web_mvc")
public class WebMvcConfig implements WebMvcConfigurer {

    /*@Bean
    SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        var resolver = new MissingExceptionResolver();
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return resolver;
    }*/

    // Comment this to implement exception handling with simpleMappingExceptionResolver() above
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    ViewResolver viewResolver(){
        var resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp" );
        resolver.setRequestContextAttribute("requestContext");

        return resolver;
    }

    //Declare our static resources. I added cache to the java config but it?s not required.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/images/").setCachePeriod(31556926);
        registry.addResourceHandler("/styles/**").addResourceLocations("/styles/").setCachePeriod(31556926);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index");
    }
}
