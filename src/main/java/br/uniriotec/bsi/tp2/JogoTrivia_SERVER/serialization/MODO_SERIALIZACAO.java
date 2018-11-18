package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization;

import java.util.HashMap;
import java.util.Map;

import br.uniriotec.bsi.tp2.JogoTrivia_API.Jogo;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Opcao;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Partida;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Questao;

public enum MODO_SERIALIZACAO {

	PARTIDA_INEDITA(), QUESTAO_INEDITA(), PARTICIPANTE();

	public final Map<Class<?>, String[]> CAMPOS_A_OMITIR;

	private MODO_SERIALIZACAO() {
		CAMPOS_A_OMITIR = new HashMap<>();
		switch (this.name()) {
		case "PARTIDA_INEDITA":
			CAMPOS_A_OMITIR.put(Jogo.class,
					new String[] { "limiteAjudasTempoBonus", "limiteAjudasRemoverOpcoes", "questoes" });
			break;
		case "QUESTAO_INEDITA":
			CAMPOS_A_OMITIR.put(Questao.class, new String[] { "opcaoCerta" });
			CAMPOS_A_OMITIR.put(Opcao.class, new String[] { "estaCerto", "ehRemovivel" });
			break;
		case "PARTICIPANTE":
			CAMPOS_A_OMITIR.put(Partida.class, new String[] { "limiteAjudasRemoverOpcoes", "limiteAjudasTempoBonus",
					"numeroQuestaoAtual", "estadoAtual", "participantes" });
		default:
			break;
		}
	}
}
