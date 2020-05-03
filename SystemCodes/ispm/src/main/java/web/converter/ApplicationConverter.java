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
        applicant.setFamilySize(3);
        applicant.setIncome(100000);
        applicant.setExpenditure(50000);
        applicant.setLoanAmount(300000);
        applicant.setSpstatus(applicationDto.getStatus());

        Preference preference=new Preference();
        PreferenceMatrix preferenceMatrix_preHospitalisationCoveredDays=new PreferenceMatrix();
        preferenceMatrix_preHospitalisationCoveredDays.setExpectedValue(75);
        preferenceMatrix_preHospitalisationCoveredDays.setImportance(10);
        preference.setPreHospitalisationCoveredDays(preferenceMatrix_preHospitalisationCoveredDays);

        PreferenceMatrix preferenceMatrix_postHospitalisationCoveredDays=new PreferenceMatrix();
        preferenceMatrix_postHospitalisationCoveredDays.setExpectedValue(75);
        preferenceMatrix_postHospitalisationCoveredDays.setImportance(10);
        preference.setPostHospitalisationCoveredDays(preferenceMatrix_postHospitalisationCoveredDays);

        PreferenceMatrix preferenceMatrix_criticalIllnesses=new PreferenceMatrix();
        preferenceMatrix_criticalIllnesses.setExpectedValue(50);
        preferenceMatrix_criticalIllnesses.setImportance(5);
        preference.setCriticalIllnesses(preferenceMatrix_criticalIllnesses);

        PreferenceMatrix preferenceMatrix_claimsProcessingDuration=new PreferenceMatrix();
        preferenceMatrix_claimsProcessingDuration.setExpectedValue(25);
        preferenceMatrix_claimsProcessingDuration.setImportance(10);
        preference.setClaimsProcessingDuration(preferenceMatrix_criticalIllnesses);

        PreferenceMatrix preferenceMatrix_policyYearLimit=new PreferenceMatrix();
        preferenceMatrix_policyYearLimit.setExpectedValue(getNormalizedUserInput("Annual_Covg",1500000,calcService));
        preferenceMatrix_policyYearLimit.setImportance(getNormalizedImportance(applicationDto.getPre5()));
        preference.setPolicyYearLimit(preferenceMatrix_policyYearLimit);

        PreferenceMatrix preferenceMatrix_majorOrganTransplant=new PreferenceMatrix();
        preferenceMatrix_majorOrganTransplant.setExpectedValue(50);
        preferenceMatrix_majorOrganTransplant.setImportance(5);
        preference.setMajorOrganTransplant(preferenceMatrix_majorOrganTransplant);

        PreferenceMatrix preferenceMatrix_emergencyOverseasTreatment=new PreferenceMatrix();
        preferenceMatrix_emergencyOverseasTreatment.setExpectedValue(50);
        preferenceMatrix_emergencyOverseasTreatment.setImportance(5);
        preference.setEmergencyOverseasTreatment(preferenceMatrix_emergencyOverseasTreatment);

        PreferenceMatrix preferenceMatrix_communityHospital=new PreferenceMatrix();
        preferenceMatrix_communityHospital.setExpectedValue(50);
        preferenceMatrix_communityHospital.setImportance(5);
        preference.setCommunityHospital(preferenceMatrix_communityHospital);

        PreferenceMatrix preferenceMatrix_prosthesis=new PreferenceMatrix();
        preferenceMatrix_prosthesis.setExpectedValue(10);
        preferenceMatrix_prosthesis.setImportance(5);
        preference.setProsthesis(preferenceMatrix_prosthesis);

        PreferenceMatrix preferenceMatrix_premium=new PreferenceMatrix();
        preferenceMatrix_premium.setExpectedValue(6000);
        preferenceMatrix_premium.setImportance(getNormalizedImportance(applicationDto.getPre3()));
        preference.setPremium(preferenceMatrix_premium);

        PreferenceMatrix preferenceMatrix_surgery=new PreferenceMatrix();
        preferenceMatrix_surgery.setExpectedValue(50);
        preferenceMatrix_surgery.setImportance(10);
        preference.setSurgery(preferenceMatrix_surgery);

        PreferenceMatrix preferenceMatrix_coinsurance=new PreferenceMatrix();
        preferenceMatrix_coinsurance.setExpectedValue(25);
        preferenceMatrix_coinsurance.setImportance(5);
        preference.setCoinsurance(preferenceMatrix_coinsurance);

        PreferenceMatrix preferenceMatrix_coPayCappedAt=new PreferenceMatrix();
        preferenceMatrix_coPayCappedAt.setExpectedValue(25);
        preferenceMatrix_coPayCappedAt.setImportance(5);
        preference.setCoPayCappedAt(preferenceMatrix_coPayCappedAt);

        PreferenceMatrix preferenceMatrix_deductible=new PreferenceMatrix();
        preferenceMatrix_deductible.setExpectedValue(25);
        preferenceMatrix_deductible.setImportance(5);
        preference.setDeductible(preferenceMatrix_deductible);

        PreferenceMatrix preferenceMatrix_nonPanelSurcharge=new PreferenceMatrix();
        preferenceMatrix_nonPanelSurcharge.setExpectedValue(25);
        preferenceMatrix_nonPanelSurcharge.setImportance(5);
        preference.setNonPanelSurcharge(preferenceMatrix_nonPanelSurcharge);

        PreferenceMatrix preferenceMatrix_postHospitalisationCoverage=new PreferenceMatrix();
        preferenceMatrix_postHospitalisationCoverage.setExpectedValue(50);
        preferenceMatrix_postHospitalisationCoverage.setImportance(10);
        preference.setPostHospitalisationCoverage(preferenceMatrix_postHospitalisationCoverage);

        PreferenceMatrix preferenceMatrix_preHospitalisationCoverage=new PreferenceMatrix();
        preferenceMatrix_preHospitalisationCoverage.setExpectedValue(50);
        preferenceMatrix_preHospitalisationCoverage.setImportance(10);
        preference.setPreHospitalisationCoverage(preferenceMatrix_preHospitalisationCoverage);

        Application application = new Application();
        application.setApplicant( applicant);
        application.setPreference(preference);
        application.setCanCollectPreference(applicationDto.getAns1().equals("1"));
        return application;
    }

    /*
    Very Important   0.237543
    Important        0.235068
    Medium           0.20895
    Unimportant      0.193069
    Very Unimportant 0.12537
     */

    private static int getNormalizedImportance(String str){
        int value=Integer.parseInt(str);
        switch (value) {
            case 1 :
                return 12;
            case  2:
                return 19;
            case  3:
                return 21;
            case  4:
                return 23;
            case  5:
                return 24;
            default:
                return 20;
        }
    }

    private static int getNormalizedUserInput(String featureName,int userExpecetdValue,CalcService calcService){
        BigDecimal score=calcService.getNormalValueWRTFeature(featureName, BigDecimal.valueOf(userExpecetdValue));
        int normalizedScore= score.setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue();
        System.out.format("\nNormalized score: %s : before %d , after default %s , rescaled %d ",featureName,userExpecetdValue,score.toString(),normalizedScore);
        return normalizedScore;
    }
}
