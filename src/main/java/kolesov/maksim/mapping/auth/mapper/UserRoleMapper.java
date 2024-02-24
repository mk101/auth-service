package kolesov.maksim.mapping.auth.mapper;

import kolesov.maksim.mapping.auth.dto.RoleGroup;
import kolesov.maksim.mapping.auth.dto.UserRoleDto;
import kolesov.maksim.mapping.auth.model.Role;
import kolesov.maksim.mapping.auth.model.UserRoleEntity;
import org.mapstruct.Mapper;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper extends AbstractMapper<UserRoleEntity, UserRoleDto> {

    default List<UserRoleEntity> groupToRoles(RoleGroup group) {
        if (group == RoleGroup.USER) {
            return toEntity(Role.getBasicRoles().stream().map(r -> UserRoleDto.builder().role(r).build()).toList());
        }

        return toEntity(Arrays.stream(Role.values()).map(r -> UserRoleDto.builder().role(r).build()).toList());
    }

}
