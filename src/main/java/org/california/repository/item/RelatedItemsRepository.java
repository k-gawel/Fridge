package org.california.repository.item;

import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.model.entity.Place;
import org.california.model.util.KeyValue;

import java.util.Collection;
import java.util.List;

public interface RelatedItemsRepository {

    Collection<Item> getMostPopularOfCategories(Collection<Category> categories);

    Collection<Item> getMostPopularOfCategories(Collection<Place> places, Collection<Category> category);

    Collection<KeyValue<Item, Long>> getItemInstanceCount(List<Item> items);

    Collection<Item> getMostPopularItems();

}
