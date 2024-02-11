package kolesov.maksim.mapping.auth.service.impl.repo;

import kolesov.maksim.mapping.auth.model.UserEntity;
import kolesov.maksim.mapping.auth.repository.UserRepository;
import kolesov.maksim.mapping.auth.service.repo.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl extends UserService {

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return getRepository().findByLogin(login);
    }

    @Override
    public boolean existById(UUID id) {
        return getRepository().existsById(id);
    }

    @Override
    public void create(UserEntity entity) {
        getRepository().createUser(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getLogin(), entity.getPassword());
    }

}
