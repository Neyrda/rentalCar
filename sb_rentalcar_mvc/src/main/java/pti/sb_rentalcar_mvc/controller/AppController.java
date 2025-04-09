package pti.sb_rentalcar_mvc.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_rentalcar_mvc.dto.AdminDto;
import pti.sb_rentalcar_mvc.dto.BookingDto;
import pti.sb_rentalcar_mvc.dto.CarDto;
import pti.sb_rentalcar_mvc.dto.CarListDto;
import pti.sb_rentalcar_mvc.dto.EditCarDto;
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
	
	@GetMapping("/admin")
	public String adminMainPage(Model model) {
		
		AdminDto adminDto = service.getAllBooking();
		
		model.addAttribute("adminDto", adminDto);
		
		return "admin.html";
	}
	
	@GetMapping("/admin/newcar")
	public String addCarMain(Model model) {
		
		model.addAttribute("actiontype", "new");
		
		return "editcar.html";
		
	}
	
	@PostMapping("/admin/newcar")
	public String addCar(
			Model model,
			@RequestParam("ctype") String type,
			@RequestParam("cprice") int price,
			@RequestParam("cactive") boolean active) {
		
		service.addCar(type, price, active);
		
		return "editcar.html";
		
	}
	
	@GetMapping("/admin/editcar")
	public String modifyCar(
			Model model,
			@RequestParam("cid") int carId) {
		
		EditCarDto ecd = service.getCarById(carId);
	
		model.addAttribute("ecd", ecd);
		
		return "editcar.html";
	}
	
	@PostMapping("/admin/editcar/finish")
	public String finishCar(
			Model model,
			@RequestParam("cid") int carId,
			@RequestParam("type") String type,
			@RequestParam("price") int price,
			@RequestParam("active") boolean active) {
		
		service.modifyCar(carId, type, price, active);
		
		AdminDto adminDto = service.getAllBooking();
		
		model.addAttribute("adminDto", adminDto);
		
		return "redirect:/admin";
		
	}
	
	
	
	

}
