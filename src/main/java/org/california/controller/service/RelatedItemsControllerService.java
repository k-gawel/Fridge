package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.Place;
import org.california.model.entity.item.Category;
import org.california.model.entity.item.Item;
import org.california.model.transfer.response.item.ItemDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.RelatedItemsService;
import org.california.util.enums.RelatedItemsType;
import org.california.util.exceptions.NotValidException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class RelatedItemsControllerService extends BaseControllerService {

    private final RelatedItemsService relatedItemsService;

    public RelatedItemsControllerService(GetterService getter, RelatedItemsService relatedItemsService, AccountPermissionsService permissions, EntityToDtoMapper mapper) {
        super(getter, mapper, permissions);
        this.relatedItemsService = relatedItemsService;
    }

    @SuppressWarnings("unchecked")
    public Collection<ItemDto> get(Account account, Collection<Place> places, Category category, String param) {
        RelatedItemsType type = RelatedItemsType.of(param);

        Collection<Item> result;

        switch (type) {
            case MOST_POPULAR:
                result = relatedItemsService.getMostPopular(category, places);
                break;
            case ALL:
                result = getter.items.searchByPlaceAndCategories(places, Collections.singleton(category));
                break;
            default:
                throw new NotValidException("params.notValid");
        }

        return filerAndMap(result, account);
    }

}
