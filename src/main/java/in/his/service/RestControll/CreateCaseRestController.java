package in.his.service.RestControll;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.his.binding.Rest.createCaseResponse;
import in.his.service.DcService;

@RestController
public class CreateCaseRestController {
	
	@Autowired
	private DcService service;
	                                                       //we use pathcariable when we have ui with number id like that @GetMapping("/case/{appId}")
	@GetMapping("/case/{appId}")
	public ResponseEntity<createCaseResponse> createCase(@PathVariable Integer appId){  
		
	   Long caseNum = service.loadCaseNum(appId);
	   Map<Integer, String> planName = service.getPlanNames();
	   
	   createCaseResponse response = new createCaseResponse();
	   response.setCaseNum(caseNum);
	   response.setPlanName(planName);
	   
	   return new ResponseEntity<>(response, HttpStatus.OK);
	   
	}
}
