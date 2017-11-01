package br.com.imobiliaria.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.imobiliaria.orm.ImobiliariaORM;
import br.com.imobiliaria.security.SecurityConfiguration;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { 
			AppWebConfiguration.class,
			SecurityConfiguration.class,
			ImobiliariaORM.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
