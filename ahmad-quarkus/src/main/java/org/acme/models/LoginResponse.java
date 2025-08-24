package org.acme.models;

public class LoginResponse {
    private String token;
    private boolean success;

    public LoginResponse(boolean success2, String token2, String string) {
        this.token = token2;
        this.success = success2;
    }

    // Getter und Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}