package kolesov.maksim.mapping.auth.service.repo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class AbstractRepoService<R extends CrudRepository<E, I>, E, I> {

    private final R repository;

    protected AbstractRepoService(R repository) {
        this.repository = repository;
    }

    public Optional<E> findById(I id) {
        return repository.findById(id);
    }

    @SuppressWarnings("UnusedReturnValue")
    public E save(E entity) {
        return repository.save(entity);
    }

    public void delete(E entity) {
        repository.delete(entity);
    }

    protected R getRepository() {
        return repository;
    }

}
