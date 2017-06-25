package edu.hm.swa.sh.abc3.mediaservice.rest.auth;

import edu.hm.swa.sh.abc3.generated.types.ValidateTokenRequest;
import edu.hm.swa.sh.abc3.generated.types.ValidateTokenResponse;
import edu.hm.swa.sh.abc3.mediaservice.common.AuthException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * AuthserviceOutbound.
 */
public class AuthserviceOutbound {
    private static final int OK_RESPONSE_CODE = 200;

    /**
     * Validate token and authorize method.
     *
     * @param token  Token.
     * @param method Method.
     * @throws AuthException AuthException.
     */
    public void validateToken(final String token, final String method) throws AuthException {
//        final String uri = "http://localhost:8083/auth/validate";
        final String uri = "https://fast-basin-46958.herokuapp.com/auth/validate";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uri);

        final ValidateTokenRequest request = new ValidateTokenRequest();
        request.setMethod(method);
        request.setToken(token);

        final Response rawResponse = target.request().post(Entity.json(request));

        final ValidateTokenResponse response = rawResponse.readEntity(ValidateTokenResponse.class);
        rawResponse.close();

        if (response.getCode() != OK_RESPONSE_CODE) {
            throw new AuthException(response.getMessage(), response.getCode());
        }
    }
}
