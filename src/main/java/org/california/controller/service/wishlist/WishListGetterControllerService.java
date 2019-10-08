package org.california.controller.service.wishlist;

import org.california.model.entity.Account;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.WishList;
import org.california.model.entity.WishListItem;
import org.california.model.transfer.request.queries.WishListGetQuery;
import org.california.model.transfer.response.item.ItemDto;
import org.california.model.transfer.response.iteminstance.ItemInstanceDto;
import org.california.model.transfer.response.place.WishListDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class WishListGetterControllerService {

    private final GetterService getter;
    private final EntityToDtoMapper mapper;
    private final AccountPermissionsService permissions;

    @Autowired
    WishListGetterControllerService(GetterService getter, EntityToDtoMapper mapper, AccountPermissionsService permissions) {
        this.getter = getter;
        this.mapper = mapper;
        this.permissions = permissions;
    }

    Serializable get(Account account, WishListGetQuery query) {
        var lists = getFiltered(account, query);
        var itemDtos = getItemDtos(lists);
        var instanceDtos = getInstanceDtos(lists);
        var listDtos = listDtos(lists);

        return new Serializable() {
            public final Collection<ItemDto> items = itemDtos;
            public final Collection<ItemInstanceDto> instances = instanceDtos;
            public final Collection<WishListDto> wishLists = listDtos;
        };
    }




    private Collection<WishList> getFiltered(Account account, WishListGetQuery query) {
        return getByQuery(account, query).stream()
                                         .filter(w -> permissions.hasAccess(account, w))
                                         .collect(Collectors.toList());
    }


    private Collection<WishList> getByQuery(Account account, WishListGetQuery query) {
        if(query.wishLists != null)
            return query.wishLists;
        else if (query.places != null)
            return getter.wishLists.get(query.places, query.active);
        else if(account != null)
            return getter.wishLists.get(account.getPlaces(), query.active);
        else
            return Collections.emptySet();
    }

    private Collection<WishListDto> listDtos(Collection<WishList> lists) {
        return lists.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    private Collection<ItemInstanceDto> getInstanceDtos(Collection<WishList> lists) {
        return instances(lists).map(mapper::toDto).collect(Collectors.toList());
    }

    private Collection<ItemDto> getItemDtos(Collection<WishList> lists) {
        return instances(lists).map(ItemInstance::getItem).distinct().map(mapper::toDto).collect(Collectors.toList());
    }


    private Stream<ItemInstance> instances(Collection<WishList> lists) {
        return lists.stream().map(WishList::getItems)
                .flatMap(Collection::stream)
                .map(WishListItem::getAddedInstance)
                .filter(Objects::nonNull);
    }


}
