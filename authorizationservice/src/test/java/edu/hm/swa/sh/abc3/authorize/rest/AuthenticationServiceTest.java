package edu.hm.swa.sh.abc3.authorize.rest;

import edu.hm.swa.sh.abc3.authorize.business.AuthManager;
import edu.hm.swa.sh.abc3.authorize.common.CredentialsDTO;
import edu.hm.swa.sh.abc3.authorize.common.TokenDTO;
import edu.hm.swa.sh.abc3.exception.InvalidCredentialsException;
import edu.hm.swa.sh.abc3.exception.InvalidTokenException;
import edu.hm.swa.sh.abc3.exception.UnauthorizedAccessException;
import edu.hm.swa.sh.abc3.generated.types.LoginRequest;
import edu.hm.swa.sh.abc3.generated.types.LoginResponse;
import edu.hm.swa.sh.abc3.generated.types.LogoutRequest;
import edu.hm.swa.sh.abc3.generated.types.LogoutResponse;
import edu.hm.swa.sh.abc3.generated.types.ValidateTokenRequest;
import edu.hm.swa.sh.abc3.generated.types.ValidateTokenResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService underTest;
    @Mock
    private AuthManager authenticationManager;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testLoginUser() throws InvalidCredentialsException {
        final LoginRequest request = new LoginRequest();
        request.setSecret("secret");
        request.setUsername("user");

        final CredentialsDTO credentialsDTO = new CredentialsDTO("user", "secret");
        final TokenDTO token = new TokenDTO("token", 1L, 999L);
        when(authenticationManager.loginUser(any())).thenReturn(token);

        final LoginResponse response = new LoginResponse();
        response.setCode(200);
        response.setMessage("OK");
        response.setToken(token.getToken());

        final LoginResponse result = underTest.loginUser(request);

        assertEquals("Should be the same code.", response.getCode(), result.getCode());
        assertEquals("Should be the same message.", response.getMessage(), result.getMessage());
        assertEquals("Should be the same token", response.getToken(), result.getToken());
    }

    @Test
    public void testLoginUserException() throws InvalidCredentialsException {
        final LoginRequest request = new LoginRequest();
        request.setSecret("secret");
        request.setUsername("user");

        final CredentialsDTO credentialsDTO = new CredentialsDTO("user", "secret");
        final TokenDTO token = new TokenDTO("token", 1L, 999L);
        final InvalidCredentialsException exception = new InvalidCredentialsException("message");
        when(authenticationManager.loginUser(any())).thenThrow(exception);

        final LoginResponse response = new LoginResponse();
        response.setCode(exception.getErrorCode());
        response.setMessage(exception.getMessage());

        final LoginResponse result = underTest.loginUser(request);

        assertEquals("Should be the same code.", response.getCode(), result.getCode());
        assertEquals("Should be the same message.", response.getMessage(), result.getMessage());
        assertEquals("Should be the same token", response.getToken(), result.getToken());
    }

    @Test
    public void testLogoutUser() {
        final LogoutRequest request = new LogoutRequest();
        request.setToken("token");

        final LogoutResponse response = new LogoutResponse();
        response.setCode(200);
        response.setMessage("OK");

        final LogoutResponse result = underTest.logoutUser(request);

        verify(authenticationManager).logoutUser(any());

        assertEquals("Should be the same code.", response.getCode(), result.getCode());
        assertEquals("Should be the same message.", response.getMessage(), result.getMessage());
    }

    @Test
    public void testValidateToken() throws UnauthorizedAccessException, InvalidTokenException,
            InvalidCredentialsException {
        final ValidateTokenRequest request = new ValidateTokenRequest();
        request.setToken("token");
        request.setMethod("method");

        final ValidateTokenResponse response = new ValidateTokenResponse();
        response.setCode(200);
        response.setMessage("OK");

        final ValidateTokenResponse result = underTest.validateToken(request);

        verify(authenticationManager).validateToken(any(), anyString());

        assertEquals("Should be the same code.", response.getCode(), result.getCode());
        assertEquals("Should be the same message.", response.getMessage(), result.getMessage());
    }

    @Test
    public void testValidateTokenException() throws UnauthorizedAccessException, InvalidTokenException,
            InvalidCredentialsException {
        final ValidateTokenRequest request = new ValidateTokenRequest();
        request.setToken("token");
        request.setMethod("method");

        final UnauthorizedAccessException exception = new UnauthorizedAccessException("message");

        final ValidateTokenResponse response = new ValidateTokenResponse();
        response.setCode(exception.getErrorCode());
        response.setMessage(exception.getMessage());

        doThrow(exception).when(authenticationManager).validateToken(any(), any());

        final ValidateTokenResponse result = underTest.validateToken(request);

        verify(authenticationManager).validateToken(any(), anyString());

        assertEquals("Should be the same code.", response.getCode(), result.getCode());
        assertEquals("Should be the same message.", response.getMessage(), result.getMessage());
    }
}
