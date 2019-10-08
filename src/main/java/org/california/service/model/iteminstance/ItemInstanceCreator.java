package org.california.service.model.iteminstance;

import org.california.model.entity.Account;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.utils.AccountDate;
import org.california.model.transfer.request.forms.ItemInstanceForm;
import org.california.model.transfer.request.forms.MoneyForm;
import org.california.repository.iteminstance.ItemInstanceRepository;
import org.california.service.model.InstanceChangeService;
import org.california.service.model.ShopListService;
import org.california.service.model.WishListItemService;
import org.california.util.exceptions.NotValidException;
import org.jetbrains.annotations.Contract;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class ItemInstanceCreator {

    private final ItemInstanceRepository itemInstanceRepository;
    private final ShopListService shopListService;
    private final InstanceChangeService instanceChangeService;
    private final WishListItemService wishListItemService;

    @Autowired
    public ItemInstanceCreator(ItemInstanceRepository itemInstanceRepository, ShopListService shopListService, InstanceChangeService instanceChangeService, WishListItemService wishListItemService) {
        this.itemInstanceRepository = itemInstanceRepository;
        this.shopListService = shopListService;
        this.instanceChangeService = instanceChangeService;
        this.wishListItemService = wishListItemService;
    }


    public ItemInstance create(Account account, ItemInstanceForm form) {
        ItemInstance result = fromForm(form);
        result.setAdded(new AccountDate(account));

        if(itemInstanceRepository.save(result) != null) {
            instanceChangeService.add(account, result);
            return result;
        } else {
            return null;
        }
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


    @Contract("null -> null")
    private Money moneyFromForm(MoneyForm moneyForm) {
        if(moneyForm == null) return null;

        try {
            var currencyUnit = CurrencyUnit.of(moneyForm.currency);
            var amount = moneyForm.amount.doubleValue();
            return Money.of(currencyUnit, amount);
        } catch (Exception e) {
            throw new NotValidException("price.notValid");
        }
        
    }

}
