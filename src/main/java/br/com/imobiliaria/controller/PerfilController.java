package br.com.imobiliaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.imobiliaria.model.Perfil;
import br.com.imobiliaria.service.ImobiliariaService;
import br.com.imobiliaria.util.ImobiliariaUtil;

@RestController
@RequestMapping("/perfis")
@SuppressWarnings("rawtypes")
public class PerfilController {
	
	@Autowired private ImobiliariaService service;
	@Autowired private ImobiliariaUtil util;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity inserePerfil(@RequestBody Perfil perfil, BindingResult result, 
			UriComponentsBuilder builder) {
		if (result.hasErrors()) {
			return util.trataResponseEntityErrors("inserir perfil", HttpStatus.BAD_REQUEST, result);			
		}
		service.inserePerfil(perfil);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/perfis/{perfilId}").buildAndExpand(perfil.getId()).toUri());
		return ResponseEntity.status(HttpStatus.CREATED).body("Perfil inserida com sucesso!");
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity removePerfil(@RequestBody Perfil perfil) {
		service.removePerfil(perfil);
		return ResponseEntity.status(HttpStatus.OK).body("Perfil removido com sucesso!");
	}
	
	@RequestMapping(value = "/{perfilId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity buscaPerfil(@PathVariable("perfilId") int perfilId) {
		Perfil perfil = service.buscaPerfil(perfilId);
		if (perfil == null) {
			return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("Nenhum perfil foi encontrado com o código: " + perfilId);
		}
		return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity list() {
		return new ResponseEntity<List<Perfil>>(service.buscaPerfis(), HttpStatus.OK);
	}

}