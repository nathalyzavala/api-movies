package movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movies.models.Movie;
import movies.models.User;
import movies.services.MoviesService;
import movies.services.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/movie")
public class MoviesController {
	
	@Autowired
	private MoviesService moviesService;
	
	@Autowired
	private UsersService usersService;
	
	/* Permite consultar solo las peliculas disponibles*/
	@GetMapping("")
	public ResponseEntity<List<Movie>> getAll(){
		List<Movie> entities = new ArrayList<>();
		moviesService.findAll().forEach(entities::add);
		
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(entities, HttpStatus.OK); 
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Object> create(@RequestBody Movie movies){
		movies.setAvailability(true);
		Movie mov = moviesService.create(movies);
		
		if(mov != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Se ha agregado la pelicula"));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "No se creo la pelicula"));
		}	
	}
	
	@DeleteMapping("/{id_movies}")
	public ResponseEntity<Object> deletes(@PathVariable("id_movies") int id_movies){
		
		boolean mov = moviesService.isEmptyByID(id_movies);
		
		if(!mov) {
			moviesService.delete(id_movies);
			return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Se elimino la pelicula"));
		}else {
			return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("message", "El id no existe"));
		}
	}
	
	@PutMapping("/{id_movies}")
	public ResponseEntity<Object> update(@PathVariable("id_movies") int id_movies, @RequestBody Movie movies){
		boolean mov = moviesService.isEmptyByID(id_movies);
		
		if(mov) {
			return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("message", "Ocurrio un error"));
		}
		
		Movie newMovies = moviesService.findBy(id_movies);
		newMovies.setTitle(movies.getTitle());
		newMovies.setDescription(movies.getDescription());
		newMovies.setImage(movies.getImage());
		newMovies.setStock(movies.getStock());
		newMovies.setRentalPrice(movies.getRentalPrice());
		newMovies.setSalePrice(movies.getSalePrice());
		newMovies.setAvailability(movies.isAvailability());
		newMovies.setNameimage(movies.getNameimage());
		
		moviesService.create(newMovies);
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Se edito la pelicula"));
	}
	
	/*Permite modificar la disponibilidad de las peliculas solo a los usuarios que poseen el rol de administrador*/
	@PutMapping("/{id_movies}/{id_users}")
	public ResponseEntity<Object> setAvailibility(@PathVariable("id_movies") int id_movies, @PathVariable("id_users") int id_users){
		boolean use = usersService.isEmptyByID(id_users);
		boolean movi = moviesService.isEmptyByID(id_movies);
		
		if(use == true || movi == true) {
			return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("mensaje", "Ocurrio un error"));	
		}else {		
			User user = usersService.findBy(id_users);		
			if(user.isRol() == true) {			
				Movie mov = moviesService.findBy(id_movies);	
				if(mov.isAvailability()==true) {
					mov.setAvailability(false);
					moviesService.create(mov);
					return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensaje", "Se deshabilito la pelicula"));
				}else {
					mov.setAvailability(true);
					moviesService.create(mov);
					return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensaje", "Se habilito la pelicula"));
				}			
			}
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("mensaje", "Ocurrio un error"));	
	}

	/*En el campo booleano availibility se le permite al usuario indicar si quiere ver las peliculas disponibles o no disponibles
	 esta accion sera realizada por los usuarios que posean el rol del administrador
	 Postman : MOVIES -> DISPONIBILIDAD / NO DISPONIBILIDAD
	  */
	@GetMapping("/{availability}/{id_users}")
	public ResponseEntity<List<Movie>> getAll(@PathVariable("availability") boolean availability, @PathVariable("id_users") int id_users){
		List<Movie> entities = new ArrayList<>();
		User user = usersService.findBy(id_users);
		
		if(user.isRol() == true) {			
			moviesService.findAllAvailibility(availability).forEach(entities::add);
			if(entities.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(entities, HttpStatus.OK); 
			}
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/*Permite filtrar las peliculas por su nombre */
	@GetMapping("/{title}")
	public ResponseEntity<List<Movie>> getName(@PathVariable("title") String title){
		List<Movie> entities = new ArrayList<>();
		
		 moviesService.findName(title).forEach(entities::add);
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(entities, HttpStatus.OK);
		}
	}
}
