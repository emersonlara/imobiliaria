package br.com.imobiliaria.model;

import java.util.Arrays;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.imobiliaria.deserializer.TipoImovelDeserializer;
import br.com.imobiliaria.serializer.TipoImovelSerializer;

@JsonSerialize(using = TipoImovelSerializer.class)
@JsonDeserialize(using = TipoImovelDeserializer.class)
public enum TipoImovel {

	CASA("C", "CASA"), 
	APARTAMENTO("A", "APARTAMENTO");

	private String chave;

	private String descricao;

	private TipoImovel(String chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}

	public String getChave() {
		return chave;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoImovel getTipoImovel(String tipoImovelId) {
		if (tipoImovelId != null) {
			return Arrays.asList(values()).parallelStream()
					.filter(ti -> ti.getChave().equalsIgnoreCase(tipoImovelId))
					.findFirst().orElse(null);
		}
		return null;
	}

}
