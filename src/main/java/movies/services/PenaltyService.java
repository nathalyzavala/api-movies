package movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movies.models.Penalty;
import movies.repositories.PenaltyRepository;

@Service
public class PenaltyService {
	
	@Autowired
	private PenaltyRepository penaltyRepository;
	
	public Penalty create(Penalty penalty) {
		return penaltyRepository.save(penalty);
	}
}
