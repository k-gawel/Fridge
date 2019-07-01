package org.california.repository.iteminstance;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.item.Item;
import org.california.model.entity.ItemInstance;
import org.california.repository.AbstractRepositoryImpl;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Repository
@Transactional
public class ItemInstanceRepositoryImpl extends AbstractRepositoryImpl<ItemInstance> implements ItemInstanceRepository {

    public ItemInstanceRepositoryImpl() { setClazz(ItemInstance.class); }


    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public Collection<ItemInstance> getByIds(Collection<Long> ids) {
        if(CollectionUtils.isEmpty(ids))
            return Collections.emptySet();

        final String HQL = "SELECT II FROM ItemInstance II WHERE II.id in :ids";

        Query<ItemInstance> query = getSession().createQuery(HQL);
        query.setParameterList("ids", ids);

        return query.getResultList();
    }


    @Override
    public Collection<ItemInstance> getByContainers(Collection<Container> containers, Boolean deleted, Boolean open, Boolean frozen, int limit) {
        if(CollectionUtils.isEmpty(containers))
            return Collections.emptySet();

        String HQL = "SELECT II FROM ItemInstance II WHERE II.container IN (:containers)";
        HQL += booleanConditions(deleted, open, frozen);

        Query<ItemInstance> query = getSession().createQuery(HQL);
        if(limit != 0) query.setMaxResults(limit);
        query.setParameterList("containers", containers);

        return query.getResultList();
    }


    @Override
    public Collection<ItemInstance> getByOwners(Collection<Account> owners, Boolean deleted, Boolean open, Boolean frozen, int limit) {
        if(CollectionUtils.isEmpty(owners))
            return Collections.emptySet();

        String HQL = "SELECT II FROM ItemInstance II WHERE II.addedBy IN :owners";
        HQL += booleanConditions(deleted, open, frozen);

        Query<ItemInstance> query = getSession().createQuery(HQL);
        if(limit != 0) query.setMaxResults(limit);
        query.setParameterList("owners", owners);

        return query.getResultList();
    }


    @Override
    public Collection<ItemInstance> getByContainersAndOwners(Collection<Container> containers, Collection<Account> owners, Boolean deleted, Boolean open, Boolean frozen, int limit) {
        if(CollectionUtils.isEmpty(containers) || CollectionUtils.isEmpty(owners))
            return Collections.emptySet();

        String HQL = "SELECT II FROM ItemInstance II WHERE II.container IN :containers " +
                                                      "AND II.addedBy IN :owners";
        HQL += booleanConditions(deleted, open, false);


        Query<ItemInstance> query = getSession().createQuery(HQL);
        if(limit != 0) query.setMaxResults(limit);
        query.setParameterList("containers", containers);
        query.setParameterList("owners", owners);

        return query.getResultList();
    }


    @Override
    public Collection<ItemInstance> getByItemsAndOwners(Collection<Item> items, Collection<Account> owners, Boolean deleted, Boolean open, Boolean frozen, int limit) {
        if(CollectionUtils.isEmpty(items) || CollectionUtils.isEmpty(owners))
            return Collections.emptySet();


        String HQL = "SELECT II FROM ItemInstance II WHERE II.item in (:items) " +
                                                      "AND II.addedBy IN (:owners)";
        HQL += booleanConditions(deleted, open, frozen);


        Query<ItemInstance> query = getSession().createQuery(HQL);
        if(limit != 0) query.setMaxResults(limit);
        query.setParameterList("items", items);
        query.setParameterList("owners", owners);

        return query.getResultList();
    }


    @Override
    public Collection<ItemInstance> getByItemsAndContainers(Collection<Item> items, Collection<Container> containers, Boolean deleted, Boolean open, Boolean frozen, int limit) {
        if(CollectionUtils.isEmpty(items) || CollectionUtils.isEmpty(containers))
            return Collections.emptySet();

        String HQL = "SELECT II FROM ItemInstance II WHERE II.item IN :items " +
                                                      "AND II.container IN :containrs";
        HQL += booleanConditions(deleted, open, frozen);

        Query<ItemInstance> query = getSession().createQuery(HQL);
        if(limit != 0) query.setMaxResults(limit);
        query.setParameterList("items", items);
        query.setParameterList("containers", containers);

        return query.getResultList();
    }


    @Override
    public Collection<ItemInstance> getByItemsAndContainersAndOwners(Collection<Item> items, Collection<Container> containers, Collection<Account> owners, Boolean deleted, Boolean open, Boolean frozen, int limit) {
        if(CollectionUtils.isEmpty(items) || CollectionUtils.isEmpty(containers) || CollectionUtils.isEmpty(owners))
            return Collections.emptySet();

        String HQL = "SELECT II FROM ItemInstance II WHERE II.item IN :items " +
                                                      "AND II.container IN :containers " +
                                                      "AND II.addedBy IN :owners";
        HQL += booleanConditions(deleted, open, frozen);

        Query<ItemInstance> query = getSession().createQuery(HQL);
        if(limit != 0) query.setMaxResults(limit);
        query.setParameterList("items", items);
        query.setParameterList("containers", containers);
        query.setParameterList("owners", owners);

        return query.getResultList();
    }


    private String booleanConditions(Boolean deleted, Boolean open, Boolean frozen) {

        StringBuilder HQLBuilder = new StringBuilder();


        final String NOT_NULL = "NOT NULL";
        final String NULL = "NULL";

        if(deleted != null)
            HQLBuilder.append(" AND II.deletedBy IS ").append(deleted ? NOT_NULL : NULL);
        if(open != null)
            HQLBuilder.append(" AND II.openBy IS ").append(open ? NOT_NULL : NULL);
        if(frozen != null)
            HQLBuilder.append(" AND II.frozenBy IS ").append(frozen ? NOT_NULL : NULL);

        return HQLBuilder.toString();
    }
}