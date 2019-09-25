package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.WishList;
import org.california.model.entity.WishListItem;
import org.california.model.entity.utils.AccountDate;
import org.california.model.transfer.request.forms.WishListItemForm;
import org.california.repository.wishlist.WishListItemRepository;
import org.california.util.exceptions.NotValidException;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        return wishListItemRepository.save(wishListItem) != null;
    }


    public boolean deleteFromList(Account account, WishListItem wishListItem) {

        if(!accountPermissionsService.hasAccessToWishListItem(account, wishListItem))
            throw new UnauthorizedException();


        WishList wishList = wishListItem.getWishList();

        wishList.getItems().remove(wishListItem);

        return wishListService.save(wishList);
    }


    public WishListItem newWishListItem(WishListItemForm wishListItemForm) {

        WishListItem result = fromForm(wishListItemForm);
        wishListItemRepository.save(result);
        return result;

    }


    // Creates object from DTO for a new created WishList
    public WishListItem fromForm(Account author, WishList currentWishList, WishListItemForm form) {
        if(author == null || currentWishList == null) {
            throw new NotValidException("Wyjebalem sie w krewoaniu z dto");
        }

        WishListItem result = fromForm(form);
        result.setAuthor(author);
        result.setWishList(currentWishList);

        wishListItemRepository.save(result);

        return result;
    }


    // Creates object from DTO with not null WishList field in
    public WishListItem fromForm(Account author, WishListItemForm form) {

        WishListItem result = fromForm(form);

        wishListItemRepository.save(result);

        return result;
    }


    // Creates from DTO object with author as null and nullable wishlist fields
    WishListItem fromForm(WishListItemForm form) {
        WishListItem result = new WishListItem();

        result.setCreatedOn(new Date());
        result.setComment(form.comment);
        result.setWishList(form.wishList);
        result.setCategory(form.category);
        result.setAuthor(form.author);

        return result;

    }


}
