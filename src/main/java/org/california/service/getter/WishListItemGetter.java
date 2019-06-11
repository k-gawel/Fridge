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

    private WishListItemRepository repository;

    @Autowired
    public WishListItemGetter(WishListItemRepository repository) {
        this.repository = repository;
    }


    public WishListItem getByKey(Serializable key) {
        return repository.getByKey(key);
    }


    public Collection<WishListItem> getByIds(Collection<Long> ids) {
        if(CollectionUtils.isEmpty(ids))
            return Collections.emptySet();

        return repository.getByIds(ids);
    }

}
