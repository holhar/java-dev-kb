package de.holhar.java_dev_kb.training.pcps.ch07_security;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
@EnableWebSecurity
public class SecurityConfig {
}
