package in.his.service.RestControll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.his.binding.DcSummary;
import in.his.service.DcService;

@RestController
public class SummaryRestController {

	@Autowired
	private DcService service;
	
	
	@GetMapping("/getAllSummary")
	public ResponseEntity<?> getAllSummaryData(@RequestBody Long caseNumber){
		
		ResponseEntity<?> resp = null;
		
		try {
			DcSummary summary = service.getSummary(caseNumber);
			String Message = "We get All Data Throove"+summary+"Summary";
			
			resp = new ResponseEntity<>(Message, HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.getMessage();
			resp = new ResponseEntity<>("Internal Server Error", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	//this summary logic i wrote here 
}
