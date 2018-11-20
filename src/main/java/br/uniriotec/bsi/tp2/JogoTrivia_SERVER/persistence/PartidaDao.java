package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.uniriotec.bsi.tp2.JogoTrivia_API.EstadoPartida;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Partida;

public class PartidaDao extends Dao<Partida> implements PartidaDataSource {

	public PartidaDao() throws DaoException {
		super(Partida.class);
	}

	public List<Partida> findByState(EstadoPartida estado) {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("FROM Partida WHERE EstadoAtual = :estado");
		q.setParameter("estado", estado.name());
		@SuppressWarnings("unchecked")
		List<Partida> results = q.getResultList();
		em.close();
		return results;
	}
}
