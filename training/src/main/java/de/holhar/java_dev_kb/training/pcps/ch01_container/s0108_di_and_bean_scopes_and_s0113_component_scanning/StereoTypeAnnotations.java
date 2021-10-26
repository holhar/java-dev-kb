package de.holhar.java_dev_kb.training.pcps.ch01_container.s0108_di_and_bean_scopes_and_s0113_component_scanning;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/*
 * Stereotype annotations indicate information about the role the class fulfills as Spring beans
 */
@Component // root stereotype annotation - indicate that the class is a candidate for auto-detection
@Controller // indicates that the class is a web controller
@RestController // indicates that the class is a REST web controller (combines @Controller and @ResponseBody)
@Repository // indicates that the class is a persistence repository
@Service // indicates that the class is a service
@Configuration // indicates that the class contains Spring Java configuration (i.e. methods annotated with @Bean)
public class StereoTypeAnnotations {
}
