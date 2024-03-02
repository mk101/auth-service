package kolesov.maksim.mapping.auth.service.impl.repo;

import kolesov.maksim.mapping.auth.model.RefreshTokenEntity;
import kolesov.maksim.mapping.auth.repository.RefreshTokenRepository;
import kolesov.maksim.mapping.auth.service.repo.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class RedisServiceImpl extends RedisService {

    @Autowired
    public RedisServiceImpl(RefreshTokenRepository repository) {
        super(repository);
    }

    @Override
    public Optional<String> findTokenByUserId(UUID id) {
        return getRepository().findById(id).map(RefreshTokenEntity::getToken);
    }

    @Override
    public void delete(UUID id) {
        Optional<RefreshTokenEntity> entity = findById(id);
        if (entity.isEmpty()) {
            return;
        }

        getRepository().delete(entity.get());
    }

}
