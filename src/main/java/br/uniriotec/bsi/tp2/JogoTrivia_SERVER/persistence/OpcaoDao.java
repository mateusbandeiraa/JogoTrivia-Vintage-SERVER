package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import br.uniriotec.bsi.tp2.JogoTrivia_API.Opcao;

public class OpcaoDao extends Dao<Opcao> implements OpcaoDataSource{

	public OpcaoDao() throws DaoException {
		super(Opcao.class);
	}

}
