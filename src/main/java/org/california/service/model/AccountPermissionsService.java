package org.california.service.model;

import org.california.model.entity.*;
import org.springframework.stereotype.Service;

@Service
public class AccountPermissionsService {


    public boolean hasAccessToAccount(Account account, Account accessingAccount) {
        return account.equals(accessingAccount);
    }


    public boolean isAdminOfPlace(Account account, Place place) {
        if(account == null || place == null)
            return false;

        return place.getAdmin().equals(account);
    }


    public boolean hasAccessToItemInstance(Account account, ItemInstance itemInstance) {
        if(account == null || itemInstance == null)
            return false;

        return hasAccessToContainer(account, itemInstance.getContainer());
    }


    public boolean isAdmin(Account account) {
        return false;
    }


    public boolean hasAccessToPlace(Account account, Place place) {
        if(account == null || place == null)
            return false;

        return place.getAccounts().contains(account);
    }


    public boolean hasAccessToItem(Account account, Item item) {
        if(item.getPlace() == null)
            return true;

        return hasAccessToPlace(account, item.getPlace());
    }


    public boolean hasAccessToContainer(Account account, Container container) {
        if(account == null || container == null)
            return false;

        return hasAccessToPlace(account, container.getPlace());
    }


    public boolean hasAccessToWishList(Account account, WishList wishList) {

        try {
            return hasAccessToPlace(account, wishList.getPlace());
        } catch (Exception e) {
            return false;
        }

    }


    public boolean hasAccessToWishListItem(Account account, WishListItem wishListItem) {

        try {
            return hasAccessToWishList(account, wishListItem.getWishList());
        } catch (Exception e) {
            return false;
        }

    }

}
