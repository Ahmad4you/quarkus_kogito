package org.acme;

import java.util.ArrayList;
import java.util.List;

import org.acme.models.LoginRequest;
import org.acme.models.LoginResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ApiClient {

    private String authToken;
    private final Client client = ClientBuilder.newClient();

    public LoginResponse login(String username, String password) {
        LoginRequest request = new LoginRequest(username, password);

        Response response = client.target("http://localhost:8080/online-shop-0.0.1-SNAPSHOT/login.xhtml")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON));

        LoginResponse loginResponse = response.readEntity(LoginResponse.class);
        this.authToken = loginResponse.getToken();
        return loginResponse;
    }

    public List<Object> getArtikels() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArtikels'");
    }

    public List<String> handleGetIDs() {
        List<String> idList = new ArrayList<>();
        idList.add("1");
        idList.add("2");

        return idList;
    }

    // Weitere Methoden für die anderen 8 Endpoints...
}
