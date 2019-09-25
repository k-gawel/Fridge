package org.california.service.model;

import org.california.model.entity.Account;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.utils.AccountDate;
import org.california.model.transfer.request.forms.ItemInstanceForm;
import org.california.model.transfer.request.forms.MoneyForm;
import org.california.repository.iteminstance.ItemInstanceRepository;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class ItemInstanceService {

    private final ItemInstanceRepository itemInstanceRepository;
    private final ShopListService shopListService;
    private final InstanceOnChangeService instanceOnChangeService;
    private final WishListItemService wishListItemService;


    @Autowired
    public ItemInstanceService(ItemInstanceRepository itemInstanceRepository, ShopListService shopListService, InstanceOnChangeService instanceOnChangeService, WishListItemService wishListItemService) {
        this.itemInstanceRepository = itemInstanceRepository;
        this.shopListService = shopListService;
        this.instanceOnChangeService = instanceOnChangeService;
        this.wishListItemService = wishListItemService;
    }


    public ItemInstance create(Account account, ItemInstanceForm form) {
        ItemInstance result = fromForm(form);
        result.setAdded(new AccountDate(account));
        return itemInstanceRepository.save(result);
    }


    public boolean open(Account account, ItemInstance itemInstance) {
        if (account == null || itemInstance == null || itemInstance.isOpen())
            return false;

        itemInstance.setOpened(new AccountDate(account));

        boolean result = itemInstanceRepository.save(itemInstance) != null;

        if(result)
            instanceOnChangeService.open(account, itemInstance);

        return result;
     }


    public boolean delete(Account account, ItemInstance itemInstance) {
        if (account == null && itemInstance == null || itemInstance.isDeleted())
            return false;

        itemInstance.setDeleted(new AccountDate(account));

        boolean result = itemInstanceRepository.save(itemInstance) != null;

        if(result)
            instanceOnChangeService.delete(account, itemInstance);

        return result;
    }


    public boolean frozeOrUnfroze(Account account, ItemInstance itemInstance) {
        //TODO frozeOr Unfroze instance;
        return true;
    }


    private ItemInstance fromForm(@Valid ItemInstanceForm form) {
        ItemInstance result = new ItemInstance();

        result.setAdded(new AccountDate(form.user, LocalDate.now()));
        result.setPrice(moneyFromForm(form.price));
        result.setItem(form.item);
        result.setContainer(form.container);
        result.setExpireOn(form.expireDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        result.setComment(form.comment);

        if (form.wishListItem != null)
            wishListItemService.addInstance(form.user, form.wishListItem, result);
        if (form.shopList != null)
            shopListService.addInstance(form.shopList, result);

        return result;
    }


    private Money moneyFromForm(MoneyForm moneyForm) {
        CurrencyUnit currencyUnit = CurrencyUnit.of(moneyForm.currency);
        double amount = moneyForm.amount.doubleValue();

        return Money.of(currencyUnit, amount);
    }

}
