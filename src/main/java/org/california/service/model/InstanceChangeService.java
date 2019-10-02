package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.InstanceChange;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.utils.AccountDate;
import org.california.model.util.ChangeOnInstance;
import org.california.repository.iteminstance.InstanceOnChangeRepository;
import org.california.repository.utils.OffsetLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

@Service
public class InstanceChangeService extends BaseService<InstanceChange> {

    private final InstanceOnChangeRepository instanceOnChangeRepository;


    @Autowired
    InstanceChangeService(InstanceOnChangeRepository instanceOnChangeRepository) {
        super(instanceOnChangeRepository);
        this.instanceOnChangeRepository = instanceOnChangeRepository;
    }


    public Collection<InstanceChange> getByContainers(Collection<Container> containers, int limit, int offset) {
        if(containers == null || containers.isEmpty())
            return Collections.emptySet();

        return instanceOnChangeRepository.getByContainers(containers, new OffsetLimit(offset, limit));
    }


    public Collection<InstanceChange> getByInstances(Collection<ItemInstance> instances, int limit, int offset) {
        return CollectionUtils.isEmpty(instances) ?
            Collections.emptySet() : instanceOnChangeRepository.getByInstances(instances, new OffsetLimit(offset, limit));
    }


    public InstanceChange newChange(Account account, ItemInstance itemInstance, ChangeOnInstance changeOnInstance) {
        InstanceChange instanceChange = new InstanceChange();

        instanceChange.setInstance(itemInstance);
        instanceChange.setChanged(new AccountDate(account));
        instanceChange.setChangeType(changeOnInstance);

        save(instanceChange);

        return instanceChange;
    }


    public InstanceChange add(Account account, ItemInstance itemInstance) {
        return newChange(account, itemInstance, ChangeOnInstance.ADD);
    }


    public InstanceChange delete(Account account, ItemInstance itemInstance) {
        return newChange(account, itemInstance, ChangeOnInstance.DELETE);
    }


    public InstanceChange froze(Account account, ItemInstance itemInstance) {
        return newChange(account, itemInstance, ChangeOnInstance.FROZE);
    }


    public InstanceChange unfroze(Account account, ItemInstance itemInstance) {
        return newChange(account, itemInstance, ChangeOnInstance.UNFROZE);
    }


    public InstanceChange open(Account account, ItemInstance itemInstance) {
        return newChange(account, itemInstance, ChangeOnInstance.OPEN);
    }

}
