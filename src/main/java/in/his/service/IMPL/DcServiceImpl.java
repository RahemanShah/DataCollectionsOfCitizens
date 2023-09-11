package in.his.service.IMPL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.his.Entity.CitizenAppEntity;
import in.his.Entity.DcCaseEntity;
import in.his.Entity.DcChildrenEntity;
import in.his.Entity.DcEducationEntity;
import in.his.Entity.DcIncomeEntity;
import in.his.Entity.PlanEntity;
import in.his.binding.DcSummary;
import in.his.binding.Education;
import in.his.binding.Income;
import in.his.binding.PlanSelection;
import in.his.binding.child;
import in.his.binding.Rest.childRequest;
import in.his.repo.CitizenAppRepository;
import in.his.repo.DcCaseRepo;
import in.his.repo.DcChildrenRepo;
import in.his.repo.DcEducationRepo;
import in.his.repo.DcIncomeRepo;
import in.his.repo.planRepo;
import in.his.service.DcService;

@Service
public class DcServiceImpl implements DcService {

	
	@Autowired
	private  DcCaseRepo dcCaseRepo;
	
	@Autowired
	private planRepo planrepo;
	
	@Autowired
	private DcIncomeRepo incomeRepo;
	
	@Autowired
	private DcEducationRepo eduRepo;
	
	@Autowired
	private DcChildrenRepo childrenRepo;
	
	@Autowired
	private CitizenAppRepository citizenEntityRepo;
	
	
	@Override
	public Long loadCaseNum(Integer appId) {
		
		Optional<CitizenAppEntity> citizenEntity =  citizenEntityRepo.findById(appId);
		
		if(citizenEntity.isPresent()) {
			
			DcCaseEntity caseEntity = new DcCaseEntity();
			caseEntity.setAppId(appId);
			                                                      //sir logic 
			//caseEntity =  dcCaseRepo.save(caseEntity);
			DcCaseEntity saveDcCase = dcCaseRepo.save(caseEntity);
			return saveDcCase.getCaseNum();
		}
		return 0l;       //its zero with L bcoz its long operation
		
		
		//My logic  |>
		
//		Optional<CitizenAppEntity> citizenEntity =  citizenEntityRepo.findById(appId);
//		
//		if(citizenEntity.isPresent()) {
//			
//			 DcCaseEntity caseEntity = new DcCaseEntity();
//			 caseEntity.setAppId(appId);
//			 
//			 dcCaseRepo.save(caseEntity);
//			                                                      //my logic
//			 if(caseEntity.getCaseNum()!=null) {
//				return caseEntity.getCaseNum();
//			 }
//		}
//		
//		
//	      return 0l;
	}

	
	@Override
	public Map<Integer, String> getPlanNames() {
		
		List<PlanEntity> allPlans =  planrepo.findAll();
		
		Map<Integer, String> planMap = new HashMap<>();
		
		for(PlanEntity pEntity : allPlans) {
		  planMap.put(pEntity.getPlanId(),pEntity.getPlanName()); 
		}
		return planMap;
	}

	
	@Override
	public Long savePlanSelection(PlanSelection planSelection) {
		
	  Optional<DcCaseEntity> caseEntity = dcCaseRepo.findById(planSelection.getCaseNum());
		
	  if(caseEntity.isPresent()) {
		  
		 DcCaseEntity dcEntity = caseEntity.get();
		 dcEntity.setPlanId(planSelection.getPlanId());
		 
		 dcCaseRepo.save(dcEntity);
		 
		 return planSelection.getCaseNum();
	  }
		return null;
	}
	
	

	@Override
	public Long saveIncomeData(Income income) {
		
		DcIncomeEntity incomeEntity = new DcIncomeEntity();
	    BeanUtils.copyProperties(income, incomeEntity);
	    
	    incomeRepo.save(incomeEntity);
	    
//	    if(income.getCaseNum()!=null) {
//	    	return income.getCaseNum();
//	    }
//		return null;       //or both will work
	    
	   return income.getCaseNum();
	}

	@Override
	public Long saveEducation(Education education) {
		
//      DcEducationEntity educationEntity = new DcEducationEntity();
//	    educationEntity.setEduId(education.getEduId());
//	    educationEntity.setCaseNum(education.getCaseNum());
//	    
//	    educationEntity =  eduRepo.save(educationEntity);
//	    
//	     if(education.getCaseNum()!=null) {
//	    	return education.getCaseNum();
//	     }
//		return null;  /which logic is fine to develop thiss
	    
		
	    DcEducationEntity educationEntity = new DcEducationEntity();
	    BeanUtils.copyProperties(education, educationEntity);
	    
	    eduRepo.save(educationEntity);
	    
	    return education.getCaseNum();
	}

	@Override
	public Long saveChildrens(childRequest request) {
		
		List<child> childList =  request.getChilds();
		
		for(child c : childList) {	
			
		DcChildrenEntity childrenEntity = new DcChildrenEntity();
		BeanUtils.copyProperties(c, childrenEntity);
			
		childrenRepo.save(childrenEntity);
		
		}

	   return request.getCaseNum();
	}

	
	
	@Override
	public DcSummary getSummary(Long caseNumber) {
		
		String plannames = "";
		
	 DcIncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNumber);
	 DcEducationEntity educationEntity =  eduRepo.findByCaseNum(caseNumber);
	 List<DcChildrenEntity> childrenEntity =  childrenRepo.findByCaseNum(caseNumber);
	 
	 Optional<DcCaseEntity> caseEntity =  dcCaseRepo.findById(caseNumber);
	 if(caseEntity.isPresent()) {
		Integer planid =  caseEntity.get().getPlanId();
		
		
		Optional<PlanEntity> plans =  planrepo.findById(planid);
		if(plans.isPresent()) {
		 plannames = plans.get().getPlanName();
		}
	 }
	 
	 DcSummary summary = new DcSummary();
	 summary.setPlanName(plannames);
	 
	 
	 Income income = new Income();
	 BeanUtils.copyProperties(incomeEntity, income);
	 summary.setIncome(income);
	 
	 
	 Education eudcation = new Education();
	 BeanUtils.copyProperties(educationEntity, eudcation);
	 summary.setEducation(eudcation);
	 
	 
	 List<child> lis = new ArrayList<>();
	 
	 for(DcChildrenEntity entity : childrenEntity) {
		 child c = new child();
		 BeanUtils.copyProperties(entity, c);
		 lis.add(c);
	 }
	  summary.setChilds(lis);   //u wrote the summary ouside the baracket bcon it will not come in for loop it will come in list object
	 
	  return summary;
	}

}
