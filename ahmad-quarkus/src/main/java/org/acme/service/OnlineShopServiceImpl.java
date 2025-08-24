package org.acme.service;

import org.acme.ApiClient;
import org.acme.models.GetArtikelsResponse;
import org.acme.models.GetIDsResponse;
import org.acme.models.LoginRequest;
import org.acme.models.LoginResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OnlineShopServiceImpl implements OnlineShopService {

    @Inject
    ApiClient apiClient;

    @Override
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

    @Override
    public GetIDsResponse ermittleIds() {
        try {
            java.util.List<String> ids = apiClient.handleGetIDs();
            return new GetIDsResponse(true, ids, "IDs retrieved successfully");
        } catch (Exception e) {
            return new GetIDsResponse(false, null, "Failed to get IDs: " + e.getMessage());
        }
    }

    @Override
    public GetArtikelsResponse getArtikels() {
        LoginRequest request = null;
        try {
            java.util.List<Object> artikels = apiClient.getArtikels();
            return new GetArtikelsResponse(true, artikels, "Artikels retrieved successfully");
        } catch (Exception e) {
            return new GetArtikelsResponse(false, null, "Failed to get Artikels: " + e.getMessage());
        }
    }
}