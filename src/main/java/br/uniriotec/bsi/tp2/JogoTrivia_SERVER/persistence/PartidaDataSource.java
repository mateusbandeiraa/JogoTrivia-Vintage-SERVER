package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import java.util.List;

import br.uniriotec.bsi.tp2.JogoTrivia_API.EstadoPartida;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Partida;

public interface PartidaDataSource extends GenericDataSource<Partida> {
	public List<Partida> findByState(EstadoPartida estado);
}
