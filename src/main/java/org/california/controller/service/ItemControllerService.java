package org.california.controller.service;

import org.california.controller.service.utils.Utils;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.model.transfer.request.forms.ItemForm;
import org.california.model.transfer.response.item.ItemDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.ItemService;
import org.california.util.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ItemControllerService {

    private GetterService getterService;
    private AccountPermissionsService accountPermissionsService;
    private ItemService itemService;
    private EntityToDtoMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public ItemControllerService(GetterService getterService, AccountPermissionsService accountPermissionsService, ItemService itemService, EntityToDtoMapper mapper) {
        this.getterService = getterService;
        this.accountPermissionsService = accountPermissionsService;
        this.itemService = itemService;
        this.mapper = mapper;
    }


    public Collection<ItemDto> searchItem(String token, String itemIdsString, String placeIdsString, String name, long barcode, Long categoryId) {
        Account account = getterService.accounts.getByToken(token);
        Category category = getterService.categories.getByKeyOrThrow(categoryId);

        logger.info("Search for items account: {}, category: {}", account, category);

        Collection<Number> placeIds = Utils.collectionOf(placeIdsString);
        Collection<Number> itemIds = Utils.collectionOf(itemIdsString);

        Collection<Place> places = getterService.places.getByKeys(placeIds);
        Collection<Item> items;

        if(!itemIds.isEmpty())
            items = getterService.items.getByKeys(itemIds);
        else
            items = name.equals("") ? getterService.items.searchByBarcode(places, barcode)
                                    : getterService.items.searchByName(places, name, category);

        logger.info("ITEMS.SIZE {}", items.size());

        return items.stream()
                .filter(i -> {
                    var res = accountPermissionsService.hasAccessToItem(account, i);
                    logger.info("{} has? {} access to {}", account, res, i);
                    return res;
                })
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    public ItemDto addItem(String token, ItemForm itemForm) {
        Account account = getterService.accounts.getByToken(token);

        if (!accountPermissionsService.hasAccessToPlace(account, itemForm.place))
            throw new UnauthorizedException(account, itemForm.place);

        return mapper.toDto(itemService.create(itemForm));
    }

}
