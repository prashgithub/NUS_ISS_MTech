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
        policyDto.setBenefit(result.getMatchedPolicy().getName()==null? "Private hospital,My own room,Choose my doctor,Cover for pre-post hospitalisation expenses":getPolicyInfo(result.getMatchedPolicy().getName().trim(),result.getApplicant().getAge()));
        policyDto.setIssuer(result.getMatchedPolicy().getInsurer()==null? "":result.getMatchedPolicy().getInsurer());
        return policyDto;
    }

    private String getPolicyInfo(String policyName,int age){
        StringBuilder sb=new StringBuilder();
        String featureInfo="%s: %s";
        int policyId=-1;
        for(ISPPolicies ispPolicies:ispPoliciesRepository.findAll()) {
            if(ispPolicies.getPolicyName().equalsIgnoreCase(policyName)){
                policyName=ispPolicies.getPolicyName();
                policyId=ispPolicies.getPolicyId();
                break;
            }
        }

        for(ISPCompPolicyPremium ispCompPolicyPremium:ispCompPoliciesPremiumRepository.findAll()){
            if(ispCompPolicyPremium.getComppolicyId()==policyId && ispCompPolicyPremium.getAge()==((age/10)*10)){
                sb.append(String.format(featureInfo,"Premium (age group "+((age/10)*10)+")",ispCompPolicyPremium.getPremAmount())).append(",");
            }
        }
            for(ISPCompPolFeatureView ispCompPolFeatureView: ispCompPoliciesFeatureViewRepository.findAll()){
                if(ispCompPolFeatureView.getPolicyName().equalsIgnoreCase(policyName)){
                    if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("PreHospCovg_days")){
                        sb.append(String.format(featureInfo,"Pre-hospitalization Coverage Days",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("PostHospCovg_days")){
                        sb.append(String.format(featureInfo,"Post-hospitalization Coverage Days",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Pre_Hosp_Covg")){
                        //sb.append(String.format(featureInfo,"Pre-hospitalization Coverage",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Post_Hosp_Covg")){
                       // sb.append(String.format(featureInfo,"Post-hospitalization Coverage",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Annual_Covg")){
                        sb.append(String.format(featureInfo,"Annual Coverage",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CoInsurance")){
                        sb.append(String.format(featureInfo,"Co-insurence",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Deductible")){
                        sb.append(String.format(featureInfo,"Annual Deductibles",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("NonPanelSurcharge")){
                        sb.append(String.format(featureInfo,"Non-panel Surcharge",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CoPayCappedAt")){
                        sb.append(String.format(featureInfo,"Co-payment Capped At",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CommunityHospital")){
                        sb.append(String.format(featureInfo,"Community Hospital Coverage Days",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Surgery_Covg")){
                        sb.append(String.format(featureInfo,"Surgery Coverage",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("MajorOrganTransplant_Covg")){
                        sb.append(String.format(featureInfo,"Major Organ Transplant Coverage",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("ClaimsProcessingDuration")){
                        sb.append(String.format(featureInfo,"Claims Processing Duration",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CriticalIllnesses_Covg")){
                        sb.append(String.format(featureInfo,"Additional Critical Illnesses Coverage",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("EmergencyOverseasTreatment")){
                        sb.append(String.format(featureInfo,"Emergency Overseas Treatment Coverage",ispCompPolFeatureView.getBenefits())).append(",");
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Prosthesis")){
                        sb.append(String.format(featureInfo,"Annual Prosthesis Coverage",ispCompPolFeatureView.getBenefits())).append(",");
                    }
                }
            }

       return sb.toString();
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
                        policy.setPreHospitalisationCoveredDays(getNormalizedBenefit(policyName,"PreHospCovg_days",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("PostHospCovg_days")){
                        policy.setPostHospitalisationCoveredDays(getNormalizedBenefit(policyName,"PostHospCovg_days",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Pre_Hosp_Covg")){
                        policy.setPreHospitalisationCoverage(getNormalizedBenefit(policyName,"Pre_Hosp_Covg",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Post_Hosp_Covg")){
                        policy.setPostHospitalisationCoverage(getNormalizedBenefit(policyName,"Post_Hosp_Covg",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Annual_Covg")){
                        policy.setPolicyYearLimit(getNormalizedBenefit(policyName,"Annual_Covg",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CoInsurance")){
                        policy.setCoinsurance(getNormalizedBenefit(policyName,"CoInsurance",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Deductible")){
                        policy.setDeductible(getNormalizedBenefit(policyName,"Deductible",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("NonPanelSurcharge")){
                        policy.setNonPanelSurcharge(getNormalizedBenefit(policyName,"NonPanelSurcharge",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CoPayCappedAt")){
                        policy.setCoPayCappedAt(getNormalizedBenefit(policyName,"CoPayCappedAt",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CommunityHospital")){
                        policy.setCommunityHospital(getNormalizedBenefit(policyName,"CommunityHospital",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Surgery_Covg")){
                        policy.setSurgery(getNormalizedBenefit(policyName,"Surgery_Covg",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("MajorOrganTransplant_Covg")){
                        policy.setMajorOrganTransplant(getNormalizedBenefit(policyName,"MajorOrganTransplant_Covg",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("ClaimsProcessingDuration")){
                        policy.setClaimsProcessingDuration(getNormalizedBenefit(policyName,"ClaimsProcessingDuration",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("CriticalIllnesses_Covg")){
                        policy.setCriticalIllnesses(getNormalizedBenefit(policyName,"CriticalIllnesses_Covg",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("EmergencyOverseasTreatment")){
                        policy.setEmergencyOverseasTreatment(getNormalizedBenefit(policyName,"EmergencyOverseasTreatment",ispCompPolFeatureView.getBenefits(),-1));
                    }else if(ispCompPolFeatureView.getPolicyFeature().equalsIgnoreCase("Prosthesis")){
                        policy.setProsthesis(getNormalizedBenefit(policyName,"Prosthesis",ispCompPolFeatureView.getBenefits(),-1));
                    }
                }
            }
            List<Premium> premiumList=new ArrayList<>();
            for(ISPCompPolicyPremium ispCompPolicyPremium:ispCompPoliciesPremiumRepository.findAll()){
                if(ispCompPolicyPremium.getComppolicyId()==policyId){
                    Premium premium=new Premium();
                    premium.setAgeGroup(ispCompPolicyPremium.getAge());
                    int normalizedScore=getNormalizedBenefit(policyName,"Premium",String.valueOf(ispCompPolicyPremium.getPremAmount()),ispCompPolicyPremium.getAge());
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

    private int getNormalizedBenefit(String policyName,String featureName,String inputValue,int age){
        BigDecimal score;
        int value=Integer.parseInt(inputValue);
        if("Premium".equalsIgnoreCase(featureName)){
            score =calcService.getNormalValueWRTPremiumAge(age,BigDecimal.valueOf(value));
        }else{
            score=calcService.getNormalValueWRTFeature(featureName, BigDecimal.valueOf(value));
        }
        int normalizedScore= score.setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue();
        System.out.format("\nPolicy Normalized score: %s %s : before %s , after default %s , rescaled %d ",policyName,"Premium".equalsIgnoreCase(featureName)?featureName+" "+age:featureName,inputValue,score.toString(),normalizedScore);
        if(normalizedScore<0 || normalizedScore>100){
            throw new RuntimeException("Invalid normalizedScore found"+normalizedScore);
        }
        return normalizedScore;
    }

}
