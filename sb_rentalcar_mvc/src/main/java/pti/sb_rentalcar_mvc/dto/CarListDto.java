package pti.sb_rentalcar_mvc.dto;

import java.time.LocalDate;
import java.util.List;

public class CarListDto {
	
	private List<CarDto> carList;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public CarListDto(List<CarDto> carList, LocalDate startDate, LocalDate endDate) {
		super();
		this.carList = carList;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public List<CarDto> getCarList() {
		return carList;
	}

	public void setCarList(List<CarDto> carList) {
		this.carList = carList;
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
