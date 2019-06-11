package org.california.repository.item;

import org.california.model.entity.Producent;
import org.california.repository.AbstractNamedEntityRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ProducerRepositoryImpl extends AbstractNamedEntityRepositoryImpl<Producent> implements ProducerRepository {

    public ProducerRepositoryImpl() { setClazz(Producent.class); }

}
