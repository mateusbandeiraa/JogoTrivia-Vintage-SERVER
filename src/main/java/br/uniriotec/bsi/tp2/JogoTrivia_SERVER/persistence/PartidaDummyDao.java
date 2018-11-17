package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.uniriotec.bsi.tp2.JogoTrivia_API.EstadoPartida;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Jogo;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Partida;

public class PartidaDummyDao implements PartidaDataSource {

	@Override
	public Partida find(int id) {
		Jogo modelo = new JogoDummyDao().find(1);
		Partida partida = new Partida(id, modelo, 1, 1);
		return partida;
	}

	@Override
	public List<Partida> findAll() {
		List<Partida> saida = new ArrayList<>();
		saida.add(find(1));
		return saida;
	}

	@Override
	public void save(Partida entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Partida entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Partida entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Partida> findByState(EstadoPartida estado) {
		List<Partida> saida = findAll();
		saida.removeIf(new Predicate<Partida>() {

			@Override
			public boolean test(Partida t) {
				return !t.getEstadoAtual().equals(estado);
			}
		});
		return saida;
	}

}
