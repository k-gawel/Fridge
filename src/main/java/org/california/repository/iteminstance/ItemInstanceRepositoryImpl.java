package org.california.repository.iteminstance;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.item.Item;
import org.california.model.transfer.request.queries.ItemInstanceParams;
import org.california.repository.AbstractRepositoryImpl;
import org.california.repository.utils.OffsetLimit;
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
    public Collection<ItemInstance> getByContainers(Collection<Container> containers, ItemInstanceParams parameters, OffsetLimit lo) {
        if(CollectionUtils.isEmpty(containers))
            return Collections.emptySet();

        String HQL = "SELECT II FROM ItemInstance II WHERE II.container IN (:containers)";
        HQL += booleanConditions(parameters);

        Query<ItemInstance> query = getSession().createQuery(HQL);
        lo.applyToQuery(query);
        query.setParameterList("containers", containers);

        return query.getResultList();
    }


    @Override
    public Collection<ItemInstance> getByOwners(Collection<Account> owners, ItemInstanceParams parameters, OffsetLimit lo) {
        if(CollectionUtils.isEmpty(owners))
            return Collections.emptySet();

        String HQL = "SELECT II FROM ItemInstance II WHERE II.added.account IN :owners";
        HQL += booleanConditions(parameters);

        Query<ItemInstance> query = getSession().createQuery(HQL);
        lo.applyToQuery(query);
        query.setParameterList("owners", owners);

        return query.getResultList();
    }


    @Override
    public Collection<ItemInstance> getByContainersAndOwners(Collection<Container> containers, Collection<Account> owners, ItemInstanceParams parameters, OffsetLimit lo) {
        if(CollectionUtils.isEmpty(containers) || CollectionUtils.isEmpty(owners))
            return Collections.emptySet();

        String HQL = "SELECT II FROM ItemInstance II " +
                "WHERE II.container IN :containers AND II.added.account IN :owners";
        HQL += booleanConditions(parameters);


        Query<ItemInstance> query = getSession().createQuery(HQL);
        lo.applyToQuery(query);
        query.setParameterList("containers", containers);
        query.setParameterList("owners", owners);

        return query.getResultList();
    }


    @Override
    public Collection<ItemInstance> getByItemsAndOwners(Collection<Item> items, Collection<Account> owners, ItemInstanceParams parameters, OffsetLimit lo) {
        if(CollectionUtils.isEmpty(items) || CollectionUtils.isEmpty(owners))
            return Collections.emptySet();


        String HQL = "SELECT II FROM ItemInstance II WHERE II.item in (:items) " +
                "AND II.added.account IN (:owners)";
        HQL += booleanConditions(parameters);


        Query<ItemInstance> query = getSession().createQuery(HQL);
        lo.applyToQuery(query);
        query.setParameterList("items", items);
        query.setParameterList("owners", owners);

        return query.getResultList();
    }


    @Override
    public Collection<ItemInstance> getByItemsAndContainers(Collection<Item> items, Collection<Container> containers, ItemInstanceParams parameters, OffsetLimit lo) {
        if(CollectionUtils.isEmpty(items) || CollectionUtils.isEmpty(containers))
            return Collections.emptySet();


        String HQL = "SELECT II FROM ItemInstance II WHERE II.item IN (:items) " +
                "AND II.container IN (:containers)";
        HQL += booleanConditions(parameters);

        Query<ItemInstance> query = getSession().createQuery(HQL);
        lo.applyToQuery(query);
        query.setParameterList("items", items);
        query.setParameterList("containers", containers);

        return query.getResultList();
    }


    @Override
    public Collection<ItemInstance> getByItemsAndContainersAndOwners(Collection<Item> items, Collection<Container> containers, Collection<Account> owners, ItemInstanceParams parameters, OffsetLimit lo) {
        if(CollectionUtils.isEmpty(items) || CollectionUtils.isEmpty(containers) || CollectionUtils.isEmpty(owners))
            return Collections.emptySet();

        String HQL = "SELECT II FROM ItemInstance II WHERE II.item IN :items " +
                                                      "AND II.container IN :containers " +
                "AND II.added.account IN :owners";
        HQL += booleanConditions(parameters);

        Query<ItemInstance> query = getSession().createQuery(HQL);
        lo.applyToQuery(query);
        query.setParameterList("items", items);
        query.setParameterList("containers", containers);
        query.setParameterList("owners", owners);

        return query.getResultList();
    }


    private String booleanConditions(ItemInstanceParams parameters) {
        StringBuilder HQLBuilder = new StringBuilder();

        final String NOT_NULL = "NOT NULL";
        final String NULL = "NULL";

        if (parameters.deleted != null)
            HQLBuilder.append(" AND II.deleted IS ").append(parameters.deleted ? NOT_NULL : NULL);
        if (parameters.opened != null)
            HQLBuilder.append(" AND II.opened IS ").append(parameters.opened ? NOT_NULL : NULL);
        if (parameters.frozen != null)
            HQLBuilder.append(" AND II.frozened IS ").append(parameters.frozen ? NOT_NULL : NULL);

        return HQLBuilder.toString();
    }
}