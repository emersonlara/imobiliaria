package br.com.imobiliaria.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "imobiliarias")
public class Imobiliaria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_imobiliaria", nullable = false)
	private Integer id;

	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;

	@Column(name = "descricao", length = 255)
	private String descricao;

	@OneToMany(targetEntity = Imovel.class, mappedBy = "imobiliaria", orphanRemoval = true, 
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Imovel> imoveis = new HashSet<Imovel>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(Set<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

}
