package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.WishList;
import org.california.model.transfer.request.forms.WishListForm;
import org.california.model.transfer.request.queries.WishListGetQuery;
import org.california.model.transfer.response.place.WishListDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.WishListService;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class WishListControllerService extends BaseControllerService {

    private final WishListService wishListService;

    @Autowired
    public WishListControllerService(GetterService getterService, AccountPermissionsService accountPermissionsService, WishListService wishListService, EntityToDtoMapper mapper) {
        super(getterService, mapper, accountPermissionsService);
        this.wishListService = wishListService;
    }


    public WishListDto newWishList(Account account, WishListForm form) {
        if (!permissions.hasAccess(account, form.place))
            throw new UnauthorizedException();

        WishList result = wishListService.create(form);
        return mapper.toDto(result);
    }


    @SuppressWarnings("unchecked")
    public Collection<WishListDto> get(Account account, WishListGetQuery query) {
        Collection<WishList> result;

        if(query.wishLists != null)
            result = query.wishLists;
        else if (query.places != null)
            result = getter.wishLists.get(query.places, query.active);
        else if(account != null)
            result = getter.wishLists.get(query.users, query.active);
        else
            return Collections.emptySet();


        return filerAndMap(result, account);
    }


    public boolean archive(Account account, Long wishListId) {
        var wishList = getter.wishLists.getByKey(wishListId).get();


        if (!permissions.hasAccess(account, wishList))
            throw new UnauthorizedException("ACCOUNT has no acces to wishlist");

        if (!wishList.isStatus())
            throw new IllegalStateException("WishList is already archived");

        return wishListService.archive(account, wishList);
    }


}
