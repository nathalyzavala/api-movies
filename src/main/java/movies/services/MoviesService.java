package movies.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movies.models.Movie;
import movies.repositories.MoviesRepository;


@Service
public class MoviesService  {
	
	@Autowired
	private MoviesRepository moviesRepository;
	
	public List<Movie> findAll(){		
		return moviesRepository.fidByMovies();
	}
	
	public Movie create(Movie movies) {
		return moviesRepository.save(movies);
	}
	

	public boolean isEmptyByID(int id_movies) {
		return moviesRepository.findById(id_movies).isEmpty();
	}
	
	public Movie getMovieByID(int id_movies) {
		return moviesRepository.findById(id_movies).get();
	}
	
	public HashMap<String, String> delete(int id_movies) {
		HashMap<String, String> resp = new HashMap<>();
		moviesRepository.deleteById(id_movies);
		resp.put("message", "Pelicula eliminada");
		return resp;
	}
	
	public Movie findBy(int id_movies) {
		return moviesRepository.findById(id_movies).get();
	}
	
	public List<Movie> findAllAvailibility(boolean availability){		
		return moviesRepository.fidByAvailibility(availability);
	}
	
	public List<Movie>findName(String title){
		return moviesRepository.fidByMoviesName(title);
	}
	
	public double returnSalePrice(int id_movies) {
		return moviesRepository.returnSalePrice(id_movies);
	}
	
	public double returnRentalPrice(int id_movies) {
		return moviesRepository.returnRentalPrice(id_movies);
	}
	
}
