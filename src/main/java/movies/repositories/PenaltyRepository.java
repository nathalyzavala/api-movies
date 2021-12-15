package movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import movies.models.Penalty;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalty, Integer> {

}
