package edu.hm.swa.sh.abc3.authorize.rest;

import edu.hm.swa.sh.abc3.authorize.business.AuthManager;
import edu.hm.swa.sh.abc3.authorize.business.AuthManagerImpl;
import edu.hm.swa.sh.abc3.authorize.common.CredentialsDTO;
import edu.hm.swa.sh.abc3.authorize.common.TokenDTO;
import edu.hm.swa.sh.abc3.exception.BaseException;
import edu.hm.swa.sh.abc3.exception.InvalidCredentialsException;
import edu.hm.swa.sh.abc3.exception.InvalidTokenException;
import edu.hm.swa.sh.abc3.exception.UnauthorizedAccessException;
import edu.hm.swa.sh.abc3.generated.types.LoginRequest;
import edu.hm.swa.sh.abc3.generated.types.LoginResponse;
import edu.hm.swa.sh.abc3.generated.types.LogoutRequest;
import edu.hm.swa.sh.abc3.generated.types.LogoutResponse;
import edu.hm.swa.sh.abc3.generated.types.MessageResponse;
import edu.hm.swa.sh.abc3.generated.types.ValidateTokenRequest;
import edu.hm.swa.sh.abc3.generated.types.ValidateTokenResponse;

/**
 * AuthenticationService.
 */
public class AuthenticationService {
    private AuthManager authenticationManager;
    private static final int CODE_OK = 200;
    private static final String MESSAGE_OK = "OK";

    /**
     * Cstr.
     */
    public AuthenticationService() {
        this.authenticationManager = new AuthManagerImpl();
    }

    /**
     * Login user.
     *
     * @param request LoginRequest.
     * @return LoginResponse.
     */
    public LoginResponse loginUser(final LoginRequest request) {
        final LoginResponse response = new LoginResponse();
        prepareOkResponse(response);
        final CredentialsDTO credentials = new CredentialsDTO(request.getUsername(), request.getSecret());
        try {
            final TokenDTO token = authenticationManager.loginUser(credentials);
            response.setToken(token.getToken());
        } catch (final InvalidCredentialsException exception) {
            handleException(exception, response);
        }
        return response;
    }

    /**
     * Logout User.
     *
     * @param request LogoutRequest.
     * @return LogoutResponse.
     */
    public LogoutResponse logoutUser(final LogoutRequest request) {
        final LogoutResponse response = new LogoutResponse();
        prepareOkResponse(response);
        final CredentialsDTO credentials = new CredentialsDTO(request.getToken());
        authenticationManager.logoutUser(credentials);
        return response;
    }

    /**
     * Valide if the token is valid and that token can call method.
     *
     * @param request ValidateTokenRequest.
     * @return ValidateTokenResponse.
     */
    public ValidateTokenResponse validateToken(final ValidateTokenRequest request) {
        final ValidateTokenResponse response = new ValidateTokenResponse();
        prepareOkResponse(response);
        final CredentialsDTO credentials = new CredentialsDTO(request.getToken());

        try {
            authenticationManager.validateToken(credentials, request.getMethod());
        } catch (final InvalidCredentialsException | InvalidTokenException | UnauthorizedAccessException exception) {
            handleException(exception, response);
        }

        return response;
    }

    /**
     * Exception handling.
     *
     * @param exception BaseException.
     * @param response  MessageResponse.
     */
    private void handleException(final BaseException exception, final MessageResponse response) {
        response.setCode(exception.getErrorCode());
        response.setMessage(exception.getMessage());
    }

    /**
     * Prepares a response to state ok.
     * @param response Response.
     */
    private void prepareOkResponse(final MessageResponse response) {
        response.setMessage(MESSAGE_OK);
        response.setCode(CODE_OK);
    }
}
