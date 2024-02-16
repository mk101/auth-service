package kolesov.maksim.mapping.auth.repository;

import kolesov.maksim.mapping.auth.model.UserRoleEntity;
import kolesov.maksim.mapping.auth.model.key.UserRoleKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, UserRoleKey> {

    @Query(value = "SELECT * FROM user_role WHERE user_id = :userId", nativeQuery = true)
    List<UserRoleEntity> getRolesByUser(@Param("userId") UUID userId);

}
