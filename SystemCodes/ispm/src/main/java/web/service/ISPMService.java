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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ISPMService {
    private ISPMIntegration ispmIntegration = new ISPMIntegration();
    @Autowired private CalcService calcService;

    @Autowired
    ISPPoliciesRepository ispPoliciesRepository;
    @Autowired
    ISPCompPoliciesFeatureViewRepository ispCompPoliciesFeatureViewRepository;
    @Autowired
    ISPCompPoliciesPremiumRepository ispCompPoliciesPremiumRepository;

    public PolicyDto getMatchedPolicy(ApplicationDto applicationDto) {
        Map<String, Map<String, BigDecimal>> scoreForPolicyFeature = calcService.getScoreForPolicyFeature();
        String policyName = "AIA HealthShield Gold Max A";
        String featureName = "PostHospCovg_days";

        Map<String, BigDecimal> featureToWeights1 = scoreForPolicyFeature.get(policyName);
        Map<String, BigDecimal> featureToWeights = calcService.getScoreForPolicyFeature(policyName);
        BigDecimal score1 = featureToWeights.get(featureName);
        BigDecimal score = calcService.getScoreForPolicyFeature(policyName, featureName);
        //        Map<String, BigDecimal> policyScore = .getPolicyScore();

        ispmIntegration.setPolicyList(getPolicyList());
        Application result = ispmIntegration.getMatchedPolicy( ApplicationConverter.convertFromApplicationDto(applicationDto));
        PolicyDto policyDto=new PolicyDto();
        policyDto.setName(result.getMatchedPolicy().getName());
        policyDto.setBenefit(result.getMatchedPolicy().getBenefit());
        policyDto.setIssuer(result.getMatchedPolicy().getInsurer());
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
                        policy.setPreHospitalisationCoveredDays(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("PostHospCovg_days")){
                        policy.setPostHospitalisationCoveredDays(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Pre_Hosp_Covg")){
                        policy.setPreHospitalisationCoverage(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Post_Hosp_Covg")){
                        policy.setPostHospitalisationCoverage(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Annual_Covg")){
                        policy.setPolicyYearLimit(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CoInsurance")){
                        policy.setCoinsurance(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Deductible")){
                        policy.setDeductible(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("NonPanelSurcharge")){
                        policy.setNonPanelSurcharge(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CoPayCappedAt")){
                        policy.setCoPayCappedAt(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CommunityHospital")){  // from here , db not covered
                        policy.setCommunityHospital(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Surgery_Covg")){
                        policy.setSurgery(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("MajorOrganTransplant_Covg")){
                        policy.setMajorOrganTransplant(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("ClaimsProcessingDuration")){
                        policy.setClaimsProcessingDuration(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CriticalIllnesses_Covg")){
                        policy.setCriticalIllnesses(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("EmergencyOverseasTreatment")){
                        policy.setEmergencyOverseasTreatment(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Prosthesis")){
                        policy.setProsthesis(Integer.parseInt(ispCompPolFeatureView.getBenefits()));
                    }
                }
            }
            List<Premium> premiumList=new ArrayList<>();
            for(ISPCompPolicyPremium ispCompPolicyPremium:ispCompPoliciesPremiumRepository.findAll()){
                if(ispCompPolicyPremium.getComppolicyId()==policyId){
                    Premium premium=new Premium();
                    premium.setAgeGroup(ispCompPolicyPremium.getAge());
                    premium.setPremiumByAge(ispCompPolicyPremium.getPremAmount());
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

}
