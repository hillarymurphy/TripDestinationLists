package model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

	@Entity
	@Table(name="trip_list_details")
	public class TripListDetails {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="TRIP_LIST_ID")
		private int id;
		@Column(name="TRIP_LIST_NAME")
		private String listName;
		@Column(name="TRIP_DATE")
		private LocalDate tripDate;
		@ManyToOne(cascade=CascadeType.PERSIST)
		@JoinColumn(name="TOURIST_ID")
		private Tourist tourist;
		@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
		@JoinTable
		( 
			name="trips_on_list",
			joinColumns= { @JoinColumn(name="TRIP_LIST_ID", referencedColumnName="TRIP_LIST_ID") },
			inverseJoinColumns= { @JoinColumn(name="TOURIST_ID", referencedColumnName="ID", unique=true) }
			)
		private List<Trip> listOfTrips;
	
	public TripListDetails() {
		
	}
	
	public TripListDetails(int id, String listName, LocalDate tripDate,
			Tourist tourist, List<Trip> listOfItems) {
		this.id = id;
		this.listName = listName;
		this.tourist = tourist;
		this.listOfTrips = listOfItems;
	}

	public TripListDetails(String listName, LocalDate tripDate,
			Tourist tourist, List<Trip> listOfItems) {
		this.listName = listName;
		this.tourist = tourist;
		this.listOfTrips = listOfItems;
	}
	
	public TripListDetails(String listName, LocalDate tripDate, Tourist tourist) {
		this.listName = listName;
		this.tourist = tourist;
	}

	public void setListOfItems(List<Trip> listOfTrips) {
		this.listOfTrips = listOfTrips;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public LocalDate getTripDate() {
		return tripDate;
	}

	public void setTripDate(LocalDate tripDate) {
		this.tripDate = tripDate;
	}

	public Tourist getTourist() {
		return tourist;
	}

	public void setTourist(Tourist tourist) {
		this.tourist = tourist;
	}

	public List<Trip> getListOfTrips() {
		return listOfTrips;
	}

	public void setListOfTrips(List<Trip> listOfTrips) {
		this.listOfTrips = listOfTrips;
	}

	@Override
	public String toString() {
		return "TripListDetails [id=" + id + ", listName=" + listName + ", tripDate=" + tripDate + ", tourist="
				+ tourist + ", listOfTrips=" + listOfTrips + "]";
	}
	
}
