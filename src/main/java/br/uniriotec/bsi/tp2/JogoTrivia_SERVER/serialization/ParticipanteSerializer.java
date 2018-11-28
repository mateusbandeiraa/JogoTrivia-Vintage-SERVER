package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import br.uniriotec.bsi.tp2.JogoTrivia_API.Participante;

public class ParticipanteSerializer implements JsonSerializer<Participante> {

	@Override
	public JsonElement serialize(Participante src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject root = (JsonObject) new GsonBuilder()
				.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.PARTICIPANTE), new GenericExclusionStrategy(MODO_SERIALIZACAO.CLASSIFICACAO)).create()
				.toJsonTree(src);
		root.addProperty("pontuacao", src.pontuacaoTotal());
		return root;
	}

}
