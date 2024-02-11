package kolesov.maksim.mapping.auth.repository;

import kolesov.maksim.mapping.auth.model.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    Optional<UserEntity> findByLogin(String login);

    @Query(
            nativeQuery = true,
            value = "INSERT INTO \"user\"(id, first_name, last_name, login, password) VALUES (" +
                        ":id, :first_name, :last_name, :login, :password" +
                    ")"
    )
    @Modifying
    void createUser(
            @Param("id") UUID id,
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("login") String login,
            @Param("password") String password
    );

}
