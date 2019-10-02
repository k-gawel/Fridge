package org.california.service.model;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.BaseEntity;
import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.repository.item.RelatedItemsRepository;
import org.california.service.getter.GetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RelatedItemsService {

    private final RelatedItemsRepository relatedItemsRepository;
    private final GetterService getterService;

    @Autowired
    public RelatedItemsService(RelatedItemsRepository relatedItemsRepository, GetterService getterService) {
        this.relatedItemsRepository = relatedItemsRepository;
        this.getterService = getterService;
    }


    public Collection<Item> getMostPopular(Category category, Collection<Place> places) {
        if(category == null)
            category = getterService.categories.getRootCategory();

        Collection<Category> categories = getterService.categories.getFinalCategories(category);

        Collection<Item> result;

        if(CollectionUtils.isEmpty(places))
            result = relatedItemsRepository.getMostPopularOfCategories(categories);
        else
            result = relatedItemsRepository.getMostPopularOfCategories(places, categories);
        System.out.println("Gettin most popular items by " + category + " and " + places + ". Result: " + result.stream().map(BaseEntity::getId).map(Objects::toString).collect(Collectors.joining()));
        return result;
    }


}
