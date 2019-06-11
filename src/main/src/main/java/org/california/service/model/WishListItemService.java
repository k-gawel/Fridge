package org.california.service.model;

import org.california.model.entity.*;
import org.california.model.transfer.request.ItemInstanceForm;
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

    public WishListItem addInstance(Account account, WishListItem wishListItem, ItemInstanceForm itemInstanceForm) {

        if(account == null || wishListItem == null || itemInstanceForm == null)
            throw new UnauthorizedException();

        ItemInstance instance = itemInstanceService.create(account, itemInstanceForm);

        wishListItem.setAddedOn(new Date());
        wishListItem.setAddedInstance(instance);
        wishListItem.setAddedBy(account);

        wishListItemRepository.save(wishListItem);

        return wishListItem;
    }


    public boolean deleteFromList(Account account, WishListItem wishListItem) {

        if(!accountPermissionsService.hasAccessToWishListItem(account, wishListItem))
            throw new UnauthorizedException();


        WishList wishList = wishListItem.getWishList();

        wishList.getItems().remove(wishListItem);

        return wishListService.save(wishList);
    }


    public WishListItem newWishListItem(WishListItemForm wishListItemForm) {

        WishListItem result = fromDTO(wishListItemForm);
        wishListItemRepository.save(result);
        return result;

    }


    // Creates object from DTO for a new created WishList
    public WishListItem fromDTO(Account author, WishList currentWishList, WishListItemForm form) {
        if(author == null || currentWishList == null) {
            throw new NotValidException("Wyjebalem sie w krewoaniu z dto");
        }

        WishListItem result = fromDTO(form);
        result.setAuthor(author);
        result.setWishList(currentWishList);

        wishListItemRepository.save(result);

        return result;
    }


    // Creates object from DTO with not null WishList field in
    public WishListItem fromDTO(Account author, WishListItemForm form) {

        WishListItem result = fromDTO(form);

        wishListItemRepository.save(result);

        return result;
    }


    // Creates from DTO object with author as null and nullable wishlist fields
    WishListItem fromDTO(WishListItemForm form) {
        WishListItem result = new WishListItem();

        result.setCreatedOn(new Date());
        result.setComment(form.getComment());

        WishList list = getterService.wishLists.getByKey(form.getWishListId());
        result.setWishList(list);

        Category category = getterService.categories.getByKey(form.getCategoryId());
        result.setCategory(category);

        Item item = getterService.items.getByKey(form.getItemId());
        result.setItem(item);

        Account author = getterService.accounts.getByKey(form.getAuthorId());
        result.setAuthor(author);


        return result;

    }


}
