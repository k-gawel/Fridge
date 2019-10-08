package org.california.model.transfer.request.queries;

import org.california.model.entity.Place;
import org.california.model.entity.ShopList;
import org.california.repository.utils.OffsetLimit;
import org.california.service.serialization.annotations.ByIds;

import java.util.Collection;

public class ShopListGetQuery extends GetQuery {

    @ByIds(entity = ShopList.class)
    public final Collection<ShopList> shopLists;

    @ByIds(entity = Place.class)
    public final Collection<Place> places;

    public final Boolean status;

    public final OffsetLimit offsetLimit;


    public ShopListGetQuery(Collection<ShopList> shopLists, Collection<Place> places, Boolean status, OffsetLimit offsetLimit) {
        this.shopLists = shopLists;
        this.places = places;
        this.status = status;
        this.offsetLimit = offsetLimit;
    }


}
