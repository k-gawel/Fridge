package org.california.model.transfer.request.queries;

import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.service.serialization.annotations.ById;
import org.california.service.serialization.annotations.ByIds;

import java.util.Collection;

public class ItemGetQuery extends GetQuery {

    @ByIds(entity = Item.class)
    public final Collection<Item> items;

    @ByIds(entity = Place.class)
    public final Collection<Place> places;

    @ById
    public final Category category;

    public final String name;
    public final Long barcode;


    public ItemGetQuery(Collection<Item> items, Collection<Place> places, Category category, String name, Long barcode) {
        this.items = items;
        this.places = places;
        this.category = category;
        this.name = name;
        this.barcode = barcode;
    }

}
