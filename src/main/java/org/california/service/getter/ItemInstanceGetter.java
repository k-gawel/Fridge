package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Item;
import org.california.model.entity.ItemInstance;
import org.california.repository.iteminstance.ItemInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;


@Service
public class ItemInstanceGetter {

    private final ItemInstanceRepository itemInstanceRepository;

    @Autowired
    ItemInstanceGetter(ItemInstanceRepository itemInstanceRepository) {
        this.itemInstanceRepository = itemInstanceRepository;
    }


    public ItemInstance getByKey(Long instanceId) {
        return itemInstanceRepository.getByKey(instanceId);
    }


    public Collection<ItemInstance> getByIds(Collection<Long> ids) {
        return CollectionUtils.isEmpty(ids) ?
            Collections.emptySet() : itemInstanceRepository.getByIds(ids);
    }


    public Collection<ItemInstance> get(Collection<Item> items, Collection<Container> containers, Collection<Account> owners, Boolean deleted, Boolean open, Boolean frozen, int limit) {
        if(items.isEmpty() && containers.isEmpty() && owners.isEmpty())
            return Collections.emptySet();

        // items ; containers : OWNERS
        if(items.isEmpty() && containers.isEmpty() && !owners.isEmpty())
            return itemInstanceRepository.getByOwners(owners, deleted, open, frozen, limit);

        //  items ; CONTAINERS ; OWNERS
        if(items.isEmpty() && !containers.isEmpty() && !owners.isEmpty())
            return itemInstanceRepository.getByContainersAndOwners(containers, owners, deleted, open, frozen, limit);

        //  items ; CONTAINERS ; owners
        if(items.isEmpty() && !containers.isEmpty() && owners.isEmpty())
            return itemInstanceRepository.getByContainers(containers, deleted, open, frozen, limit);

        //  ITEMS ; containers ; OWNERS
        if(!items.isEmpty() && containers.isEmpty() && !owners.isEmpty())
            return itemInstanceRepository.getByItemsAndOwners(items, owners, deleted, open, frozen, limit);

        // ITEMS ; CONTAINERS ; owners
        if(!items.isEmpty() && !containers.isEmpty() && owners.isEmpty())
            return itemInstanceRepository.getByItemsAndContainers(items, containers, deleted, open, frozen, limit);

        // ITEMS ; containers ; OWNERS
        if(!items.isEmpty() && containers.isEmpty() && !owners.isEmpty())
            return itemInstanceRepository.getByItemsAndContainers(items, containers, deleted, open, frozen, limit);

        // ITEMS ; CONTAINERS ; OWNERS
        if(!items.isEmpty() && !containers.isEmpty() && !owners.isEmpty())
            return itemInstanceRepository.getByItemsAndContainersAndOwners(items, containers, owners, deleted, open, frozen, limit);

        return Collections.emptySet();
    }

}
