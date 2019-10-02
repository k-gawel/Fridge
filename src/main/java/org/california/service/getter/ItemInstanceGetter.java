package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.item.Item;
import org.california.model.transfer.request.queries.ItemInstanceParams;
import org.california.repository.iteminstance.ItemInstanceRepository;
import org.california.repository.utils.OffsetLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;


@Service
public class ItemInstanceGetter extends BaseGetter<ItemInstance> {

    private final ItemInstanceRepository itemInstanceRepository;

    @Autowired
    ItemInstanceGetter(ItemInstanceRepository itemInstanceRepository) {
        super(itemInstanceRepository, ItemInstance.class);
        this.itemInstanceRepository = itemInstanceRepository;
    }


    @SuppressWarnings("ConstantConditions")
    public Collection<ItemInstance> get(Collection<Item> items, Collection<Container> containers, Collection<Account> owners, ItemInstanceParams params, OffsetLimit offsetLimit) {

        boolean i = CollectionUtils.isNotEmpty(items);
        boolean c = CollectionUtils.isNotEmpty(containers);
        boolean o = CollectionUtils.isNotEmpty(owners);

        // items ; containers ; owners
        if(!i && !c && !o)
            return Collections.emptySet();

        // items ; containers : OWNERS
        if(!i && !c &&  o)
            return itemInstanceRepository.getByOwners(owners, params, offsetLimit);

        //  items ; CONTAINERS ; OWNERS
        if(!i &&  c &&  o)
            return itemInstanceRepository.getByContainersAndOwners(containers, owners, params, offsetLimit);

        //  items ; CONTAINERS ; owners
        if(!i &&  c && !o)
            return itemInstanceRepository.getByContainers(containers, params, offsetLimit);

        //  ITEMS ; containers ; OWNERS
        if(i  && !c &&  o)
            return itemInstanceRepository.getByItemsAndOwners(items, owners, params, offsetLimit);

        // ITEMS ; CONTAINERS ; owners
        if(i  &&  c &&  !o)
            return itemInstanceRepository.getByItemsAndContainers(items, containers, params, offsetLimit);

        // ITEMS ; CONTAINERS ; OWNERS
        if(i  &&  c &&  o)
            return itemInstanceRepository.getByItemsAndContainersAndOwners(items, containers, owners, params, offsetLimit);

        return Collections.emptySet();
    }


}
