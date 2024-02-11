package kolesov.maksim.mapping.auth.mapper;

import kolesov.maksim.mapping.auth.dto.UserRoleDto;
import kolesov.maksim.mapping.auth.model.UserRoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper extends AbstractMapper<UserRoleEntity, UserRoleDto> {
}
