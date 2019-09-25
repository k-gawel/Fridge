package org.california.repository;

import java.util.Collection;
import java.util.Optional;

public interface AbstractNamedEntityRepository<T> extends AbstractRepository<T> {

    Optional<T> getByName(String name);

    Collection<T> searchByName(String name);

    Collection<T> getWhereNameStartsWith(String nameStart);
}
