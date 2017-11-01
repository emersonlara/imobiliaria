package br.com.imobiliaria.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.imobiliaria.model.TipoImovel;

public class TipoImovelDeserializer extends JsonDeserializer<TipoImovel> {

	@Override
	public TipoImovel deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = parser.getCodec();
        JsonNode node = oc.readTree(parser);
        if (node == null) {
            return null;
        }
        return TipoImovel.getTipoImovel(node.textValue());
	}

}
