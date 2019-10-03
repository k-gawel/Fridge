package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.model.transfer.request.forms.ContainerForm;
import org.california.model.transfer.response.place.ContainerDto;
import org.california.model.transfer.response.place.PlaceUserStats;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.ContainerService;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class
ContainerControllerService extends BaseControllerService {

    private final ContainerService containerService;

    @Autowired
    public ContainerControllerService(EntityToDtoMapper mapper, GetterService getterservice, AccountPermissionsService permissions, ContainerService containerService) {
        super(getterservice, mapper, permissions);
        this.containerService = containerService;
    }

    public ContainerDto newContainer(Account account, ContainerForm form) {
        if (!permissions.hasAccess(account, form.place))
            throw new UnauthorizedException("containeraccess.denied");

        Container container = containerService.createNewContainer(account, form);

        return mapper.toDto(container);
    }


    public Collection<ContainerDto> get(Account account, Collection<Container> containers, Collection<Place> places, Collection<Account> users) {

        Collection<Container> result;
        
        if(containers != null)
            result = containers;
        else if(places != null)
            result = getter.containers.getByPlaces(places);
        else if(users != null)
            result = getter.containers.getByAccounts(users);
        else
            result = Collections.emptySet();
        
        return filerAndMap(result, account);
    }
    

    public Collection<PlaceUserStats> getUserStats(Account account, Collection<Account> users, Collection<Place> places, Collection<Container> containers) {
        if(account == null)
            throw new UnauthorizedException();

        if(containers == null)
            containers = places != null ? getter.containers.getByPlaces(places) : getter.containers.getByAccounts(users);

        containers.removeIf(c -> !permissions.hasAccess(account, c));
        var accounts = users == null ? getter.accounts.getAllByContainers(containers) : users;

        return containerService.getUsersStats(accounts, containers);
    }


}
