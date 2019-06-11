package org.california.repository.wishlist;

import org.california.model.entity.Place;
import org.california.model.entity.WishList;
import org.california.repository.AbstractRepository;

import java.util.Collection;

public interface WishListRepository extends AbstractRepository<WishList> {

    boolean changeStatus(WishList wishList);
    Collection<WishList> getByIds(Collection<Long> ids, boolean active);
    Collection<WishList> getByPlaces(Collection<Place> places, boolean active);
}
