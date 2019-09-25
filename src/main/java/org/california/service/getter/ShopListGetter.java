package org.california.service.getter;

import org.california.model.entity.ShopList;
import org.california.repository.shoplist.ShopListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopListGetter extends BaseGetter<ShopList> {

    @Autowired
    public ShopListGetter(ShopListRepository shopListRepository) {
        super(shopListRepository, ShopList.class);
    }

}
