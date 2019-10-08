package org.california.service.getter;

import org.california.model.entity.BaseEntity;
import org.california.service.model.TokenService;
import org.california.service.model.item.ItemGetter;
import org.california.service.model.iteminstance.ItemInstanceGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetterService {

    public final static Map<Class<? extends BaseEntity>, BaseGetter<? extends BaseEntity>> GETTER = new HashMap<>();
    private static GetterService getter;

    public final AccountGetter accounts;
    public final PlaceGetter places;
    public final TokenService tokens;
    public final ContainerGetter containers;
    public final ItemGetter items;
    public final ItemInstanceGetter itemInstances;
    public final CategoryGetter categories;
    public final WishListGetter wishLists;
    public final WishListItemGetter wishListItems;
    public final AllergenGetter allergens;
    public final IngredientGetter ingredients;
    public final ProducerGetter producers;
    public final PlaceUserStatsGetter placeUserStats;
    public final ShopListGetter shopLists;
    public final InstanceChangeGetter instanceLogs;

    @Autowired
    public GetterService(AccountGetter accounts, PlaceGetter places, TokenService tokens, ContainerGetter containers, ItemGetter items, ItemInstanceGetter itemInstances, CategoryGetter categories, WishListGetter wishLists,
                         WishListItemGetter wishListItems, AllergenGetter allergens, IngredientGetter ingredients, ProducerGetter producers, PlaceUserStatsGetter placeUserStats, ShopListGetter shopLists, InstanceChangeGetter instanceLogs) {
        this.accounts = accounts;
        this.places = places;
        this.tokens = tokens;
        this.containers = containers;
        this.items = items;
        this.itemInstances = itemInstances;
        this.categories = categories;
        this.wishLists = wishLists;
        this.wishListItems = wishListItems;
        this.allergens = allergens;
        this.ingredients = ingredients;
        this.producers = producers;
        this.placeUserStats = placeUserStats;
        this.shopLists = shopLists;
        this.instanceLogs = instanceLogs;
        getter = this;
    }

    public static GetterService GETTER() {
        return getter;
    }
}
