package web.converter;

import com.iss_mr.integrated_shield_plan_master.Applicant;
import com.iss_mr.integrated_shield_plan_master.Application;
import com.iss_mr.integrated_shield_plan_master.Preference;
import com.iss_mr.integrated_shield_plan_master.PreferenceMatrix;
import web.dao.ApplicationDto;
import web.service.CalcService;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
*  q1: collect preference?
*  q2: Ward type
*  q3: Premium amount payment
*  q4: Hospitalization coverage
*  q5: Annual coverage limit
* */
public class ApplicationConverter {

    public static Application convertFromApplicationDto(ApplicationDto applicationDto, CalcService calcService) {
        Applicant applicant=new Applicant();
        applicant.setName(applicationDto.getName());
        applicant.setAge(Integer.parseInt(applicationDto.getAge()));
        applicant.setGender(applicationDto.getGender());
        applicant.setNationality("Singaporean");
        applicant.setFamilySize(Integer.parseInt(applicationDto.getFamilySize()));
        applicant.setIncome(Integer.parseInt(applicationDto.getIncome()));
        applicant.setExpenditure(Integer.parseInt(applicationDto.getExpenditure()));
        applicant.setLoanAmount(Integer.parseInt(applicationDto.getLoanAmount()));
        applicant.setSpstatus(applicationDto.getStatus());

        Preference preference=new Preference();
        PreferenceMatrix preferenceMatrix_preHospitalisationCoveredDays=new PreferenceMatrix();
        preferenceMatrix_preHospitalisationCoveredDays.setExpectedValue(getNormalizedUserInput("PreHospCovg_days",calcService,applicationDto));
        preferenceMatrix_preHospitalisationCoveredDays.setImportance(getNormalizedImportance("PreHospCovg_days",applicationDto));
        preference.setPreHospitalisationCoveredDays(preferenceMatrix_preHospitalisationCoveredDays);

        PreferenceMatrix preferenceMatrix_postHospitalisationCoveredDays=new PreferenceMatrix();
        preferenceMatrix_postHospitalisationCoveredDays.setExpectedValue(getNormalizedUserInput("PostHospCovg_days",calcService,applicationDto));
        preferenceMatrix_postHospitalisationCoveredDays.setImportance(getNormalizedImportance("PostHospCovg_days",applicationDto));
        preference.setPostHospitalisationCoveredDays(preferenceMatrix_postHospitalisationCoveredDays);

        PreferenceMatrix preferenceMatrix_criticalIllnesses=new PreferenceMatrix();
        preferenceMatrix_criticalIllnesses.setExpectedValue(getNormalizedUserInput("CriticalIllnesses_Covg",calcService,applicationDto));
        preferenceMatrix_criticalIllnesses.setImportance(getNormalizedImportance("CriticalIllnesses_Covg",applicationDto));
        preference.setCriticalIllnesses(preferenceMatrix_criticalIllnesses);

        PreferenceMatrix preferenceMatrix_claimsProcessingDuration=new PreferenceMatrix();
        preferenceMatrix_claimsProcessingDuration.setExpectedValue(getNormalizedUserInput("ClaimsProcessingDuration",calcService,applicationDto));
        preferenceMatrix_claimsProcessingDuration.setImportance(getNormalizedImportance("ClaimsProcessingDuration",applicationDto));
        preference.setClaimsProcessingDuration(preferenceMatrix_criticalIllnesses);

        PreferenceMatrix preferenceMatrix_policyYearLimit=new PreferenceMatrix();
        preferenceMatrix_policyYearLimit.setExpectedValue(getNormalizedUserInput("Annual_Covg",calcService,applicationDto));
        preferenceMatrix_policyYearLimit.setImportance(getNormalizedImportance("Annual_Covg",applicationDto));
        preference.setPolicyYearLimit(preferenceMatrix_policyYearLimit);

        PreferenceMatrix preferenceMatrix_majorOrganTransplant=new PreferenceMatrix();
        preferenceMatrix_majorOrganTransplant.setExpectedValue(getNormalizedUserInput("MajorOrganTransplant_Covg",calcService,applicationDto));
        preferenceMatrix_majorOrganTransplant.setImportance(getNormalizedImportance("MajorOrganTransplant_Covg",applicationDto));
        preference.setMajorOrganTransplant(preferenceMatrix_majorOrganTransplant);

        PreferenceMatrix preferenceMatrix_emergencyOverseasTreatment=new PreferenceMatrix();
        preferenceMatrix_emergencyOverseasTreatment.setExpectedValue(getNormalizedUserInput("EmergencyOverseasTreatment",calcService,applicationDto));
        preferenceMatrix_emergencyOverseasTreatment.setImportance(getNormalizedImportance("EmergencyOverseasTreatment",applicationDto));
        preference.setEmergencyOverseasTreatment(preferenceMatrix_emergencyOverseasTreatment);

        PreferenceMatrix preferenceMatrix_communityHospital=new PreferenceMatrix();
        preferenceMatrix_communityHospital.setExpectedValue(getNormalizedUserInput("CommunityHospital",calcService,applicationDto));
        preferenceMatrix_communityHospital.setImportance(getNormalizedImportance("CommunityHospital",applicationDto));
        preference.setCommunityHospital(preferenceMatrix_communityHospital);

        PreferenceMatrix preferenceMatrix_prosthesis=new PreferenceMatrix();
        preferenceMatrix_prosthesis.setExpectedValue(getNormalizedUserInput("Prosthesis",calcService,applicationDto));
        preferenceMatrix_prosthesis.setImportance(getNormalizedImportance("Prosthesis",applicationDto));
        preference.setProsthesis(preferenceMatrix_prosthesis);

        PreferenceMatrix preferenceMatrix_premium=new PreferenceMatrix();
        preferenceMatrix_premium.setExpectedValue(getNormalizedUserInput("Premium",calcService,applicationDto));
        preferenceMatrix_premium.setImportance(getNormalizedImportance("Premium",applicationDto));
        preference.setPremium(preferenceMatrix_premium);

        PreferenceMatrix preferenceMatrix_surgery=new PreferenceMatrix();
        preferenceMatrix_surgery.setExpectedValue(getNormalizedUserInput("Surgery_Covg",calcService,applicationDto));
        preferenceMatrix_surgery.setImportance(getNormalizedImportance("Surgery_Covg",applicationDto));
        preference.setSurgery(preferenceMatrix_surgery);

        PreferenceMatrix preferenceMatrix_coinsurance=new PreferenceMatrix();
        preferenceMatrix_coinsurance.setExpectedValue(getNormalizedUserInput("CoInsurance",calcService,applicationDto));
        preferenceMatrix_coinsurance.setImportance(getNormalizedImportance("CoInsurance",applicationDto));
        preference.setCoinsurance(preferenceMatrix_coinsurance);

        PreferenceMatrix preferenceMatrix_coPayCappedAt=new PreferenceMatrix();
        preferenceMatrix_coPayCappedAt.setExpectedValue(getNormalizedUserInput("CoPayCappedAt",calcService,applicationDto));
        preferenceMatrix_coPayCappedAt.setImportance(getNormalizedImportance("CoPayCappedAt",applicationDto));
        preference.setCoPayCappedAt(preferenceMatrix_coPayCappedAt);

        PreferenceMatrix preferenceMatrix_deductible=new PreferenceMatrix();
        preferenceMatrix_deductible.setExpectedValue(getNormalizedUserInput("Deductible",calcService,applicationDto));
        preferenceMatrix_deductible.setImportance(getNormalizedImportance("Deductible",applicationDto));
        preference.setDeductible(preferenceMatrix_deductible);

        PreferenceMatrix preferenceMatrix_nonPanelSurcharge=new PreferenceMatrix();
        preferenceMatrix_nonPanelSurcharge.setExpectedValue(getNormalizedUserInput("NonPanelSurcharge",calcService,applicationDto));
        preferenceMatrix_nonPanelSurcharge.setImportance(getNormalizedImportance("NonPanelSurcharge",applicationDto));
        preference.setNonPanelSurcharge(preferenceMatrix_nonPanelSurcharge);

        PreferenceMatrix preferenceMatrix_postHospitalisationCoverage=new PreferenceMatrix();
        preferenceMatrix_postHospitalisationCoverage.setExpectedValue(getNormalizedUserInput("Post_Hosp_Covg",calcService,applicationDto));
        preferenceMatrix_postHospitalisationCoverage.setImportance(getNormalizedImportance("Post_Hosp_Covg",applicationDto));
        preference.setPostHospitalisationCoverage(preferenceMatrix_postHospitalisationCoverage);

        PreferenceMatrix preferenceMatrix_preHospitalisationCoverage=new PreferenceMatrix();
        preferenceMatrix_preHospitalisationCoverage.setExpectedValue(getNormalizedUserInput("Pre_Hosp_Covg",calcService,applicationDto));
        preferenceMatrix_preHospitalisationCoverage.setImportance(getNormalizedImportance("Pre_Hosp_Covg",applicationDto));
        preference.setPreHospitalisationCoverage(preferenceMatrix_preHospitalisationCoverage);

        Application application = new Application();
        application.setApplicant( applicant);
        application.setPreference(preference);
        application.setCanCollectPreference(applicationDto.getUserAnswers().get("Preference").getAnsValue().equals("TRUE"));
        return application;
    }

