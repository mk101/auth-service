package kolesov.maksim.mapping.auth.service.impl.repo;

import kolesov.maksim.mapping.auth.model.UserRoleEntity;
import kolesov.maksim.mapping.auth.repository.UserRoleRepository;
import kolesov.maksim.mapping.auth.service.repo.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRoleServiceImpl extends UserRoleService {

    @Autowired
    protected UserRoleServiceImpl(UserRoleRepository repository) {
        super(repository);
    }

    @Override
    public List<UserRoleEntity> getRolesByUser(UUID userId) {
        return getRepository().getRolesByUser(userId);
    }

}
