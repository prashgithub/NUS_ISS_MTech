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
import web.jpa.jparepository.*;
import web.jpa.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ISPMService {

    //@Autowired
    private ISPMIntegration ispmIntegration = new ISPMIntegration();

    @Autowired
    ISPPoliciesRepository ispPoliciesRepository;
    @Autowired
    ISPCompPoliciesFeatureViewRepository ispCompPoliciesFeatureViewRepository;
    @Autowired
    ISPCompPoliciesPremiumRepository ispCompPoliciesPremiumRepository;

    public PolicyDto getMatchedPolicy(ApplicationDto applicationDto) {
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