    /*
    Very Important   0.237543
    Important        0.235068
    Medium           0.20895
    Unimportant      0.193069
    Very Unimportant 0.12537
     */

    private static int getNormalizedImportance(String featureName,ApplicationDto applicationDto){
        int userImportance=1;
        if(applicationDto.getUserAnswers().get(featureName)!=null){
            userImportance=applicationDto.getUserAnswers().get(featureName).getPrefVal();
        }

        switch (userImportance) {
            case 1 :
                return 25;
            case  2:
                return 50;
            case  3:
                return 75;
            case  4:
                return 100;
            case  5:
                return 120;
            default:
                return 20;
        }
    }

    private static int getNormalizedUserInput(String featureName,CalcService calcService,ApplicationDto applicationDto){
        System.out.println("start normalizing "+featureName);
        int normalizedScore=0;
        if(applicationDto.getUserAnswers().get(featureName)!=null) {
            BigDecimal userExpecetdValue =applicationDto.getUserAnswers().get(featureName).getFeatureValue();
            BigDecimal score;
            if("Premium".equalsIgnoreCase(featureName)){
                score=calcService.getNormalValueWRTPremiumAge(Integer.parseInt(applicationDto.getAge()),userExpecetdValue);
            }
            else {
                score = calcService.getNormalValueWRTFeature(featureName, userExpecetdValue);
            }
            normalizedScore = score.setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue();
            System.out.format("\nNormalized score: %s : before %s , after default %s , rescaled %d ","Premium".equalsIgnoreCase(featureName)?featureName+" "+applicationDto.getAge():featureName,userExpecetdValue.toString(),score.toString(),normalizedScore);
            if(normalizedScore<0 || normalizedScore>100){
                throw new RuntimeException("Invalid normalizedScore found"+normalizedScore);
            }
        }
        return normalizedScore;
    }
}
