package org.california.repository.item;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.model.entity.Place;
import org.california.repository.AbstractRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Repository
public class ItemSearchRepositoryImpl extends AbstractRepositoryImpl implements ItemSearchRepository {


    @Override
    @Transactional(readOnly = true)
    public Collection<Item> getByIds(Collection<Long> ids) {
        if(ids == null || ids.isEmpty())
            return Collections.emptySet();

        final String HQL = "SELECT I from Item I WHERE I.id IN (:ids)";

        Query<Item> query = getSession().createQuery(HQL);
        query.setParameter("ids", ids);

        return query.getResultList();
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<Item> searchByName(String name) {
        if(name == null || name.isEmpty())
            return Collections.emptySet();

        final String HQL = "SELECT I FROM Item I WHERE I.name LIKE :name " +
                                                  "AND I.place IS NULL";

        Query<Item> query = getSession().createQuery(HQL);
        query.setParameter("name", "%" + name  + "%");

        return query.getResultList();
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<Item> searchByNameAndPlaces(String name, Collection<Place> places) {
        if(name == null || CollectionUtils.isEmpty(places))
            return Collections.emptySet();

        final String HQL = "SELECT I FROM Item I WHERE I.name LIKE :name " +
                                                  "AND (I.place IS NULL OR I.place in :places)";

        Query<Item> query = getSession().createQuery(HQL);
        query.setParameter("name", "%" + name  + "%");
        query.setParameterList("places", places);

        return query.getResultList();
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<Item> searchByNameAndCategories(String name, Collection<Category> categories) {
        if(name == null || CollectionUtils.isEmpty(categories))
            return Collections.emptySet();

        final String HQL = "SELECT I FROM Item I WHERE I.name LIKE :name " +
                "AND I.category IN :categories " +
                "AND I.place IS NULL";

        Query<Item> query = getSession().createQuery(HQL);
        query.setParameter("name", "%" + name  + "%");
        query.setParameterList("categories", categories);

        return query.getResultList();
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<Item> searchByNameAndPlacesAndCategories(String name, Collection<Place> places, Collection<Category> categories) {
        if(name == null || name.isEmpty() || CollectionUtils.isEmpty(places) || CollectionUtils.isEmpty(categories))
            return Collections.emptySet();

        final String HQL = "SELECT I FROM Item I WHERE I.name LIKE :name " +
                                                  "AND I.category IN :categories " +
                                                  "AND (I.place IS NULL OR I.place IN :places)";

        Query<Item> query = getSession().createQuery(HQL);
        query.setParameter("name", "%" + name  + "%");
        query.setParameterList("categories", categories);
        query.setParameterList("places", places);

        return query.getResultList();
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<Item> searchByBarcode(long barcode) {

        final String HQL = "SELECT I FROM Item I WHERE I.barcode = :barcode " +
                                                  "AND I.place IS NULL";

        Query<Item> query = getSession().createQuery(HQL);
        query.setParameter("barcode", barcode);

        return query.getResultList();
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<Item> searchByBarcodeAndPlaces(long barcode, Collection<Place> places) {
        if(CollectionUtils.isEmpty(places))
            return Collections.emptySet();

        final String HQL = "SELECT I FROM Item I WHERE I.barcode = :barcode " +
                                                  "AND (I.place IN :places OR I.place IS NULL)";

        Query<Item> query = getSession().createQuery(HQL);
        query.setParameterList("places", places);
        query.setParameter("barcode", barcode);

        return query.getResultList();
    }

}
