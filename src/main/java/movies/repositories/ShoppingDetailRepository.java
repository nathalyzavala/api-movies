package movies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import movies.models.ShoppingDetail;

@Repository
public interface ShoppingDetailRepository extends JpaRepository<ShoppingDetail, Integer> {
	
	/* Consulta nativa, Permite extraer toda la informacion correspondiente a la compra entre esta el usuario, las peliculas que adquirio,
	 el precio, cantidad entre otros */
	@Query(value="SELECT  us.username, shop.shopping_date, mov.title, mov.description, sd.quantity, sd.price\r\n"
			+ "FROM shopping_detail sd, (SELECT m.title, m.id_movies, m.description FROM movies m) AS mov, (SELECT u.id_users, u.username FROM users u) AS us,\r\n"
			+ "(SELECT sh.id_shopping, sh.shopping_date, sh.id_users FROM shopping sh) AS shop\r\n"
			+ "WHERE sd.id_movies = mov.id_movies AND sd.id_shopping = shop.id_shopping AND shop.id_users = us.id_users\r\n"
			+ "GROUP BY us.username, shop.shopping_date, mov.title, mov.description, sd.quantity, sd.price\r\n"
			+ "ORDER BY shop.shopping_date", nativeQuery=true)
	List<Object> findAllShopping();
}
