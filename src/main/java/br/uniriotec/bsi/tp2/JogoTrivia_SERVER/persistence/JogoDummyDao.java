package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import java.util.ArrayList;
import java.util.List;

import br.uniriotec.bsi.tp2.JogoTrivia_API.Jogo;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Opcao;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Questao;
/**
 * Classe para testes.
 * @author Mateus Bandeira
 *
 */
public class JogoDummyDao implements JogoDataSource{

	@Override
	public Jogo find(int id) {
		Jogo j = new Jogo("Conhecimentos gerais", 0, 1);

		Questao q = new Questao("Qual super herói da Marvel usa um martelo?", 10, 5);

		Opcao o1 = new Opcao("Homem de Ferro", false, true);
		Opcao o2 = new Opcao("Doutor Estranho", false, false);
		Opcao o3 = new Opcao("Homem-Aranha", false, true);
		Opcao o4 = new Opcao("Thor", true, false);

		ArrayList<Opcao> opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		q.setOpcoes(opcoes);
		j.adicionarQuestao(q);

		q = new Questao("Cada versão do Android recebe um nome relacionado a o que?", 10, 5);

		o1 = new Opcao("Animais", false, true);
		o2 = new Opcao("Lugares", false, true);
		o3 = new Opcao("Doces", true, false);
		o4 = new Opcao("Cientistas", false, false);

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		q.setOpcoes(opcoes);
		j.adicionarQuestao(q);

		q = new Questao("Em O Senhor dos Anéis, quantos membros há inicialmente na Sociedade do Anel?", 10, 5);

		o1 = new Opcao("8", false, true);
		o2 = new Opcao("9", true, false);
		o3 = new Opcao("10", false, false);
		o4 = new Opcao("11", false, true);

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		q.setOpcoes(opcoes);
		j.adicionarQuestao(q);

		return j;
	}

	@Override
	public List<Jogo> findAll() {
		List<Jogo> saida = new ArrayList<>();
		saida.add(find(1));
		return saida;
	}

	@Override
	public void save(Jogo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Jogo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Jogo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

}
