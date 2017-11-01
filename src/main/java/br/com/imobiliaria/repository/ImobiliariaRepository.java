package br.com.imobiliaria.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.imobiliaria.model.Imobiliaria;
import br.com.imobiliaria.model.Imovel;
import br.com.imobiliaria.model.Perfil;

@Repository
@Transactional(transactionManager = "imobiliariaTX", readOnly = true)
public class ImobiliariaRepository {
	
	@PersistenceContext private EntityManager em;

	@Transactional(transactionManager = "imobiliariaTX", readOnly = false)
	public void inserePerfil(Perfil perfil) {
		em.merge(perfil);
		em.flush();
	}

	public void removePerfil(Perfil perfil) {
		em.remove(perfil);
	}

	public boolean verificaPerfilExistente(Integer perfilId, String email) {
		return em.createQuery("select case when count(id) = 0 then true else false end from Perfil where upper(email) = upper(:email)  and :perfilId is null or id <> :perfilId", Boolean.class)
				.setParameter("email", email)
				.setParameter("perfilId", perfilId)
				.getSingleResult();
	}

	public Perfil buscaPerfil(Integer perfilId) {
		try {
			return em.find(Perfil.class, perfilId);
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Perfil buscaPerfil(String email) {
		return em.createQuery("from Perfil where upper(email) = upper(:email)", Perfil.class)
				.setParameter("email", email).getSingleResult();
	}

	public List<Perfil> buscaPerfis() {
		return em.createQuery("from Perfil", Perfil.class).getResultList();
	}

	public String recuperaSenha(String email) {
		return em.createQuery("select senha from Perfil where upper(email) = upper(:email)", String.class)
				.setParameter("email", email)
				.getSingleResult();
	}
	
	@Transactional(transactionManager = "imobiliariaTX", readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insereImobiliaria(Imobiliaria imobiliaria) {
		em.merge(imobiliaria);
		em.flush();
	}

	@Transactional(transactionManager = "imobiliariaTX", readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void removeImobiliaria(Imobiliaria imobiliaria) {
		em.remove(imobiliaria);
	}

	public boolean verificaImobiliariaExistente(Integer imobiliariaId, String nome) {
		return em.createQuery("select case when count(id) = 0 then true else false end from Imobiliaria where upper(nome) = upper(:nome)  and :imobiliariaId is null or id <> :imobiliariaId", Boolean.class)
				.setParameter("nome", nome)
				.setParameter("imobiliariaId", imobiliariaId)
				.getSingleResult();
	}

	public Imobiliaria buscaImobiliaria(Integer imobiliariaId) {
		try {
			return em.find(Imobiliaria.class, imobiliariaId);
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Imobiliaria> buscaImobiliarias() {
		return em.createQuery("from Imobiliaria", Imobiliaria.class).getResultList();
	}
	
	@Transactional(transactionManager = "imobiliariaTX", readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insereImovel(Imovel imovel) {
		em.merge(imovel);
		em.flush();
	}

	@Transactional(transactionManager = "imobiliariaTX", readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void removeImovel(Imovel imovel) {
		em.remove(imovel);
	}

	public boolean verificaImovelExistente(Integer imovelId, String descricao) {
		return em.createQuery("select case when count(id) = 0 then true else false end from Imovel where upper(descricao) = upper(:descricao)  and :imovelId is null or id <> :imovelId", Boolean.class)
				.setParameter("descricao", descricao)
				.setParameter("imovelId", imovelId)
				.getSingleResult();
	}

	public Imovel buscaImovel(Integer imovelId) {
		try {
			return em.find(Imovel.class, imovelId);
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Imovel> buscaImoveis(String descricao) {
		return em.createQuery("from Imovel where upper(descricao) contains upper(:descricao)", Imovel.class)
				.setParameter("descricao", descricao)
				.getResultList();
	}

	public List<Imovel> buscaImoveis() {
		return em.createQuery("from Imovel", Imovel.class).getResultList();
	}

}