package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Dao<T> {
	protected static EntityManagerFactory emf;
	private Class<T> persistedClass;
		
	public Dao(Class<T> persistedClass) throws Exception {
		this.persistedClass = persistedClass;
		this.setUp();
	}

	protected void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("br.uniriotec.bsi.tp2.JogoTrivia");
	}
	
	protected void tearDown() throws Exception{
		emf.close();
	}
	
	public T find(int id) {
		return emf.createEntityManager().find(persistedClass, id);
	}
	
	public void save(T entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}
	public void update(T entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(T entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
		em.close();
	}
	
	public void remove(int id) {
		T entity = find(id);
		remove(entity);
	}
	
}
