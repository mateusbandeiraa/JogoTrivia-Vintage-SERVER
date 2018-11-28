package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization;

import java.util.HashMap;
import java.util.Map;

import br.uniriotec.bsi.tp2.JogoTrivia_API.Interacao;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Jogo;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Opcao;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Participante;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Partida;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Questao;

public enum MODO_SERIALIZACAO {

	PARTIDA_INEDITA(), QUESTAO_EM_JOGO(), PARTICIPANTE(), CLASSIFICACAO();

	public final Map<Class<?>, String[]> CAMPOS_A_OMITIR;

	private MODO_SERIALIZACAO() {
		CAMPOS_A_OMITIR = new HashMap<>();
		switch (this.name()) {
		case "PARTIDA_INEDITA":
			CAMPOS_A_OMITIR.put(Jogo.class,
					new String[] { "limiteAjudasTempoBonus", "limiteAjudasRemoverOpcoes", "questoes" });
			CAMPOS_A_OMITIR.put(Opcao.class, new String[] {"estaCerto", "ehRemovivel"});
			CAMPOS_A_OMITIR.put(Questao.class, new String[] {"opcaoCerta"});
			CAMPOS_A_OMITIR.put(Partida.class, new String[] { "limiteAjudasRemoverOpcoes", "limiteAjudasTempoBonus", "estadoAtual"});
			CAMPOS_A_OMITIR.put(Participante.class, new String[] { "partida", "interacoes", "chave", "ajudasTempoBonusUsadas", "ajudasRemoverOpcoesUsadas" });
			break;
		case "QUESTAO_EM_JOGO":
			CAMPOS_A_OMITIR.put(Questao.class, new String[] { "conjuntoSolucao", "opcoesARemover" });
			CAMPOS_A_OMITIR.put(Opcao.class, new String[] { "estaCerto", "ehRemovivel" });
			break;
		case "PARTICIPANTE":
			CAMPOS_A_OMITIR.put(Partida.class, new String[] { "limiteAjudasRemoverOpcoes", "limiteAjudasTempoBonus",
					"numeroQuestaoAtual", "estadoAtual", "participantes" });
			CAMPOS_A_OMITIR.put(Participante.class, new String[] { "partida" });
			CAMPOS_A_OMITIR.put(Interacao.class, new String[] { "participante", "partida" });
			break;
		case "CLASSIFICACAO":
			CAMPOS_A_OMITIR.put(Participante.class, new String[] { "partida", "interacoes", "chave", "ajudasTempoBonusUsadas", "ajudasRemoverOpcoesUsadas" });
		default:
			break;
		}
	}
}
