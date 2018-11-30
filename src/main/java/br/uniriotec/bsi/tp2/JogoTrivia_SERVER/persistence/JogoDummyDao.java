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
		Jogo j = new Jogo("Apresentação TP2", 0, 1);

		Questao q = new Questao("Qual é a atriz conhecida pelo meme das fórmulas matemáticas?", 10, 0);

		Opcao o1 = new Opcao("Renata Sorrah");
		Opcao o2 = new Opcao("Nazaré Tedesco");
		Opcao o3 = new Opcao("Regina Duarte");
		Opcao o4 = new Opcao("Susana Vieira");

		ArrayList<Opcao> opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		ConjuntoOrdenado cOpcoes = new ConjuntoOrdenado(opcoes);

		ConjuntoMultiplo cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o2);
		cOpcoesARemover.adicionarOpcao(o3);

		ConjuntoUnitario conjuntoSolucao = new ConjuntoUnitario(o1);

		q.setOpcoes(cOpcoes);
		q.setOpcoesARemover(cOpcoesARemover);
		q.setConjuntoSolucao(conjuntoSolucao);

		j.adicionarQuestao(q);

		q = new Questao("Qual é o nome do grupo conhecido pela música Cheia de Manias?", 10, 0);

		o1 = new Opcao("Kid Abelha");
		o2 = new Opcao("Black Eyed Peas");
		o3 = new Opcao("Revelação");
		o4 = new Opcao("Raça Negra");

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		cOpcoes = new ConjuntoOrdenado(opcoes);

		cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o1);
		cOpcoesARemover.adicionarOpcao(o2);

		conjuntoSolucao = new ConjuntoUnitario(o4);

		q.setOpcoes(cOpcoes);
		q.setOpcoesARemover(cOpcoesARemover);
		q.setConjuntoSolucao(conjuntoSolucao);

		j.adicionarQuestao(q);

		q = new Questao("Qual é o nome do repórter conhecido por tomar choque na TV?", 10, 0);

		o1 = new Opcao("Galvão");
		o2 = new Opcao("Tino Marcos");
		o3 = new Opcao("Lasier Martins");
		o4 = new Opcao("Cid Moreira");

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		cOpcoes = new ConjuntoOrdenado(opcoes);

		cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o1);
		cOpcoesARemover.adicionarOpcao(o2);

		conjuntoSolucao = new ConjuntoUnitario(o3);

		q.setOpcoes(cOpcoes);
		q.setOpcoesARemover(cOpcoesARemover);
		q.setConjuntoSolucao(conjuntoSolucao);

		j.adicionarQuestao(q);

		q = new Questao("Quantos são os Vingadores do filme de 2012?", 10, 0);

		o1 = new Opcao("5");
		o2 = new Opcao("6");
		o3 = new Opcao("7");
		o4 = new Opcao("8");

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		cOpcoes = new ConjuntoOrdenado(opcoes);

		cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o3);
		cOpcoesARemover.adicionarOpcao(o4);

		conjuntoSolucao = new ConjuntoUnitario(o2);

		q.setOpcoes(cOpcoes);
		q.setOpcoesARemover(cOpcoesARemover);
		q.setConjuntoSolucao(conjuntoSolucao);

		j.adicionarQuestao(q);

		q = new Questao("Qual país não faz parte dos BRICS?", 10, 0);

		o1 = new Opcao("África do Sul");
		o2 = new Opcao("Canadá");
		o3 = new Opcao("Brasil");
		o4 = new Opcao("Rússia");

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		cOpcoes = new ConjuntoOrdenado(opcoes);

		cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o3);
		cOpcoesARemover.adicionarOpcao(o4);

		conjuntoSolucao = new ConjuntoUnitario(o2);

		q.setOpcoes(cOpcoes);
		q.setOpcoesARemover(cOpcoesARemover);
		q.setConjuntoSolucao(conjuntoSolucao);

		j.adicionarQuestao(q);

		q = new Questao("O que é \"De Morgan\"?", 10, 0);

		o1 = new Opcao("Um ator");
		o2 = new Opcao("Regra de Inferência");
		o3 = new Opcao("Modelo de carro");
		o4 = new Opcao("Regra de 3");

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

		q = new Questao("Quantos ônibus Morganna contou na sua volta do Rio Sul?", 10, 0);

		o1 = new Opcao("5 107 e 3 513");
		o2 = new Opcao("6 107 e 3 513");
		o3 = new Opcao("5 107 e 2 513");
		o4 = new Opcao("6 107 e 2 513");

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		cOpcoes = new ConjuntoOrdenado(opcoes);

		cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o1);
		cOpcoesARemover.adicionarOpcao(o3);

		conjuntoSolucao = new ConjuntoUnitario(o4);

		q.setOpcoes(cOpcoes);
		q.setOpcoesARemover(cOpcoesARemover);
		q.setConjuntoSolucao(conjuntoSolucao);

		j.adicionarQuestao(q);

		q = new Questao("Quantos professores estão na página do BSI?", 10, 0);

		o1 = new Opcao("17");
		o2 = new Opcao("29");
		o3 = new Opcao("31");
		o4 = new Opcao("38");

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		cOpcoes = new ConjuntoOrdenado(opcoes);

		cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o1);
		cOpcoesARemover.adicionarOpcao(o4);

		conjuntoSolucao = new ConjuntoUnitario(o3);

		q.setOpcoes(cOpcoes);
		q.setOpcoesARemover(cOpcoesARemover);
		q.setConjuntoSolucao(conjuntoSolucao);

		j.adicionarQuestao(q);

		q = new Questao("Aham, senta lá...", 10, 0);

		o1 = new Opcao("Claudia");
		o2 = new Opcao("Bruna");
		o3 = new Opcao("Ana");
		o4 = new Opcao("Bia");

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		cOpcoes = new ConjuntoOrdenado(opcoes);

		cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o2);
		cOpcoesARemover.adicionarOpcao(o3);

		conjuntoSolucao = new ConjuntoUnitario(o1);

		q.setOpcoes(cOpcoes);
		q.setOpcoesARemover(cOpcoesARemover);
		q.setConjuntoSolucao(conjuntoSolucao);

		j.adicionarQuestao(q);

		q = new Questao("Qual o recorde de dias sem treta no grupo geral de BSI?", 10, 0);

		o1 = new Opcao("8");
		o2 = new Opcao("15");
		o3 = new Opcao("22");
		o4 = new Opcao("26");

		opcoes = new ArrayList<>();

		opcoes.add(o1);
		opcoes.add(o2);
		opcoes.add(o3);
		opcoes.add(o4);

		cOpcoes = new ConjuntoOrdenado(opcoes);

		cOpcoesARemover = new ConjuntoMultiplo();
		cOpcoesARemover.adicionarOpcao(o1);
		cOpcoesARemover.adicionarOpcao(o2);

		conjuntoSolucao = new ConjuntoUnitario(o3);

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
