package movies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import movies.models.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

	@Query(value="SELECT mov.title, mov.description, mov.rental_price, mov.sale_price, COUNT(r.reaction) FROM reactions r, (SELECT m.id_movies, m.title, m.description, m.rental_price, m.sale_price FROM movies m) AS mov WHERE r.id_movies = mov.id_movies AND r.reaction = true GROUP BY mov.title, mov.description, mov.rental_price, mov.sale_price", nativeQuery = true)
	List<Object> findReactionsMovie();
	
}
