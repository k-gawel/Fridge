package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Category;
import org.california.model.entity.Item;
import org.california.model.entity.Place;
import org.california.repository.item.ItemRepository;
import org.california.repository.item.ItemSearchRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Service
public class ItemGetter {

    private ItemRepository itemRepository;
    private ItemSearchRepository itemSearchRepository;
    private CategoryGetter categoryGetter;

    @Autowired
    public ItemGetter(ItemRepository itemRepository, ItemSearchRepository itemSearchRepository, CategoryGetter categoryGetter) {
        this.itemRepository = itemRepository;
        this.itemSearchRepository = itemSearchRepository;
        this.categoryGetter = categoryGetter;
    }


    public Item getByKey(Serializable itemId) {
        return itemRepository.getByKey(itemId);
    }


    public Collection<Item> getByIds(Collection<Long> ids) {
        if(CollectionUtils.isEmpty(ids))
            return Collections.emptySet();

        return itemSearchRepository.getByIds(ids);
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
        if(CollectionUtils.isEmpty(places))
            return itemSearchRepository.searchByBarcode(barcode);
        else
            return itemSearchRepository.searchByBarcodeAndPlaces(barcode, places);

    }

}
