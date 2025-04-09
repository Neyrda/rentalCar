package pti.sb_rentalcar_mvc.model;

import java.util.Base64;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name= "type")
	private String type;
	
	@Column(name="price")
	private int price;
	
	@Column(name="active")
	private boolean active;
	
	@Lob
	@Column(name="image")
	private byte[] image;

	public Car() {
		super();
	}

	public Car(String type, int price, boolean active, byte[] image) {
		super();
		this.type = type;
		this.price = price;
		this.active = active;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getImageBase64() {
		
		String base64String = Base64.getEncoder().encodeToString(image);
		
		return base64String;
	}
	
	

}
