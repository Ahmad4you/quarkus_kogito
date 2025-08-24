package org.acme.models;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderService {

    @POST
    @Path("/start")
    public Response startOrder(Order order) {
        System.out.println("📦 Order received: " + order);
        // Hier deine Geschäftslogik
        return Response.ok().entity("Order processed").build();
    }
}