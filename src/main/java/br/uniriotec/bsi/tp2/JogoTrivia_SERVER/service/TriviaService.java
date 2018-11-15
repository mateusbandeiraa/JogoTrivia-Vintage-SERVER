package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.uniriotec.bsi.tp2.JogoTrivia_API.EstadoPartida;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Partida;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence.PartidaDao;

@Path("/")
public class TriviaService {
	private static final String CHARSET = ";charset=utf8";

	@GET
	@Produces(MediaType.TEXT_HTML + CHARSET)
	@Path("/")
	public static String HelloWorld() {
		return "Hello World!";
	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON + CHARSET)
//	@Path("Jogo/")
//	public static String teste() {
//		return new Jogo("Super Heróis", 0, 0);
//	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET)
	@Path("obterPartidasDisponiveis/")
	public List<Partida> obterPartidasDisponiveis() {
		PartidaDao dao = new PartidaDao();
		List<Partida> partidas = dao.findByState(EstadoPartida.DISPONIVEL);
		return partidas;
	}
}
