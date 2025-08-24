package org.acme.run;

import java.util.Map;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/debug")
@Produces(MediaType.APPLICATION_JSON)
public class DebugController {

    @Inject
    ApiWorkflow apiWorkflow;

    @POST
    @Path("/start")
    public Response startProcess() {
        try {
            String instanceId = apiWorkflow.startOrderProcess();
            return Response.ok()
                    .entity(Map.of(
                            "success", true,
                            "message", "Process started successfully",
                            "instanceId", instanceId))
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(Map.of(
                            "success", false,
                            "error", e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/status/{instanceId}")
    public Response getStatus(@PathParam("instanceId") String instanceId) {
        try {
            String status = apiWorkflow.getSimpleStatus(instanceId);
            return Response.ok()
                    .entity(Map.of(
                            "instanceId", instanceId,
                            "status", status))
                    .build();
        } catch (Exception e) {
            return Response.status(404)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/info/{instanceId}")
    public Response getInfo(@PathParam("instanceId") String instanceId) {
        try {
            Map<String, Object> info = apiWorkflow.getProcessInfo(instanceId);
            return Response.ok().entity(info).build();
        } catch (Exception e) {
            return Response.status(404)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/debug/{instanceId}")
    public Response debugInstance(@PathParam("instanceId") String instanceId) {
        try {
            String debugInfo = apiWorkflow.debugProcessInstance(instanceId);
            return Response.ok()
                    .entity(Map.of(
                            "instanceId", instanceId,
                            "debugInfo", debugInfo))
                    .build();
        } catch (Exception e) {
            return Response.status(404)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }
}