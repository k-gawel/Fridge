package org.california.controller.service.shoplist;

import org.california.model.entity.Account;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.ShopList;
import org.california.model.transfer.request.queries.ShopListGetQuery;
import org.california.model.transfer.response.item.ItemDto;
import org.california.model.transfer.response.iteminstance.ItemInstanceDto;
import org.california.model.transfer.response.place.ShopListDto;
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
public class ShopListGetterControllerService {

    private final GetterService getter;
    private final AccountPermissionsService permissions;
    private final EntityToDtoMapper mapper;

    @Autowired
    public ShopListGetterControllerService(GetterService getter, AccountPermissionsService permissions, EntityToDtoMapper mapper) {
        this.getter = getter;
        this.permissions = permissions;
        this.mapper = mapper;
    }


    public Serializable get(Account account, ShopListGetQuery query) {
        var lists = getFiltered(account, query);
        var itemDtos = getItemDtos(lists);
        var instanceDtos = getInstanceDtos(lists);
        var listDtos = getShopListDtos(lists);


        return new Serializable() {
            public final Collection<ItemDto> items = itemDtos;
            public final Collection<ItemInstanceDto> instances = instanceDtos;
            public final Collection<ShopListDto> shopLists = listDtos;
        };
    }


    private Collection<ShopList> getFromQuery(Account account, ShopListGetQuery query) {
        if(query.shopLists != null)
            return query.shopLists;
        else if(query.places != null)
            return getter.shopLists.get(query.places, query.status, query.offsetLimit);
        else if(account != null)
            return getter.shopLists.get(account.getPlaces(), query.status, query.offsetLimit);
        else
            return Collections.emptyList();
    }


    private Collection<ShopList> getFiltered(Account account, ShopListGetQuery query) {
        return getFromQuery(account, query).stream()
                                           .filter(l -> permissions.hasAccess(account, l))
                                           .collect(Collectors.toList());
    }


    private Collection<ShopListDto> getShopListDtos(Collection<ShopList> lists) {
        return lists.stream().map(mapper::toDto)
                             .collect(Collectors.toList());
    }


    private Collection<ItemDto> getItemDtos(Collection<ShopList> lists) {
        return instances(lists)
                .map(ItemInstance::getItem)
                .distinct()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    private Collection<ItemInstanceDto> getInstanceDtos(Collection<ShopList> lists) {
        return instances(lists).distinct()
                               .map(mapper::toDto)
                               .collect(Collectors.toList());
    }


    private Stream<ItemInstance> instances(Collection<ShopList> lists) {
        return lists.stream().map(ShopList::getInstances)
                             .flatMap(Collection::stream)
                             .filter(Objects::nonNull);
    }


}
