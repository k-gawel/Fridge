package org.california.service.getter;

import org.california.model.entity.WishListItem;
import org.california.repository.wishlist.WishListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListItemGetter extends BaseGetter<WishListItem> {

    private final WishListItemRepository repository;

    @Autowired
    WishListItemGetter(WishListItemRepository repository) {
        super(repository, WishListItem.class);
        this.repository = repository;
    }

}
