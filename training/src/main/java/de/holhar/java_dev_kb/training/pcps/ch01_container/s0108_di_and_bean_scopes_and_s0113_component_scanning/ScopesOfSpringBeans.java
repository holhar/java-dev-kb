package de.holhar.java_dev_kb.training.pcps.ch01_container.s0108_di_and_bean_scopes_and_s0113_component_scanning;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

// Q1.8:
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // <- a single bean instance is created per Spring container (DEFAULT)
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // <- every time the bean is requested, a new instance is created
// The following scopes are only available for web-aware Spring application contexts
// @Scope(WebApplicationContext.SCOPE_REQUEST) // <- single bean instance per HTTP request
// @Scope(WebApplicationContext.SCOPE_SESSION) // <- single bean instance per HTTP session
// @Scope(WebApplicationContext.SCOPE_APPLICATION) // <- single bean instance per ServletContext
// @Scope("websocket") // <- single bean instance per WebSocket
public class ScopesOfSpringBeans {
}
