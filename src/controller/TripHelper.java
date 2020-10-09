package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Trip;

public class TripHelper {
	static EntityManagerFactory emfactory =	
			Persistence.createEntityManagerFactory("TripDestinationLists");
	
	public void	insertTrip(Trip	t){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Trip> showAllTrips(){
		EntityManager em = emfactory.createEntityManager();
		List<Trip> allTrips	= em.createQuery("SELECT i FROM Trip i").getResultList();
		return allTrips;
		}
	
	public void deleteTrip(Trip toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Trip> typedQuery	= em.createQuery("select t from "
				+ "Trip t where t.location = :selectedLocation and t.state = :selectedState and t.attraction = :selectedAttraction", Trip.class);
		
		//Substitute parameter with	actual data from the toDelete item
		typedQuery.setParameter("selectedLocation", toDelete.getLocation());
		typedQuery.setParameter("selectedState", toDelete.getState());
		typedQuery.setParameter("selectedAttraction", toDelete.getAttraction());
		
		//we only want one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a	new	list item
		Trip result	= typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		}
	
	public Trip	searchForTripById(int idToEdit)	{
	//	TODO Auto-generated	method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Trip found = em.find(Trip.class, idToEdit);
		em.close();
		return found;
	}
	
	public void updateTrip(Trip toEdit)	{
		//	TODO Auto-generated	method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<Trip> searchForTripByLocation(String locDesc){
		// TODO	Auto-generated	method	stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Trip> typedQuery	= em.createQuery("select t from "
				+ "Trip t where t.location = :selectedLocation", Trip.class);
		typedQuery.setParameter("selectedLocation", locDesc);
		List<Trip> foundTrips =	typedQuery.getResultList();
		em.close();
		return foundTrips;
	}
	
	public List<Trip> searchForTripByState(String stDesc)	{
		//	TODO	Auto-generated	method	stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Trip> typedQuery	= em.createQuery("select t from "
				+ "Trip t where t.state = :selectedState", Trip.class);
		typedQuery.setParameter("selectedState", stDesc);
		List<Trip> foundTrips =	typedQuery.getResultList();
		em.close();
		return foundTrips;
	}
	
	public List<Trip> searchForTripByAttraction(String attDesc)	{
		//	TODO	Auto-generated	method	stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Trip> typedQuery	= em.createQuery("select t	from "
				+ "Trip t where t.Attraction = :selectedAttraction", Trip.class);
		typedQuery.setParameter("selectedAttraction", attDesc);
		List<Trip> foundTrips =	typedQuery.getResultList();
		em.close();
		return foundTrips;
	}
	
	
	public void	cleanUp(){
		emfactory.close();
		}
}
