package de.holhar.java_dev_kb.training.pcps.ch07_security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private static final Logger logger = LoggerFactory.getLogger(AnimalController.class);

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/user/{id}")
    public Animal getAnimal(@PathVariable("id") long id) {
        logPrincipalUser();
        return animalService.getAnimal(id);
    }

    @GetMapping("/user")
    public List<Animal> getAnimals() {
        logPrincipalUser();
        return animalService.getAnimals();
    }

    // curl --user admin:pass -X POST --data '{"type":"fish", "name":"Nemo", "weight":0.12}' -H"Content-Type:application/json" "http://localhost:8080/animals/admin"
    @PostMapping("/admin")
    public void putAnimal(@RequestBody Animal animal) {
        logPrincipalUser();
        animalService.addAnimal(animal);
    }

    /**
     * Q7.9:
     * Spring Security also has support for security on method level with which security constraints can be applied
     * to individual methods in Spring beans. Security on the method level needs to be explicitly enabled using the
     * {@link EnableGlobalMethodSecurity} annotation in regular Spring applications or the
     * {@link EnableReactiveMethodSecurity} annotation in reactive Spring applications.
     *
     * Method security is an additional level of security in web applications but can also be the only layer
     * of security in applications that do not expose a web interface.
     *
     * Method-level security is commonly applied to services in the service layer of an application.
     *
     * Q7.10:
     * The {@link PreAuthorize} and {@link RolesAllowed} annotations are annotations with which method security can be
     * configured either on individual methods or on class level. In the latter case the security
     * constraints will be applied to all methods in the class.
     *
     * @PreAuthorize:
     * --------------
     * The @PreAuthorize annotation allows for specifying access constraints to a method using the Spring Expression
     * Language (SpEL). These constraints are evaluated prior to the method being executed and may result in execution of
     * the method being denied if the constraints are not fulfilled. The @PreAuthorize annotation is part of the Spring
     * Security framework.
     * In order to be able to use @PreAuthorize, the prePostEnabled attribute in the @EnableGlobalMethodSecurity
     * annotation needs to be set to true: @EnableGlobalMethodSecurity(prePostEnabled=true)
     *
     * @RolesAllowed:
     * --------------
     * The @RolesAllowed annotation has its origin in the JSR-250 Java security standard. This annotation is more limited
     * than the @PreAuthorize annotation in that it only supports role-based security.
     * In order to use the @RolesAllowed annotation the library containing this annotation needs to be on the classpath,
     * as it is not part of Spring Security. In addition, the jsr250Enabled attribute of the @EnableGlobalMethodSecurity
     * annotation need to be set to true: @EnableGlobalMethodSecurity(jsr250Enabled=true)
     *
     * What does Spring’s @Secured do?:
     * --------------------------------
     * The {@link Secured} annotation is a legacy Spring Security 2 annotation that can be used to configure method
     * security. It supports more than only role-based security, but does not support using Spring Expression Language
     * (SpEL) to specify security constraints. It is recommended to use the @PreAuthorize annotation in new applications
     * over this annotation.
     * Support for the @Secured annotation needs to be explicitly enabled in the @EnableGlobalMethodSecurity annotation
     * using the securedEnabled attribute. @EnableGlobalMethodSecurity(securedEnabled=true) -> see {@link SecurityConfig}
     *
     * Q7.11:
     * Method-level security is accomplished using Spring AOP proxies
     *
     * Q7.12:
     * The following table shows the support for Spring Expression Language in the security annotations that can be used
     * with Spring Security 5:
     * Security Annotation  => Has SpEL Support?
     * @PreAuthorize        => yes
     * @PostAuthorize       => yes
     * @PreFilter           => yes
     * @PostFilter          => yes
     * @Secured             => no
     * @RolesAllowed        => no
     *
     * POI: https://www.baeldung.com/spring-security-method-security
     *
     * curl --user superadmin:pass -X DELETE "http://localhost:8080/animals/superadmin/2"
     */
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @DeleteMapping("/superadmin/{id}")
    public void deleteAnimal(@PathVariable("id") long id) {
        logPrincipalUser();
        animalService.delete(id);
    }

    /**
     * Q7.5:
     * An object implementing the {@link SecurityContext} interface is stored in an instance of the
     * {@link SecurityContextHolder}. The SecurityContextHolder class not only keeps a reference to a security context,
     * but it also allows for specifying the strategy used to store the security context:
     * - Thread local; a security context is stored in a thread-local variable and available only to one single thread of execution.
     * - Inheritable thread local; like thread local, but with the addition that child threads created by a thread
     *   containing a thread-local variable holding a reference to a security context, will also have a thread local
     *   variable, containing a reference to the same security context.
     * - Global; a security context is available throughout the application, from any thread.
     *
     * Taking a look at the SecurityContext interface, which defines the minimum security information associated with a
     * thread of execution, there are two methods; one for setting and one for retrieving an object that implements the
     * {@link Authentication} interface.
     *
     * The Authentication interface defines the properties of an object that represents a security token for:
     * - An authentication request; this is the case prior to a user having been authenticated, when a user tries to log in
     * - An authenticated principal; after a user has been authenticated by an {@link AuthenticationManager}
     *
     * The basic properties contained in an object implementing the Authentication interface are:
     *
     * - A {@link GrantedAuthority} collection for the principal
     * - The credentials used to authenticate a user; this can be a login name and a password that has been verified to match
     * - Details; additional information, may be application specific or null if not used.
     * - Principal
     * - Authenticated flag; a boolean indicating whether the principal has been successfully authenticated.
     *
     * There are a number of different implementations of the Authentication interface that can be used with different
     * authentication schemes – please refer to the API documentation for further details.
     */
    private void logPrincipalUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User) {
            final String name = ((User) authentication.getPrincipal()).getUsername();
            logger.info("Name of logged in user is '{}'", name);
        }
    }
}
