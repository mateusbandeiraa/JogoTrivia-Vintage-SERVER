package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import br.uniriotec.bsi.tp2.JogoTrivia_API.Jogo;

public class JogoDao extends Dao<Jogo> implements JogoDataSource {

	public JogoDao() throws DaoException {
		super(Jogo.class);
	}

}
