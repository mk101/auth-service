package kolesov.maksim.mapping.auth.service.impl.request_processing;

import kolesov.maksim.mapping.auth.dto.RoleGroup;
import kolesov.maksim.mapping.auth.dto.UserDto;
import kolesov.maksim.mapping.auth.exception.ServiceException;
import kolesov.maksim.mapping.auth.mapper.UserMapper;
import kolesov.maksim.mapping.auth.model.UserEntity;
import kolesov.maksim.mapping.auth.model.UserRoleEntity;
import kolesov.maksim.mapping.auth.service.repo.UserRoleService;
import kolesov.maksim.mapping.auth.service.repo.UserService;
import kolesov.maksim.mapping.auth.service.request_processing.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserMapper mapper;
    private final UserService userService;
    private final UserRoleService userRoleService;

    @Override
    @Transactional
    public void register(UserDto userDto) throws ServiceException {
        if (userService.findByLogin(userDto.getLogin()).isPresent()) {
            throw new ServiceException("User with this login already exists");
        }

        if (userDto.getGroup() == RoleGroup.ADMIN) {
            throw new ServiceException("Please contact with system owner to create a admin account");
        }

        UserEntity user = mapper.toEntity(userDto);
        user.setActive(true);

        user.setId(UUID.randomUUID());
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        for (UserRoleEntity r : user.getRoles()) {
            r.setUserId(user.getId());
        }

        userService.create(user);
        for (UserRoleEntity r : user.getRoles()) {
            userRoleService.save(r);
        }
    }

}
