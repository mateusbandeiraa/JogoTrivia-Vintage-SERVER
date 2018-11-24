package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import br.uniriotec.bsi.tp2.JogoTrivia_API.Questao;

public class QuestaoDao extends Dao<Questao> implements QuestaoDataSource{

	public QuestaoDao() throws DaoException {
		super(Questao.class);
	}

}
