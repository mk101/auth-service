package kolesov.maksim.mapping.auth.service.repo;

import kolesov.maksim.mapping.auth.model.UserRoleEntity;
import kolesov.maksim.mapping.auth.model.key.UserRoleKey;
import kolesov.maksim.mapping.auth.repository.UserRoleRepository;

public abstract class UserRoleService extends AbstractRepoService<UserRoleRepository, UserRoleEntity, UserRoleKey> {

    protected UserRoleService(UserRoleRepository repository) {
        super(repository);
    }

}
