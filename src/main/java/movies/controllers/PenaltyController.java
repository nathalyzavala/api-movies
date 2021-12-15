package movies.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movies.models.Penalty;
import movies.models.RentalDetail;
import movies.services.PenaltyService;
import movies.services.RentalDetailService;

@RestController
@RequestMapping("/api/v1/penalty")
public class PenaltyController {
	
	@Autowired
	private PenaltyService penaltyService;
	
	@Autowired 
	private RentalDetailService rentalDetailService;
	
	@PostMapping("/{id_rental_detail}")
	public ResponseEntity<Object> create(@RequestBody Penalty penalty, @PathVariable("id_rental_detail") int id_rental_detail){
		RentalDetail detail = rentalDetailService.findByID(id_rental_detail);
		penalty.setRentalDetail(detail);
		penalty.setPayment(false);
		Penalty penaltys = penaltyService.create(penalty);
		if(penaltys == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("mensaje","No se ha creado la penalizacion"));
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensaje","Se ha asignado una penalización"));
		}
	}
	
}
