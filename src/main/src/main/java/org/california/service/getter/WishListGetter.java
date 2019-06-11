package org.california.service.getter;

import org.california.model.entity.Place;
import org.california.model.entity.WishList;
import org.california.repository.wishlist.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Service
public class WishListGetter {

    private WishListRepository wishListRepository;

    @Autowired
    public WishListGetter(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }


    public WishList getByKey(Serializable key) {
        return wishListRepository.getByKey(key);
    }


    public <T> Collection<WishList> get(Collection<T> collection, boolean active) {
        if(collection == null || collection.isEmpty())
            return Collections.emptySet();

        if(collection.stream().allMatch(Long.class::isInstance)) {
            return wishListRepository.getByIds((Collection<Long>) collection, active);
        }
        if(collection.stream().allMatch(Place.class::isInstance)) {
            return wishListRepository.getByPlaces((Collection<Place>) collection, active);
        }

        return Collections.emptySet();
    }

}
