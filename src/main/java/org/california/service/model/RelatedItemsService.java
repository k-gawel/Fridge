package org.california.service.model;

import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.model.entity.Place;
import org.california.repository.item.RelatedItemsRepository;
import org.california.service.getter.GetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RelatedItemsService {

    private RelatedItemsRepository relatedItemsRepository;
    private GetterService getterService;

    @Autowired
    public RelatedItemsService(RelatedItemsRepository relatedItemsRepository, GetterService getterService) {
        this.relatedItemsRepository = relatedItemsRepository;
        this.getterService = getterService;
    }


    public Collection<Item> getMostPopular(Category category, Collection<Place> places) {
        if(category == null)
            category = getterService.categories.getByKey(0L);

        Collection<Category> categories = getterService.categories.getFinalCategories(category);

        if(places == null || places.isEmpty())
            return relatedItemsRepository.getMostPopularOfCategories(categories);
        else
            return relatedItemsRepository.getMostPopularOfCategories(places, categories);

    }


}
