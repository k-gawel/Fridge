package org.california.repository.item;


import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Category;
import org.california.model.entity.Item;
import org.california.model.entity.Place;
import org.california.model.util.KeyValue;
import org.california.repository.AbstractRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class RelatedItemsRepositoryImpl extends AbstractRepositoryImpl implements RelatedItemsRepository{

    @Override
    public Collection<Item> getMostPopularOfCategories(Collection<Category> categories) {
        if(CollectionUtils.isEmpty(categories))
            return Collections.emptySet();

        final String HQL = "SELECT II.item FROM ItemInstance II " +
                           "WHERE II.item.category IN (:categories) AND II.item.place IS NULL " +
                           "GROUP BY II.item ORDER BY count (*) DESC";

        final String HQL2 = "SELECT I FROM Item I LEFT JOIN ItemInstance II ON I = II.item " +
                            "WHERE I.category in :categories AND I.place IS NULL " +
                            "GROUP BY I " +
                            "ORDER BY COUNT(II.item) DESC";

        Query<Item> query = getSession().createQuery(HQL2);
        query.setParameterList("categories", categories);
        query.setMaxResults(10);

        return query.getResultList();
    }


    @Override
    public Collection<Item> getMostPopularOfCategories(Collection<Place> places, Collection<Category> categories) {
        if(CollectionUtils.isEmpty(places) || CollectionUtils.isEmpty(categories))
            return Collections.emptySet();

        final String HQL = "SELECT II.item FROM ItemInstance II " +
                           "WHERE II.item.category IN (:categories) AND (II.item.place IS NULL OR II.item.place IN (:places)) " +
                           "GROUP BY II.item ORDER BY count (*) DESC ";


        final String HQL2 = "SELECT I FROM Item I LEFT JOIN ItemInstance II ON I = II.item " +
                "WHERE I.category IN (:categories) AND (I.place IS NULL OR I.place IN (:places)) " +
                "GROUP BY I " +
                "ORDER BY COUNT(II.item) DESC";

        Query<Item> query = getSession().createQuery(HQL2);
        query.setParameterList("categories", categories);
        query.setParameterList("places", places);
        query.setMaxResults(10);

        return query.getResultList();
    }


    @Override
    public Collection<KeyValue<Item, Long>> getItemInstanceCount(List<Item> items) {
        if(CollectionUtils.isEmpty(items))
            return Collections.emptySet();

        final String HQL = "SELECT new org.california.model.util.KeyValue(II.item, count (*)) FROM ItemInstance II " +
                           "WHERE II.item in (:items) GROUP BY II.item ORDER BY count (*) DESC ";

        Query<KeyValue<Item, Long>> query = getSession().createQuery(HQL);
        query.setParameter("items", items);

        return query.getResultList();
    }


    @Override
    public Collection<Item> getMostPopularItems() {
        final String HQL = "SELECT new org.california.model.util.KeyValue(II.item, count (*)) FROM ItemInstance II " +
                           "WHERE II.item.place = null GROUP BY II.item ORDER BY count (*) DESC";

        Query<Item> query = getSession().createQuery(HQL);
        query.setMaxResults(10);

        return query.getResultList();
    }

}
