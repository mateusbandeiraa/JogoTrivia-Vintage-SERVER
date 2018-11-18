package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization;

import java.util.Arrays;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GenericExclusionStrategy implements ExclusionStrategy {
	private final MODO_SERIALIZACAO modo_SERIALIZACAO;

	public GenericExclusionStrategy(MODO_SERIALIZACAO modo_SERIALIZACAO) {
		this.modo_SERIALIZACAO = modo_SERIALIZACAO;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		String[] CAMPOS_A_OMITIR = modo_SERIALIZACAO.CAMPOS_A_OMITIR.get(f.getDeclaringClass());
		if(CAMPOS_A_OMITIR == null) {
			return false;
		}
		return Arrays.asList(CAMPOS_A_OMITIR).contains(f.getName());
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
}
