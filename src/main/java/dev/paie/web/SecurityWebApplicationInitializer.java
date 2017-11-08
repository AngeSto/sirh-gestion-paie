package dev.paie.web;

import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{
	//A mettre si on a pas servletContext.addListener(new ContextLoaderListener(webContext)); dans le WebAppInitializer
	/*public SecurityWebApplicationInitializer() {
		super(SecurityConfig.class);
		}*/
}
