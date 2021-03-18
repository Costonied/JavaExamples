package ru.costonied.examples.jax.war.jetty;

import javax.ws.rs.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/rest")
public class MainController {
    @GET @Path("/check") @Produces(APPLICATION_JSON)
    public Response getCheck() throws NamingException {
        // Read JNDI resource
        InitialContext initContext = new InitialContext();
        String statusCode = (String) initContext.lookup("some/resource/string");

        String body = "{\"status\":\"" + statusCode + "\"}";
        return Response.ok(body).build();
    }
}
