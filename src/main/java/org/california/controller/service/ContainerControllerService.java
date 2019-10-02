package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.transfer.request.forms.ContainerForm;
import org.california.model.transfer.response.place.ContainerDto;
import org.california.model.transfer.response.place.PlaceUserStats;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.ContainerService;
import org.california.util.StringUtils;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class
ContainerControllerService {

    private EntityToDtoMapper mapper;
    private GetterService getterService;
    private AccountPermissionsService accountPermissionsService;
    private ContainerService containerService;

    @Autowired
    public ContainerControllerService(EntityToDtoMapper mapper, GetterService getterService, AccountPermissionsService accountPermissionsService, ContainerService containerService) {
        this.mapper = mapper;
        this.getterService = getterService;
        this.accountPermissionsService = accountPermissionsService;
        this.containerService = containerService;
    }

    public ContainerDto newContainer(String token, ContainerForm form) {
        Account account = getterService.accounts.getByToken(token);

        if (!accountPermissionsService.hasAccess(account, form.place))
            throw new UnauthorizedException("containeraccess.denied");

        Container container = containerService.createNewContainer(account, form);

        return mapper.toDto(container);
    }


    public Collection<ContainerDto> get(String token, String containerIdsString, String placeIdsString) {

        Account account = getterService.accounts.getByToken(token);
        Collection<Number> containerIds = StringUtils.collectionOf(containerIdsString);
        Collection<Number> placeIds = StringUtils.collectionOf(placeIdsString);

        Collection<Container> result = get(account, containerIds, placeIds);

        return result.stream()
                .filter(c -> accountPermissionsService.hasAccess(account, c))
                .map(mapper::toDto)
                .collect(Collectors.toList());

    }


    private Collection<Container> get(Account account, Collection<Number> containerIds, Collection<Number> placeIds) {

        if(!containerIds.isEmpty())
            return getterService.containers.getByKeys(containerIds);

        else if(!placeIds.isEmpty())
            return getterService.containers.getByPlaces(getterService.places.getByKeys(placeIds));

        else if(account != null)
            return getterService.containers.getByPlaces(account.getPlaces());

        else
            return Collections.emptySet();
    }


    public Collection<PlaceUserStats> getUserStats(String token, String userIdsString, String placeIdsString, String containerIdsString) {
        Account account = getterService.accounts.getByToken(token);

        if(account == null)
            throw new UnauthorizedException();

        Collection<Number> userIds = StringUtils.collectionOf(userIdsString);
        Collection<Number> placeIds = StringUtils.collectionOf(placeIdsString);
        Collection<Number> containerIds = StringUtils.collectionOf(containerIdsString);


        Collection<Container> containers = containerIds.isEmpty() ?
                getterService.containers.getByPlaces(getterService.places.getByKeys(placeIds))
                : getterService.containers.getByKeys(containerIds);

        containers.removeIf(c -> !accountPermissionsService.hasAccess(account, c));

        Collection<Account> accounts = userIds.isEmpty() ?
                getterService.accounts.getAllByContainers(containers)
                : getterService.accounts.getByKeys(containerIds);

        return containerService.getUsersStats(accounts, containers);
    }


}
