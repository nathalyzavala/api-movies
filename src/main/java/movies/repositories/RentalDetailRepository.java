package movies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import movies.models.RentalDetail;

@Repository
public interface RentalDetailRepository extends JpaRepository<RentalDetail, Integer> {
	
	/* Consulta nativa que proporciona los datos de las peliculas rentadas, el usuario que la compro, la pelicula, descripcion
	 y se recupera de la tabla detalle el costo del alquiler de la pelicula ya que este podria ser modificado por el administrador
	 en esta consultase controla un estado para identificar si la fecha de devolucion ya expiro, si la fecha de devolucion ya paso
	 el campo state contiene un true, caso contrario false
	 
	 Postman: Rental -> GET RENTAL
	  */
	
	@Query(value="SELECT use.username As cliente, mov.title AS pelicula, mov.description AS descripcion, rent.rental_date AS fecha, rent.return_date\r\n"
			+ "AS dechaDevolucion, rd.quantity AS cantidad, rd.price AS precio, rent.state, rd.id_rental_detail\r\n"
			+ "FROM rental_detail rd, (SELECT r.id_rental, r.id_users, r.rental_date, r.return_date, r.state FROM rental r) AS rent,\r\n"
			+ "(SELECT u.id_users, u.username FROM users u) AS use, (SELECT m.id_movies, m.title, m.description FROM movies m) AS mov\r\n"
			+ "WHERE rd.id_rental = rent.id_rental AND rent.id_users = use.id_users AND rd.id_movies = mov.id_movies\r\n"
			+ "ORDER BY rent.rental_date", nativeQuery=true)
	List<Object> getAllRental();
	
}
