package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.uniriotec.bsi.tp2.JogoTrivia_API.Participante;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization.GenericExclusionStrategy;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization.MODO_SERIALIZACAO;

public class DaoTest {
	public static void main(String[] args) {
		ParticipanteDao pd = new ParticipanteDao();
		Participante p = pd.find(167);
		Gson gson = new GsonBuilder().setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.PARTICIPANTE)).create();
		System.out.println(gson.toJson(p));
	}
}
