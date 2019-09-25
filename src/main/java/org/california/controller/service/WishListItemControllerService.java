package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.ItemInstance;
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
public class WishListItemControllerService {

    private WishListItemService wishListItemService;
    private GetterService getterService;
    private AccountPermissionsService accountPermissionsService;
    private final EntityToDtoMapper mapper;


    @Autowired
    public WishListItemControllerService(WishListItemService wishListItemService, GetterService getterService, AccountPermissionsService accountPermissionsService, EntityToDtoMapper mapper) {
        this.wishListItemService = wishListItemService;
        this.getterService = getterService;
        this.accountPermissionsService = accountPermissionsService;
        this.mapper = mapper;
    }


    public WishListItemDto newWishListItem(String token, WishListItemForm form) {
        Account account = getterService.accounts.getByToken(token);

        if (!accountPermissionsService.hasAccessToWishList(account, form.wishList))
            throw new UnauthorizedException();

        WishListItem resultItem = wishListItemService.newWishListItem(form);

        return mapper.toDto(resultItem);
    }


    public boolean addInstanceToWishListItem(String token, Long wishListItemId, Number instanceId) {
        Account account = getterService.accounts.getByToken(token);
        WishListItem wishListItem = getterService.wishListItems.getByKeyOrThrow(wishListItemId);
        ItemInstance instance = getterService.itemInstances.getByKeyOrThrow(instanceId);

        if(!accountPermissionsService.hasAccessToWishListItem(account, wishListItem)
                || !accountPermissionsService.hasAccessToItemInstance(account, instance))
            throw new UnauthorizedException();

        return this.wishListItemService.addInstance(account, wishListItem, instance);
    }


}
