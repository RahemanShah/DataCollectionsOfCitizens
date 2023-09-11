package in.his.binding;

import java.util.List;

import lombok.Data;

@Data
public class DcSummary {
	
	private Income income;
	private Education education;
	private List<child> childs;
	public String planName;

}
