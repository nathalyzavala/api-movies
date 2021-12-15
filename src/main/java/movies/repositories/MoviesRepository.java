package movies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import movies.models.Movie;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer>{
	
	/*Consulta nativa, Permite filtrar las peliculas por disponibilidad */
	
	@Query(value="SELECT * FROM movies WHERE movies.availability = ?", nativeQuery=true)
	List<Movie> fidByAvailibility(boolean availability);
	
	/* Consulta nativa, recupera solo las peliculas disponibles */
	@Query(value="SELECT * FROM movies WHERE movies.availability = TRUE", nativeQuery=true)
	List<Movie> fidByMovies();
	
	/* Consulta nativa, Permite filtrar las peliculas por nombre */
	@Query(value="SELECT * FROM movies WHERE movies.title = ?", nativeQuery=true)
	List<Movie> fidByMoviesName(String title);
	
	/* Consulta nativa, Recupera el precio de venta de una pelicula en especifico para asignarlo al shoppingDetail e identificar
	 a que precio fue vendida*/
	@Query(value="SELECT m.sale_price FROM movies m WHERE m.id_movies = ?", nativeQuery=true)
	double returnSalePrice(int id_movies);
	
	/* Consulta nativa, Recupera el precio de alquiler de una pelicula en especifico para asignarlo al rentalDetail e identificar
	 a que precio se alquilo*/
	@Query(value="SELECT m.rental_price FROM movies m WHERE m.id_movies = ?", nativeQuery=true)
	double returnRentalPrice(int id_movies);
}
