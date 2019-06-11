package org.california.repository.wishlist;

import org.california.model.entity.WishListItem;
import org.california.repository.AbstractRepository;

import java.util.Collection;

public interface WishListItemRepository extends AbstractRepository<WishListItem> {

    Collection<WishListItem> getByIds(Collection<Long> ids);
}
