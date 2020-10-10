package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.TripListDetails;

public class TripListDetailsHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("TripDestinationLists");

	public void insertNewTripListDetails(TripListDetails s) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateList(TripListDetails toEdit) {   
		EntityManager em = emfactory .createEntityManager();   
		em.getTransaction().begin(); 
	    em.merge(toEdit);   
	    em.getTransaction().commit();   
	    em.close();  
	} 
	 
	
	public List<TripListDetails> getLists() {
		EntityManager em = emfactory.createEntityManager();
		List<TripListDetails> allDetails = em.createQuery("SELECT "
				+ "t FROM TripListDetails t").getResultList();
		return allDetails;
	}
	
	public void deleteList(TripListDetails toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<TripListDetails> typedQuery = em.createQuery("select "
				+ "detail from TripListDetails detail where detail.id "
				+ "= :selectedId", TripListDetails.class);
		
		// Substitute parameter with actual data from the toDelete item  
		typedQuery.setParameter("selectedId", toDelete.getId()); 
		
		 // we only want one result  
		typedQuery.setMaxResults(1); 
		
		 // get the result and save it into a new list item  
		TripListDetails result = typedQuery.getSingleResult(); 
		
		// remove it  
		em.remove(result);  
		em.getTransaction().commit();  
		em.close(); 
		  
	}
	
	public TripListDetails searchForTripListDetailsById(Integer tempId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TripListDetails found = em.find(TripListDetails.class, tempId);
		em.close();
		return found;
	}
}
