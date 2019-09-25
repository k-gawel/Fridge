package org.california.service.model;

import org.california.model.entity.ItemInstance;
import org.california.model.entity.ShopList;
import org.california.model.transfer.request.forms.ShopListForm;
import org.california.repository.shoplist.ShopListRepository;
import org.california.service.getter.GetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShopListService {

    private final ShopListRepository repository;
    private final GetterService getter;

    @Autowired
    public ShopListService(ShopListRepository repository, GetterService getter) {
        this.repository = repository;
        this.getter = getter;
    }


    public ShopList createShopList(ShopListForm form) {
        ShopList shopList = fromForm(form);
        return repository.save(shopList);
    }


    public boolean addInstance(ShopList shopList, ItemInstance instance) {
        shopList.getInstances().add(instance);
        instance.setShopList(shopList);
        return repository.save(shopList) != null;
    }


    public boolean removeInstance(ShopList shopList, ItemInstance instance) {
        if (!shopList.getInstances().contains(instance))
            return false;

        shopList.getInstances().remove(instance);
        instance.setShopList(null);

        return repository.save(shopList) != null;
    }


    public boolean archive(ShopList shopList) {
        if (!shopList.isStatus())
            return false;

        shopList.setStatus(false);
        return repository.save(shopList) != null;
    }


    private ShopList fromForm(ShopListForm form) {
        ShopList shopList = new ShopList();
        shopList.setCreated(LocalDate.now());
        shopList.setDescription(form.description);
        shopList.setPlace(form.place);
        shopList.setShopName(form.shopName);
        shopList.setStatus(true);
        return shopList;
    }

}
