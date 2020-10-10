package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tourist")
public class Tourist {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TOURIST_ID")
	private int id;
	@Column(name="TOURIST_NAME")
	private String touristName;
	
	
	public Tourist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tourist(int id, String touristName) {
		super();
		this.id = id;
		this.touristName = touristName;
	}
	
	public Tourist(String touristName) {
		super();
		this.touristName = touristName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTouristName() {
		return touristName;
	}
	
	public void setTouristName(String touristName) {
		this.touristName = touristName;
	}
	
	@Override
	public String toString() {
		return "Tourist [id=" + id + ", touristName=" + touristName + "]";
	}
	

}
