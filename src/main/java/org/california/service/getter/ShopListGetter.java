package org.california.service.getter;

import org.apache.commons.collections.CollectionUtils;
import org.california.model.entity.Place;
import org.california.model.entity.ShopList;
import org.california.repository.shoplist.ShopListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class ShopListGetter extends BaseGetter<ShopList> {

    private final ShopListRepository repository;

    @Autowired
    public ShopListGetter(ShopListRepository repository) {
        super(repository, ShopList.class);
        this.repository = repository;
    }


    public Collection<ShopList> get(Collection<Place> places, boolean status) {
        if (CollectionUtils.isEmpty(places))
            return Collections.emptyList();

        return repository.get(places, status);
    }


}
