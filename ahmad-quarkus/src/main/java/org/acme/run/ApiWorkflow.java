package org.acme.run;

import java.util.Map;
import java.util.Optional;

import org.kie.kogito.MapOutput;
import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.Processes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ApiWorkflow {

    @Inject
    Processes processes;

    public String startOrderProcess() {
        try {
            Process<?> process = processes.processById("custom_order_process");

            // createModel() und dann createInstance()
            Object model = process.createModel();
            ProcessInstance<?> instance = process.createInstance((Model) model);
            instance.start();

            return instance.id();
        } catch (Exception e) {
            throw new RuntimeException("Failed to start process: " + e.getMessage(), e);
        }
    }

    // Prozess-Instanz abfragen - KORRIGIERT
    public String getProcessStatus(String instanceId) {
        try {
            Process<?> process = processes.processById("order_process");

            Optional<? extends ProcessInstance<?>> instanceOptional = process.instances().findById(instanceId);

            if (instanceOptional.isPresent()) {
                ProcessInstance<?> processInstance = instanceOptional.get();

                // Verfügbare Methoden verwenden:
                String status = convertStatusToString(processInstance.status());
                String id = processInstance.id();
                String processId = processInstance.process().id();

                return "Status: " + status +
                        ", ID: " + id +
                        ", ProcessId: " + processId;
            } else {
                return "NOT_FOUND";
            }
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    // Status-Konvertierung für bessere Lesbarkeit
    private String convertStatusToString(int statusCode) {
        switch (statusCode) {
            case 0:
                return "PENDING";
            case 1:
                return "ACTIVE";
            case 2:
                return "COMPLETED";
            case 3:
                return "ABORTED";
            case 4:
                return "SUSPENDED";
            case 5:
                return "ERROR";
            default:
                return "UNKNOWN (" + statusCode + ")";
        }
    }

    // Einfache Status-Abfrage
    public String getSimpleStatus(String instanceId) {
        try {
            Process<?> process = processes.processById("order_process");
            var instanceOptional = process.instances().findById(instanceId);

            if (instanceOptional.isPresent()) {
                ProcessInstance<?> processInstance = instanceOptional.get();
                int statusCode = processInstance.status();
                return convertStatusToString(statusCode);
            } else {
                return "NOT_FOUND";
            }
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    // Detaillierte Informationen
    public Map<String, Object> getProcessInfo(String instanceId) {
        try {
            Process<?> process = processes.processById("order_process");
            var instanceOptional = process.instances().findById(instanceId);

            if (instanceOptional.isPresent()) {
                ProcessInstance<?> processInstance = instanceOptional.get();

                return Map.of(
                        "instanceId", processInstance.id(),
                        "processId", processInstance.process().id(),
                        "statusCode", processInstance.status(),
                        "status", convertStatusToString(processInstance.status()),
                        "variables", ((MapOutput) processInstance.variables()).toMap());
            } else {
                return Map.of("error", "Process instance not found");
            }
        } catch (Exception e) {
            return Map.of("error", "Exception: " + e.getMessage());
        }
    }

    // Verfügbare Methoden testen
    public String debugProcessInstance(String instanceId) {
        try {
            Process<?> process = processes.processById("order_process");
            var instanceOptional = process.instances().findById(instanceId);

            if (instanceOptional.isPresent()) {
                ProcessInstance<?> processInstance = instanceOptional.get();

                StringBuilder debugInfo = new StringBuilder();
                debugInfo.append("Available methods:\n");
                debugInfo.append("- id(): ").append(processInstance.id()).append("\n");
                debugInfo.append("- status(): ").append(processInstance.status()).append("\n");
                debugInfo.append("- process().id(): ").append(processInstance.process().id()).append("\n");
                debugInfo.append("- variables().size(): ")
                        .append(((MapOutput) processInstance.variables()).toMap().size()).append("\n");

                return debugInfo.toString();
            } else {
                return "NOT_FOUND";
            }
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}