package org.california.repository.wishlist;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Place;
import org.california.model.entity.WishList;
import org.california.repository.AbstractRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;

@Repository
public class WishListRepositoryImpl extends AbstractRepositoryImpl<WishList> implements WishListRepository {

    public WishListRepositoryImpl() {
        setClazz(WishList.class);
    }


    @Override
    public boolean changeStatus(WishList wishList) {
        return false;
    }


    @Override
    public Collection<WishList> getByIds(Collection<Long> ids, boolean active) {
        if(CollectionUtils.isEmpty(ids))
            return Collections.emptySet();

        final String HQL = "SELECT WL FROM WishList WL WHERE WL.id IN (:ids) AND WL.status = :status";

        Query<WishList> query = getSession().createQuery(HQL);
        query.setParameterList("ids", ids);
        query.setParameter("status", active);

        return query.getResultList();
    }


    @Override
    public Collection<WishList> getByPlaces(Collection<Place> places, boolean active) {
        if(CollectionUtils.isEmpty(places))
            return Collections.emptySet();

        final String HQL = "SELECT WL FROM WishList WL WHERE WL.place IN (:places) AND WL.status = :status";

        Query<WishList> query = getSession().createQuery(HQL);
        query.setParameterList("places", places);
        query.setParameter("status", active);

        return query.getResultList();
    }

}
