package in.his.service.RestControll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.his.binding.PlanSelection;
import in.his.service.DcService;


@RestController
public class PlanSelectionRestController {
	
	@Autowired
	private DcService service;
	
	@PostMapping("/plansel")
	public ResponseEntity<Long> planSelections(@RequestBody PlanSelection planSelect){
		
		Long caseNum = service.savePlanSelection(planSelect);
		
		return new ResponseEntity<Long>(caseNum, HttpStatus.CREATED);
	}
	

}
