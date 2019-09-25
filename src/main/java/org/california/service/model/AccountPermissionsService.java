package org.california.service.model;

import org.california.model.entity.*;
import org.california.model.entity.item.Item;
import org.springframework.stereotype.Service;

@Service
public class AccountPermissionsService {


    public boolean hasAccessToAccount(Account account, Account accessingAccount) {
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


    public boolean hasAccessToItemInstance(Account account, ItemInstance itemInstance) {
        try {
            return hasAccessToContainer(account, itemInstance.getContainer());
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean isAdmin(Account account) {
        return false;
    }


    public boolean hasAccessToPlace(Account account, Place place) {
        try {
            return place.getAccounts().contains(account);
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccessToItem(Account account, Item item) {
        try {
            return item.getPlace() == null || hasAccessToPlace(account, item.getPlace());
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccessToContainer(Account account, Container container) {
        try {
            return hasAccessToPlace(account, container.getPlace());
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccessToWishList(Account account, WishList wishList) {
        try {
            return hasAccessToPlace(account, wishList.getPlace());
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccessToWishListItem(Account account, WishListItem wishListItem) {
        try {
            return hasAccessToWishList(account, wishListItem.getWishList());
        } catch (NullPointerException e) {
            return false;
        }
    }


    public boolean hasAccessToShopList(Account account, ShopList shopList) {
        try {
            return hasAccessToPlace(account, shopList.getPlace());
        } catch (NullPointerException e) {
            return false;
        }
    }



}
