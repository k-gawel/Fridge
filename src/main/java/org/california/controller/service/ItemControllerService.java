package org.california.controller.service;

import org.california.controller.service.utils.Utils;
import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.model.transfer.request.ItemForm;
import org.california.service.builders.EntityToDtoMapper;
import org.california.model.transfer.response.ItemDto;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.ItemService;
import org.california.util.exceptions.NotValidException;
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
        Category category = getterService.categories.getByKey(categoryId);

        Collection<Long> placeIds = Utils.collectionOf(placeIdsString);
        Collection<Long> itemIds = Utils.collectionOf(itemIdsString);

        Collection<Place> places = getterService.places.getByIds(placeIds);
        Collection<Item> items;

        if(!itemIds.isEmpty())
            items = getterService.items.getByIds(itemIds);
        else
            items = name.equals("") ? getterService.items.searchByBarcode(places, barcode)
                                    : getterService.items.searchByName(places, name, category);

        logger.info("ITEMS.SIZE {}", items.size());

        return items.stream()
                .filter(i -> accountPermissionsService.hasAccessToItem(account, i))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    public ItemDto addItem(String token, ItemForm itemForm) {
        Account account = getterService.accounts.getByToken(token);
        Place place = getterService.places.getByKey(itemForm.placeId);

        if(!accountPermissionsService.hasAccessToPlace(account, place))
            throw new UnauthorizedException();

        return mapper.toDto(itemService.create(itemForm));
    }

}
