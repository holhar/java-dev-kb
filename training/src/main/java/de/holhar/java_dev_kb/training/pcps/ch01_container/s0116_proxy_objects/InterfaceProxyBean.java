package de.holhar.java_dev_kb.training.pcps.ch01_container.s0116_proxy_objects;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Specify Prototype-scoped bean with an interface-based proxy class, i.e. the proxy is created by the (required)
 * implemented interface of the class (technology basis are JDK Dynamic Proxies).
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
@Component
public class InterfaceProxyBean implements ProxyBase {
}
