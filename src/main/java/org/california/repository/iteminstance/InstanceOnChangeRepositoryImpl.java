package org.california.repository.iteminstance;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Container;
import org.california.model.entity.InstanceChange;
import org.california.model.entity.ItemInstance;
import org.california.repository.AbstractRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Repository
public class InstanceOnChangeRepositoryImpl extends AbstractRepositoryImpl<InstanceChange> implements InstanceOnChangeRepository {

    public InstanceOnChangeRepositoryImpl() {
        setClazz(InstanceChange.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<InstanceChange> getByContainers(Collection<Container> containers, int limit) {
        if(CollectionUtils.isEmpty(containers))
            return Collections.emptySet();

        final String HQL = "SELECT IC FROM InstanceChange IC WHERE IC.instance.container IN (:containers) ORDER BY IC.changeDate DESC";

        Query<InstanceChange> query = getSession().createQuery(HQL);
        query.setParameterList("containers", containers);
        query.setMaxResults(limit);

        return query.getResultList();
    }


    @Override
    public Collection<InstanceChange> getByInstances(Collection<ItemInstance> instances, int limit) {
        if(CollectionUtils.isEmpty(instances))
            return Collections.emptySet();

        final String HQL = "SELECT IC FROM InstanceChange IC WHERE IC.instance IN (:instances) ORDER BY IC.changeDate DESC";

        Query<InstanceChange> query = getSession().createQuery(HQL);
        query.setParameterList("instances", instances);
        query.setMaxResults(limit);

        return query.getResultList();
    }

}
