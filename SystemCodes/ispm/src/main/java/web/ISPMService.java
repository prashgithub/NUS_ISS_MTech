package web;

import com.iss_mr.integrated_shield_plan_master.Application;
import integration.ISPMIntegration;
import org.springframework.stereotype.Service;
import web.dao.ApplicationDto;
import web.dao.PolicyDto;

import java.util.List;

@Service
public class ISPMService {

    //@Autowired
    private ISPMIntegration ispmIntegration = new ISPMIntegration();

    public PolicyDto getMatchedPolicy(ApplicationDto applicationDto) {

        Application result = ispmIntegration.getMatchedPolicy( ApplicationConverter.convertFromApplicationDto(applicationDto) );
        PolicyDto policyDto=new PolicyDto();
        policyDto.setName(result.getMatchedPolicy().getName());
        policyDto.setBenefit(result.getMatchedPolicy().getBenefit());
        policyDto.setIssuer(result.getMatchedPolicy().getInsurer());
        return policyDto;
    }
}
