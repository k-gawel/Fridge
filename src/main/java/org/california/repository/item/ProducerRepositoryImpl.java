package org.california.repository.item;

import org.california.model.entity.item.Producer;
import org.california.repository.AbstractNamedEntityRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ProducerRepositoryImpl extends AbstractNamedEntityRepositoryImpl<Producer> implements ProducerRepository {

    public ProducerRepositoryImpl() { setClazz(Producer.class); }

    @Override
    public Collection<Producer> getAll() {
        final String HQL = "SELECT P FROM Producer P";
        Query<Producer> query = getSession().createQuery(HQL);
        return query.getResultList();
    }
}
