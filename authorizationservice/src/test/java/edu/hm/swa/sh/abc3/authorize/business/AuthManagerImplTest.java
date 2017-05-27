package edu.hm.swa.sh.abc3.authorize.business;

import edu.hm.swa.sh.abc3.authorize.common.CredentialsDTO;
import edu.hm.swa.sh.abc3.authorize.common.Permission;
import edu.hm.swa.sh.abc3.authorize.common.TokenDTO;
import edu.hm.swa.sh.abc3.authorize.common.UserDTO;
import edu.hm.swa.sh.abc3.authorize.persistence.AuthPersistenceLayer;
import edu.hm.swa.sh.abc3.exception.InvalidCredentialsException;
import edu.hm.swa.sh.abc3.exception.InvalidTokenException;
import edu.hm.swa.sh.abc3.exception.UnauthorizedAccessException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthManagerImplTest {

    @InjectMocks
    private AuthManagerImpl underTest;
    @Mock
    private AuthPersistenceLayer persistenceLayerMock;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testLoginUser() throws InvalidCredentialsException {
        final UserDTO user = new UserDTO(1L, "user", "password", Permission.ADMIN);
        final CredentialsDTO credentials = new CredentialsDTO("user", "password");
        when(persistenceLayerMock.getUser(anyString())).thenReturn(user);
        when(persistenceLayerMock.getToken(anyString())).thenReturn(null);

        final TokenDTO result = underTest.loginUser(credentials);
        assertFalse("Token should not be empty", result.getToken().isEmpty());
        verify(persistenceLayerMock).storeToken(result);
    }

    @Test(expected = InvalidCredentialsException.class)
    public void testLoginUserNoUsername() throws InvalidCredentialsException {
        final CredentialsDTO credentials = new CredentialsDTO("", "password");
        underTest.loginUser(credentials);
    }

    @Test(expected = InvalidCredentialsException.class)
    public void testLoginUserMemberNotExist() throws InvalidCredentialsException {
        final CredentialsDTO credentials = new CredentialsDTO("user", "password");
        when(persistenceLayerMock.getUser(anyString())).thenReturn(null);
        underTest.loginUser(credentials);
    }

    @Test(expected = InvalidCredentialsException.class)
    public void testLoginUserInvalidSecret() throws InvalidCredentialsException {
        final UserDTO user = new UserDTO(1L, "user", "password", Permission.ADMIN);
        final CredentialsDTO credentials = new CredentialsDTO("user", "wrong");
        when(persistenceLayerMock.getUser(anyString())).thenReturn(user);
        underTest.loginUser(credentials);
    }

    @Test
    public void testLogoutUser() {
        final CredentialsDTO token = new CredentialsDTO("token");
        underTest.logoutUser(token);
        verify(persistenceLayerMock).destroyToken(anyString());
    }

    @Test
    public void testValidateToken() throws UnauthorizedAccessException, InvalidTokenException,
            InvalidCredentialsException {
        final CredentialsDTO credentialsDTO = new CredentialsDTO("token");
        final String method = "method";

        final TokenDTO token = new TokenDTO("token", 1L, System.currentTimeMillis() + AuthManagerImpl.TOKEN_TTL);
        final UserDTO user = new UserDTO(1L, "user", "password", Permission.ADMIN);
        final List<String> permissionListMock = new ArrayList<>();
        permissionListMock.add(method);

        when(persistenceLayerMock.getToken(credentialsDTO.getToken())).thenReturn(token);
        when(persistenceLayerMock.getUser(token.getUserid())).thenReturn(user);
        when(persistenceLayerMock.getMethodsForPermission(Permission.ADMIN)).thenReturn(permissionListMock);

        underTest.validateToken(credentialsDTO, method);

        verify(persistenceLayerMock).updateToken(any());
    }

    @Test(expected = InvalidCredentialsException.class)
    public void testValidateTokenNoToken() throws UnauthorizedAccessException, InvalidTokenException,
            InvalidCredentialsException {
        final CredentialsDTO credentialsDTO = new CredentialsDTO("");
        final String method = "method";

        underTest.validateToken(credentialsDTO, method);
    }

    @Test(expected = InvalidTokenException.class)
    public void testValidateTokenInvalidToken() throws UnauthorizedAccessException, InvalidTokenException,
            InvalidCredentialsException {
        final CredentialsDTO credentialsDTO = new CredentialsDTO("token");
        final String method = "method";

        when(persistenceLayerMock.getToken(credentialsDTO.getToken())).thenReturn(null);

        underTest.validateToken(credentialsDTO, method);
    }

    @Test(expected = InvalidTokenException.class)
    public void testValidateTokenTokenExpired() throws UnauthorizedAccessException, InvalidTokenException,
            InvalidCredentialsException {
        final CredentialsDTO credentialsDTO = new CredentialsDTO("token");
        final String method = "method";

        final TokenDTO token = new TokenDTO("token", 1L, System.currentTimeMillis() - 9_999_999);

        when(persistenceLayerMock.getToken(credentialsDTO.getToken())).thenReturn(token);

        underTest.validateToken(credentialsDTO, method);
    }

    @Test(expected = InvalidTokenException.class)
    public void testValidateTokenInvalidUseridInToken() throws UnauthorizedAccessException, InvalidTokenException,
            InvalidCredentialsException {
        final CredentialsDTO credentialsDTO = new CredentialsDTO("token");
        final String method = "method";

        final TokenDTO token = new TokenDTO("token", 1L, System.currentTimeMillis() + AuthManagerImpl.TOKEN_TTL);

        when(persistenceLayerMock.getToken(credentialsDTO.getToken())).thenReturn(token);
        when(persistenceLayerMock.getUser(token.getUserid())).thenReturn(null);

        underTest.validateToken(credentialsDTO, method);

        verify(persistenceLayerMock).updateToken(any());
    }

    @Test(expected = UnauthorizedAccessException.class)
    public void testValidateTokenUnauthorizedMethod() throws UnauthorizedAccessException, InvalidTokenException,
            InvalidCredentialsException {
        final CredentialsDTO credentialsDTO = new CredentialsDTO("token");
        final String method = "method";

        final TokenDTO token = new TokenDTO("token", 1L, System.currentTimeMillis() + AuthManagerImpl.TOKEN_TTL);
        final UserDTO user = new UserDTO(1L, "user", "password", Permission.ADMIN);
        final List<String> permissionListMock = new ArrayList<>();

        when(persistenceLayerMock.getToken(credentialsDTO.getToken())).thenReturn(token);
        when(persistenceLayerMock.getUser(token.getUserid())).thenReturn(user);
        when(persistenceLayerMock.getMethodsForPermission(Permission.ADMIN)).thenReturn(permissionListMock);

        underTest.validateToken(credentialsDTO, method);

        verify(persistenceLayerMock).updateToken(any());
    }
}
