package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import java.io.Serializable;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service.CodigoErro;
import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service.MensagemErro;

@Provider
public class DaoException extends PersistenceException implements ExceptionMapper<PersistenceException>, Serializable {

	private static final long serialVersionUID = 1L;
	public static final Status STATUS = Status.INTERNAL_SERVER_ERROR;
	public static final String MENSAGEM = "Houve um erro com a conexão com o banco de dados.";

	public DaoException() {
		super(MENSAGEM);
	}

	@Override
	public Response toResponse(PersistenceException exception) {
		exception.printStackTrace();
		return Response.status(STATUS).entity(new MensagemErro(STATUS, CodigoErro.CONEXAO_BD,  MENSAGEM))
				.type(MediaType.APPLICATION_JSON + ";charset=utf8").build();
	}
}
