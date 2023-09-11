package in.his.binding.Rest;

import java.util.Map;

import lombok.Data;

@Data
public class createCaseResponse {
	
	private Long caseNum;
	private Map<Integer, String> planName;

}
