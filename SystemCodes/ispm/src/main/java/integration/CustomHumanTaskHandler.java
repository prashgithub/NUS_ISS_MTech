package integration;

import org.jbpm.process.instance.impl.humantask.HumanTaskHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class CustomHumanTaskHandler implements WorkItemHandler  {

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        System.out.println("CustomHumanTaskHandler : executeWorkItem");
        workItemManager.completeWorkItem(workItem.getId(), workItem.getParameters());
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        System.out.println("CustomHumanTaskHandler : abortWorkItem");
    }
}
