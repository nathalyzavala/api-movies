package movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movies.models.ShoppingDetail;
import movies.repositories.ShoppingDetailRepository;

@Service
public class ShoppingDetailService {
	
	@Autowired
	private ShoppingDetailRepository shoppingDetailRepository;
	
	public List<ShoppingDetail> create(List<ShoppingDetail> shoppingDetail) {
		return shoppingDetailRepository.saveAll(shoppingDetail);
	}
	
	public List<ShoppingDetail> getAll(){
		return shoppingDetailRepository.findAll();
	}
	
	public ShoppingDetail findByID(int id_shopping_detail) {
		return shoppingDetailRepository.findById(id_shopping_detail).get();
	}
	
	public boolean isEmptyByID(int id_shopping_detail) {
		return shoppingDetailRepository.findById(id_shopping_detail).isEmpty();
	}
	
	public List<Object> getAllShopping(){
		return shoppingDetailRepository.findAllShopping();
	}
	
}
