package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trips")
public class Trip {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="LOCATION")
	private String location;
	@Column(name="STATE")
	private String state;
	@Column(name="ATTRACTION")
	private String attraction;
	
	public Trip() {
		super();
	}
	
	public Trip(String location, String state, String attraction) {
		super();
		this.location = location;
		this.state = state;
		this.attraction = attraction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAttraction() {
		return attraction;
	}

	public void setAttraction(String attraction) {
		this.attraction = attraction;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", location=" + location + ", state=" + state + ", attraction=" + attraction + "]";
	}
	
}