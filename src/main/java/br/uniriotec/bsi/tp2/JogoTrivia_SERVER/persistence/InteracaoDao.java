package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import br.uniriotec.bsi.tp2.JogoTrivia_API.Interacao;

public class InteracaoDao extends Dao<Interacao> implements InteracaoDataSource {

	public InteracaoDao() throws DaoException {
		super(Interacao.class);
	}

}
