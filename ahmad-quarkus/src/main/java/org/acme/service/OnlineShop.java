package org.acme.service;

import org.acme.ApiClient;
import org.acme.models.GetArtikelsResponse;
import org.acme.models.GetIDsResponse;
import org.acme.models.LoginRequest;
import org.acme.models.LoginResponse;
import org.jbpm.bpmn2.core.SequenceFlow;
import org.jbpm.workflow.core.node.Split;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/services")
@ApplicationScoped
public class OnlineShop {
    @Inject
    ApiClient apiClient;

    @POST
    @Path("/login")
    public LoginResponse login() {
        LoginRequest request = new LoginRequest();
        request.setUsername("test@example.de");
        request.setPassword("P$assword123");
        try {
            LoginResponse apiResponse = apiClient.login(request.getUsername(), request.getPassword());
            return new LoginResponse(apiResponse.isSuccess(), apiResponse.getToken(), "Login successful");
        } catch (Exception e) {
            return new LoginResponse(false, null, "Login failed: " + e.getMessage());
        }
    }

    @POST
    @Path("/get_ids")
    public GetIDsResponse ermittleIds() {
        try {
            java.util.List<String> ids = apiClient.handleGetIDs();
            return new GetIDsResponse(true, ids, "IDs retrieved successfully");
        } catch (Exception e) {
            return new GetIDsResponse(false, null, "Failed to get IDs: " + e.getMessage());
        }
    }

    @POST
    @Path("/get_Artikle")
    // @Consumes(MediaType.APPLICATION_JSON)
    // @Produces(MediaType.APPLICATION_JSON)
    public GetArtikelsResponse getArtikels() {
        try {
            java.util.List<Object> artikels = apiClient.getArtikels();

            Split gateway = new Split();
            gateway.setType(Split.TYPE_XOR); // Exclusive Gateway
            gateway.setId(24325246);
            // Connection connection1 = new ConnectionImpl(gateway,
            // Node.CONNECTION_DEFAULT_TYPE, targetNode, Node.CONNECTION_DEFAULT_TYPE);
            // gateway.addOutgoingConnection(Node.CONNECTION_DEFAULT_TYPE, connection1);

            SequenceFlow flow1 = new SequenceFlow(null, null, null);
            return new GetArtikelsResponse(true, artikels, "Artikels retrieved successfully");
        } catch (Exception e) {
            return new GetArtikelsResponse(false, null, "Failed to get Artikels: " + e.getMessage());
        }
    }
}
