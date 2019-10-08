package org.california.repository.item;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.repository.AbstractRepositoryImpl;
import org.california.repository.utils.OffsetLimit;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
@Repository
public class ItemSearchRepositoryImpl extends AbstractRepositoryImpl implements ItemSearchRepository {

    @Transactional(readOnly = true)
    public Collection<Item> getByIds(Collection<Long> ids) {

        return new QueryBuilder().idIn(ids)
                         .build().getResultList();
    }


    @Transactional(readOnly = true)
    public Collection<Item> searchByName(String name) {

        return new QueryBuilder().nameLike(name)
                           .AND().placeNull()
                           .build().getResultList();
    }


    @Transactional(readOnly = true)
    public Collection<Item> searchByNameAndPlaces(String name, Collection<Place> places) {

        return new QueryBuilder().nameLike(name)
                           .AND().placeIn(places)
                           .build().getResultList();
    }


    public Collection<Item> searchByPlaceAndCategories(Collection<Place> places, Collection<Category> categories) {

        return new QueryBuilder().categoryIn(categories)
                           .AND().placeIn(places)
                           .build().getResultList();
    }


    @Transactional(readOnly = true)
    public Collection<Item> searchByNameAndCategories(String name, Collection<Category> categories) {

        return new QueryBuilder().nameLike(name)
                           .AND().categoryIn(categories)
                           .AND().placeNull()
                           .build().getResultList();
    }


    @Transactional(readOnly = true)
    public Collection<Item> searchByNameAndPlacesAndCategories(String name, Collection<Place> places, Collection<Category> categories) {
        return  new QueryBuilder().nameLike(name)
                            .AND().categoryIn(categories)
                            .AND().placeIn(places)
                            .build().getResultList();
    }


    @Transactional(readOnly = true)
    public Collection<Item> searchByBarcode(long barcode) {

        return new QueryBuilder().barcodeEq(barcode)
                           .AND().placeNull()
                           .build().getResultList();
    }


    @Transactional(readOnly = true)
    public Collection<Item> searchByBarcodeAndPlaces(long barcode, Collection<Place> places) {

        return new QueryBuilder().barcodeEq(barcode).AND().placeNull().AND().placeIn(places).build().getResultList();
    }


    private class QueryBuilder {


        private String query = " SELECT I FROM Item I WHERE ";
        private OffsetLimit offsetLimit;
        private Map<String, Collection> parameterLists = new HashMap<>();
        private Map<String, Object> parameters = new HashMap<>();


        private QueryBuilder OR() {
            query = query + " OR ";
            return this;
        }

        private QueryBuilder AND() {
            query = query + " AND ";
            return this;
        }

        private QueryBuilder nameLike(String name) {
            query = query + " I.name LIKE :nameLike ";
            parameters.put("nameLike", "%" + name + "%");
            return this;
        }

        private QueryBuilder nameEq(String name) {
            query = query + " I.name LIKE :nameEq ";
            parameters.put("nameEq", name);
            return this;
        }

        private QueryBuilder placeNull() {
            query = query + " I.place IS NULL ";
            return this;
        }

        private QueryBuilder placeIn(Collection<Place> places) {
            if(CollectionUtils.isEmpty(places))
                return placeNull();

            query = query + " (I.place IS NULL OR I.place IN :placeIn) ";
            parameterLists.put("placeIn", places);
            return this;
        }

        private QueryBuilder barcodeEq(Long barcode) {
            query = query + " I.barcode = :barcodeEq";
            parameters.put("barcodeEq", barcode);
            return this;
        }

        private QueryBuilder offsetLimit(OffsetLimit offsetLimit) {
            this.offsetLimit = offsetLimit;
            return this;
        }

        private QueryBuilder categoryIn(Collection<Category> categories) {
            query = query + " I.category IN :categoriesIn ";
            parameterLists.put("categoriesIn", categories);
            return this;
        }

        private QueryBuilder idIn(Collection<Long> ids) {
            query = query + " I.id IN :idsIn ";
            parameterLists.put("idsIn", ids);
            return this;
        }


        private Query<Item> build() {
            Query<Item> query = getSession().createQuery(this.query);
            if(offsetLimit != null)
                offsetLimit.applyToQuery(query);

            parameters.forEach(query::setParameter);
            parameterLists.forEach(query::setParameterList);

            return query;
        }


    }


}
