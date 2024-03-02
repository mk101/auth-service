package kolesov.maksim.mapping.auth.service.repo;

import kolesov.maksim.mapping.auth.model.RefreshTokenEntity;
import kolesov.maksim.mapping.auth.repository.RefreshTokenRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class RedisService extends AbstractRepoService<RefreshTokenRepository, RefreshTokenEntity, UUID> {

    protected RedisService(RefreshTokenRepository repository) {
        super(repository);
    }

    public abstract Optional<String> findTokenByUserId(UUID id);

    public abstract void delete(UUID id);

}
