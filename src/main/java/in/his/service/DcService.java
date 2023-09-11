package in.his.service;

import java.util.List;
import java.util.Map;

import in.his.binding.DcSummary;
import in.his.binding.Education;
import in.his.binding.Income;
import in.his.binding.PlanSelection;
import in.his.binding.child;
import in.his.binding.Rest.childRequest;

public interface DcService {
	
	public Long loadCaseNum(Integer appId);
	
	public Map<Integer, String> getPlanNames(); 
	
	public Long savePlanSelection(PlanSelection planSelection);
	
	public Long saveIncomeData(Income income);
	
	public Long saveEducation(Education education);
	
	public Long saveChildrens(childRequest request);
	
	public DcSummary getSummary(Long caseNumber);//by using case number we have to fetch citizen in summary
	
	

}
