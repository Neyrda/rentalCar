package pti.sb_rentalcar_mvc.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pti.sb_rentalcar_mvc.database.Database;
import pti.sb_rentalcar_mvc.dto.AdminDto;
import pti.sb_rentalcar_mvc.dto.BookingDto;
import pti.sb_rentalcar_mvc.dto.CarDto;
import pti.sb_rentalcar_mvc.dto.CarListDto;
import pti.sb_rentalcar_mvc.dto.EditCarDto;
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

	public AdminDto getAllBooking() {
		
		AdminDto adminDto = null;
		BookingDto bookingDto = null;
		CarDto carDto = null;
		
		List<BookingDto> bookingDtos = new ArrayList<>();
		List<CarDto> carDtos = new ArrayList<>();
		
		List<Car> allCars = database.getAllCars();
		
		for(int carIndex= 0; carIndex < allCars.size(); carIndex++) {
			Car currentCar = allCars.get(carIndex);
			carDto = new CarDto(
					currentCar.getId(),
					currentCar.getType(),
					currentCar.getPrice());
			carDtos.add(carDto);
		}
		
		List<Booking> savedBookings = database.getAllBooking();
		
		for(int bookingIndex = 0; bookingIndex < savedBookings.size(); bookingIndex++) {
			Booking currentBooking = savedBookings.get(bookingIndex);
			bookingDto = new BookingDto(
					currentBooking.getCarId(),
					currentBooking.getStartDate(),
					currentBooking.getEndDate());
			
			bookingDtos.add(bookingDto);
		}
		
		adminDto = new AdminDto(bookingDtos, carDtos);
		
		return adminDto;
	}

	public void addCar(String type, int price, boolean active) {
		
		Car newCar = new Car(
				type,
				price,
				active,
				null);
		
		database.saveCar(newCar);
		
	}

	public EditCarDto getCarById(int carId) {
		
		EditCarDto ecd = null;
		
		Car car = database.getCarByCarId(carId);
		
		if(car != null) {
			ecd = new EditCarDto(
					carId,
					car.getType(),
					car.getPrice(),
					car.isActive(),
					car.getImageBase64());
		}
		
		return ecd;
	}

	public void modifyCar(int carId, String type, int price, boolean active) {
		
		
		Car car = database.getCarByCarId(carId);
		
		if(car != null) {
			
			car.setType(type);
			car.setPrice(price);
			car.setActive(active);
			database.modifyCar(car);
			

			
		}
	}

	public void uploadImage(MultipartFile file, int carId) throws IOException {
		
		Car car = null;
		
		car = database.getCarByCarId(carId);
		
		byte[] bFile = file.getBytes();
		car.setImage(bFile);
		database.saveImage(car);
		
		
	}

}
