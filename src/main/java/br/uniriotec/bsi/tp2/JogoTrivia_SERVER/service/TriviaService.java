package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service;

import java.util.Date;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.uniriotec.bsi.tp2.JogoTrivia_API.ConjuntoAlternativas;
import br.uniriotec.bsi.tp2.JogoTrivia_API.ConjuntoMultiplo;
import br.uniriotec.bsi.tp2.JogoTrivia_API.ConjuntoUnitario;
import br.uniriotec.bsi.tp2.JogoTrivia_API.EstadoPartida;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Interacao;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Jogo;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Opcao;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Participante;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Partida;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Questao;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.JogoDao;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.JogoDataSource;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.JogoDummyDao;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.OpcaoDao;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.OpcaoDataSource;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.ParticipanteDao;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.ParticipanteDataSource;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.PartidaDao;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.PartidaDataSource;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.QuestaoDao;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.QuestaoDataSource;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization.GenericExclusionStrategy;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization.MODO_SERIALIZACAO;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization.ParticipanteSerializer;

@Path("/")
public class TriviaService {
	private static final String CHARSET = ";charset=utf8";
	private static final PartidaDataSource PARTIDA_DAO = new PartidaDao();
	private static final ParticipanteDataSource PARTICIPANTE_DAO = new ParticipanteDao();
	private static final JogoDataSource JOGO_DAO = new JogoDao();
	private static final QuestaoDataSource QUESTAO_DAO = new QuestaoDao();
	private static final OpcaoDataSource OPCAO_DAO = new OpcaoDao();

	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("obterPartidasDisponiveis/")
	public String obterPartidasDisponiveis() {
		List<Partida> partidas = PARTIDA_DAO.findByState(EstadoPartida.DISPONIVEL);
		Gson gson = new GsonBuilder()
				.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.PARTIDA_INEDITA)).create();
		return gson.toJson(partidas);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("obterPartida/{id}")
	public String obterPartida(@PathParam("id") int id) {
		Partida partida = PARTIDA_DAO.find(id);
		Gson gson;
		if ((partida.getEstadoAtual() == EstadoPartida.EM_ANDAMENTO) && partida.obterDataMaximaParaResposta().before(new Date()))
			gson = new GsonBuilder()
					.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.PARTIDA_INEDITA)).create();
		else
			gson = new GsonBuilder().setExclusionStrategies(
					new ExclusionStrategy[] { new GenericExclusionStrategy(MODO_SERIALIZACAO.PARTIDA_INEDITA),
							new GenericExclusionStrategy(MODO_SERIALIZACAO.QUESTAO_EM_JOGO) })
					.create();
		return gson.toJson(partida);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("matricularParticipante/")
	public String matricularParticipante(@FormParam("idPartida") int idPartida,
			@FormParam("nickname") String nickname) {
		System.out.println(idPartida);
		Partida partida = PARTIDA_DAO.find(idPartida);
		Participante participante = new Participante(nickname, 0, 0, partida);
		participante.setChave(gerarChave(participante));
		PARTICIPANTE_DAO.save(participante);
		Gson gson = new GsonBuilder()
				.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.PARTICIPANTE)).create();
		return gson.toJson(participante);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("obterQuestaoAtual/{idPartida}")
	public String obterQuestaoAtual(@PathParam("idPartida") int idPartida) {
		Partida partida = PARTIDA_DAO.find(idPartida);
		Questao questaoAtual = partida.getQuestaoAtual();
		Gson gson;

		if (partida.obterDataMaximaParaResposta().before(new Date()))
			gson = new GsonBuilder().create();
		else
			gson = new GsonBuilder()
					.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.QUESTAO_EM_JOGO)).create();
		return gson.toJson(questaoAtual);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON + CHARSET)
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
		ConjuntoMultiplo questoesARemover = questaoAtual.getOpcoesARemover();

		Gson gson = new GsonBuilder()
				.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.QUESTAO_EM_JOGO)).create();
		return gson.toJson(questoesARemover);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("registrarInteracao/")
	public String registrarInteracao(@FormParam("idPartida") int idPartida, @FormParam("idQuestao") int idQuestao,
			@FormParam("idOpcao") int idOpcao, @FormParam("idParticipante") int idParticipante,
			@FormParam("chave") String chave) {
		Gson gson = new GsonBuilder()
				.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.PARTICIPANTE)).create();
		Partida partida = PARTIDA_DAO.find(idPartida);
		Participante participante = PARTICIPANTE_DAO.find(idParticipante);
		Questao questao = QUESTAO_DAO.find(idQuestao);
		Opcao opcao = OPCAO_DAO.find(idOpcao);

		ConjuntoAlternativas solucao = new ConjuntoUnitario(opcao);
		Interacao interacao = new Interacao(questao, participante, solucao, new Date(), partida);

		if (!participante.getChave().equals(chave)) {
			throw new WebApplicationException(401);
		}

		participante.adicionarInteracao(interacao);
		PARTICIPANTE_DAO.update(participante);
		return gson.toJson(participante);

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("obterClassificacao/{idPartida}")
	public String obterClassificacao(@PathParam("idPartida") int idPartida) {
		Partida partida = PARTIDA_DAO.find(idPartida);
		System.out.println(partida);
		List<Participante> top10 = partida.obterClassificacao();
		Gson gson = new GsonBuilder()
				.setExclusionStrategies(new GenericExclusionStrategy(MODO_SERIALIZACAO.CLASSIFICACAO))
				.registerTypeAdapter(Participante.class, new ParticipanteSerializer()).create();
		return gson.toJson(top10);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("iniciarPartida/")
	public Response iniciarPartida(@FormParam("idPartida") int idPartida) {
		Partida partida = PARTIDA_DAO.find(idPartida);
		partida.iniciarPartida();
		PARTIDA_DAO.update(partida);
		return Response.status(Status.OK).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("proximaQuestao/")
	public Response proximaQuestão(@FormParam("idPartida") int idPartida) {
		Partida partida = PARTIDA_DAO.find(idPartida);
		partida.proximaQuestao();
		PARTIDA_DAO.update(partida);
		return Response.status(Status.OK).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("criarPartida/")
	public String criarPartida(@FormParam("idJogo") int idJogo) {
		Jogo jogo = JOGO_DAO.find(idJogo);
		Partida partida = new Partida(jogo);
		PARTIDA_DAO.save(partida);
		return new Gson().toJson(partida);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("criarJogoPadrao/")
	public String criarPartida() {
		Jogo jogo = new JogoDummyDao().find(0);
		JOGO_DAO.save(jogo);
		return new Gson().toJson(jogo);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("obterHora/")
	public String obterHora() {
		return new Gson().toJson(new Date());
	}

	private String gerarChave(Participante p) {
		return "1234";
	}
}
