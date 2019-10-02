package org.california.model.transfer.request.queries;

import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.service.serialization.ById;
import org.california.service.serialization.ByIds;

import java.util.Collection;

public class ItemGetQuery {

    @ByIds(entity = Item.class)
    public final Collection<Item> items;

    @ByIds(entity = Place.class)
    public final Collection<Place> places;

    @ById
    public final Category category;

    public final String name;
    public final long barcode;


    public ItemGetQuery(Collection<Item> items, Collection<Place> places, Category category, String name, long barcode) {
        this.items = items;
        this.places = places;
        this.category = category;
        this.name = name;
        this.barcode = barcode;
    }

}
