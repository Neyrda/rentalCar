package pti.sb_rentalcar_mvc.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_rentalcar_mvc.dto.BookingDto;
import pti.sb_rentalcar_mvc.dto.CarDto;
import pti.sb_rentalcar_mvc.dto.CarListDto;
import pti.sb_rentalcar_mvc.service.AppService;

@Controller
public class AppController {
	
	private AppService service;

	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/")
	public String mainPage() {
		
		return "index.html";
	}
	
	@GetMapping("/cars")
	public String showAvailableCars(
			Model model,
			@RequestParam("start_date") LocalDate startDate,
			@RequestParam("end_date") LocalDate endDate) {
		
		CarListDto carList = service.getCars(startDate, endDate);
		
		model.addAttribute("carList", carList);
		
		return "cars.html";
	}
	
	@GetMapping("/cars/startbooking")
	public String startBooking(
			Model model,
			@RequestParam("start_date") LocalDate startDate,
			@RequestParam("end_date") LocalDate endDate,
			@RequestParam("cid") int carId)
			 {
		
		BookingDto bookingDto = service.createBookingDto(startDate, endDate, carId);
		
		model.addAttribute("bookingDto", bookingDto);
		
		
		return "booking.html";
		
	}
	
	@PostMapping("/cars/endbooking")
	public String booking(
			Model model,
			@RequestParam("start_date") LocalDate startDate,
			@RequestParam("end_date") LocalDate endDate,
			@RequestParam("cid") int carId,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone) {
		
		service.saveBooking(startDate, endDate, carId, name, email, address, phone);
		
		CarDto carDto = service.getCarById(carId, startDate, endDate);
		
		model.addAttribute("carDto", carDto);
		
		
		return "thanks.html";
	}
	
	
	
	

}
