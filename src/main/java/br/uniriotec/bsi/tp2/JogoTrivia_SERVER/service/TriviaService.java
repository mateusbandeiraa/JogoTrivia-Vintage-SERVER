package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.uniriotec.bsi.tp2.JogoTrivia_API.EstadoPartida;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Opcao;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Participante;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Partida;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Questao;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.ParticipanteDataSource;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.ParticipanteDummyDao;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.PartidaDataSource;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.PartidaDummyDao;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization.GenericExclusionStrategy;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization.MODO_SERIALIZACAO;

@Path("/")
public class TriviaService {
	private static final String CHARSET = ";charset=utf8";
	private static final PartidaDataSource PARTIDA_DAO = new PartidaDummyDao();
	private static final ParticipanteDataSource PARTICIPANTE_DAO = new ParticipanteDummyDao();

	@GET
	@Produces(MediaType.TEXT_HTML + CHARSET)
	@Path("/")
	public static String HelloWorld() {
		return "Hello World!";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("obterPartidasDisponiveis/")
	public String obterPartidasDisponiveis() {
		List<Partida> partidas = PARTIDA_DAO.findByState(EstadoPartida.DISPONIVEL);
		System.out.println(partidas);
		Gson gson = new GsonBuilder()
				.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.PARTIDA_INEDITA)).create();
		return gson.toJson(partidas);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("obterPartida/{id}")
	public String obterPartida(@PathParam("id") int id) {
		Partida partida = PARTIDA_DAO.find(1);
		Gson gson = new GsonBuilder()
				.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.PARTIDA_INEDITA)).create();
		return gson.toJson(partida);
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + CHARSET)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("matricularParticipante/")
	public String matricularParticipante(@FormParam("idPartida") int idPartida,
			@FormParam("nickname") String nickname) {
		Partida partida = new Partida(idPartida, null);
		Participante participante = new Participante(nickname, 0, 0, partida);
		participante.setChave(gerarChave(participante));
		// PARTICIPANTE_DAO.save(participante);
		Gson gson = new GsonBuilder()
				.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.PARTICIPANTE)).create();
		return gson.toJson(participante);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("obterQuestaoAtual/{idPartida}")
	public String obterQuestaoAtual(@PathParam("idPartida") int idPartida) {
		Partida partida = PARTIDA_DAO.find(idPartida);
		partida.iniciarPartida();
		Questao questaoAtual = partida.getQuestaoAtual();
		Gson gson = new GsonBuilder()
				.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.QUESTAO_EM_JOGO)).create();
		return gson.toJson(questaoAtual);
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED + CHARSET)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("usarAjudaRemoverQuestoes/")
	public String usarAjudaRemoverQuestoes(@FormParam("idParticipante") String idParticipante,
			@FormParam("chave") String chave) {
		Participante participante = PARTICIPANTE_DAO.find(Integer.parseInt(idParticipante));
		
		if (!participante.getChave().equals(chave)) {
			throw new WebApplicationException(401);
		}
		
		participante.usarAjudaRemoverOpcoes();
		PARTICIPANTE_DAO.update(participante);

		Partida partida = participante.getPartida();
		Questao questaoAtual = partida.getQuestaoAtual();
		List<Opcao> questoesARemover = questaoAtual.obterOpcoesARemover();

		Gson gson = new GsonBuilder()
				.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.QUESTAO_EM_JOGO)).create();
		return gson.toJson(questoesARemover);
	}

	private String gerarChave(Participante p) {
		return "1234";
	}
}
