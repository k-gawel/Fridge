package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.WishList;
import org.california.model.entity.WishListItem;
import org.california.model.transfer.request.WishListItemForm;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.WishListItemService;
import org.california.util.exceptions.NotValidException;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListItemControllerService {

    private WishListItemService wishListItemService;
    private GetterService getterService;
    private AccountPermissionsService accountPermissionsService;


    @Autowired
    public WishListItemControllerService(WishListItemService wishListItemService, GetterService getterService, AccountPermissionsService accountPermissionsService) {
        this.wishListItemService = wishListItemService;
        this.getterService = getterService;
        this.accountPermissionsService = accountPermissionsService;
    }


    public WishListItem newWishListItem(String token, WishListItemForm wishListItemForm) {
        Account account = getterService.accounts.getByToken(token);
        WishList wishList = getterService.wishLists.getByKey(wishListItemForm.wish_list_id);

        if(!accountPermissionsService.hasAccessToWishList(account, wishList))
            throw new UnauthorizedException();

        return this.wishListItemService.newWishListItem(wishListItemForm);
    }


    public boolean addInstanceToWishListItem(String token, Long wishListItemId, Long instanceId) {
        Account account = getterService.accounts.getByToken(token);
        WishListItem wishListItem = getterService.wishListItems.getByKey(wishListItemId);
        ItemInstance instance = getterService.itemInstances.getByKey(instanceId);

        if(!accountPermissionsService.hasAccessToWishListItem(account, wishListItem)
            || !accountPermissionsService.hasAccessToItemInstance(account, instance))
            throw new UnauthorizedException();

        return this.wishListItemService.addInstance(account, wishListItem, instance);
    }


}
