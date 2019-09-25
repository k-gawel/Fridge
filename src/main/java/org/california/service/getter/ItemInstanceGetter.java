package org.california.service.getter;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.item.Item;
import org.california.repository.iteminstance.ItemInstanceRepository;
import org.california.repository.iteminstance.Parameters;
import org.california.repository.utils.LimitAndOffset;
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
    public Collection<ItemInstance> get(Collection<Item> items, Collection<Container> containers, Collection<Account> owners,
                                        Boolean deleted, Boolean open, Boolean frozen, int limit, int start) {

        LimitAndOffset limitAndOffset = new LimitAndOffset(limit, start);
        Parameters parameters = new Parameters(deleted, open, frozen);
        
        if(items.isEmpty() && containers.isEmpty() && owners.isEmpty())
            return Collections.emptySet();

        // items ; containers : OWNERS
        if(items.isEmpty() && containers.isEmpty() && !owners.isEmpty())
            return itemInstanceRepository.getByOwners(owners, parameters, limitAndOffset);

        //  items ; CONTAINERS ; OWNERS
        if(items.isEmpty() && !containers.isEmpty() && !owners.isEmpty())
            return itemInstanceRepository.getByContainersAndOwners(containers, owners, parameters, limitAndOffset);

        //  items ; CONTAINERS ; owners
        if(items.isEmpty() && !containers.isEmpty() && owners.isEmpty())
            return itemInstanceRepository.getByContainers(containers, parameters, limitAndOffset);

        //  ITEMS ; containers ; OWNERS
        if(!items.isEmpty() && containers.isEmpty() && !owners.isEmpty())
            return itemInstanceRepository.getByItemsAndOwners(items, owners, parameters, limitAndOffset);

        // ITEMS ; CONTAINERS ; owners
        if(!items.isEmpty() && !containers.isEmpty() && owners.isEmpty())
            return itemInstanceRepository.getByItemsAndContainers(items, containers, parameters, limitAndOffset);

        // ITEMS ; CONTAINERS ; OWNERS
        if(!items.isEmpty() && !containers.isEmpty() && !owners.isEmpty())
            return itemInstanceRepository.getByItemsAndContainersAndOwners(items, containers, owners, parameters, limitAndOffset);

        return Collections.emptySet();
    }

}
