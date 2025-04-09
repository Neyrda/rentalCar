package pti.sb_rentalcar_mvc.database;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import pti.sb_rentalcar_mvc.model.Booking;
import pti.sb_rentalcar_mvc.model.Car;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;

	public Database() {
		
		Configuration cfg = new Configuration();
		cfg.configure();
		
		this.sessionFactory = cfg.buildSessionFactory();
	}

	public List<Car> findAvailableCars(LocalDate startDate, LocalDate endDate) {
		
		List<Car> availableCars = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query<Car> query = session.createQuery(
				"SELECT c FROM Car c WHERE c.active = true AND c.id NOT IN(SELECT b.carId FROM Booking b WHERE(:startDate <= b.endDate AND :endDate >= b.startDate))", Car.class);
		
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		
		availableCars = query.getResultList();
		
		tx.commit();
		session.close();
		
		return availableCars;
	}

	public void saveNewBooking(Booking newBooking) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(newBooking);
		
		tx.commit();
		session.close();
		
	}

	public Car getCarByCarId(int carId) {
		
		Car car = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		car = session.get(Car.class, carId);
		
		tx.commit();
		session.close();
		
		
		return car;
	}

	public List<Car> getAllCars() {
		
		List<Car> allCars = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Car> sq = session.createSelectionQuery(
				"SELECT c FROM Car c", Car.class);
		
		allCars = sq.getResultList();
		
		tx.commit();
		session.close();
		
		
		return allCars;
	}

	public List<Booking> getAllBooking() {
		
		List<Booking> savedBookings = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Booking> sq =session.createSelectionQuery(
				"SELECT b FROM Booking b", Booking.class);
		
		savedBookings = sq.getResultList();
		
		tx.commit();
		session.close();
		
		return savedBookings;
	}

	public void saveCar(Car newCar) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(newCar);
		
		tx.commit();
		session.close();
		
	}


	public void modifyCar(Car car) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.merge(car);
		
		tx.commit();
		session.close();
		
	}

}
