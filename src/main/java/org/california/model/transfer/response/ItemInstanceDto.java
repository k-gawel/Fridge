package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.item.Item;
import org.california.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDate;


@EqualsAndHashCode @ToString
@Getter
public class ItemInstanceDto implements Serializable {

    private Long id;
    private String comment;
    private LocalDate expireOn;

    private Long itemId;
    private Long containerId;

    private Long addedById;
    private LocalDate addedOn;

    private Long openById;
    private LocalDate openOn;

    private Long frozenById;
    private LocalDate frozenOn;

    private Long deletedById;
    private LocalDate deletedOn;


    public static class Builder {

        private ItemInstanceDto result = new ItemInstanceDto();

        public CommentSetter setId(@NotNull Long id) {
            result.id = id;
            return new CommentSetter();
        }

        public CommentSetter setId(@NotNull ItemInstance itemInstance) {
            return setId(itemInstance.getId());
        }

        public class CommentSetter {
            public ExpireOnSetter setComment(String comment) {
                Builder.this.result.comment = comment;
                return new ExpireOnSetter();
            }
        }

        public class ExpireOnSetter {
            public ItemIdSetter setExpireOn(LocalDate expireOn) {
                Builder.this.result.expireOn = expireOn;
                return new ItemIdSetter();
            }
        }

        public class ItemIdSetter {
            public ContainerIdSetter setItemId(@NotNull Long itemId) {
                Builder.this.result.itemId = itemId;
                return new ContainerIdSetter();
            }

            public ContainerIdSetter setItemId(@NotNull Item item) {
                return setItemId(item.getId());
            }
        }

        public class ContainerIdSetter {
            public AddedByIdSetter setContainerId(@NotNull Long containerId) {
                Builder.this.result.containerId = containerId;
                return new AddedByIdSetter();
            }

            public AddedByIdSetter setContainerId(@NotNull Container container) {
                return setContainerId(container.getId());
            }
        }

        public class AddedByIdSetter {
            AddedOnSetter setAddedById(@NotNull Long addedById) {
                Builder.this.result.addedById = addedById;
                return new AddedOnSetter();
            }

            public AddedOnSetter setAddedById(@NotNull Account addedBy) {
                return setAddedById(addedBy.getId());
            }
        }

        public class AddedOnSetter {
            public OpenByIdSetter setAddedOn(@NotNull LocalDate date) {
                Builder.this.result.addedOn = date;
                return new OpenByIdSetter();
            }
        }

        public class OpenByIdSetter {
            public OpenOnSetter setOpenById(Long openById) {
                Builder.this.result.openById = openById;
                return new OpenOnSetter();
            }

            public OpenOnSetter setOpenById(Account openBy) {
                return setOpenById(openBy != null ? openBy.getId() : null);
            }
        }

        public class OpenOnSetter {
            public FrozenByIdSetter setOpenOn(LocalDate date) {
                ObjectUtils.areAllNullOrNoneIs(date, Builder.this.result.openById);

                Builder.this.result.openOn = date;
                return new FrozenByIdSetter();
            }
        }

        public class FrozenByIdSetter {
            public FrozenOnSetter setFrozenById(Long frozenById) {
                Builder.this.result.frozenById = frozenById;
                return new FrozenOnSetter();
            }

            public FrozenOnSetter setFrozenById(Account frozenBy) {
                return setFrozenById(frozenBy != null ? frozenBy.getId() : null);
            }
        }

        public class FrozenOnSetter {
            public DeletedByIdSetter setFrozenOn(LocalDate localDate) {
                ObjectUtils.areAllNullOrNoneIs(localDate, Builder.this.result.frozenById);
                Builder.this.result.frozenOn = localDate;

                return new DeletedByIdSetter();
            }
        }

        public class DeletedByIdSetter {
            public DeletedOnSetter setDeletedById(Long deletedById) {
                Builder.this.result.deletedById = deletedById;
                return new DeletedOnSetter();
            }

            public DeletedOnSetter setDeletedById(Account account) {
                return setDeletedById(account != null ? account.getId() : null);
            }
        }

        public class DeletedOnSetter {
            public FinalBuilder setDeletedOn(LocalDate deletedOn) {
                ObjectUtils.areAllNullOrNoneIs(deletedOn, Builder.this.result.deletedById);

                Builder.this.result.deletedOn = deletedOn;

                return new FinalBuilder();
            }
        }

        public class FinalBuilder {
            public ItemInstanceDto build() {
                return Builder.this.result;
            }
        }
        
    }


}
