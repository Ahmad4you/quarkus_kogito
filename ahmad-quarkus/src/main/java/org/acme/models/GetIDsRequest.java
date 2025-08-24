package org.acme.models;

public class GetIDsRequest {
    public String category;
    public int limit;

    public GetIDsRequest() {
    }

    public GetIDsRequest(String category, int limit) {
        this.category = category;
        this.limit = limit;
    }
}
