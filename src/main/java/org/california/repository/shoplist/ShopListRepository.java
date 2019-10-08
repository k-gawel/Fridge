package org.california.repository.shoplist;

import org.california.model.entity.Place;
import org.california.model.entity.ShopList;
import org.california.repository.AbstractRepository;
import org.california.repository.utils.OffsetLimit;

import java.util.Collection;

public interface ShopListRepository extends AbstractRepository<ShopList> {

    Collection<ShopList> get(Collection<Place> places, boolean status, OffsetLimit offsetLimit);

}
