package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.service;

public enum CodigoErro {
	DESCONHECIDO(-1), CONEXAO_BD(5);
	
	public final int CODIGO;
	private CodigoErro(int codigo) {
		this.CODIGO = codigo;
	}
}
