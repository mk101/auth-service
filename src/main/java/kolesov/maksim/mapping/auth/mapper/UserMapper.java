package kolesov.maksim.mapping.auth.mapper;

import kolesov.maksim.mapping.auth.dto.UserDto;
import kolesov.maksim.mapping.auth.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {UserRoleMapper.class}
)
public interface UserMapper extends AbstractMapper<UserEntity, UserDto> {

    @Override
    @Mapping(target = "roles", source = "group")
    @Mapping(target = "active", ignore = true)
    UserEntity toEntity(UserDto dto);

}
