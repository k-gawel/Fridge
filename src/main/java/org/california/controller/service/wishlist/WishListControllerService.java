package org.california.controller.service.wishlist;

import org.california.controller.service.BaseControllerService;
import org.california.model.entity.Account;
import org.california.model.entity.WishList;
import org.california.model.transfer.request.forms.WishListForm;
import org.california.model.transfer.request.queries.WishListGetQuery;
import org.california.model.transfer.response.place.WishListDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.WishListService;
import org.california.util.exceptions.NoContentException;
import org.california.util.exceptions.NotValidException;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class WishListControllerService extends BaseControllerService {

    private final WishListService wishListService;
    private final WishListGetterControllerService getterControllerService;

    @Autowired
    public WishListControllerService(GetterService getterService, AccountPermissionsService accountPermissionsService, WishListService wishListService, EntityToDtoMapper mapper, WishListGetterControllerService getterControllerService) {
        super(getterService, mapper, accountPermissionsService);
        this.wishListService = wishListService;
        this.getterControllerService = getterControllerService;
    }


    public WishListDto newWishList(Account account, WishListForm form) {
        if (!permissions.hasAccess(account, form.place))
            throw new UnauthorizedException();

        WishList result = wishListService.create(form);
        return mapper.toDto(result);
    }


    @SuppressWarnings("unchecked")
    public Serializable get(Account account, WishListGetQuery query) {
        return getterControllerService.get(account, query);
    }


    public boolean archive(Account account, Long wishListId) {
        var wishList = getter.wishLists.getByKey(wishListId)
                                       .orElseThrow(() -> new NoContentException(WishList.class, wishListId));

        if (!permissions.hasAccess(account, wishList))
            throw new UnauthorizedException(account, wishList);

        if (!wishList.isStatus())
            throw new NotValidException("wishList.alreadyArchived");

        return wishListService.archive(account, wishList);
    }


}
