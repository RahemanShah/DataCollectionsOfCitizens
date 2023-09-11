package in.his.service.RestControll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.his.binding.DcSummary;
import in.his.binding.Rest.childRequest;
import in.his.service.DcService;

@RestController
public class ChildRestController {
	
	
	@Autowired
	private DcService service;
	
	@PostMapping("/children")
	public ResponseEntity<DcSummary> saveChildren(@RequestBody childRequest request){
		
		Long caseNum = service.saveChildrens(request);
		
		DcSummary summary = service.getSummary(caseNum);
		
		
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}

}
