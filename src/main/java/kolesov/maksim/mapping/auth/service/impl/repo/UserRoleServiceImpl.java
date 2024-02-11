package kolesov.maksim.mapping.auth.service.impl.repo;

import kolesov.maksim.mapping.auth.repository.UserRoleRepository;
import kolesov.maksim.mapping.auth.service.repo.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends UserRoleService {

    @Autowired
    protected UserRoleServiceImpl(UserRoleRepository repository) {
        super(repository);
    }

}
