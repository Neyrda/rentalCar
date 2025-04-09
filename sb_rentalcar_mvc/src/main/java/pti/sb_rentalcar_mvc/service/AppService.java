package pti.sb_rentalcar_mvc.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pti.sb_rentalcar_mvc.database.Database;
import pti.sb_rentalcar_mvc.dto.BookingDto;
import pti.sb_rentalcar_mvc.dto.CarDto;
import pti.sb_rentalcar_mvc.dto.CarListDto;
import pti.sb_rentalcar_mvc.model.Booking;
import pti.sb_rentalcar_mvc.model.Car;

@Service
public class AppService {
	
	private Database database;
	
	public AppService(Database database) {
		super();
		this.database = database;
	}

	public CarListDto getCars(LocalDate startDate, LocalDate endDate) {
		
		CarListDto carListDto = null;
		CarDto carDto = null;
		List<CarDto> carList = new ArrayList<>();
		
		List<Car> availableCars = database.findAvailableCars(startDate, endDate);
		
		if(availableCars != null) {
			for(int index = 0; index < availableCars.size(); index++) {
				Car currentCar = availableCars.get(index);
				
					carDto = new CarDto(
							currentCar.getId(),
							currentCar.getType(),
							currentCar.getPrice());
					
					carList.add(carDto);
				
			}
		}
		
		carListDto = new CarListDto(carList, startDate, endDate);
		
		return carListDto;
	}

	public BookingDto createBookingDto(LocalDate startDate, LocalDate endDate, int carId) {
		
		BookingDto bookingDto = new BookingDto(carId, startDate, endDate);
		
		return bookingDto;
	}

	public void saveBooking(LocalDate startDate, LocalDate endDate, int carId, String name, String email,
			String address, String phone) {
		
		Booking newBooking = new Booking(
				name,
				email,
				address,
				phone,
				startDate,
				endDate,
				carId);
		
		
		database.saveNewBooking(newBooking);
		
	}

	public CarDto getCarById(int carId, LocalDate startDate, LocalDate endDate) {
		
		CarDto carDto = null;
		Car bookedCar = database.getCarByCarId(carId);
		
		if(bookedCar != null) {
		
			long days = ChronoUnit.DAYS.between(startDate, endDate);
			long totalPrice = days * bookedCar.getPrice();
			
			
			carDto = new CarDto(bookedCar.getType(), totalPrice);
		
		}
		
		
		return carDto;
	}

	

	

}
