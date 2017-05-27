package edu.hm.swa.sh.abc3.authorize.rest;

import edu.hm.swa.sh.abc3.generated.types.LoginRequest;
import edu.hm.swa.sh.abc3.generated.types.LoginResponse;
import edu.hm.swa.sh.abc3.generated.types.LogoutRequest;
import edu.hm.swa.sh.abc3.generated.types.LogoutResponse;
import edu.hm.swa.sh.abc3.generated.types.ValidateTokenRequest;
import edu.hm.swa.sh.abc3.generated.types.ValidateTokenResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Service for auth.
 */
@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicePort {
    private AuthenticationService authenticationService = new AuthenticationService();

    /**
     * Login the user.
     * @param request LoginRequest.
     * @return LoginResponse.
     */
    @POST
    @Path("login")
    public LoginResponse loginUser(LoginRequest request) {
        return authenticationService.loginUser(request);
    }

    /**
     * Logout the user.
     * @param request LogoutRequest.
     * @return LogoutResponse.
     */
    @POST
    @Path("logout")
    public LogoutResponse logoutUser(LogoutRequest request) {
        return authenticationService.logoutUser(request);
    }

    /**
     * Validate the token and the called method.
     * @param request ValidateTokenRequest.
     * @return ValidateTokenResponse.
     */
    @POST
    @Path("validate")
    public ValidateTokenResponse validateToken(ValidateTokenRequest request) {
        return authenticationService.validateToken(request);
    }
}
