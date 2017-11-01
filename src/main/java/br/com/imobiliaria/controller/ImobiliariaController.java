package br.com.imobiliaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.imobiliaria.model.Imobiliaria;
import br.com.imobiliaria.service.ImobiliariaService;
import br.com.imobiliaria.util.ImobiliariaUtil;

@RestController
@RequestMapping("/imobiliarias")
@SuppressWarnings("rawtypes")
public class ImobiliariaController {
	
	@Autowired private ImobiliariaService service;
	@Autowired private ImobiliariaUtil util;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity insereImobiliaria(@Validated @RequestBody Imobiliaria imobiliaria, BindingResult result, 
			UriComponentsBuilder builder) {
		if (result.hasErrors()) {
			return util.trataResponseEntityErrors("inserir imobiliária", HttpStatus.BAD_REQUEST, result);			
		}
		service.insereImobiliaria(imobiliaria);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/imobiliarias/{imobiliariaId}").buildAndExpand(imobiliaria.getId()).toUri());
		return  ResponseEntity
					.status(HttpStatus.CREATED)
					.body("Imobiliária inserida com sucesso!");
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity removeImobiliaria(@RequestBody Imobiliaria imobiliaria) {
		service.removeImobiliaria(imobiliaria);
		return ResponseEntity.status(HttpStatus.OK).body("Imobiliária removida com sucesso!");
	}
	
	@RequestMapping(value = "/{imobiliariaId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity buscaImobiliaria(@PathVariable("imobiliariaId") int imobiliariaId) {
		Imobiliaria imobiliaria = service.buscaImobiliaria(imobiliariaId);
		if (imobiliaria == null) {
			return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("Nenhuma imobiliária foi encontrada com o código: " + imobiliariaId);
		}
		return new ResponseEntity<Imobiliaria>(imobiliaria, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity list() {
		return new ResponseEntity<List<Imobiliaria>>(service.buscaImobiliarias(), HttpStatus.OK);
	}

}