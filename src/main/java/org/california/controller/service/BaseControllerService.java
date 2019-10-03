package org.california.controller.service;

import org.california.model.entity.Account;
import org.california.model.entity.BaseEntity;
import org.california.model.transfer.response.BaseDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.stream.Collectors;

public class BaseControllerService<T extends BaseEntity> {

    protected final GetterService getter;
    protected final EntityToDtoMapper mapper;
    protected final AccountPermissionsService permissions;

    protected final Logger logger = LoggerFactory.getLogger(getClass());


    protected BaseControllerService(GetterService getterService, EntityToDtoMapper mapper, AccountPermissionsService permissions) {
        this.mapper = mapper;
        this.getter = getterService;
        this.permissions = permissions;
    }


    protected Collection<BaseDto<T>> filerAndMap(Collection<T> entities, Account account) {
        return entities.stream()
                       .filter(e -> permissions.hasAccess(account, e))
                       .map(mapper::toDto)
                       .collect(Collectors.toList());
    }


    protected Collection<BaseDto<T>> map(Collection<T> entities) {
        return entities.stream()
                       .map(mapper::toDto)
                       .collect(Collectors.toList());
    }


}
