package pti.sb_rentalcar_mvc.dto;

import java.time.LocalDate;

public class BookingDto {
	
	private int carId;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public BookingDto(int carId, LocalDate startDate, LocalDate endDate) {
		super();
		this.carId = carId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	
	

}
