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

        final String HQL = "SELECT new org.california.model.util.KeyValue(II.added.account, COUNT(II.added.account)) " +
                "FROM ItemInstance II " +
                "WHERE II.added.account in (:accounts) AND II.container IN (:containers) " +
                "                       GROUP BY II.added.account";


        Query<KeyValue<Account, Long>> query = getSession().createQuery(HQL);
        query.setParameterList("accounts", accounts);
        query.setParameterList("containers", containers);

        return query.getResultList().stream().collect(Collectors.toMap(KeyValue::getKey, KeyValue::getValue));
    }


    @Override
    public Map<Account, Long> getOpenedInstancesStats(Collection<Account> accounts, Collection<Container> containers) {
        if(accounts == null || containers == null || accounts.isEmpty() || containers.isEmpty())
            return Collections.emptyMap();

        final String HQL = "SELECT new org.california.model.util.KeyValue(II.opened.account, COUNT(II.opened.account)) " +
                "FROM ItemInstance II " +
                "WHERE II.opened.account IN (:accounts) AND II.container IN (:containers) " +
                "GROUP BY II.opened.account";

        Query<KeyValue<Account, Long>> query = getSession().createQuery(HQL);
        query.setParameterList("accounts", accounts);
        query.setParameterList("containers", containers);

        return query.getResultList().stream().collect(Collectors.toMap(KeyValue::getKey, KeyValue::getValue));
    }


    @Override
    public Map<Account, Long> getDeletedInstancesStats(Collection<Account> accounts, Collection<Container> containers) {
        if(accounts == null || containers == null || accounts.isEmpty() || containers.isEmpty())
            return Collections.emptyMap();

        final String HQL = "SELECT new org.california.model.util.KeyValue(II.deleted.account, COUNT(II.deleted.account)) " +
                "FROM ItemInstance II " +
                "WHERE II.deleted.account in (:accounts) AND II.container IN (:containers) " +
                "GROUP BY II.deleted.account";

        Query<KeyValue<Account, Long>> query = getSession().createQuery(HQL);
        query.setParameterList("accounts", accounts);
        query.setParameterList("containers", containers);

        return query.getResultList().stream().collect(Collectors.toMap(KeyValue::getKey, KeyValue::getValue));
    }

}
