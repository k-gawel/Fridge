package org.california.repository.item;

import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;

import java.util.Collection;

public interface ItemSearchRepository {

    Collection<Item> getByIds(Collection<Long> ids);

    Collection<Item> searchByName(String name);

    Collection<Item> searchByNameAndPlaces(String name, Collection<Place> places);

    Collection<Item> searchByPlaceAndCategories(Collection<Place> places, Collection<Category> categories);

    Collection<Item> searchByNameAndCategories(String name, Collection<Category> categories);

    Collection<Item> searchByNameAndPlacesAndCategories(String name, Collection<Place> places, Collection<Category> categories);

    Collection<Item> searchByBarcode(long barcode);

    Collection<Item> searchByBarcodeAndPlaces(long barcode, Collection<Place> places);
}
