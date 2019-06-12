package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.WishListItem;
import org.california.repository.wishlist.WishListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Service
public class WishListItemGetter {

    private final WishListItemRepository repository;

    @Autowired
    WishListItemGetter(WishListItemRepository repository) {
        this.repository = repository;
    }


    public WishListItem getByKey(Serializable key) {
        return repository.getByKey(key);
    }


    public Collection<WishListItem> getByIds(Collection<Long> ids) {
        return CollectionUtils.isEmpty(ids) ?
            Collections.emptySet() : repository.getByIds(ids);
    }

}
