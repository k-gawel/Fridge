package org.california.controller.service;

import org.california.controller.service.utils.Utils;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.Place;
import org.california.model.transfer.request.ContainerForm;
import org.california.model.transfer.response.ContainerDto;
import org.california.model.transfer.response.EntityToDtoMapper;
import org.california.model.transfer.response.PlaceUserStats;
import org.california.model.validator.ContainerFormValidator;
import org.california.model.validator.Validator;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.ContainerService;
import org.california.util.exceptions.NotValidException;
import org.california.util.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ContainerControllerService {

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

    public ContainerDto newContainer(String token, ContainerForm containerForm) {

        Validator validator = new ContainerFormValidator();
        if(!validator.validate(containerForm))
            throw new NotValidException(validator.getMessagesAsString());

        Account account = getterService.accounts.getByToken(token);
        Place place = getterService.places.getByKey(containerForm.getPlaceId());

        if(!accountPermissionsService.hasAccessToPlace(account, place))
            throw new UnauthorizedException("containeraccess.denied");

        Container container = containerService.createNewContainer(account, containerForm);

        return mapper.containerToDto(container);
    }


    public Collection<ContainerDto> get(String token, String containerIdsString, String placeIdsString) {

        Account account = getterService.accounts.getByToken(token);
        Collection<Long> containerIds = Utils.collectionOf(containerIdsString);
        Collection<Long> placeIds = Utils.collectionOf(placeIdsString);

        Collection<Container> result = get(account, containerIds, placeIds);

        return result.stream()
                .filter(c -> accountPermissionsService.hasAccessToContainer(account, c))
                .map(mapper::containerToDto)
                .collect(Collectors.toList());

    }


    private Collection<Container> get(Account account, Collection<Long> containerIds, Collection<Long> placeIds) {

        if(!containerIds.isEmpty())
            return getterService.containers.getByIds(containerIds);

        else if(!placeIds.isEmpty())
            return getterService.containers.getByPlaces(getterService.places.getByIds(placeIds));

        else if(account != null)
            return getterService.containers.getByPlaces(account.getPlaces());

        else
            return Collections.emptySet();
    }


    public Collection<PlaceUserStats> getUserStats(String token, String userIdsString, String placeIdsString, String containerIdsString) {

        Account account = getterService.accounts.getByToken(token);
        if(account == null)
            throw new UnauthorizedException();

        Collection<Long> userIds = Utils.collectionOf(userIdsString);
        Collection<Long> placeIds = Utils.collectionOf(placeIdsString);
        Collection<Long> containerIds = Utils.collectionOf(containerIdsString);


        Collection<Container> containers = containerIds.isEmpty() ?
                getterService.containers.getByPlaces(getterService.places.getByIds(placeIds))
                : getterService.containers.getByIds(containerIds);

        containers.removeIf(c -> !accountPermissionsService.hasAccessToContainer(account, c));

        Collection<Account> accounts = userIds.isEmpty() ?
                getterService.accounts.getAllByContainers(containers)
                : getterService.accounts.getByIds(userIds);

        return containerService.getUsersStats(accounts, containers);
    }


}
