package pti.sb_rentalcar_mvc.dto;

public class EditCarDto {
	
	private int id;
	private String type;
	private int price;
	private boolean active;
	private String base64Image;
	
	public EditCarDto(int id, String type, int price, boolean active) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
		this.active = active;
	}
	
	

	public EditCarDto(int id, String type, int price, boolean active, String base64Image) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
		this.active = active;
		this.base64Image = base64Image;
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

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
	
	
	
	
	

}
