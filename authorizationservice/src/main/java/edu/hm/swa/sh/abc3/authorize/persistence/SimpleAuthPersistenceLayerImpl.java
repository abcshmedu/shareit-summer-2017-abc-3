package edu.hm.swa.sh.abc3.authorize.persistence;

import edu.hm.swa.sh.abc3.authorize.common.Permission;
import edu.hm.swa.sh.abc3.authorize.common.TokenDTO;
import edu.hm.swa.sh.abc3.authorize.common.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of AuthPersistenceLayer.
 */
public class SimpleAuthPersistenceLayerImpl implements AuthPersistenceLayer {
    private static SimpleAuthPersistenceLayerImpl instance;
    private static List<UserDTO> userList;
    private static List<String> adminPermissionList;
    private static List<String> registredPermissionList;
    private static List<TokenDTO> tokenList;

    /**
     * To make persistence layer singleton.
     *
     * @return singleton instance of this class.
     */
    public static SimpleAuthPersistenceLayerImpl getInstance() {
        if (instance == null) {
            instance = new SimpleAuthPersistenceLayerImpl();
            initPersistence();
        }
        return instance;
    }

    private static void initPersistence() {
        userList = new ArrayList<>();
        userList.add(new UserDTO(1L, "shareituser", "shareitpassword", Permission.REGISTRED));
        userList.add(new UserDTO(2L, "shareitadmin", "shareitpassword", Permission.ADMIN));

        adminPermissionList = new ArrayList<>();
        adminPermissionList.add("addBook");
        adminPermissionList.add("getBook");
        adminPermissionList.add("getBooks");
        adminPermissionList.add("updateBooks");
        adminPermissionList.add("addDisc");
        adminPermissionList.add("getDisc");
        adminPermissionList.add("getDiscs");
        adminPermissionList.add("updateDisc");

        registredPermissionList = new ArrayList<>();
        registredPermissionList.add("getBook");
        registredPermissionList.add("getBooks");
        registredPermissionList.add("getDisc");
        registredPermissionList.add("getDiscs");

        tokenList = new ArrayList<>();
    }

    /**
     * Private constructor.
     */
    private SimpleAuthPersistenceLayerImpl() {
    }

    @Override
    public UserDTO getUser(final String username) {
        for (final UserDTO user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public UserDTO getUser(final long userid) {
        for (final UserDTO user : userList) {
            if (user.getUserId() == userid) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void storeToken(final TokenDTO token) {
        tokenList.add(token);
    }

    @Override
    public void updateToken(final TokenDTO token) {
        for (int index = 0; index < tokenList.size(); index++) {
            final TokenDTO oldToken = tokenList.get(index);
            if (oldToken.getToken().equals(token.getToken())) {
                final TokenDTO newToken = new TokenDTO(oldToken.getToken(),
                                                       oldToken.getUserid(),
                                                       token.getTimeToLive());
                tokenList.remove(index);
                tokenList.add(newToken);
                return;
            }
        }
    }

    @Override
    public TokenDTO getToken(final String token) {
        for (final TokenDTO tokenDTO : tokenList) {
            if (tokenDTO.getToken().equals(token)) {
                return tokenDTO;
            }
        }
        return null;
    }

    @Override
    public void destroyToken(final String token) {
        for (int index = 0; index < tokenList.size(); index++) {
            final TokenDTO tokenDTO = tokenList.get(index);
            if (tokenDTO.getToken().equals(token)) {
                tokenList.remove(index);
                return;
            }
        }
    }

    @Override
    public List<String> getMethodsForPermission(final Permission permission) {
        switch (permission) {
            case ADMIN:
                return adminPermissionList;
            case REGISTRED:
                return registredPermissionList;
            default:
                return new ArrayList<>();
        }
    }
}
