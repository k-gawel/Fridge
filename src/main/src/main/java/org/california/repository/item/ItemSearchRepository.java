package org.california.repository.item;

import org.california.model.entity.Category;
import org.california.model.entity.Item;
import org.california.model.entity.Place;

import java.util.Collection;

public interface ItemSearchRepository {

    Collection<Item> getByIds(Collection<Long> ids);

    Collection<Item> searchByName(String name);

    Collection<Item> searchByNameAndPlaces(String name, Collection<Place> places);

    Collection<Item> searchByNameAndCategories(String name, Collection<Category> categories);

    Collection<Item> searchByNameAndPlacesAndCategories(String name, Collection<Place> places, Collection<Category> categories);

    Collection<Item> searchByBarcode(long barcode);

    Collection<Item> searchByBarcodeAndPlaces(long barcode, Collection<Place> places);
}
