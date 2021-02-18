package ru.costonied.examples.jax.war.jetty;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/rest")
public class MainController {
    @GET @Path("/check") @Produces(APPLICATION_JSON)
    public Response getCheck() {
        String body = "{\"status\":\"ok\"}";
        return Response.ok(body).build();
    }
}
