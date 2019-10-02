package org.california.repository.shoplist;

import org.california.model.entity.Place;
import org.california.model.entity.ShopList;
import org.california.repository.AbstractRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ShopListRepositoryImpl extends AbstractRepositoryImpl<ShopList> implements ShopListRepository {

    public ShopListRepositoryImpl() {
        setClazz(ShopList.class);
    }

    @Override
    public Collection<ShopList> get(Collection<Place> places, boolean status) {

        final String HQL = "SELECT SL FROM ShopList SL WHERE SL.place IN (:places) AND status = :status";

        Query<ShopList> query = getSession().createQuery(HQL);
        query.setParameterList("places", places);
        query.setParameter("status", status);

        return query.getResultList();
    }


}
