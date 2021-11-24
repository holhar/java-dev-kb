package de.holhar.java_dev_kb.training.pcps.ch07_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Q7.1:
 * Authentication:
 * ---------------
 * The short explanation of authentication is that it is the process of verifying that, for instance, a user of a
 * computer system is who he/she claims to be. Authentication is the process of confirming that some piece of
 * information is true.
 *
 * In Spring Security, the authentication process consists of the following steps quoted from the Spring
 * Security reference:
 * - The username and password are obtained and combined into an instance of
 *   {@link UsernamePasswordAuthenticationToken} (an instance of the Authentication interface).
 * - The token is passed to an instance of {@link AuthenticationManager} for validation.
 * - The AuthenticationManager returns a fully populated {@link Authentication} instance on successful authentication.
 * - The security context is established by calling SecurityContextHolder.getContext().setAuthentication(…), passing
 *   in the returned authentication object.
 *
 * Authorization:
 * --------------
 * The short explanation of authorization is the process of determining that a user of a computer system is permitted
 * to do something that the user is attempting to do. Authorization is the process of specifying access rights to
 * resources.
 *
 * Q7.2:
 * Security is a cross-cutting concern. Security is a function of an application that is not immediately associated
 * with the business logic of the application – it is a secondary or supporting function. In addition, security may
 * be required for many different areas of functionality of an application and thus does not fit well in software
 * development primarily concerned with decomposition (of an application) by functionality.
 *
 * How is security implemented internally in Spring Security?
 * Spring Security is implemented in the following two ways depending on what is to be secured:
 * - Using a Spring AOP proxy that inherits from the {@link AbstractSecurityInterceptor} class. Applied to method
 *   invocation authorization on objects secured with Spring Security.
 * - Spring Security’s web infrastructure is based entirely on servlet filters.
 *
 * Core Components:
 * - SecurityContextHolder - Contains and provides access to the SecurityContext of the application. Default behavior
 *                           is to associate the SecurityContext with the current thread.
 * SecurityContext - Default and only implementation in Spring Security holds an Authentication object. May also hold
 *                   additional request-specific information.
 * Authentication - Represents token for authentication request or authenticated principal after the request has been
 *                  granted. Also contains the authorities in the application that an authenticated principal has been granted.
 * GrantedAuthority - Represents an authority granted to an authenticated principal.
 * UserDetails - Holds user information, such as user-name, password and authorities of the user. This information is
 *               used to create an Authentication object on successful authentication. May be extended to contain
 *               application-specific user information.
 * UserDetailsService - Given a user-name this service retrieves information about the user in a UserDetails object.
 *                      Depending on the implementation of the user details service used, the information may be
 *                      stored in a database, in memory or elsewhere if a custom implementation is used.
 *
 * Q7.3:
 * The {@link DelegatingFilterProxy} class implements the javax.servlet.Filter interface and thus is a servlet filter.
 * One delegating filter proxy delegates to one Spring bean that is required to implement the javax.servlet.Filter
 * interface. This mechanism allows for defining servlet filters in the web.xml (for the Servlet 2 standard) which
 * later looks up a named bean from the Spring application context and delegates filtering to the Spring bean.
 *
 * The Spring bean to which a delegating filter proxy delegates to, will, as any Spring bean, have its lifecycle
 * handled by the Spring container. The servlet filter lifecycle methods init and destroy will by default not be
 * called. The delegating filter proxy can be configured to invoke these methods on the Spring bean when the
 * corresponding method is called on the delegating filter proxy by setting the targetFilterLifecycle property to true.
 *
 * Q7.4:
 * The {@link SecurityFilterChain} associates a request URL pattern with a list of (security) filters. The security
 * filter chain implements the SecurityFilterChain interface and the only implementation provided by Spring Security
 * is the {@link DefaultSecurityFilterChain} class. There are two parts to a security filter chain; the request
 * matcher and the filters. The request matcher determines whether the filters in the chain are to be applied to a
 * request or not.
 *
 * The order in which security filter chains are declared is significant, since the first filter chain which has a
 * request URL pattern which matches the current request will be used. Thus, a security filter chain with a more
 * specific URL pattern should be declared before a security filter chain with a more general URL pattern.
 *
 * Request Matcher:
 * ----------------
 * There are a number of different request matchers which all implement the RequestMatcher interface with perhaps the
 * two most common ones being {@link MvcRequestMatcher} and {@link AntPathRequestMatcher}. An example for a
 * MvcRequestMatcher is to configure it with the URL pattern "/**", which will match requests to the application with
 * any URL. For example, "http://localhost:8080/myapp/index.html" will be matched and so will
 * "http://localhost:8080/myapp/services/userservice/", assuming the root application URL is
 * "http://localhost:8080/myapp".
 *
 * Filters:
 * --------
 * The constructor of the DefaultSecurityFilterChain class takes a variable number of parameters, the first always
 * being a request matcher. The remaining parameters are all filters which implements the javax.servlet.Filter
 * interface. The order of the filters in a security filter chain is important – filters must be declared in the
 * following order (filters may be omitted if not needed):
 * - ChannelProcessingFilter
 * - SecurityContextPersistenceFilter
 * - ConcurrentSessionFilter
 * - Any authentication filter (e.g. UsernamePasswordAuthenticationFilter, CasAuthenticationFilter, BasicAuthenticationFilter)
 * - SecurityContextHolderAwareRequestFilter
 * - JaasApiIntegrationFilter
 * - RememberMeAuthenticationFilter
 * - AnonymousAuthenticationFilter
 * - ExceptionTranslationFilter
 * - FilterSecurityInterceptor
 */
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Q7.8:
     * Password Hashing:
     * -----------------
     * Password hashing is the process of calculating a hash-value for a password. The hash-value is stored, for instance
     * in a database, instead of storing the password itself. Later when a user attempts to log in, a hash-value is
     * calculated for the password supplied by the user and compared to the stored hash-value. If the hash-values do  not
     * match, the user has not supplied the correct password.
     *
     * In Spring Security, this process is referred to as password encoding and is implemented using the
     * {@link PasswordEncoder} interface.
     *
     * Salting:
     * --------
     * A salt used when calculating the hash-value for a password is a sequence of random bytes that are used in
     * combination with the cleartext password to calculate a hash-value. The salt is stored in
     * cleartext alongside the password hash-value and can later be used when calculating hash-values for
     * user-supplied passwords at login.
     *
     * The reason for salting is to avoid always having the same hash-value for a certain word, which would make it
     * easier to guess passwords using a dictionary of hash-values and their corresponding passwords.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user")
                .password(passwordEncoder.encode("pass"))
                .roles("USER");

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("admin")
                .password(passwordEncoder.encode("pass"))
                .roles("ADMIN");

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("superadmin")
                .password(passwordEncoder.encode("pass"))
                .roles("SUPERADMIN");
    }

    /**
     * Q7.6:
     * There are two wildcards that can be used in URL patterns:
     * - *
     * Matches any path on the level at which the wildcard occurs.
     * Example: /services/* matches /services/users and /services/orders but not
     * /services/orders/123/items.
     *
     * - **
     * Matches any path on the level that the wildcard occurs and all levels below.
     * If only /** or ** then will match any request.
     * Example: /services/** matches /
     *
     * Q7.7:
     * As an example antMatchers("/services") only matches the exact "/services" URL while mvcMatchers("/services")
     * matches "/services" but also "/services/", "/services.html" and "/services.abc". Thus, the mvcMatcher matches more
     * than the antMatcher and is more forgiving as far as configuration mistakes are concerned. In addition, the
     * mvcMatchers API uses the same matching rules as used by the @RequestMapping annotation. Finally, the mvcMatchers
     * API is newer than the antMatchers API.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                    .mvcMatchers("/user/**").hasRole("USER")
                    .antMatchers("/admin/*").hasRole("ADMIN") // <= prefer mvcMatchers (see explanation above)
                .and()
                .httpBasic(withDefaults())
                .csrf().disable();
    }
}
