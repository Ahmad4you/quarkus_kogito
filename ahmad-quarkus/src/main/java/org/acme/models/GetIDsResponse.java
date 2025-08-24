package org.acme.models;

public class GetIDsResponse {
    public boolean success;
    public java.util.List<String> ids;
    public String message;

    public GetIDsResponse() {
    }

    public GetIDsResponse(boolean success, java.util.List<String> ids, String message) {
        this.success = success;
        this.ids = ids;
        this.message = message;
    }
}