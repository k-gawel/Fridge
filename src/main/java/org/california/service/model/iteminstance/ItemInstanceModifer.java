package org.california.service.model.iteminstance;

import org.california.model.entity.Account;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.utils.AccountDate;
import org.california.repository.iteminstance.ItemInstanceRepository;
import org.california.service.model.InstanceChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemInstanceModifer {

    private final ItemInstanceRepository repository;
    private final InstanceChangeService changeService;

    @Autowired
    public ItemInstanceModifer(ItemInstanceRepository repository, InstanceChangeService changeService) {
        this.repository = repository;
        this.changeService = changeService;
    }


    public boolean open(Account account, ItemInstance itemInstance) {
        if (account == null || itemInstance == null || itemInstance.isOpen())
            return false;

        itemInstance.setOpened(new AccountDate(account));

        if (repository.save(itemInstance) != null) {
            changeService.open(account, itemInstance);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(Account account, ItemInstance itemInstance, ItemInstanceCreator itemInstanceCreator) {
        if (account == null && itemInstance == null || itemInstance.isDeleted())
            return false;

        itemInstance.setDeleted(new AccountDate(account));

        if(repository.save(itemInstance) != null) {
            changeService.delete(account, itemInstance);
            return true;
        } else {
            return false;
        }
    }

    public boolean frozeOrUnfroze(Account account, ItemInstance itemInstance) {
        //TODO frozeOr Unfroze instance;
        return true;
    }

}