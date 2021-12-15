package movies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import movies.models.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
	
	/* Consulta nativa que retorna el encabezado de el alquiler cuando la fecha de devolucion ya expiro y el campo
	 state = false, esta consulta es utilizada en el metodo que se ejecuta todos los dias a la 2 am para identificar
	 todos aquellos usuarios que seran penalizados por no devolver la pelicula a tiempo  */
	
	@Query(value="SELECT * FROM rental r WHERE  r.return_date < now() AND r.state=false", nativeQuery=true)
	List<Rental> getArray();
}
