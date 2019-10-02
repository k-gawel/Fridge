package org.california.model.transfer.response.iteminstance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.ItemInstance;
import org.california.model.transfer.response.AccountDateDto;
import org.california.model.transfer.response.BaseDto;

import java.io.Serializable;
import java.time.LocalDate;


@EqualsAndHashCode
@ToString
@Getter
public class ItemInstanceDto implements Serializable, BaseDto<ItemInstance> {

    private Long id;
    private String comment;

    private MoneyDto price;
    private LocalDate expireOn;

    private Long itemId;
    private Long containerId;

    private AccountDateDto added;
    private AccountDateDto opened;
    private AccountDateDto frozen;
    private AccountDateDto deleted;


    public static class Builder {
        public IdSetter create() {
            return new InnerBuilder();
        }

        public interface FinalBuilder {
            ItemInstanceDto build();
        }

        public interface OpenedSetter {
            FrozenSetter withOpened(AccountDateDto opened);
        }

        public interface DeletedSetter {
            FinalBuilder withDeleted(AccountDateDto deleted);
        }

        public interface ExpireOnSetter {
            ItemIdSetter withExpireOn(LocalDate expireOn);
        }

        public interface FrozenSetter {
            DeletedSetter withFrozen(AccountDateDto frozen);
        }

        public interface IdSetter {
            CommentSetter withId(Long id);
        }

        public interface PriceSetter {
            ExpireOnSetter withPrice(MoneyDto price);
        }

        public interface AddedSetter {
            OpenedSetter withAdded(AccountDateDto added);
        }

        public interface CommentSetter {
            PriceSetter withComment(String comment);
        }

        public interface ContainerIdSetter {
            AddedSetter withContainerId(Long containerId);
        }

        public interface ItemIdSetter {
            ContainerIdSetter withItemId(Long itemId);
        }

        public static class InnerBuilder implements FinalBuilder, OpenedSetter, DeletedSetter, ExpireOnSetter, FrozenSetter, IdSetter, PriceSetter, AddedSetter, CommentSetter, ContainerIdSetter, ItemIdSetter {
            private ItemInstanceDto result = new ItemInstanceDto();

            public CommentSetter withId(Long id) {
                result.id = id;
                return this;
            }

            public PriceSetter withComment(String comment) {
                result.comment = comment;
                return this;
            }

            public ExpireOnSetter withPrice(MoneyDto price) {
                result.price = price;
                return this;
            }

            public ItemIdSetter withExpireOn(LocalDate expireOn) {
                result.expireOn = expireOn;
                return this;
            }

            public ContainerIdSetter withItemId(Long itemId) {
                result.itemId = itemId;
                return this;
            }

            public AddedSetter withContainerId(Long containerId) {
                result.containerId = containerId;
                return this;
            }

            public OpenedSetter withAdded(AccountDateDto added) {
                result.added = added;
                return this;
            }

            public FrozenSetter withOpened(AccountDateDto opened) {
                result.opened = opened;
                return this;
            }

            public DeletedSetter withFrozen(AccountDateDto frozen) {
                result.frozen = frozen;
                return this;
            }

            public FinalBuilder withDeleted(AccountDateDto deleted) {
                result.deleted = deleted;
                return this;
            }

            public ItemInstanceDto build() {
                return result;
            }
        }
    }

}
