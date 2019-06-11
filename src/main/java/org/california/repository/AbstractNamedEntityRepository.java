package org.california.repository;

import java.util.Collection;

public interface AbstractNamedEntityRepository<T> extends AbstractRepository<T> {

    T getByName(String name);

    Collection<T> searchByName(String name);

    Collection<T> getWhereNameStartsWith(String nameStart);
}
