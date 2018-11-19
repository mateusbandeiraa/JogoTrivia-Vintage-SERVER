package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service;

public enum CodigoErro {
	DESCONHECIDO(-1), CONEXAO_BD(5), TEMPO_RESPOSTA_ESGOTADO(3);
	
	public final int CODIGO;
	private CodigoErro(int codigo) {
		this.CODIGO = codigo;
	}
}
