package pti.sb_rentalcar_mvc.dto;

public class CarDto {
	
	private int id;
	private String type;
	private int price;
	private long totalPrice;
	
	public CarDto(int id, String type, int price) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
	}

	public CarDto(String type, long totalPrice) {
		super();
		this.type = type;
		this.totalPrice = totalPrice;
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

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	

}
