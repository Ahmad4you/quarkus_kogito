package org.acme.models;

public class GetArtikelsRequest {
    public String[] ids;
    public String category;

    public GetArtikelsRequest() {
    }

    public GetArtikelsRequest(String[] ids, String category) {
        this.ids = ids;
        this.category = category;
    }
}
