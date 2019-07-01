package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.WishList;
import org.california.model.entity.WishListItem;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.model.transfer.request.WishListItemForm;
import org.california.repository.wishlist.WishListItemRepository;
import org.california.service.getter.GetterService;
import org.california.util.exceptions.NotValidException;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WishListItemService {


    private WishListItemRepository wishListItemRepository;
    private WishListService wishListService;
    private GetterService getterService;
    private ItemInstanceService itemInstanceService;
    private AccountPermissionsService accountPermissionsService;

    @Autowired
    public WishListItemService(WishListItemRepository wishListItemRepository, WishListService wishListService, GetterService getterService, ItemInstanceService itemInstanceService, AccountPermissionsService accountPermissionsService) {
        this.wishListItemRepository = wishListItemRepository;
        this.wishListService = wishListService;
        this.getterService = getterService;
        this.itemInstanceService = itemInstanceService;
        this.accountPermissionsService = accountPermissionsService;
    }

    public boolean addInstance(Account account, WishListItem wishListItem, ItemInstance instance) {
        if(account == null || wishListItem == null || instance == null)
            throw new UnauthorizedException();

        wishListItem.setAddedInstance(instance);
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

        WishList list = getterService.wishLists.getByKey(form.wish_list_id);
        result.setWishList(list);

        Category category = getterService.categories.getByKey(form.category_id);
        result.setCategory(category);

        Account author = getterService.accounts.getByKey(form.author_id);
        result.setAuthor(author);

        return result;

    }


}
