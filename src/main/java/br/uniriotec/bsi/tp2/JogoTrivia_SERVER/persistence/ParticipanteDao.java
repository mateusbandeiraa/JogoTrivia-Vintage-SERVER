package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import br.uniriotec.bsi.tp2.JogoTrivia_API.Participante;

public class ParticipanteDao extends Dao<Participante> implements ParticipanteDataSource {

	public ParticipanteDao() throws DaoException {
		super(Participante.class);
	}

}
