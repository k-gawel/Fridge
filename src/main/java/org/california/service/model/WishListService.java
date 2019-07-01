package org.california.service.model;

import org.california.model.entity.WishList;
import org.california.model.transfer.request.WishListForm;
import org.california.repository.wishlist.WishListRepository;
import org.california.service.getter.GetterService;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    private WishListRepository wishListRepository;
    private GetterService getterService;

    @Autowired
    public WishListService(WishListRepository wishListRepository, GetterService getterService) {
        this.wishListRepository = wishListRepository;
        this.getterService = getterService;
    }


    public WishList create(WishListForm form) {
        WishList wishList = fromForm(form);

        return wishListRepository.save(wishList);
    }


    public boolean archive(WishList wishList) {
        if(!wishList.isStatus())
            return false;

        wishList.setStatus(false);

        return save(wishList);
    }


    public boolean save(WishList wishList) {
        return wishListRepository.save(wishList) != null;
    }


    private WishList fromForm(WishListForm form) {
        WishList wishList = new WishList();
        wishList.setDescription(form.description);
        wishList.setName(form.name);
        wishList.setPlace(getterService.places.getByKey(form.place));
        return wishList;
    }

}
