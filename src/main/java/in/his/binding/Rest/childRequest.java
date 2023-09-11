package in.his.binding.Rest;

import java.util.List;

import in.his.binding.child;
import lombok.Data;

@Data	
public class childRequest {
	
	private Long caseNum;
	private List<child> childs;

}
