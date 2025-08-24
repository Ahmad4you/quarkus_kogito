package org.acme.service;

import org.acme.models.GetArtikelsResponse;
import org.acme.models.GetIDsResponse;
import org.acme.models.LoginResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/services")
@ApplicationScoped
public interface OnlineShopService {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    LoginResponse login();

    @POST
    @Path("/get-ids")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    GetIDsResponse ermittleIds();

    @POST
    @Path("/get-artikels")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    GetArtikelsResponse getArtikels();
}
