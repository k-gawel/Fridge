package org.california.repository.item;

import org.california.model.entity.item.Producer;
import org.california.repository.AbstractNamedEntityRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ProducerRepositoryImpl extends AbstractNamedEntityRepositoryImpl<Producer> implements ProducerRepository {

    public ProducerRepositoryImpl() { setClazz(Producer.class); }

}
