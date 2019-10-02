package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.entity.WishList;
import org.california.model.entity.utils.AccountDate;
import org.california.model.transfer.request.forms.WishListForm;
import org.california.repository.wishlist.WishListRepository;
import org.california.service.getter.GetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final GetterService getterService;

    @Autowired
    public WishListService(WishListRepository wishListRepository, GetterService getterService) {
        this.wishListRepository = wishListRepository;
        this.getterService = getterService;
    }


    public WishList create(WishListForm form) {
        WishList wishList = fromForm(form);
        return wishListRepository.save(wishList);
    }


    public boolean archive(Account account, WishList wishList) {
        if (!wishList.isStatus()) return false;

        wishList.setStatus(false);
        wishList.setArchived(new AccountDate(account));

        return save(wishList);
    }


    public boolean save(WishList wishList) {
        return wishListRepository.save(wishList) != null;
    }


    private WishList fromForm(WishListForm form) {
        WishList wishList = new WishList();
        wishList.setCreated(new AccountDate(form.author));
        System.out.println("Created WishList " + wishList.getCreated().toString());
        wishList.setDescription(form.description);
        wishList.setName(form.name);
        wishList.setPlace(form.place);
        return wishList;
    }

}
