package movies.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movies.models.Rental;
import movies.models.RentalDetail;
import movies.models.User;
import movies.services.MoviesService;
import movies.services.RentalDetailService;
import movies.services.RentalService;
import movies.services.UsersService;

@RestController
@RequestMapping("/api/v1/rental")
public class RentalDetailController {

	@Autowired
	private RentalDetailService rentalDetailService;
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private MoviesService moviesService;
	
	@Autowired
	private UsersService userService;
	
	/* Permite insertar un arreglo de las peliculas a alquilar, la fecha de alquiler se genera automaticamente*/
	@PostMapping("/{id_users}")
	public ResponseEntity<Object> create(@RequestBody() List<RentalDetail> rentalDetail, @PathVariable("id_users") int id_users){
		
		if(rentalDetail.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("mensaje", "Ha ocurrido un error"));
		}else {
			Rental rental = new Rental();
			User use = userService.findBy(id_users);
			
			rental.setUsers(use);
			rental.setState(false);
			Rental newRental = rentalService.create(rental);
			
			for(RentalDetail d:rentalDetail) {
				d.setRental(newRental);
				d.setPrice(moviesService.returnRentalPrice(d.getMovies().getIdMovies()));
			}
			rentalDetailService.create(rentalDetail);
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Se ha alquilado la pelicula"));			
		}

	}
	
	/* Proporciona toda la informacion referente a la renta entre estas, el usuario las peliculas que rentaron costo, cantidad*/
	@GetMapping
	public ResponseEntity<Object> getAllRental(){
		List<Object> entities = new ArrayList<>();
		
		rentalDetailService.getAllRental().forEach(entities::add);
			
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(entities, HttpStatus.OK);			
		}
	}
	
}
