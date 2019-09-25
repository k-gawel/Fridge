package org.california.controller.service;

import org.california.controller.service.utils.Utils;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.item.Item;
import org.california.model.transfer.request.forms.ItemInstanceForm;
import org.california.model.transfer.response.iteminstance.ItemInstanceDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.AccountService;
import org.california.service.model.ItemInstanceService;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


@Service
public class ItemInstanceControllerService {


    private ItemInstanceService itemInstanceService;
    private AccountPermissionsService accountPermissionsService;
    private GetterService getterService;
    private AccountService accountService;
    private EntityToDtoMapper mapper;

    @Autowired
    public ItemInstanceControllerService(ItemInstanceService itemInstanceService, AccountPermissionsService accountPermissionsService, GetterService getterService, AccountService accountService, EntityToDtoMapper mapper) {
        this.itemInstanceService = itemInstanceService;
        this.accountPermissionsService = accountPermissionsService;
        this.getterService = getterService;
        this.accountService = accountService;
        this.mapper = mapper;
    }

    public ItemInstanceDto addItemInstance(String token, @Valid ItemInstanceForm form) {
        Account account = getterService.accounts.getByToken(token);

        if (!accountPermissionsService.hasAccessToContainer(account, form.container)
                || !accountPermissionsService.hasAccessToItem(account, form.item))
            throw new UnauthorizedException("item.accessdenied|container.accessdenied");

        return mapper.toDto(itemInstanceService.create(account, form));
    }


    public Collection<ItemInstanceDto> get(String token, String idsString, String placeIdsString,
                                           String containerIdsString, String itemIdsString, String ownerIdsString,
                                           String deletedString, String openString, String frozenString, int limit, int offset) {
        Account account = getterService.accounts.getByToken(token);

        Collection<Number> ids = Utils.collectionOf(idsString);
        Collection<Number> placeIds = Utils.collectionOf(placeIdsString);
        Collection<Number> containerIds = Utils.collectionOf(containerIdsString);
        Collection<Number> itemIds = Utils.collectionOf(itemIdsString);
        Collection<Number> ownerIds = Utils.collectionOf(ownerIdsString);

        Boolean deleted = Utils.stringToBoolean(deletedString);
        Boolean open = Utils.stringToBoolean(openString);
        Boolean frozen = Utils.stringToBoolean(frozenString);


        Collection<ItemInstance> instances;

        if (!ids.isEmpty())
            instances = getterService.itemInstances.getByKeys(ids);
        else {
            Collection<Container> containers = getContainers(containerIds, placeIds, account);
            Collection<Item> items = getterService.items.getByKeys(itemIds);
            Collection<Account> owners = getterService.accounts.getByKeys(ownerIds);
            instances = getterService.itemInstances.get(items, containers, owners, deleted, open, frozen, limit, offset);
        }

        System.out.println("Instances before delete " + instances.size());

        return filterInstancesThenMapToDto(instances, account);
    }

    private Collection<ItemInstanceDto> filterInstancesThenMapToDto(Collection<ItemInstance> instances, Account account) {
        return instances.stream()
                .filter(ii -> accountPermissionsService.hasAccessToItemInstance(account, ii))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    private Collection<Container> getContainers(Collection<Number> containerIds, Collection<Number> placeIds, Account account) {
        if(!containerIds.isEmpty())
            return getterService.containers.getByKeys(containerIds);
        else if(!placeIds.isEmpty())
            return getterService.containers.getByPlaces(getterService.places.getByKeys(placeIds));
        else
            return getterService.containers.getByAccounts(Collections.singleton(account));
    }


    public Boolean update(String token, Number instanceId, boolean frozeOrUnfroze, boolean open, boolean delete) {

        Account account = getterService.accounts.getByToken(token);
        ItemInstance itemInstance = getterService.itemInstances.getByKey(instanceId).get();

        if(!accountPermissionsService.hasAccessToItemInstance(account, itemInstance))
            return false;
        else if(frozeOrUnfroze)
            return itemInstanceService.frozeOrUnfroze(account, itemInstance);
        else if(delete)
            return itemInstanceService.delete(account, itemInstance);
        else if(open)
            return itemInstanceService.open(account, itemInstance);
        else
            return false;
    }

}
