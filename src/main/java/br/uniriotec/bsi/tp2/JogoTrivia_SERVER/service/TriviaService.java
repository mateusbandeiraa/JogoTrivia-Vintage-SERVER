package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class TriviaService {

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/")
	public static String HelloWorld() {
		return "Hello World!";
	}
}
