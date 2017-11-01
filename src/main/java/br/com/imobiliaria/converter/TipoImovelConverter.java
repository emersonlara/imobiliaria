package br.com.imobiliaria.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.imobiliaria.model.TipoImovel;

@Converter
public class TipoImovelConverter implements AttributeConverter<TipoImovel, String> {

	@Override
	public String convertToDatabaseColumn(TipoImovel tipoImovel) {
		if (tipoImovel != null) {
			return tipoImovel.getChave();
		}
		return null;
	}

	@Override
	public TipoImovel convertToEntityAttribute(String tipoImovelId) {
		return TipoImovel.getTipoImovel(tipoImovelId);
	}

}
