package org.california.repository.container;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.util.KeyValue;
import org.california.repository.AbstractRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ContainerRepositoryImpl extends AbstractRepositoryImpl<Container> implements ContainerRepository {

    public ContainerRepositoryImpl() {
        setClazz(Container.class);
    }

    @Override
    public Collection<Container> getByIds(Collection<Long> ids) {
        if(ids == null || ids.isEmpty())
            return Collections.emptySet();

        final String HQL = "SELECT C FROM Container C WHERE C.id in (:ids)";

        org.hibernate.query.Query query = getSession().createQuery(HQL);
        query.setParameter("ids", ids);

        return (Collection<Container>) query.getResultList();
    }


    @Override
    public Map<Account,Long> getAddedInstancesStats(Collection<Account> accounts, Collection<Container> containers) {
        if(accounts == null || containers == null || accounts.isEmpty() || containers.isEmpty())
            return Collections.emptyMap();

        final String HQL = "SELECT new org.california.model.util.KeyValue(II.addedBy, COUNT(II.addedBy)) FROM ItemInstance II " +
                              "WHERE II.addedBy in (:accounts) AND II.container IN (:containers) GROUP BY II.addedBy";


        Query<KeyValue<Account, Long>> query = getSession().createQuery(HQL);
        query.setParameterList("accounts", accounts);
        query.setParameterList("containers", containers);

        return query.getResultList().stream().collect(Collectors.toMap(KeyValue::getKey, KeyValue::getValue));
    }


    @Override
    public Map<Account, Long> getOpenedInstancesStats(Collection<Account> accounts, Collection<Container> containers) {
        if(accounts == null || containers == null || accounts.isEmpty() || containers.isEmpty())
            return Collections.emptyMap();

        final String HQL = "SELECT new org.california.model.util.KeyValue(II.openBy, COUNT(II.openBy)) FROM ItemInstance II WHERE II.openBy in (:accounts) AND II.container IN (:containers) GROUP BY II.openBy";

        Query<KeyValue<Account, Long>> query = getSession().createQuery(HQL);
        query.setParameterList("accounts", accounts);
        query.setParameterList("containers", containers);

        return query.getResultList().stream().collect(Collectors.toMap(KeyValue::getKey, KeyValue::getValue));
    }


    @Override
    public Map<Account, Long> getDeletedInstancesStats(Collection<Account> accounts, Collection<Container> containers) {
        if(accounts == null || containers == null || accounts.isEmpty() || containers.isEmpty())
            return Collections.emptyMap();

        final String HQL = "SELECT new org.california.model.util.KeyValue(II.deletedBy, COUNT(II.deletedBy)) FROM ItemInstance II " +
                "WHERE II.deletedBy in (:accounts) AND II.container IN (:containers) GROUP BY II.deletedBy";

        Query<KeyValue<Account, Long>> query = getSession().createQuery(HQL);
        query.setParameterList("accounts", accounts);
        query.setParameterList("containers", containers);

        return query.getResultList().stream().collect(Collectors.toMap(KeyValue::getKey, KeyValue::getValue));
    }

}
