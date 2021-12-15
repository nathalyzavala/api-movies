package movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movies.models.Shopping;
import movies.models.ShoppingDetail;
import movies.models.User;
import movies.services.MoviesService;
import movies.services.ShoppingDetailService;
import movies.services.ShoppingService;
import movies.services.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/shopping")
public class ShoppingController {
	
	@Autowired
	private ShoppingDetailService shoppingDetailService;
	
	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private MoviesService movieService;
	
	/* Almacena las peliculas que el usuario desea adquirir, este metodo almacena el arreglo dedichas peliculas,
	  para la fecha se retoma la actual y el precio de las peliculas es extraido de la base de datos*/
	@PostMapping("/{id_users}")
	public ResponseEntity<Object> create(@RequestBody() List<ShoppingDetail> detail, @PathVariable("id_users") int id_users){
		
		if(detail.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("message", "Seleccione las peliculas"));
		}else {
			Shopping shopp = new Shopping();
			User use = userService.findBy(id_users);
			shopp.setUsers(use);
			shopp.setState(true);
			Shopping shop = shoppingService.create(shopp);
			
			for(ShoppingDetail d: detail) {
				d.setShopping(shop);
				d.setPrice(movieService.returnSalePrice(d.getMovies().getIdMovies()));
			}
			shoppingDetailService.create(detail);
			
			 return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Se ha vendido la pelicula"));
		}
	}
	
	/* Extrae todos los datos de la compra como fecha usuario que realizo la compra y las peliculas que adquirio y el precio
	 al cual las adquirio*/
	@GetMapping
	public ResponseEntity<List<Object>> findAll(){
		List<Object> entities = new ArrayList<>();
		shoppingDetailService.getAllShopping().forEach(entities::add);
		
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(entities, HttpStatus.OK);
		}
	}
	
}
