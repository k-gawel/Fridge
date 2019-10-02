package org.california.model.transfer.request.queries;

import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.entity.WishList;
import org.california.repository.utils.OffsetLimit;
import org.california.service.serialization.ById;
import org.california.service.serialization.ByIds;

import java.io.Serializable;
import java.util.Collection;

public class WishListGetQuery implements Serializable {

    @ById
    public final Collection<Account> users;

    @ByIds(entity = Place.class)
    public final Collection<Place> places;

    @ByIds(entity = WishList.class)
    public final Collection<WishList> wishLists;

    public final Boolean active;

    public final OffsetLimit offsetAndLimit;


    public WishListGetQuery(Collection<Account> users, Collection<Place> places, Collection<WishList> wishLists, Boolean active, OffsetLimit offsetAndLimit) {
        this.users = users;
        this.places = places;
        this.wishLists = wishLists;
        this.active = active;
        this.offsetAndLimit = offsetAndLimit;
    }


}
