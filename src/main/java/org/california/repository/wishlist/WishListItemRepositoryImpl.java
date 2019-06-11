package org.california.repository.wishlist;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.WishListItem;
import org.california.repository.AbstractRepositoryImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Repository
@Transactional
public class WishListItemRepositoryImpl extends AbstractRepositoryImpl<WishListItem> implements WishListItemRepository {

    public WishListItemRepositoryImpl() {
        setClazz(WishListItem.class);
    }

    @Override
    public Collection<WishListItem> getByIds(Collection<Long> ids) {
        if(CollectionUtils.isEmpty(ids))
            return Collections.emptySet();

        final String HQL = "SELECT WLI FROM WishListItem WLI WHERE WLI.id IN (:ids)";

        Query<WishListItem> query = getSession().createQuery(HQL);
        query.setParameterList("ids", ids);

        return query.getResultList();
    }

}
