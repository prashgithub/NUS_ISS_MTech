package integration;

import com.iss_mr.integrated_shield_plan_master.Application;
import com.iss_mr.integrated_shield_plan_master.Policy;
import org.kie.api.KieServices;
import org.kie.api.event.process.*;
import org.kie.api.event.rule.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import web.WebApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ISPMIntegration {

	static Logger log = LoggerFactory.getLogger(ISPMIntegration.class);
	
	public static KieContainer initContainer() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();	
		return kContainer;
	}
	
	private static KieSession createStatefulSession(KieContainer container, final Map<String, Object> createdObjMap) {
		//get stateless session
		KieSession session = container.newKieSession();
		
		session.addEventListener(new RuleRuntimeEventListener() {
			public void objectInserted(ObjectInsertedEvent event) {
				log.info("Object inserted: {}", event.getObject().toString());
				
				if( event.getObject() instanceof Application ) {
					Application applicationResult = (Application)event.getObject();
					createdObjMap.put("Application", applicationResult);
					System.out.println( "#########################" + applicationResult);
				}else if(event.getObject() instanceof Policy){
					Policy applicationResult = (Policy)event.getObject();
					createdObjMap.put("Policy", applicationResult);
					System.out.println( "#########################" + applicationResult);
				}
			}

			public void objectUpdated(ObjectUpdatedEvent event) {
				log.info("Object was updated, new Content: {}", event.getObject().toString());
			}

			public void objectDeleted(ObjectDeletedEvent event) {
				log.info("Object retracted: {}", event.getOldObject().toString());
			}
		});
		session.addEventListener(new AgendaEventListener() {
			public void matchCreated(MatchCreatedEvent event) {
				log.info("The rule: {}  can be fired in agenda", event.getMatch().getRule().getName());
			}

			public void matchCancelled(MatchCancelledEvent event) {
				log.info("The rule: {} cannot b in agenda", event.getMatch().getRule().getName());
			}

			public void beforeMatchFired(BeforeMatchFiredEvent event) {
				log.info("The rule: {}  will be fired", event.getMatch().getRule().getName());
			}

			public void afterMatchFired(AfterMatchFiredEvent event) {
				log.info("The rule: {}  has be fired", event.getMatch().getRule().getName());
			}

			public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
				log.info("Agenda group: {}  has be poped", event.getAgendaGroup().getName());
			}

			public void agendaGroupPushed(AgendaGroupPushedEvent event) {
				log.info("Agenda group: {}  has be pushed", event.getAgendaGroup().getName());
			}

			public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
				log.info("Before rule flow group: {}  activated", event.getRuleFlowGroup().getName());
			}

			public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
				log.info("After rule flow group: {}  activated", event.getRuleFlowGroup().getName());
			}

			public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
				log.info("Before rule flow group: {}  deactivated", event.getRuleFlowGroup().getName());
			}

			public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
				log.info("after rule flow group: {}  activated", event.getRuleFlowGroup().getName());
			}
		});

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
				// if (arg0.getNodeInstance() instanceof RuleSetNodeInstance){
				log.info("Node Name: {} has been left", arg0.getNodeInstance().getNodeName());
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

		return session;
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
	
	public boolean invokeRules(List<Object> dataObjectList, String ruleName, Map<String, Object> resultMap) {
		KieContainer container = null;
		boolean success = true;

		try {
			container = initContainer();
			KieSession session = createStatefulSession(container, resultMap);
			
			for (Object dataObj : dataObjectList) {
				session.insert(dataObj);
			}
			session.getAgenda().getAgendaGroup(ruleName).setFocus();
			int firedRules = session.fireAllRules();
			log.info("firedRules: Triggered {} rules", firedRules);
		} catch (Exception exp) {
			success = false;
			log.error("Phq2 rule error: ", exp);
		} finally {
			releaseResource(container);
		}
		return success;
	}
	
	/**
	 *
	 * 
	 * @param
	 * @return
	 */
	public Application getMatchedPolicy(Application details) {
		Application result = null;
		ArrayList<Object> inputList = new ArrayList<Object>();
		inputList.add(details);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		boolean invokeResult = invokeRules(inputList, "policyreasoning", resultMap);
		log.info("ApplicationResult invoke rule: " + invokeResult);
		
		Object application = resultMap.get("Application");
		Object policy=resultMap.get("Policy");
		if(policy==null){
			policy=new Policy();
			policy=(Policy)policy;
			((Policy) policy).setName("Not Found");
			((Policy) policy).setBenefit("N.A.");
		}
		if (application != null && application instanceof Application) {
			log.info("Application is found");
			result= (Application)application;
			result.setMatchedPolicy((Policy)policy);
		}
		
		return result;
	}

	
	public static void main(String[] args) {
		log.info("Logger loaded.");

		try {
		} catch (Exception exp) {
			log.error("Create KJar error", exp);
		}
	}
}
