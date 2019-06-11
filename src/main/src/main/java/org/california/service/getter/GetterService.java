package org.california.service.getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetterService {

    public AccountGetter accounts;
    public PlaceGetter places;
    public ContainerGetter containers;
    public ItemGetter items;
    public ItemInstanceGetter itemInstances;
    public CategoryGetter categories;
    public WishListGetter wishLists;
    public WishListItemGetter wishListItems;
    public AllergenGetter allergens;
    public IngredientGetter ingredients;
    public ProducerGetter producers;

    @Autowired

    public GetterService(AccountGetter accounts, PlaceGetter places, ContainerGetter containers, ItemGetter items, ItemInstanceGetter itemInstances, CategoryGetter categories, WishListGetter wishLists,
                         WishListItemGetter wishListItems, AllergenGetter allergens, IngredientGetter ingredients, ProducerGetter producers) {
        this.accounts = accounts;
        this.places = places;
        this.containers = containers;
        this.items = items;
        this.itemInstances = itemInstances;
        this.categories = categories;
        this.wishLists = wishLists;
        this.wishListItems = wishListItems;
        this.allergens = allergens;
        this.ingredients = ingredients;
        this.producers = producers;
    }
}
