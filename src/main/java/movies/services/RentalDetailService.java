package movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movies.models.RentalDetail;
import movies.repositories.RentalDetailRepository;

@Service
public class RentalDetailService {
	
	@Autowired
	private RentalDetailRepository rentalDetailRepository;
	
	public List<RentalDetail> create(List<RentalDetail> rentalDetail) {
		return rentalDetailRepository.saveAll(rentalDetail);
	}
	
	public List<Object> getAllRental(){
		return rentalDetailRepository.getAllRental();
	}
	
	public RentalDetail findByID(int id_rental_detail) {
		return rentalDetailRepository.getById(id_rental_detail);
	}
} 
