package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import br.uniriotec.bsi.tp2.JogoTrivia_API.TempoEsgotadoException;

public class MappedTempoEsgotadoException extends TempoEsgotadoException implements ExceptionMapper<TempoEsgotadoException>{
	private static final long serialVersionUID = 1L;
	public static final Status STATUS = Status.FORBIDDEN;

	@Override
	public Response toResponse(TempoEsgotadoException exception) {
		exception.printStackTrace();
		return Response.status(STATUS)
				.entity(new MensagemErro(STATUS, CodigoErro.TEMPO_RESPOSTA_ESGOTADO, exception.getMessage()))
				.type(MediaType.APPLICATION_JSON + ";charset=utf8").build();
	}

}
