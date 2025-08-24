package org.acme.models;

public class GetArtikelsResponse {
    public boolean success;
    public java.util.List<Object> artikels;
    public String message;

    public GetArtikelsResponse() {
    }

    public GetArtikelsResponse(boolean success, java.util.List<Object> artikels, String message) {
        this.success = success;
        this.artikels = artikels;
        this.message = message;
    }
}
