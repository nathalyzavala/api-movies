package movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movies.models.Shopping;
import movies.repositories.ShoppingRepository;

@Service
public class ShoppingService {
	
	@Autowired
	private ShoppingRepository shoppingRepository;
	
	public Shopping create(Shopping shopping) {
		return shoppingRepository.save(shopping);
	}
	
	public List<Shopping> getAll() {
		return shoppingRepository.findAll();
	}
	
	public Shopping getByID(int id_shopping) {
		return shoppingRepository.findById(id_shopping).get();
	}
	
	public boolean isEmptyByID(int id_shopping) {
		return shoppingRepository.findById(id_shopping).isEmpty();
	}
}
