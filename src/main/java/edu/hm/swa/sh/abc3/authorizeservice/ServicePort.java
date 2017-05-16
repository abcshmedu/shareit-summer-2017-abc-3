package edu.hm.swa.sh.abc3.authorizeservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Service for auth.
 */
@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
public class ServicePort {

    @POST
    @Path("/user")
    public Response createNewUser() {
        return null;
    }

    public Response loginUser() {
        return null;
    }

    public Response logoutUser() {
        return null;
    }

    public Response validateToken() {
        return null;
    }
}
