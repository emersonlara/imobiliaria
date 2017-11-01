package br.com.imobiliaria.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.imobiliaria.model.TipoImovel;

public class TipoImovelSerializer extends JsonSerializer<TipoImovel> {

	@Override
	public void serialize(TipoImovel tipoImovel, JsonGenerator generator, SerializerProvider provider) throws IOException {
		generator.writeStartObject();
		generator.writeStringField("chave", tipoImovel.getChave());
		generator.writeStringField("descricao", tipoImovel.getDescricao());
		generator.writeEndObject();
	}

}
