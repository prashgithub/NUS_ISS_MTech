package integration;

import com.iss_mr.integrated_shield_plan_master.Applicant;
import com.iss_mr.integrated_shield_plan_master.Application;
import com.iss_mr.integrated_shield_plan_master.Policy;
import com.iss_mr.integrated_shield_plan_master.Validation;
import com.iss_mr.optaisp.ISPConstraintConfiguration;
import com.iss_mr.optaisp.ISPSolution;
import com.iss_mr.optaisp.Preference;
import com.iss_mr.optaisp.Premium;
import org.drools.core.ClassObjectFilter;
import org.jbpm.bpmn2.xml.ExclusiveGatewayHandler;
import org.jbpm.bpmn2.xml.UserTaskHandler;
import org.jbpm.process.core.impl.WorkImpl;
import org.jbpm.process.instance.context.exclusive.ExclusiveGroupInstance;
import org.jbpm.process.instance.impl.humantask.HumanTaskHandler;
import org.jbpm.workflow.core.node.HumanTaskNode;
import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.jbpm.workflow.instance.WorkflowRuntimeException;
import org.jbpm.workflow.instance.node.HumanTaskNodeInstance;
import org.kie.api.KieServices;
import org.kie.api.event.process.*;
import org.kie.api.event.rule.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ISPMIntegration {

    static Logger log = LoggerFactory.getLogger(ISPMIntegration.class);

    private List<com.iss_mr.optaisp.Policy> policyList;
    public void setPolicyList(List<com.iss_mr.optaisp.Policy> policyList){
        this.policyList=policyList;
    }

    private void releaseResource(KieContainer container) {
        try {
            if (container != null) {
                container.dispose();
            }
        } catch (Exception e) {
            log.error("Release container error: {}", e.getMessage());
        }
    }

    public boolean invokeDM(Application application, Map<String, Object> resultMap) {
        boolean success = true;
        try {
            DMIntegration dmIntegration=new DMIntegration();
            String predictPolicy=dmIntegration.predict(application);
            log.info("invoke DM: Triggered {} knn prediction", predictPolicy);
            resultMap.put("Policy", predictPolicy);
        } catch (Exception exp) {
            success = false;
            log.error(" dm error: ", exp);
        }
        return success;
    }

    public boolean invokeOpta(Application application, Map<String, Object> resultMap) {
        KieContainer container = null;
        boolean success = true;

        try {
            KieServices kieServices = KieServices.Factory.get();
            container = kieServices.newKieContainer(kieServices.newReleaseId("com.iss_mr", "OptaISP", "1.0.0"));
            SolverFactory<ISPSolution> solverFactory = SolverFactory.createFromKieContainerXmlResource(container, "com/iss_mr/optaisp/ispSolverConfig.solver.xml");
            Solver<ISPSolution> solver = solverFactory.buildSolver();
            solver.solve(getSolution(application));
            ISPSolution solution = solver.getBestSolution();
            log.info("opta best score: "+solver.getBestScore().toString());
            System.out.println("opta solver explain: " + solver.explainBestScore());
            log.info("invoke opta: Triggered {} opta", solution.getPreferenceList().get(0).getPolicy().getName());
            resultMap.put("Policy", solution.getPreferenceList().get(0).getPolicy());
        } catch (Exception exp) {
            success = false;
            log.error(" opta error: ", exp);
        } finally {
            releaseResource(container);
        }
        return success;
    }

    public boolean invokeProcess(List<Object> dataObjectList, Map<String, Object> resultMap) {
        KieContainer container = null;
        boolean success = true;

        try {
            KieServices kieServices = KieServices.Factory.get();
            container = kieServices.newKieContainer(
                    kieServices.newReleaseId("com.iss_mr", "Integrated_Shield_Plan_Master", "1.0.0"));
            KieSession session = container.newKieSession("ispSession");
            session.addEventListener(new ProcessEventListener() {
                public void beforeVariableChanged(ProcessVariableChangedEvent arg0) {
                    log.info("before variable: {} changed", arg0);
                }

                public void beforeProcessStarted(ProcessStartedEvent arg0) {
                    log.info("Process Name: {} has been started", arg0.getProcessInstance().getProcessName());
                }

                public void beforeProcessCompleted(ProcessCompletedEvent arg0) {
                    log.info("Process Name: {} has been completed", arg0.getProcessInstance().getProcessName());
                }

                public void beforeNodeTriggered(ProcessNodeTriggeredEvent arg0) {
                    log.info("Node Name: {} has been triggered", arg0.getNodeInstance().getNodeName());
                }

                public void beforeNodeLeft(ProcessNodeLeftEvent arg0) {
                    if (arg0.getNodeInstance() instanceof HumanTaskNodeInstance) {
                        HumanTaskNodeInstance humanTaskNode = (HumanTaskNodeInstance) arg0.getNodeInstance();
                        if (humanTaskNode.getHumanTaskNode().getName().equalsIgnoreCase("OPTA")) {
                            Application application = (Application) humanTaskNode.getWorkItem().getParameter("application");
                            invokeOpta(application, resultMap);
                        }
                        if (humanTaskNode.getHumanTaskNode().getName().equalsIgnoreCase("DataMining")) {
                            Application application = (Application) humanTaskNode.getWorkItem().getParameter("application");
                            invokeDM(application, resultMap);
                        }
                    }
                    // if (arg0.getNodeInstance() instanceof RuleSetNodeInstance){
                    log.info("before Node Left: {} ", arg0.getNodeInstance().getNodeName());
                    // }
                }

                public void afterVariableChanged(ProcessVariableChangedEvent arg0) {
                    log.info("after variable: {} changed", arg0);
                }

                public void afterProcessStarted(ProcessStartedEvent arg0) {
                    log.info("after Process Name: {} has been started", arg0.getProcessInstance().getProcessName());
                }

                public void afterProcessCompleted(ProcessCompletedEvent arg0) {
                    log.info("Process Name: {} has stopped", arg0.getProcessInstance().getProcessName());
                }

                public void afterNodeTriggered(ProcessNodeTriggeredEvent arg0) {
                    // if (arg0.getNodeInstance() instanceof RuleSetNodeInstance){
                    log.info("Node Name: {}  has been entered", arg0.getNodeInstance().getNodeName());
                    // }
                }

                public void afterNodeLeft(ProcessNodeLeftEvent arg0) {
                    log.info("Node Name: {}  has been left", arg0.getNodeInstance().getNodeName());
                }
            });
            CustomHumanTaskHandler humanTaskHandler = new CustomHumanTaskHandler();
            session.getWorkItemManager().registerWorkItemHandler("Human Task", humanTaskHandler);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("application", dataObjectList.get(0));
            ProcessInstance process = session.startProcess("Integrated_Shield_Plan_Master.process", parameters);
            System.out.println("process state :" + process.getState());
            resultMap.put("Application", dataObjectList.get(0));
        } catch (Exception exp) {
            success = false;
            log.error(" process error: ", exp);
        } finally {
            releaseResource(container);
        }
        return success;
    }


    private ISPSolution getSolution(Application application) {
        return new ISPSolution(policyList, Arrays.asList(formPreference(application)), formConstrain(application.getPreference()));
    }

    private Preference formPreference(Application application){
        com.iss_mr.integrated_shield_plan_master.Preference applicationPreference=application.getPreference();
        Preference preference = new Preference();
        preference.setRequiredAge((application.getApplicant().getAge()/10)*10);
        preference.setRequiredPreHospitalisationCoveredDays(applicationPreference.getPreHospitalisationCoveredDays().getExpectedValue());
        preference.setRequiredPostHospitalisationCoveredDays(applicationPreference.getPostHospitalisationCoveredDays().getExpectedValue());
        preference.setRequiredMajorOrganTransplant(applicationPreference.getMajorOrganTransplant().getExpectedValue());
        preference.setRequiredSurgery(applicationPreference.getSurgery().getExpectedValue());
        preference.setRequiredClaimsProcessingDuration(applicationPreference.getClaimsProcessingDuration().getExpectedValue());
        preference.setRequiredCoinsurance(applicationPreference.getCoinsurance().getExpectedValue());
        preference.setRequiredcommunityHospital(applicationPreference.getCommunityHospital().getExpectedValue());
        preference.setRequiredCoPayCappedAt(applicationPreference.getCoPayCappedAt().getExpectedValue());
        preference.setRequiredCriticalIllnesses(applicationPreference.getCriticalIllnesses().getExpectedValue());
        preference.setRequiredDeductible(applicationPreference.getDeductible().getExpectedValue());
        preference.setRequiredEmergencyOverseasTreatment(applicationPreference.getEmergencyOverseasTreatment().getExpectedValue());
        preference.setRequiredNonPanelSurcharge(applicationPreference.getNonPanelSurcharge().getExpectedValue());
        preference.setRequiredPremium(applicationPreference.getPremium().getExpectedValue());
        preference.setRequiredPolicyYearLimit(applicationPreference.getPolicyYearLimit().getExpectedValue());
        preference.setRequiredPostHospitalisationCoverage(applicationPreference.getPostHospitalisationCoverage().getExpectedValue());
        preference.setRequiredPreHospitalisationCoverage(applicationPreference.getPreHospitalisationCoverage().getExpectedValue());
        preference.setRequiredProsthesis(applicationPreference.getProsthesis().getExpectedValue());
        return preference;
    }

    private ISPConstraintConfiguration formConstrain(com.iss_mr.integrated_shield_plan_master.Preference applicationPreference){
        ISPConstraintConfiguration constraintConfiguration = new ISPConstraintConfiguration();
        constraintConfiguration.setHardLastEntryAge(HardSoftScore.ofHard(1));
        constraintConfiguration.setPreHospitalisationCoveredDays(HardSoftScore.ofSoft(applicationPreference.getPreHospitalisationCoveredDays().getImportance()));
        constraintConfiguration.setPostHospitalisationCoveredDays(HardSoftScore.ofSoft(applicationPreference.getPostHospitalisationCoveredDays().getImportance()));
        constraintConfiguration.setPolicyYearLimit(HardSoftScore.ofSoft(applicationPreference.getPolicyYearLimit().getImportance()));
        constraintConfiguration.setCoinsurance(HardSoftScore.ofSoft(applicationPreference.getCoinsurance().getImportance()));
        constraintConfiguration.setCommunityHospital(HardSoftScore.ofSoft(applicationPreference.getCommunityHospital().getImportance()));
        constraintConfiguration.setCoPayCappedAt(HardSoftScore.ofSoft(applicationPreference.getCoPayCappedAt().getImportance()));
        constraintConfiguration.setDeductible(HardSoftScore.ofSoft(applicationPreference.getDeductible().getImportance()));
        constraintConfiguration.setEmergencyOverseasTreatment(HardSoftScore.ofSoft(applicationPreference.getEmergencyOverseasTreatment().getImportance()));
        constraintConfiguration.setMajorOrganTransplant(HardSoftScore.ofSoft(applicationPreference.getMajorOrganTransplant().getImportance()));
        constraintConfiguration.setPremiumAmount(HardSoftScore.ofSoft(applicationPreference.getPremium().getImportance()));
        constraintConfiguration.setSurgery(HardSoftScore.ofSoft(applicationPreference.getSurgery().getImportance()));
        constraintConfiguration.setClaimProcessingDuration(HardSoftScore.ofSoft(applicationPreference.getClaimsProcessingDuration().getImportance()));
        constraintConfiguration.setCriticalIllness(HardSoftScore.ofSoft(applicationPreference.getCriticalIllnesses().getImportance()));
        constraintConfiguration.setNonPanelsurcharge(HardSoftScore.ofSoft(applicationPreference.getNonPanelSurcharge().getImportance()));
        constraintConfiguration.setPostHopitalisationCoverage(HardSoftScore.ofSoft(applicationPreference.getPostHospitalisationCoverage().getImportance()));
        constraintConfiguration.setPreHopitalisationCoverage(HardSoftScore.ofSoft(applicationPreference.getPreHospitalisationCoverage().getImportance()));
        constraintConfiguration.setProsthsis(HardSoftScore.ofSoft(applicationPreference.getProsthesis().getImportance()));
        return constraintConfiguration;
    }

    /**
     * @param
     * @return
     */
    public Application getMatchedPolicy(Application details) {
        Application result = null;
        ArrayList<Object> inputList = new ArrayList<Object>();
        inputList.add(details);
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        //boolean invokeResult = invokeRules(inputList, "policyreasoning", resultMap);
        //log.info("ApplicationResult invoke rule: " + invokeResult);
        boolean invokeResult = invokeProcess(inputList, resultMap);
        log.info("ApplicationResult: " + invokeResult);

        Object application = resultMap.get("Application");
        Object policy = resultMap.get("Policy");
        if (application != null && application instanceof Application) {
            log.info("Application is found");
            result = (Application) application;
            com.iss_mr.integrated_shield_plan_master.Policy policyDisplayed = new com.iss_mr.integrated_shield_plan_master.Policy();
            if(policy==null){
                policyDisplayed.setName("Not Found");
            }
            else if(policy instanceof  com.iss_mr.optaisp.Policy){
                policyDisplayed.setName(((com.iss_mr.optaisp.Policy)policy).getName());
            }else if(policy instanceof String){
                policyDisplayed.setName((String)policy);
            }
            if(((Application) application).getIssuer()!=null){
                policyDisplayed.setName(((Application)application).getIssuer());
            }
            result.setMatchedPolicy(policyDisplayed);
        }

        return result;
    }
}
