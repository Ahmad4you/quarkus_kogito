package org.acme.handlers;

import java.util.Map;

import org.acme.ApiClient;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * 
 * CustomWorkItemHandler [RestHandler]
 */
@ApplicationScoped
public class RestHandler implements WorkItemHandler {

    @Inject
    ApiClient apiClient;

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        try {
            Map<String, Object> parameters = workItem.getParameters();

            // endpoint-Parameter aus der BPMN MetaData lesen
            String endpoint = (String) parameters.get("endpoint");
            String taskName = (String) workItem.getParameter("NodeName"); // Automatisch von Kogito

            System.out.println("🚀 Handling task: " + taskName);
            System.out.println("🌐 Endpoint: " + endpoint);

            // Hier deine Logik für die verschiedenen Endpoints
            Object result = handleEndpoint(endpoint, parameters);

            manager.completeWorkItem(workItem.getId(), Map.of(
                    "success", true,
                    "result", result,
                    "processedBy", "RestHandler"));

        } catch (Exception e) {
            manager.completeWorkItem(workItem.getId(), Map.of(
                    "success", false,
                    "error", e.getMessage()));
        }
    }

    private Object handleEndpoint(String endpoint, Map<String, Object> params) {
        switch (endpoint) {
            case "login":
                return apiClient.login(
                        (String) params.get("username"),
                        (String) params.get("password"));
            case "getIDs":
                return apiClient.handleGetIDs();
            case "getArtikels":
                return "TO-DOs";

            default:
                throw new IllegalArgumentException("Unknown endpoint: " + endpoint);
        }
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        System.out.println("Task aborted: " + workItem.getParameter("TaskName"));
    }
}