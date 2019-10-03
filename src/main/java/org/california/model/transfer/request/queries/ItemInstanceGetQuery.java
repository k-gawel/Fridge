package org.california.model.transfer.request.queries;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.Place;
import org.california.model.entity.item.Item;
import org.california.repository.utils.OffsetLimit;
import org.california.service.serialization.annotations.ByIds;

import java.util.Collection;

public class ItemInstanceGetQuery extends GetQuery {

    @ByIds(entity = ItemInstance.class)
    public final Collection<ItemInstance> itemInstances;

    @ByIds(entity = Item.class)
    public final Collection<Item> items;

    @ByIds(entity = Place.class)
    public final Collection<Place> places;

    @ByIds(entity = Container.class)
    public final Collection<Container> containers;

    @ByIds(entity = Account.class)
    public final Collection<Account> owners;

    public final ItemInstanceParams params;

    public final OffsetLimit offsetLimit;

    public ItemInstanceGetQuery(Collection<ItemInstance> itemInstances, Collection<Item> items, Collection<Place> places, Collection<Container> containers, Collection<Account> owners, ItemInstanceParams params, OffsetLimit offsetLimit) {
        this.itemInstances = itemInstances;
        this.items = items;
        this.places = places;
        this.containers = containers;
        this.owners = owners;
        this.params = params;
        this.offsetLimit = offsetLimit;
    }

}
