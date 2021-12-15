package movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movies.models.Rental;
import movies.repositories.RentalRepository;

@Service
public class RentalService {

	@Autowired
	private RentalRepository rentalrepository;
	
	public Rental create(Rental rental) {
		return rentalrepository.save(rental);
	}
	
	public List<Rental> createAll(List<Rental> rental) {
		return rentalrepository.saveAll(rental);
	}
	
	public List<Rental> getAll(){
       return rentalrepository.getArray();
	}
}
