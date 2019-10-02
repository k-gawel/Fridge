package org.california.service.model;

import org.california.model.entity.*;
import org.california.model.entity.item.Item;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Service
public class AccountPermissionsService {


    public boolean hasAccess(Account account, Account accessingAccount) {
        try {
            return account.equals(accessingAccount);
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean isAdminOfPlace(Account account, Place place) {
        try {
            return place.getAdmin().equals(account);
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccess(Account account, ItemInstance itemInstance) {
        try {
            return hasAccess(account, itemInstance.getContainer());
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean isAdmin(Account account) {
        return false;
    }


    public boolean hasAccess(Account account, Place place) {
        try {
            return place.getAccounts().contains(account);
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccess(Account account, Item item) {
        try {
            return item.getPlace() == null || hasAccess(account, item.getPlace());
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccess(Account account, Container container) {
        try {
            return hasAccess(account, container.getPlace());
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccess(Account account, WishList wishList) {
        try {
            return hasAccess(account, wishList.getPlace());
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccess(Account account, WishListItem wishListItem) {
        try {
            return hasAccess(account, wishListItem.getWishList());
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccess(Account account, ShopList shopList) {
        try {
            return hasAccess(account, shopList.getPlace());
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccess(Account account, BaseEntity entity) {
        if(entity == null) return false;

        Method mapperMethod = Arrays.stream(getClass().getDeclaredMethods())
                .filter(m -> m.getName().equals("hasAccess"))
                .filter(m -> m.getParameterCount() == 2)
                .filter(m -> m.getParameterTypes()[0].equals(Account.class))
                .filter(m -> m.getParameterTypes()[1].equals(entity.getClass()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Cannot find access method for " + entity.getClass()));

        try {
            return (boolean) mapperMethod.invoke(this, account, entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot invoke  access method for " + entity, e);
        }

    }


}
