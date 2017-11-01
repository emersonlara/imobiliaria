package br.com.imobiliaria.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@SuppressWarnings("rawtypes")
public class ImobiliariaUtil {
	
	public ResponseEntity trataResponseEntityErrors(String operacao, HttpStatus status, BindingResult result) {
		StringBuilder sbError = new StringBuilder();
		sbError.append("Ocorreu um erro ao " + operacao + ": \n");
		result.getAllErrors().forEach(error -> sbError.append("\t- ").append(error.getDefaultMessage()).append("\n"));
		ResponseEntity<String> response = ResponseEntity.status(status).body(sbError.toString());
		sbError.setLength(0);
		return response;
	}

}
