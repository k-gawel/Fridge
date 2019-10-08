package org.california.service.model.item;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.repository.item.ItemRepository;
import org.california.repository.item.ItemSearchRepository;
import org.california.service.getter.BaseGetter;
import org.california.service.getter.CategoryGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ItemGetter extends BaseGetter<Item> {

    private final ItemSearchRepository itemSearchRepository;
    private final CategoryGetter categoryGetter;

    @Autowired
    ItemGetter(ItemRepository itemRepository, ItemSearchRepository itemSearchRepository, CategoryGetter categoryGetter) {
        super(itemRepository, Item.class);
        this.itemSearchRepository = itemSearchRepository;
        this.categoryGetter = categoryGetter;
    }


    public Collection<Item> searchByName(Collection<Place> places, String name, Category category) {
        Collection<Category> categories = categoryGetter.getFinalCategories(category);

        if(places.isEmpty() && categories.isEmpty())
            return itemSearchRepository.searchByName(name);
        if(places.isEmpty())
            return itemSearchRepository.searchByNameAndCategories(name, categories);
        if(categories.isEmpty())
            return itemSearchRepository.searchByNameAndPlaces(name, places);

        return itemSearchRepository.searchByNameAndPlacesAndCategories(name, places, categories);
    }


    public Collection<Item> searchByPlaceAndCategories(Collection<Place> places, Collection<Category> categories) {
        Collection<Category> finalCategories = categories.stream()
                                                         .map(categoryGetter::getFinalCategories)
                                                         .flatMap(Collection::stream)
                                                         .collect(Collectors.toSet());

        return itemSearchRepository.searchByPlaceAndCategories(places, finalCategories);
    }


    public Collection<Item> searchByBarcode(Collection<Place> places, long barcode) {
        return CollectionUtils.isEmpty(places) ?
                itemSearchRepository.searchByBarcode(barcode) :
                itemSearchRepository.searchByBarcodeAndPlaces(barcode, places);

    }

}
