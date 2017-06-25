package edu.hm.swa.sh.abc3.authorize.persistence.transformer;

import edu.hm.swa.sh.abc3.authorize.common.Permission;
import edu.hm.swa.sh.abc3.authorize.common.UserDTO;
import edu.hm.swa.sh.abc3.authorize.persistence.entity.UserEntity;
import edu.hm.swa.sh.abc3.authorize.persistence.entity.UserRoleEntity;

/**
 * UserTransformer.
 */
public class UserTransformer {
    /**
     * Transform entity to dto.
     *
     * @param entity entity.
     * @return dto.
     */
    public UserDTO toUserDTO(final UserEntity entity) {
        if (entity == null) {
            return null;
        }
        final Long id = entity.getId();
        final String username = entity.getUsername();
        final String secret = entity.getSecret();
        final String permission = entity.getUserRoleEntity().getName();
        return new UserDTO(id, username, secret, Permission.find(permission));
    }

    /**
     * Transform to entity.
     *
     * @param dto dto.
     * @return entity.
     */
    public UserEntity toEntity(final UserDTO dto, final UserRoleEntity userRoleEntity) {
        if (dto == null) {
            return null;
        }
        final UserEntity entity = new UserEntity();
        entity.setId(dto.getUserId());
        entity.setUsername(dto.getUsername());
        entity.setSecret(dto.getSecret());
        entity.setUserRoleEntity(userRoleEntity);
        return entity;
    }
}
