package org.california.repository.iteminstance;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.item.Item;
import org.california.repository.AbstractRepository;
import org.california.repository.utils.LimitAndOffset;

import java.util.Collection;

public interface ItemInstanceRepository extends AbstractRepository<ItemInstance> {

    Collection<ItemInstance> getByIds(Collection<Long> ids);

    Collection<ItemInstance> getByContainers(Collection<Container> containers, Parameters parameters, LimitAndOffset lo);

    Collection<ItemInstance> getByOwners(Collection<Account> owners, Parameters parameters, LimitAndOffset lo);

    Collection<ItemInstance> getByContainersAndOwners(Collection<Container> containers, Collection<Account> owners, Parameters parameters, LimitAndOffset lo);

    Collection<ItemInstance> getByItemsAndOwners(Collection<Item> items, Collection<Account> owners, Parameters parameters, LimitAndOffset lo);

    Collection<ItemInstance> getByItemsAndContainers(Collection<Item> items, Collection<Container> containers, Parameters parameters, LimitAndOffset lo);

    Collection<ItemInstance> getByItemsAndContainersAndOwners(Collection<Item> items, Collection<Container> containers, Collection<Account> owners, Parameters parameters, LimitAndOffset lo);
}