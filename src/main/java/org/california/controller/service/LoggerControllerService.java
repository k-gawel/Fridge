package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.InstanceChange;
import org.california.model.entity.Place;
import org.california.model.transfer.response.iteminstance.InstanceChangeDto;
import org.california.repository.utils.OffsetLimit;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.california.service.model.InstanceChangeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class LoggerControllerService extends BaseControllerService {

    private InstanceChangeService instanceLoggerService;


    public LoggerControllerService(GetterService getter, EntityToDtoMapper mapper, InstanceChangeService instanceLoggerService, AccountPermissionsService permissions) {
        super(getter, mapper, permissions);
        this.instanceLoggerService = instanceLoggerService;
    }


    public Collection<InstanceChangeDto> getInstancesChangesByPlace(Account account, Collection<Container> containers, Collection<Place> places, OffsetLimit ol) {

        if(containers != null)
            containers = containers;
        else if(places != null)
            containers = getter.containers.getByPlaces(places);
        else if(account != null)
            containers = getter.containers.getByAccounts(Collections.singleton(account));
        else
            return Collections.emptySet();

        Collection<InstanceChange> result = instanceLoggerService.getByContainers(containers, ol.limit, ol.offset);

        return filerAndMap(result, account);
    }

}
