src/
├── main/
│   ├── java/org/acme/
│   │   ├── ApiWorkflow.java      # Haupt-Workflow Klasse
│   │   ├── ApiClient.java        # REST Client für alle Calls
│   │   ├── models/
│   │   │   ├── LoginRequest.java
│   │   │   ├── LoginResponse.java
│   │   │   └── ApiResponse.java
│   │   └── handlers/
│   │       ├── LoginHandler.java
│   │       ├── GetIdsHandler.java
│   │       └── CustomWorkItemHandler.java
│   └── resources/
│       └── api-workflow.bpmn     # BPMN Datei