package br.uniriotec.bsi.tp2.JogoTrivia_SERVER.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Dao<T> implements GenericDataSource<T>{
	protected static EntityManagerFactory emf;
	private Class<T> persistedClass;

	public Dao(Class<T> persistedClass) throws DaoException{
		try {
			this.persistedClass = persistedClass;
			this.setUp();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DaoException();
		}
	}

	protected void setUp() {
		emf = Persistence.createEntityManagerFactory("br.uniriotec.bsi.tp2.JogoTrivia");
	}

	protected void tearDown() {
		emf.close();
	}

	public T find(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		T result = em.find(persistedClass, id);
		em.getTransaction().commit();
		em.close();
		return result;
	}

	public List<T> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("FROM " + persistedClass.getSimpleName()); // Não é bonito, mas funciona.
		@SuppressWarnings("unchecked")
		List<T> result = q.getResultList();
		em.getTransaction().commit();
		em.close();
		return result;
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
