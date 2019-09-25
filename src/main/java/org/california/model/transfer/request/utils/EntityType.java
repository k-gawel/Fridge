package org.california.model.transfer.request.utils;

import org.california.model.entity.*;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;

public enum EntityType {

    ACCOUNT("account", Account.class),
    PLACE("place", Place.class), CONTAINER("container", Container.class),
    SHOPLIST("shopList", ShopList.class), WISHLIST("wishlist", WishList.class),
    WISHLISTITEM("wishlistitem", WishListItem.class), ITEMINSTANCE("itemInstance", ItemInstance.class),
    CATEGORY("category", Category.class), ITEM("item", Item.class);

    private final String stringType;
    private final Class entityClass;

    EntityType(String type, Class entityClass) {
        this.stringType = type;
        this.entityClass = entityClass;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    @Override
    public String toString() {
        return this.stringType;
    }

}
