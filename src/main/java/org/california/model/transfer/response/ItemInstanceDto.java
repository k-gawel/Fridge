package org.california.model.transfer.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.california.model.entity.Account;
import org.california.model.entity.Container;
import org.california.model.entity.ItemInstance;
import org.california.model.entity.item.Item;
import org.california.model.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDate;


@EqualsAndHashCode @ToString
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

        class CommentSetter {
            ExpireOnSetter setComment(String comment) {
                Builder.this.result.comment = comment;
                return new ExpireOnSetter();
            }
        }

        class ExpireOnSetter {
            public ItemIdSetter setExpireOn(LocalDate expireOn) {
                Builder.this.result.expireOn = expireOn;
                return new ItemIdSetter();
            }
        }

        class ItemIdSetter {
            ContainerIdSetter setItemId(@NotNull Long itemId) {
                Builder.this.result.itemId = itemId;
                return new ContainerIdSetter();
            }

            ContainerIdSetter setItemId(@NotNull Item item) {
                return setItemId(item.getId());
            }
        }

        class ContainerIdSetter {
            AddedByIdSetter setContainerId(@NotNull Long containerId) {
                Builder.this.result.containerId = containerId;
                return new AddedByIdSetter();
            }

            AddedByIdSetter setContainerId(@NotNull Container container) {
                return setContainerId(container.getId());
            }
        }

        class AddedByIdSetter {
            AddedOnSetter setAddedById(@NotNull Long addedById) {
                Builder.this.result.addedById = addedById;
                return new AddedOnSetter();
            }

            AddedOnSetter setAddedById(@NotNull Account addedBy) {
                return setAddedById(addedBy.getId());
            }
        }

        class AddedOnSetter {
            OpenByIdSetter setAddedOn(@NotNull LocalDate date) {
                Builder.this.result.addedOn = date;
                return new OpenByIdSetter();
            }
        }

        class OpenByIdSetter {
            OpenOnSetter setOpenById(Long openById) {
                Builder.this.result.openById = openById;
                return new OpenOnSetter();
            }

            OpenOnSetter setOpenById(Account openBy) {
                return setOpenById(openBy != null ? openBy.getId() : null);
            }
        }

        class OpenOnSetter {
            FrozenByIdSetter setOpenOn(LocalDate date) {
                ObjectUtils.allAreNullOrNoneIs(date, Builder.this.result.openById);

                Builder.this.result.openOn = date;
                return new FrozenByIdSetter();
            }
        }

        class FrozenByIdSetter {
            FrozenOnSetter setFrozenById(Long frozenById) {
                Builder.this.result.frozenById = frozenById;
                return new FrozenOnSetter();
            }

            FrozenOnSetter setFrozenById(Account frozenBy) {
                return setFrozenById(frozenBy != null ? frozenBy.getId() : null);
            }
        }

        class FrozenOnSetter {
            DeletedByIdSetter setFrozenOn(LocalDate localDate) {
                ObjectUtils.allAreNullOrNoneIs(localDate, Builder.this.result.frozenById);
                Builder.this.result.frozenOn = localDate;

                return new DeletedByIdSetter();
            }
        }

        class DeletedByIdSetter {
            DeletedOnSetter setDeletedById(Long deletedById) {
                Builder.this.result.deletedById = deletedById;
                return new DeletedOnSetter();
            }

            DeletedOnSetter setDeletedById(Account account) {
                return setDeletedById(account != null ? account.getId() : null);
            }
        }

        class DeletedOnSetter {
            FinalBuilder setDeletedOn(LocalDate deletedOn) {
                ObjectUtils.allAreNullOrNoneIs(deletedOn, Builder.this.result.deletedById);

                Builder.this.result.deletedOn = deletedOn;

                return new FinalBuilder();
            }
        }

        class FinalBuilder {
            ItemInstanceDto build() {
                return Builder.this.result;
            }
        }



    }


}
