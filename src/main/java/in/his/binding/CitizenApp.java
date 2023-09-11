package in.his.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenApp {

	
	private String fullName;
	
	private String email;
	
	private long phno;
	
	private String gender;
	
	private long ssn;
	
	private LocalDate dob;

}
