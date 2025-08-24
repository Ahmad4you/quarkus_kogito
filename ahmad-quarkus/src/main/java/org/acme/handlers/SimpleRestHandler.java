package org.acme.handlers;

import java.util.Map;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimpleRestHandler implements WorkItemHandler {

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        String input = (String) workItem.getParameter("input");
        String result = input.toUpperCase(); // Simple Transformation

        manager.completeWorkItem(workItem.getId(), Map.of("result", result));
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        // Cleanup logic
    }
}