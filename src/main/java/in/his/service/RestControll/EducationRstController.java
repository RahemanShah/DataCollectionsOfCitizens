package in.his.service.RestControll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.his.binding.Education;
import in.his.service.DcService;

@RestController
public class EducationRstController {
	
	@Autowired
	private DcService service;
	
	@PostMapping("/education")
	public ResponseEntity<Long> saveEducation(@RequestBody Education education){
		
		Long caseNum =  service.saveEducation(education);
		return new ResponseEntity<Long>(caseNum, HttpStatus.CREATED);
	}

}
