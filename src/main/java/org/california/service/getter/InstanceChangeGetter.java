package org.california.service.getter;

import org.california.model.entity.Container;
import org.california.model.entity.InstanceChange;
import org.california.model.entity.Place;
import org.california.repository.iteminstance.InstanceOnChangeRepository;
import org.california.repository.utils.OffsetLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstanceChangeGetter extends BaseGetter<InstanceChange> {

    private final InstanceOnChangeRepository icRepository;

    @Autowired
    InstanceChangeGetter(InstanceOnChangeRepository icRepository) {
        super(icRepository, InstanceChange.class);
        this.icRepository = icRepository;
    }


    public List<InstanceChange> get(Collection<Place> places, int offset, int limit) {
        Collection<Container> containers = places.stream().flatMap(p -> p.getContainers().stream())
                                                          .collect(Collectors.toList());

        OffsetLimit lo = new OffsetLimit(offset, limit);

        return icRepository.getByContainers(containers, lo).stream().collect(Collectors.toList());
    }


}
