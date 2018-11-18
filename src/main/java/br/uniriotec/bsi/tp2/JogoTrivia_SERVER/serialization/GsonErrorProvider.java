package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service.MensagemErro;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GsonErrorProvider implements MessageBodyWriter<MensagemErro> {

	private static final String PRETTY_PRINT = "pretty-print";

	private final Gson gson;
	private final Gson prettyGson;

	@Context
	private UriInfo ui;

	public GsonErrorProvider() {
		GsonBuilder builder = new GsonBuilder().serializeNulls().enableComplexMapKeySerialization();

		this.gson = builder.create();
		this.prettyGson = builder.setPrettyPrinting().create();
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type.equals(MensagemErro.class);
	}

	@Override
	public long getSize(MensagemErro t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(MensagemErro t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {

		PrintWriter printWriter = new PrintWriter(entityStream);
		try {
			String json;
			if (ui.getQueryParameters().containsKey(PRETTY_PRINT)) {
				json = prettyGson.toJson(t);
			} else {
				json = gson.toJson(t);
			}
			printWriter.write(json);
			printWriter.flush();
		} finally {
			printWriter.close();
		}
	}
}