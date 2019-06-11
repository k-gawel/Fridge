package org.california.controller.service;

import org.california.controller.service.utils.Utils;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Item;
import org.california.model.entity.ItemInstance;
import org.california.model.transfer.request.ItemInstanceForm;
import org.california.model.transfer.response.EntityToDtoMapper;
import org.california.model.transfer.response.ItemInstanceDto;
import org.california.model.validator.ItemInstanceFormValidator;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.AccountService;
import org.california.service.model.ItemInstanceService;
import org.california.util.exceptions.NotValidException;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ItemInstanceDto addItemInstance(String token, ItemInstanceForm form) {

        ItemInstanceFormValidator validator = new ItemInstanceFormValidator();
        if(!validator.validate(form)) {
            throw new NotValidException(validator.getMessagesAsString());
        }

        Account account = getterService.accounts.getByToken(token);

        Container container = getterService.containers.getById(form.getContainerId());
        Item item = getterService.items.getByKey(form.getItemId());

        if(!accountPermissionsService.hasAccessToContainer(account, container)
            || !accountPermissionsService.hasAccessToItem(account, item))
            throw new UnauthorizedException("item.accessdenied|container.accessdenied");

        return mapper.itemInstanceToDto(itemInstanceService.create(account, form));
    }


    public Collection<ItemInstanceDto> get(String token, String idsString, String placeIdsString,
                                           String containerIdsString, String itemIdsString, String ownerIdsString,
                                           String deletedString, String openString, String frozenString, int limit) {
        Account account = getterService.accounts.getByToken(token);

        Collection<Long> ids = Utils.collectionOf(idsString);
        Collection<Long> placeIds = Utils.collectionOf(placeIdsString);
        Collection<Long> containerIds = Utils.collectionOf(containerIdsString);
        Collection<Long> itemIds = Utils.collectionOf(itemIdsString);
        Collection<Long> ownerIds = Utils.collectionOf(ownerIdsString);

        Boolean deleted = Utils.stringToBoolean(deletedString);
        Boolean open = Utils.stringToBoolean(openString);
        Boolean frozen = Utils.stringToBoolean(frozenString);

        Collection<ItemInstance> instances;

        if (!ids.isEmpty())
            instances = getterService.itemInstances.getByIds(ids);
        else {
            Collection<Container> containers = getContainers(containerIds, placeIds, account);
            Collection<Item> items = getterService.items.getByIds(itemIds);
            Collection<Account> owners = getterService.accounts.getByIds(ownerIds);
            instances = getterService.itemInstances.get(items, containers, owners, deleted, open, frozen, limit);
        }

        return filterInstancesThenMapToDto(instances, account);
    }

    private Collection<ItemInstanceDto> filterInstancesThenMapToDto(Collection<ItemInstance> instances, Account account) {
        return instances.stream()
                .filter(ii -> accountPermissionsService.hasAccessToItemInstance(account, ii))
                .map(mapper::itemInstanceToDto)
                .collect(Collectors.toList());
    }


    private Collection<Container> getContainers(Collection<Long> containerIds, Collection<Long> placeIds, Account account) {
        if(!containerIds.isEmpty())
            return getterService.containers.getByIds(containerIds);
        else if(!placeIds.isEmpty())
            return getterService.containers.getByPlaces(getterService.places.getByIds(placeIds));
        else
            return getterService.containers.getByAccounts(Collections.singleton(account));
    }


    public Boolean update(String token, Long instanceId, boolean frozeOrUnfroze, boolean open, boolean delete) {

        Account account = getterService.accounts.getByToken(token);
        ItemInstance itemInstance = getterService.itemInstances.getByKey(instanceId);

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
