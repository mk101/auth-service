package kolesov.maksim.mapping.auth.service.repo;

import kolesov.maksim.mapping.auth.model.UserEntity;
import kolesov.maksim.mapping.auth.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class UserService extends AbstractRepoService<UserRepository, UserEntity, UUID> {

    protected UserService(UserRepository repository) {
        super(repository);
    }

    public abstract Optional<UserEntity> findByLogin(String login);

    public abstract boolean existById(UUID id);

    public abstract void create(UserEntity entity);
}
