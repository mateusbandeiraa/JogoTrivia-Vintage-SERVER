package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import java.util.List;

import br.uniriotec.bsi.tp2.JogoTrivia_API.Participante;

public class ParticipanteDummyDao implements ParticipanteDataSource{

	@Override
	public Participante find(int id) {
		Participante p = new Participante("LukeSkywalker", 0, 0, new PartidaDummyDao().find(0));
		p.setChave("1234");
		p.getPartida().iniciarPartida();
		return p;
	}

	@Override
	public List<Participante> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Participante entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Participante entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Participante entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

}
