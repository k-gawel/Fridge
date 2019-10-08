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
import org.california.service.model.iteminstance.ItemInstanceCreator;
import org.california.service.model.iteminstance.ItemInstanceModifer;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;


@Service
public class ItemInstanceControllerService extends BaseControllerService {


    private final ItemInstanceCreator creator;
    private final ItemInstanceModifer modifer;

    @Autowired
    public ItemInstanceControllerService(ItemInstanceCreator creator, AccountPermissionsService permissions, GetterService getter, EntityToDtoMapper mapper, ItemInstanceModifer modifer) {
        super(getter, mapper, permissions);
        this.creator = creator;
        this.modifer = modifer;
    }


    public ItemInstanceDto addItemInstance(Account account, @Valid ItemInstanceForm form) {
        if (!permissions.hasAccess(account, form.container)
                || !permissions.hasAccess(account, form.item))
            throw new UnauthorizedException("item.accessdenied|container.accessdenied");

        return mapper.toDto(creator.create(account, form));
    }


    @SuppressWarnings("unchecked")
    public Collection<ItemInstanceDto> get(Account account, ItemInstanceGetQuery q) {
        Collection<ItemInstance> instances;

        if (q.itemInstances != null)
            instances = q.itemInstances;
        else
            instances = getter.itemInstances.get(q.items, q.containers, q.owners, q.params, q.offsetLimit);

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


    public Boolean update(Account account, Number instanceId, boolean frozeOrUnfroze, boolean open, boolean delete) {
        ItemInstance itemInstance = getter.itemInstances.getByKey(instanceId).get();

        if(!permissions.hasAccess(account, itemInstance))
            return false;
        else if(frozeOrUnfroze)
            return modifer.frozeOrUnfroze(account, itemInstance);
        else if(delete)
            return modifer.delete(account, itemInstance, creator);
        else if(open)
            return modifer.open(account, itemInstance);
        else
            return false;
    }


}
