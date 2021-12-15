package movies.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import movies.models.Rental;
import movies.services.RentalService;

@Component
public class PenaltysRental {

	@Autowired
	private RentalService rentalService;
	
	/*Este metodo se ejecuta todos los días a la 2 am, se encarga de modificar el campo state a true
	 de la tabla renta cuando la fecha actual supera a la fecha en la que el usuario debia devolver la pelicula,
	 con este estado se identificara los usuarios que deberan pagar penalizacion*/
	
	@Scheduled(cron= "0 0 2 * * *")
	public void updateState() {
		List<Rental> rentalArray = new ArrayList<Rental>();
		List<Rental> newRentalArray = new ArrayList<Rental>();
		
		rentalService.getAll().forEach(rentalArray::add);

		for(Rental r: rentalArray) {
				r.setState(true);
				newRentalArray.add(r);
		}
		rentalService.createAll(newRentalArray);
	}
}
