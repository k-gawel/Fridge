package org.california.repository.iteminstance;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.item.Item;
import org.california.model.entity.ItemInstance;
import org.california.repository.AbstractRepository;

import java.util.Collection;

public interface ItemInstanceRepository extends AbstractRepository<ItemInstance> {

    Collection<ItemInstance> getByIds(Collection<Long> ids);

    Collection<ItemInstance> getByContainers(Collection<Container> containers, Boolean deleted, Boolean open, Boolean frozen, int limit);
    Collection<ItemInstance> getByOwners(Collection<Account> owners, Boolean deleted, Boolean open, Boolean frozen, int limit);
    Collection<ItemInstance> getByContainersAndOwners(Collection<Container> containers, Collection<Account> owners, Boolean deleted, Boolean open, Boolean frozen, int limit);
    Collection<ItemInstance> getByItemsAndOwners(Collection<Item> items, Collection<Account> owners, Boolean deleted, Boolean open, Boolean frozen, int limit);
    Collection<ItemInstance> getByItemsAndContainers(Collection<Item> items, Collection<Container> containers, Boolean deleted, Boolean open, Boolean frozen, int limit);
    Collection<ItemInstance> getByItemsAndContainersAndOwners(Collection<Item> items, Collection<Container> containers, Collection<Account> owners, Boolean deleted, Boolean open, Boolean frozen, int limit);
}