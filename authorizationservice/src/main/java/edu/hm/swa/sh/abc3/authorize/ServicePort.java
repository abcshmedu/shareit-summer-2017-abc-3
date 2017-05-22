package edu.hm.swa.sh.abc3.authorize;

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
    public Response loginUser() {
        return null;
    }

    @POST
    public Response logoutUser() {
        return null;
    }

    @POST
    public Response validateToken() {
        return null;
    }
}
