package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.item.Item;
import org.california.model.transfer.request.ItemInstanceForm;
import org.california.repository.iteminstance.ItemInstanceRepository;
import org.california.service.getter.GetterService;
import org.california.util.exceptions.NotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class ItemInstanceService {

    private ItemInstanceRepository itemInstanceRepository;
    private GetterService getterService;
    private InstanceOnChangeService instanceOnChangeService;



    @Autowired
    public ItemInstanceService(ItemInstanceRepository itemInstanceRepository, GetterService getterService, InstanceOnChangeService instanceOnChangeService) {
        this.itemInstanceRepository = itemInstanceRepository;
        this.getterService = getterService;
        this.instanceOnChangeService = instanceOnChangeService;
    }


    public ItemInstance create(Account account, ItemInstanceForm form) {
        ItemInstance result = fromForm(form);

        result.setAddedBy(account);
        result.setAddedOn(LocalDate.now());

        result =  itemInstanceRepository.save(result);

        if(result != null)
            instanceOnChangeService.add(account, result);

        return result;
    }


    public boolean open(Account account, ItemInstance itemInstance) {
        if(account == null || itemInstance == null ||
                itemInstance.getOpenBy() != null || itemInstance.getOpenOn() != null)
            return false;

        itemInstance.setOpenBy(account);
        itemInstance.setOpenOn(LocalDate.now());

        boolean result = itemInstanceRepository.save(itemInstance) != null;

        if(result)
            instanceOnChangeService.open(account, itemInstance);

        return result;
     }


    public boolean delete(Account account, ItemInstance itemInstance) {
        if(account == null && itemInstance == null ||
                itemInstance.getDeletedOn() != null || itemInstance.getDeletedBy() != null)
            return false;

        itemInstance.setDeletedBy(account);
        itemInstance.setDeletedOn(LocalDate.now());

        boolean result = itemInstanceRepository.save(itemInstance) != null;

        if(result)
            instanceOnChangeService.delete(account, itemInstance);

        return result;
    }


    public boolean frozeOrUnfroze(Account account, ItemInstance itemInstance) {
        //TODO frozeOr Unfroze instance;
        return true;
    }


    private ItemInstance fromForm(ItemInstanceForm form) {
        Item item = getterService.items.getByKey(form.itemId);
        Container container = getterService.containers.getById(form.containerId);
        LocalDate expireDate = form.expireDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String comment = form.comment;

        if(item == null || container == null)
            throw new NotValidException("item_or_container.null");

        ItemInstance result = new ItemInstance();

        result.setItem(item);
        result.setContainer(container);
        result.setExpireOn(expireDate);
        result.setComment(comment);

        return result;
    }

}
