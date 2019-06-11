package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.InstanceChange;
import org.california.model.entity.ItemInstance;
import org.california.model.util.ChangeOnInstance;
import org.california.repository.iteminstance.InstanceOnChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Service
public class InstanceOnChangeService {


    private InstanceOnChangeRepository instanceOnChangeRepository;



    @Autowired
    InstanceOnChangeService(InstanceOnChangeRepository instanceOnChangeRepository) {
        this.instanceOnChangeRepository = instanceOnChangeRepository;
    }


    public InstanceChange save(InstanceChange instanceChange) {
            return instanceOnChangeRepository.save(instanceChange);
    }


    public Collection<InstanceChange> getByContainers(Collection<Container> containers, int limit) {
        if(containers == null || containers.isEmpty())
            return Collections.emptySet();

        return instanceOnChangeRepository.getByContainers(containers, limit);
    }


    public Collection<InstanceChange> getByInstances(Collection<ItemInstance> instances, int limit) {
        if(instances == null || instances.isEmpty())
            return Collections.emptySet();

        return instanceOnChangeRepository.getByInstances(instances, limit);
    }


    public InstanceChange newChange(Account account, ItemInstance itemInstance, ChangeOnInstance changeOnInstance) {
        InstanceChange instanceChange = new InstanceChange();
        instanceChange.setAccount(account);
        instanceChange.setChangeType(changeOnInstance);
        instanceChange.setChangeDate(new Date());
        instanceChange.setInstance(itemInstance);

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
