package org.california.controller.service;

import org.apache.commons.lang3.StringUtils;
import org.california.model.entity.BaseNamedEntity;
import org.california.model.transfer.response.BaseDto;
import org.california.service.builders.EntityToDtoMapper;
import org.california.service.getter.BaseNamedGetter;
import org.california.service.getter.GetterService;
import org.california.service.model.AccountPermissionsService;

import java.util.Collection;
import java.util.Collections;

public abstract class BaseNamedControllerService<T extends BaseNamedEntity> extends BaseControllerService<T> {


    protected BaseNamedControllerService(GetterService getterService, EntityToDtoMapper mapper, AccountPermissionsService permissions) {
        super(getterService, mapper, permissions);
    }

    protected abstract BaseNamedGetter<T> getter();


    public Collection<BaseDto<T>> search(Collection<T> entities, String name, String nameStart) {

        Collection<T> result;

        if(entities != null)
            result = null;
        else if(StringUtils.isNotBlank(name))
            result = getter().searchByName(name);
        else if(StringUtils.isNotBlank(nameStart))
            result = getter().searchByStart(nameStart);
        else
            result = Collections.emptySet();

        return map(result);
    }


}
