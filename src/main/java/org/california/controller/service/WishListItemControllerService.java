package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.WishListItem;
import org.california.model.transfer.request.forms.WishListItemForm;
import org.california.model.transfer.response.place.WishListItemDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.WishListItemService;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListItemControllerService extends BaseControllerService<WishListItem> {

    private WishListItemService wishListItemService;


    @Autowired
    public WishListItemControllerService(WishListItemService wishListItemService, GetterService getterService, AccountPermissionsService accountPermissionsService, EntityToDtoMapper mapper) {
        super(getterService, mapper, accountPermissionsService);
        this.wishListItemService = wishListItemService;
    }


    public WishListItemDto newWishListItem(String token, WishListItemForm form) {
        Account account = getter.accounts.getByToken(token);

        if (!permissions.hasAccess(account, form.wishList))
            throw new UnauthorizedException();

        WishListItem resultItem = wishListItemService.newWishListItem(form);

        return mapper.toDto(resultItem);
    }


    public boolean addInstanceToWishListItem(String token, Long wishListItemId, Number instanceId) {
        var account = getter.accounts.getByToken(token);
        var wishListItem = getter.wishListItems.getByKeyOrThrow(wishListItemId);
        var instance = getter.itemInstances.getByKeyOrThrow(instanceId);

        if(!permissions.hasAccess(account, wishListItem)
                || !permissions.hasAccess(account, instance))
            throw new UnauthorizedException();

        return this.wishListItemService.addInstance(account, wishListItem, instance);
    }


}
