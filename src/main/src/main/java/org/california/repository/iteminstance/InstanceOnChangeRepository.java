package org.california.repository.iteminstance;

import org.california.model.entity.Container;
import org.california.model.entity.InstanceChange;
import org.california.model.entity.ItemInstance;
import org.california.repository.AbstractRepository;

import java.util.Collection;

public interface InstanceOnChangeRepository extends AbstractRepository<InstanceChange> {

    Collection<InstanceChange> getByContainers(Collection<Container> containers, int limit);
    Collection<InstanceChange> getByInstances(Collection<ItemInstance> instances, int limit);
}
