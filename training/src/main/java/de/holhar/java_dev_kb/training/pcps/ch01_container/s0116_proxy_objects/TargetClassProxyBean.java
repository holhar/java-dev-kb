package de.holhar.java_dev_kb.training.pcps.ch01_container.s0116_proxy_objects;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Q1.16:
 * Specify Prototype-scoped bean with a class-based proxy class, i.e. the proxy derived by the type of the proxied
 * class (technology basis is CGLIB). The class and proxied method may NOT be final!
 */
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class TargetClassProxyBean {
}
