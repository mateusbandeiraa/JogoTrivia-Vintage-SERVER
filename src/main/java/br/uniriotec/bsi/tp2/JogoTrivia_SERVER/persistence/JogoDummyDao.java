package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import java.util.ArrayList;
import java.util.List;

import br.uniriotec.bsi.tp2.JogoTrivia_API.ConjuntoMultiplo;
import br.uniriotec.bsi.tp2.JogoTrivia_API.ConjuntoOrdenado;
import br.uniriotec.bsi.tp2.JogoTrivia_API.ConjuntoUnitario;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Jogo;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Opcao;
import br.uniriotec.bsi.tp2.JogoTrivia_API.Questao;

/**
 * Classe para testes.
 * 
 * @author Mateus Bandeira
 *
 */
public class JogoDummyDao implements JogoDataSource {

	@Override
	public Jogo find(int id) {
		Jogo j = new Jogo("Conhecimentos gerais", 0, 1);

		Questao q = new Questao("Qual super herói da Marvel usa um martelo?", 10, 5);

		Opcao o1 = new Opcao("Homem de Ferro");
		Opcao o2 = new Opcao("Doutor Estranho");
		Opcao o3 = new Opcao("Homem-Aranha");
		Opcao o4 = new Opcao("Thor");

		ArrayList<Opcao> opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		ConjuntoOrdenado cOpcoes = new ConjuntoOrdenado(opcoes);

		ConjuntoMultiplo cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o1);
		cOpcoesARemover.adicionarOpcao(o3);

		ConjuntoUnitario conjuntoSolucao = new ConjuntoUnitario(o4);

		q.setOpcoes(cOpcoes);
		q.setOpcoesARemover(cOpcoesARemover);
		q.setConjuntoSolucao(conjuntoSolucao);

		j.adicionarQuestao(q);

		q = new Questao("Cada versão do Android recebe um nome relacionado a o que?", 10, 5);

		o1 = new Opcao("Animais");
		o2 = new Opcao("Lugares");
		o3 = new Opcao("Doces");
		o4 = new Opcao("Cientistas");

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		cOpcoes = new ConjuntoOrdenado(opcoes);

		cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o2);
		cOpcoesARemover.adicionarOpcao(o4);

		conjuntoSolucao = new ConjuntoUnitario(o3);

		q.setOpcoes(cOpcoes);
		q.setOpcoesARemover(cOpcoesARemover);
		q.setConjuntoSolucao(conjuntoSolucao);

		j.adicionarQuestao(q);

		q = new Questao("Em O Senhor dos Anéis, quantos membros há inicialmente na Sociedade do Anel?", 10, 5);

		o1 = new Opcao("8");
		o2 = new Opcao("9");
		o3 = new Opcao("10");
		o4 = new Opcao("11");

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		cOpcoes = new ConjuntoOrdenado(opcoes);

		cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o1);
		cOpcoesARemover.adicionarOpcao(o4);

		conjuntoSolucao = new ConjuntoUnitario(o2);

		q.setOpcoes(cOpcoes);
		q.setOpcoesARemover(cOpcoesARemover);
		q.setConjuntoSolucao(conjuntoSolucao);

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
