package web.converter;

import com.iss_mr.integrated_shield_plan_master.Applicant;
import com.iss_mr.integrated_shield_plan_master.Application;
import com.iss_mr.integrated_shield_plan_master.Preference;
import com.iss_mr.integrated_shield_plan_master.PreferenceMatrix;
import web.dao.ApplicationDto;

public class ApplicationConverter {
    public static Application convertFromApplicationDto(ApplicationDto applicationDto) {
        Applicant applicant=new Applicant();
        applicant.setName(applicationDto.getName());
        applicant.setAge(35);
        applicant.setGender(applicationDto.getGender());
        applicant.setNationality("Singaporean");
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
        preferenceMatrix_policyYearLimit.setExpectedValue(25);
        preferenceMatrix_policyYearLimit.setImportance(10);
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
        preferenceMatrix_premium.setExpectedValue(50);
        preferenceMatrix_premium.setImportance(15);
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
        return application;
    }

    private static String getAge(String age) {
        if( age.equals("0") ) {
            return "18-34";
        } else if ( age.equals("1") ) {
            return "35-49";
        } else if ( age.equals("2") ) {
            return "50-64";
        }
        return "65+";
    }

    private static String getGender(String gender) {
        if( gender.equals("0") ) {
            return "Male";
        }
        return "Female";
    }

    private static String getRace(String race) {
        if( race.equals("0") ) {
            return "Chinese";
        }else if ( race.equals("1") ) {
            return "Malay";
        } else if ( race.equals("2") ) {
            return "Indian";
        }
        return "Others";
    }

    private static String getMaritialStatus(String status) {
        if( status.equals("0") ) {
            return "Single";
        }else if ( status.equals("1") ) {
            return "Married";
        } else if ( status.equals("2") ) {
            return "Separated";
        }
        return "Widowed";
    }

    private static String getChronic(String status) {
        if( status.equals("0") ) {
            return "Yes";
        }
        return "No";
    }
}
