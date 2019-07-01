package org.california.repository.item;

import org.california.model.entity.item.Producer;
import org.california.repository.AbstractNamedEntityRepository;

import java.util.Collection;

public interface ProducerRepository extends AbstractNamedEntityRepository<Producer> {

    Collection<Producer> getAll();
}
