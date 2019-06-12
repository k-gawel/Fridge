package org.california.repository.place;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Place;
import org.california.repository.AbstractRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;

@Repository
public class PlaceRopositoryImpl extends AbstractRepositoryImpl<Place> implements PlaceRepository {

    public PlaceRopositoryImpl() {
        setClazz(Place.class);
    }

    @Override
    public Collection<Place> getByIds(Collection<Long> placeIds) {
        if(CollectionUtils.isEmpty(placeIds))
            return Collections.emptySet();

        final String HQL = "SELECT P FROM Place P WHERE P.id IN (:ids)";

        Query<Place> query = getSession().createQuery(HQL);
        query.setParameterList("ids", placeIds);

        return query.getResultList();
    }


}
