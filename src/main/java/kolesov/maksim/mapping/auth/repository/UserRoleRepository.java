package kolesov.maksim.mapping.auth.repository;

import kolesov.maksim.mapping.auth.model.UserRoleEntity;
import kolesov.maksim.mapping.auth.model.key.UserRoleKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, UserRoleKey> {
}
