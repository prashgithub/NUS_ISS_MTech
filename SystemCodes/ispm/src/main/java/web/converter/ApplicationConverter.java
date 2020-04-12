package web.converter;

import com.iss_mr.integrated_shield_plan_master.Applicant;
import com.iss_mr.integrated_shield_plan_master.Application;
import web.dao.ApplicationDto;

public class ApplicationConverter {
    public static Application convertFromApplicationDto(ApplicationDto applicationDto) {
        Applicant applicant=new Applicant();
        applicant.setName(applicationDto.getName());
        applicant.setAge(Integer.parseInt(applicationDto.getAge()));
        applicant.setId(applicationDto.getId());

        Application application = new Application();
        application.setApplicant( applicant);
        application.setIssuer(applicationDto.getIssuer());
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
