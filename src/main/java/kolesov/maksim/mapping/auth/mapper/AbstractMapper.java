package kolesov.maksim.mapping.auth.mapper;

import java.util.List;

public interface AbstractMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtos);

    List<D> toDto(List<E> entities);

}
