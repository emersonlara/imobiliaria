package br.com.imobiliaria.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Override
	public String getAuthority() {
		return "ROLE_ADMINISTRADOR";
	}

}