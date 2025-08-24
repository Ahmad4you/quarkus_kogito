package org.acme.handlers;

//B
//
//
// Handler registrieren
// CustomWorkItemHandler registrieren
//
// Service Task ist das richtige Element --> 
// --> Extension Elements machen ihn zum Custom-Task
// --> bpmn2:extensionElements
// customTask MetaData verknüpft mit Handler-Namen
// oder ManualHandlerRegistration{}
//

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.kogito.process.WorkItemHandlerConfig;
import org.kie.kogito.process.impl.DefaultWorkItemHandlerConfig;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class WorkflowConfiguration {

    @Produces
    @ApplicationScoped
    public WorkItemHandlerConfig workItemHandlerConfig(RestHandler restHandler) {
        Map<String, WorkItemHandler> handlers = new HashMap<>();

        // Name muss mit BPMN metaValue übereinstimmen!
        // handlers.put("RestHandler", restHandler);

        return new DefaultWorkItemHandlerConfig();
    }
}
