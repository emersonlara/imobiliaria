package br.com.imobiliaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.imobiliaria.model.Imobiliaria;
import br.com.imobiliaria.model.Imovel;
import br.com.imobiliaria.model.Perfil;
import br.com.imobiliaria.repository.ImobiliariaRepository;

@Service
public class ImobiliariaService implements UserDetailsService {
	
	@Autowired private ImobiliariaRepository repository;
	
	public void inserePerfil(Perfil perfil) {
		repository.inserePerfil(perfil);
	}
	
	public void removePerfil(Perfil perfil) {
		repository.removePerfil(perfil);
	}
	
	public boolean verificaPerfilExistente(Integer perfilId, String email) {
		return repository.verificaPerfilExistente(perfilId, email);
	}
	
	public Perfil buscaPerfil(Integer perfilId) {
		return repository.buscaPerfil(perfilId);
	}
	
	public List<Perfil> buscaPerfis() {
		return repository.buscaPerfis();
	}
	
	public String recuperaSenha(String email) {
		return repository.recuperaSenha(email);
	}
	
	public void insereImobiliaria(Imobiliaria imobiliaria) {
		repository.insereImobiliaria(imobiliaria);
	}
	
	public void removeImobiliaria(Imobiliaria imobiliaria) {
		repository.removeImobiliaria(imobiliaria);
	}
	
	public boolean verificaImobiliariaExistente(Integer imobiliariaId) {
		return repository.verificaImobiliariaExistente(imobiliariaId);
	}
	
	public Imobiliaria buscaImobiliaria(Integer imobiliariaId) {
		return repository.buscaImobiliaria(imobiliariaId);
	}
	
	public List<Imobiliaria> buscaImobiliarias() {
		return repository.buscaImobiliarias();
	}
	
	public void insereImovel(Imovel imovel) {
		repository.insereImovel(imovel);
	}
	
	public void removeImovel(Imovel imovel) {
		repository.removeImovel(imovel);
	}
	
	public Imovel buscaImovel(Integer imovelId) {
		return repository.buscaImovel(imovelId);
	}
	
	public List<Imovel> buscaImoveis(String descricao) {
		return repository.buscaImoveis(descricao);
	}
	
	public List<Imovel> buscaImoveis() {
		return repository.buscaImoveis();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return repository.buscaPerfil(email);
	}

}