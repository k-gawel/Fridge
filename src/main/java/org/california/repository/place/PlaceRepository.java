package org.california.repository.place;

import org.california.model.entity.Place;
import org.california.repository.AbstractRepository;

import java.util.Collection;

public interface PlaceRepository extends AbstractRepository<Place> {

    Collection<Place> getByIds(Collection<Long> placeIds);
}
