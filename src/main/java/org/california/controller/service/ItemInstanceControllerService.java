package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ItemInstance;
import org.california.model.transfer.request.forms.ItemInstanceForm;
import org.california.model.transfer.request.queries.ItemInstanceGetQuery;
import org.california.model.transfer.response.iteminstance.ItemInstanceDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.ItemInstanceService;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;


@Service
public class ItemInstanceControllerService extends BaseControllerService {


    private final ItemInstanceService itemInstanceService;

    @Autowired
    public ItemInstanceControllerService(ItemInstanceService itemInstanceService, AccountPermissionsService permissions, GetterService getter, EntityToDtoMapper mapper) {
        super(getter, mapper, permissions);
        this.itemInstanceService = itemInstanceService;
    }


    public ItemInstanceDto addItemInstance(String token, @Valid ItemInstanceForm form) {
        Account account = getter.accounts.getByToken(token);

        if (!permissions.hasAccess(account, form.container)
                || !permissions.hasAccess(account, form.item))
            throw new UnauthorizedException("item.accessdenied|container.accessdenied");

        return mapper.toDto(itemInstanceService.create(account, form));
    }


    @SuppressWarnings("unchecked")
    public Collection<ItemInstanceDto> get(String token, ItemInstanceGetQuery q) {
        Account account = getter.accounts.getByToken(token);

        Collection<ItemInstance> instances;

        if (q.itemInstances != null)
            instances = q.itemInstances;
        else
            instances = getter.itemInstances.get(q.items, q.containers, q.owners, q.params, q.offsetLimit);

        System.out.println("Instances before delete " + instances.size());

        return filerAndMap(instances, account);
    }


    private Collection<Container> getContainers(Collection<Number> containerIds, Collection<Number> placeIds, Account account) {
        if(!containerIds.isEmpty())
            return getter.containers.getByKeys(containerIds);
        else if(!placeIds.isEmpty())
            return getter.containers.getByPlaces(getter.places.getByKeys(placeIds));
        else
            return getter.containers.getByAccounts(Collections.singleton(account));
    }


    public Boolean update(String token, Number instanceId, boolean frozeOrUnfroze, boolean open, boolean delete) {

        Account account = getter.accounts.getByToken(token);
        ItemInstance itemInstance = getter.itemInstances.getByKey(instanceId).get();

        if(!permissions.hasAccess(account, itemInstance))
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
