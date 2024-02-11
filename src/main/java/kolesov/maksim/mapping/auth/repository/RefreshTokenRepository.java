package kolesov.maksim.mapping.auth.repository;

import kolesov.maksim.mapping.auth.model.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, UUID> {
}
