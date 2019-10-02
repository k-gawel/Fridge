package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.transfer.response.iteminstance.InstanceChangeDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.InstanceChangeService;
import org.california.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class LoggerControllerService {

    private GetterService getterService;
    private EntityToDtoMapper mapper;
    private InstanceChangeService instanceLoggerService;
    private AccountPermissionsService accountPermissionsService;


    public LoggerControllerService(GetterService getterService, EntityToDtoMapper mapper, InstanceChangeService instanceLoggerService, AccountPermissionsService accountPermissionsService) {
        this.mapper = mapper;
        this.instanceLoggerService = instanceLoggerService;
        this.accountPermissionsService = accountPermissionsService;
        this.getterService = getterService;

    }


    public Collection<InstanceChangeDto> getInstancesChangesByPlace(String token, String placeIdsString, String containerIdsString, int limit) {

        Account account = getterService.accounts.getByToken(token);
        Collection<Number> placeIds = StringUtils.collectionOf(placeIdsString);
        Collection<Number> containerIds = StringUtils.collectionOf(containerIdsString);

        Collection<Container> containers;

        if(!containerIds.isEmpty())
            containers = getterService.containers.getByKeys(containerIds);
        else if(!placeIds.isEmpty())
            containers = getterService.containers.getByPlaces(getterService.places.getByKeys(placeIds));
        else if(account != null)
            containers = getterService.containers.getByAccounts(Collections.singleton(account));
        else
            return Collections.emptySet();

        return instanceLoggerService.getByContainers(containers, 50, 0).stream()
                .filter(ic -> accountPermissionsService.hasAccess(account, ic.getInstance()))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
