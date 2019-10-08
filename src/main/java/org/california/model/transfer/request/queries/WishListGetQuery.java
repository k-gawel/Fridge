package org.california.model.transfer.request.queries;

import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.entity.WishList;
import org.california.repository.utils.OffsetLimit;
import org.california.service.serialization.annotations.ByIds;

import java.util.Collection;

public class WishListGetQuery extends GetQuery {

    @ByIds(entity = Account.class)
    public final Collection<Account> users;

    @ByIds(entity = Place.class)
    public final Collection<Place> places;

    @ByIds(entity = WishList.class)
    public final Collection<WishList> wishLists;

    public final Boolean active;

    public final OffsetLimit offsetLimit;


    public WishListGetQuery(Collection<Account> users, Collection<Place> places, Collection<WishList> wishLists, Boolean active, OffsetLimit offsetLimit) {
        this.users = users;
        this.places = places;
        this.wishLists = wishLists;
        this.active = active;
        this.offsetLimit = offsetLimit;
    }


}
