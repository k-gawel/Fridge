package org.california.model.transfer.request.queries;

import lombok.ToString;
import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.service.serialization.annotations.ById;
import org.california.service.serialization.annotations.ByIds;
import org.california.util.enums.RelatedItemsType;

import java.util.Collection;

@ToString
public class ItemGetQuery extends GetQuery {

    @ByIds(entity = Item.class)
    public final Collection<Item> items;

    @ByIds(entity = Place.class)
    public final Collection<Place> places;

    @ById
    public final Category category;

    public final String name;

    public final Long barcode;

    public final RelatedItemsType params;

    public ItemGetQuery(Collection<Item> items, Collection<Place> places, Category category, String name, Long barcode, RelatedItemsType params) {
        this.items = items;
        this.places = places;
        this.category = category;
        this.name = name;
        this.barcode = barcode;
        this.params = params;
    }

}
