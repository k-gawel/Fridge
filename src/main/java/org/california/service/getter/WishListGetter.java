package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Place;
import org.california.model.entity.WishList;
import org.california.repository.wishlist.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class WishListGetter extends BaseGetter<WishList> {

    private final WishListRepository wishListRepository;

    @Autowired
    WishListGetter(WishListRepository wishListRepository) {
        super(wishListRepository, WishList.class);
        this.wishListRepository = wishListRepository;
    }


    public <T> Collection<WishList> get(Collection<T> collection, boolean active) {
        if(CollectionUtils.isEmpty(collection))
            return Collections.emptySet();

        if(collection.stream().allMatch(Long.class::isInstance))
            return wishListRepository.getByIds((Collection<Long>) collection, active);
        if(collection.stream().allMatch(Place.class::isInstance))
            return wishListRepository.getByPlaces((Collection<Place>) collection, active);

        return Collections.emptySet();
    }

}
