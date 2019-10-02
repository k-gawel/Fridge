package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.WishList;
import org.california.model.entity.WishListItem;
import org.california.model.entity.utils.AccountDate;
import org.california.model.transfer.request.forms.WishListItemForm;
import org.california.repository.wishlist.WishListItemRepository;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListItemService {


    private final WishListItemRepository wishListItemRepository;
    private final WishListService wishListService;
    private final AccountPermissionsService accountPermissionsService;

    @Autowired
    public WishListItemService(WishListItemRepository wishListItemRepository, WishListService wishListService, AccountPermissionsService accountPermissionsService) {
        this.wishListItemRepository = wishListItemRepository;
        this.wishListService = wishListService;
        this.accountPermissionsService = accountPermissionsService;
    }

    public boolean addInstance(Account account, WishListItem wishListItem, ItemInstance instance) {
        if(account == null || wishListItem == null || instance == null)
            throw new UnauthorizedException();

        wishListItem.setAddedInstance(instance);
        wishListItem.setAdded(new AccountDate(account));
        instance.setWishListItem(wishListItem);

        instance.setWishListItem(wishListItem);
        return wishListItemRepository.save(wishListItem) != null;
    }


    public boolean deleteFromList(Account account, WishListItem wishListItem) {

        if(!accountPermissionsService.hasAccess(account, wishListItem))
            throw new UnauthorizedException();


        WishList wishList = wishListItem.getWishList();

        wishList.getItems().remove(wishListItem);

        return wishListService.save(wishList);
    }


    public WishListItem newWishListItem(WishListItemForm form) {
        WishListItem result = fromForm(form);
        wishListItemRepository.save(result);
        return result;
    }


    // Creates from DTO object with author as null and nullable wishlist fields
    private WishListItem fromForm(WishListItemForm form) {
        WishListItem result = new WishListItem();

        result.setCreated(new AccountDate(form.author));
        result.setComment(form.comment);
        result.setWishList(form.wishList);
        result.setCategory(form.category);

        return result;

    }


}
