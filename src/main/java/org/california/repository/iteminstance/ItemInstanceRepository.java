package org.california.repository.iteminstance;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.item.Item;
import org.california.model.transfer.request.queries.ItemInstanceParams;
import org.california.repository.AbstractRepository;
import org.california.repository.utils.OffsetLimit;

import java.util.Collection;

public interface ItemInstanceRepository extends AbstractRepository<ItemInstance> {

    Collection<ItemInstance> getByIds(Collection<Long> ids);

    Collection<ItemInstance> getByContainers(Collection<Container> containers, ItemInstanceParams parameters, OffsetLimit lo);

    Collection<ItemInstance> getByOwners(Collection<Account> owners, ItemInstanceParams ItemInstanceParams, OffsetLimit lo);

    Collection<ItemInstance> getByContainersAndOwners(Collection<Container> containers, Collection<Account> owners, ItemInstanceParams parameters, OffsetLimit lo);

    Collection<ItemInstance> getByItemsAndOwners(Collection<Item> items, Collection<Account> owners, ItemInstanceParams parameters, OffsetLimit lo);

    Collection<ItemInstance> getByItemsAndContainers(Collection<Item> items, Collection<Container> containers, ItemInstanceParams parameters, OffsetLimit lo);

    Collection<ItemInstance> getByItemsAndContainersAndOwners(Collection<Item> items, Collection<Container> containers, Collection<Account> owners, ItemInstanceParams parameters, OffsetLimit lo);
}