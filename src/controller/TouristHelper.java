package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Tourist;


public class TouristHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebCarDealerList");

	public void insertTourist(Tourist t) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	public List<Tourist> showAllTourists() {
		EntityManager em = emfactory.createEntityManager();
		List<Tourist> allTourists = em.createQuery("SELECT t FROM Tourist t").getResultList();
		return allTourists;
	}

	public Tourist findTourist(String nameToLookUp) {

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Tourist> typedQuery = em.createQuery("select t from Tourist t where t.touristName = :selectedName",
				Tourist.class);
		typedQuery.setParameter("selectedName", nameToLookUp);
		Tourist foundTourist;
		try {
			foundTourist = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			foundTourist = new Tourist(nameToLookUp);
		}
		em.close();

		return foundTourist;
	}
}
