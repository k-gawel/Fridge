package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.model.entity.Place;
import org.california.repository.item.ItemRepository;
import org.california.repository.item.ItemSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Service
public class ItemGetter {

    private final ItemRepository itemRepository;
    private final ItemSearchRepository itemSearchRepository;
    private final CategoryGetter categoryGetter;

    @Autowired
    ItemGetter(ItemRepository itemRepository, ItemSearchRepository itemSearchRepository, CategoryGetter categoryGetter) {
        this.itemRepository = itemRepository;
        this.itemSearchRepository = itemSearchRepository;
        this.categoryGetter = categoryGetter;
    }


    public Item getByKey(Serializable itemId) {
        return itemRepository.getByKey(itemId);
    }


    public Collection<Item> getByIds(Collection<Long> ids) {
        return CollectionUtils.isEmpty(ids) ?
            Collections.emptySet() : itemSearchRepository.getByIds(ids);
    }


    public Collection<Item> searchByName(Collection<Place> places, String name, Category category) {
        if(places == null || name == null || category == null)
            return Collections.emptySet();

        Collection<Category> categories = categoryGetter.getFinalCategories(category);

        if(places.isEmpty() && categories.isEmpty())
            return itemSearchRepository.searchByName(name);
        if(places.isEmpty())
            return itemSearchRepository.searchByNameAndCategories(name, categories);
        if(categories.isEmpty())
            return itemSearchRepository.searchByNameAndPlaces(name, places);


        return itemSearchRepository.searchByNameAndPlacesAndCategories(name, places, categories);
    }


    public Collection<Item> searchByBarcode(Collection<Place> places, long barcode) {
        return CollectionUtils.isEmpty(places) ?
                itemSearchRepository.searchByBarcode(barcode) :
                itemSearchRepository.searchByBarcodeAndPlaces(barcode, places);

    }

}
