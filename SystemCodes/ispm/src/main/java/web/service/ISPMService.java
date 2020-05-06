package web.service;

import com.iss_mr.integrated_shield_plan_master.Application;
import com.iss_mr.optaisp.Policy;
import com.iss_mr.optaisp.Premium;
import integration.ISPMIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.converter.ApplicationConverter;
import web.dao.ApplicationDto;
import web.dao.PolicyDto;
import web.jpa.jparepository.ISPCompPoliciesFeatureViewRepository;
import web.jpa.jparepository.ISPCompPoliciesPremiumRepository;
import web.jpa.jparepository.ISPPoliciesRepository;
import web.jpa.model.ISPCompPolFeatureView;
import web.jpa.model.ISPCompPolicyPremium;
import web.jpa.model.ISPPolicies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Service
public class ISPMService {
    private ISPMIntegration ispmIntegration = new ISPMIntegration();
    @Autowired
    private CalcService calcService;
    @Autowired
    ISPPoliciesRepository ispPoliciesRepository;
    @Autowired
    ISPCompPoliciesFeatureViewRepository ispCompPoliciesFeatureViewRepository;
    @Autowired
    ISPCompPoliciesPremiumRepository ispCompPoliciesPremiumRepository;

    public PolicyDto getMatchedPolicy(ApplicationDto applicationDto) {
        ispmIntegration.setPolicyList(getPolicyList());
        Application result = ispmIntegration.getMatchedPolicy( ApplicationConverter.convertFromApplicationDto(applicationDto,calcService));
        PolicyDto policyDto=new PolicyDto();
        policyDto.setName(result.getMatchedPolicy().getName());
        policyDto.setBenefit(result.getMatchedPolicy().getBenefit()==null? "":result.getMatchedPolicy().getBenefit());
        policyDto.setIssuer(result.getMatchedPolicy().getInsurer()==null? "":result.getMatchedPolicy().getInsurer());
        return policyDto;
    }

    private List<com.iss_mr.optaisp.Policy> getPolicyList(){
        List<com.iss_mr.optaisp.Policy> policyList=new ArrayList<>();
        for(ISPPolicies ispPolicies:ispPoliciesRepository.findAll()){
            String policyName=ispPolicies.getPolicyName();
            int policyId=ispPolicies.getPolicyId();
            com.iss_mr.optaisp.Policy policy=new Policy();
            policy.setName(policyName);
            policy.setId(policyId);
            policy.setLastEntryAge(75);
            for(ISPCompPolFeatureView ispCompPolFeatureView: ispCompPoliciesFeatureViewRepository.findAll()){
                if(ispCompPolFeatureView.getPolicyName().equalsIgnoreCase(policyName)){
                    if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("PreHospCovg_days")){
                        policy.setPreHospitalisationCoveredDays(getNormalizedBenefit(policyName,"PreHospCovg_days",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("PostHospCovg_days")){
                        policy.setPostHospitalisationCoveredDays(getNormalizedBenefit(policyName,"PostHospCovg_days",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Pre_Hosp_Covg")){
                        policy.setPreHospitalisationCoverage(getNormalizedBenefit(policyName,"Pre_Hosp_Covg",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Post_Hosp_Covg")){
                        policy.setPostHospitalisationCoverage(getNormalizedBenefit(policyName,"Post_Hosp_Covg",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Annual_Covg")){
                        policy.setPolicyYearLimit(getNormalizedBenefit(policyName,"Annual_Covg",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CoInsurance")){
                        policy.setCoinsurance(getNormalizedBenefit(policyName,"CoInsurance",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Deductible")){
                        policy.setDeductible(getNormalizedBenefit(policyName,"Deductible",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("NonPanelSurcharge")){
                        policy.setNonPanelSurcharge(getNormalizedBenefit(policyName,"NonPanelSurcharge",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CoPayCappedAt")){
                        policy.setCoPayCappedAt(getNormalizedBenefit(policyName,"CoPayCappedAt",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CommunityHospital")){  // from here , db not covered
                        policy.setCommunityHospital(getNormalizedBenefit(policyName,"CommunityHospital",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Surgery_Covg")){
                        policy.setSurgery(getNormalizedBenefit(policyName,"Surgery_Covg",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("MajorOrganTransplant_Covg")){
                        policy.setMajorOrganTransplant(getNormalizedBenefit(policyName,"MajorOrganTransplant_Covg",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("ClaimsProcessingDuration")){
                        policy.setClaimsProcessingDuration(getNormalizedBenefit(policyName,"ClaimsProcessingDuration",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CriticalIllnesses_Covg")){
                        policy.setCriticalIllnesses(getNormalizedBenefit(policyName,"CriticalIllnesses_Covg",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("EmergencyOverseasTreatment")){
                        policy.setEmergencyOverseasTreatment(getNormalizedBenefit(policyName,"EmergencyOverseasTreatment",0));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Prosthesis")){
                        policy.setProsthesis(getNormalizedBenefit(policyName,"Prosthesis",0));
                    }
                }
            }
            List<Premium> premiumList=new ArrayList<>();
            for(ISPCompPolicyPremium ispCompPolicyPremium:ispCompPoliciesPremiumRepository.findAll()){
                if(ispCompPolicyPremium.getComppolicyId()==policyId){
                    Premium premium=new Premium();
                    premium.setAgeGroup(ispCompPolicyPremium.getAge());
                    int normalizedScore=getNormalizedBenefit(policyName,"Premium",ispCompPolicyPremium.getAge());
                    premium.setPremiumByAge(normalizedScore);
                    premiumList.add(premium);
                }
            }
            policy.setPremium(premiumList);
            if(validPolicy(policy)) {
                policyList.add(policy);
            }
        }
        return policyList;
    }

    private boolean validPolicy(com.iss_mr.optaisp.Policy policy){
          return policy.getPostHospitalisationCoveredDays()!=null && !policy.getPremium().isEmpty();
    }

    private int getNormalizedBenefit(String policyName,String featureName,int age){
        BigDecimal score;
        if("Premium".equalsIgnoreCase(featureName)){
            score =calcService.getNormalScoreForPolicyPremium(policyName,String.valueOf(age));
        }else{
            score=calcService.getNormalScoreForPolicyFeature(policyName, featureName);
        }
        int normalizedScore= score.setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue();
        System.out.format("\nPolicy Normalized score: %s - %s : default %s , rescaled %d ",policyName,featureName +" "+age,score.toString(),normalizedScore);
        return normalizedScore;
    }

}
