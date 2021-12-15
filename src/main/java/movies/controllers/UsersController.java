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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movies.models.User;
import movies.services.UsersService;

@RestController
@RequestMapping("api/v1/user")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("")
	public ResponseEntity<List<User>> getAll(){
		List<User> entities = new ArrayList<>();
		usersService.findAll().forEach(entities::add);
		
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(entities, HttpStatus.OK);
		}
	} 
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody User user){
		user.setState(true);
		User use = usersService.create(user);
		if(use != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensaje",  "Se creo el usuario con exito"));
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Ha ocurrido un error"));
		}
	}
	
	@PutMapping("/{id_users}")
	public ResponseEntity<Object> disableEnable(@PathVariable("id_users") int id_users){
		boolean use = usersService.isEmptyByID(id_users);
		
		if(use) {
			return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("mensaje", "Ocurrio un error"));
		}
		
		User newUser = usersService.findBy(id_users);
		
		if(newUser.isState() == true) {
			newUser.setState(false);
			usersService.create(newUser);
			return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensaje", "Usuario desabilitado"));
		}else {
			newUser.setState(true);
			usersService.create(newUser);
			return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensaje", "Usuario habilitado"));
		}
	}
	
	@PutMapping("/e/{id_users}")
	public ResponseEntity<Object> update(@PathVariable("id_users") int id_users, @RequestBody User user){
		boolean use = usersService.isEmptyByID(id_users);
		
		if(use) {
			return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("mensaje", "Ocurrio un error"));
		}
		
		User newUser = usersService.findBy(id_users);
		newUser.setEmail(user.getEmail());
		newUser.setUsername(user.getUsername());
		newUser.setPasswords(user.getPasswords());
	//	newUser.setRol(user.isRol());
		
		usersService.create(newUser);
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("Mensaje", "Se edito el usuario"));
		
	}
	
}
