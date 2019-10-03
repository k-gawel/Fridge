package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.item.Item;
import org.california.model.transfer.request.forms.ItemForm;
import org.california.model.transfer.request.queries.ItemGetQuery;
import org.california.model.transfer.response.item.ItemDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.ItemService;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemControllerService extends BaseControllerService {

    private final ItemService itemService;


    @Autowired
    public ItemControllerService(GetterService getterService, AccountPermissionsService accountPermissionsService, ItemService itemService, EntityToDtoMapper mapper) {
        super(getterService, mapper, accountPermissionsService);
        this.itemService = itemService;
    }


    @SuppressWarnings("unchecked")
    public Collection<ItemDto> searchItem(Account account, ItemGetQuery q) {
        Collection<Item> items;

        if(q.items != null)
            items = q.items;
        else
            items = q.name == null  ? getter.items.searchByBarcode(q.places, q.barcode)
                                    : getter.items.searchByName(q.places, q.name, q.category);

        return filerAndMap(items, account);
    }


    public ItemDto addItem(Account account, ItemForm itemForm) {
        if (!permissions.hasAccess(account, itemForm.place))
            throw new UnauthorizedException(account, itemForm.place);

        return mapper.toDto(itemService.create(itemForm));
    }

}
