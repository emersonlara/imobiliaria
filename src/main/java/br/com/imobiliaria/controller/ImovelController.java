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

import br.com.imobiliaria.model.Imovel;
import br.com.imobiliaria.service.ImobiliariaService;
import br.com.imobiliaria.util.ImobiliariaUtil;

@RestController
@RequestMapping("/imoveis")
@SuppressWarnings("rawtypes")
public class ImovelController {
	
	@Autowired private ImobiliariaService service;
	@Autowired private ImobiliariaUtil util;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity insereImovel(@Validated @RequestBody Imovel imovel, BindingResult result, 
			UriComponentsBuilder builder) {
		if (result.hasErrors()) {
			return util.trataResponseEntityErrors("inserir imóvel", HttpStatus.BAD_REQUEST, result);			
		}
		service.insereImovel(imovel);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/imoveis/{imovelId}").buildAndExpand(imovel.getId()).toUri());
		return ResponseEntity.status(HttpStatus.CREATED).body("Imóvel inserido com sucesso!");
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity removeImovel(@RequestBody Imovel imovel) {
		service.removeImovel(imovel);
		return ResponseEntity.status(HttpStatus.OK).body("Imóvel removido com sucesso!");
	}
	
	@RequestMapping(value = "/{imovelId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity buscaPerfil(@PathVariable("imovelId") int imovelId) {
		Imovel imovel = service.buscaImovel(imovelId);
		if (imovel == null) {
			return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("Nenhum imóvel foi encontrado com o código: " + imovelId);
		}
		return new ResponseEntity<Imovel>(imovel, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity list() {
		return new ResponseEntity<List<Imovel>>(service.buscaImoveis(), HttpStatus.OK);
	}

}