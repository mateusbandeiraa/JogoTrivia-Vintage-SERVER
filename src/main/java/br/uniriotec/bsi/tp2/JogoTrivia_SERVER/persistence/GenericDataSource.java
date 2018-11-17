package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import java.util.List;

/**
 * A interface GenericDataSource descreve os métodos de uma classe genérica de
 * acesso ao banco de dados. Essa abordagem permite a implementação de diversas
 * fontes, como um banco de dados externo ou a leitura de um arquivo txt, por
 * exemplo.
 * 
 * @author Mateus Bandeira
 *
 * @param <T>
 */
public interface GenericDataSource<T> {

	public T find(int id);

	public List<T> findAll();

	public void save(T entity);

	public void update(T entity);

	public void remove(T entity);

	public void remove(int id);

}
