package movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movies.models.Update;
import movies.repositories.UpdateRepository;

@Service
public class UpdateService {
	
	@Autowired
	private UpdateRepository updateRepository;
	
	public Update create(Update update) {
		return updateRepository.save(update);
	}
}
