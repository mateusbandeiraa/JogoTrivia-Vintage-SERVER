package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.serialization;

public enum MODO_SERIALIZACAO {

	PARTIDA_INEDITA(new String[] {"questoes"}), QUESTAO_INEDITA(new String[] {"estaCerto", "ehRemovivel", "opcaoCerta"});
	
	public final String[] CAMPOS_A_OMITIR;

	private MODO_SERIALIZACAO(String[] campos_a_omitir) {
		this.CAMPOS_A_OMITIR = campos_a_omitir;
	}
}
