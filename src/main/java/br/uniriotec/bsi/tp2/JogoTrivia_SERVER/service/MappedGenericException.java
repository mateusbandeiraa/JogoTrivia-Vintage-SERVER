package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MappedGenericException extends Exception implements ExceptionMapper<Exception> {
	private static final long serialVersionUID = 1L;
	public static final Status STATUS = Status.INTERNAL_SERVER_ERROR;

	@Override
	public Response toResponse(Exception exception) {
		exception.printStackTrace();
		return Response.status(STATUS)
				.entity(new MensagemErro(STATUS, CodigoErro.DESCONHECIDO, "Ocorreu um erro desconhecido."))
				.type(MediaType.APPLICATION_JSON + ";charset=utf8").build();
	}

}
